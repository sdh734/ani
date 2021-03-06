package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.Event;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.stomp.StompResponseMessage;
import edu.smxy.associationmanagement.services.event.EventService;
import edu.smxy.associationmanagement.services.file.FileService;
import edu.smxy.associationmanagement.utils.PathUtil;
import edu.smxy.associationmanagement.utils.StompMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ResponseBody
@RestController
@EnableAutoConfiguration
public class EventController {
  @Autowired EventService eventService;
  @Autowired FileService fileService;
  /**
   * 获取时间内的通知事件
   *
   * @return
   */
  @RequestMapping({"/getEventlistInTime"})
  public JSONResult getEventlistInTime() {
    final List<Event> events = this.eventService.getAllEventInTime();
    return JSONResult.build(200, "查询成功", events);
  }

  /**
   * 获得所有通知事件
   *
   * @return
   */
  @RequestMapping({"/getAllEventList"})
  public JSONResult getAllEventList() {
    final List<Event> events = this.eventService.getAllEvent();
    return JSONResult.build(200, "查询成功", events);
  }

  /**
   * 更新事件
   *
   * @param request 请求体 获取各种参数
   * @return
   */
  @RequestMapping({"/updateEvent"})
  public JSONResult updateEvent(final HttpServletRequest request) {
    final Event event = new Event();
    final String eventid = request.getParameter("eventid");
    final String eventname = request.getParameter("eventName");
    final String eventstarttime = request.getParameter("eventStarttime");
    final String eventEndtime = request.getParameter("eventEndtime");
    final String enentAuthorid = request.getParameter("enentAuthorid");
    final String eventContent = request.getParameter("eventContent");
    final String eventType = request.getParameter("eventType");
    event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
    event.setEventName(eventname);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
    event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
    event.setEventid(Integer.valueOf(eventid));
    event.setEventContent(eventContent);
    event.seteventType(Integer.valueOf(eventType));
    final int result = this.eventService.updateByPrimaryKey(event);
    if (result > 0) {
      StompResponseMessage message =
          new StompResponseMessage(
              -1, "管理员", -1, "", "已修改" + event.getEventName() + "的通知内容,请注意查看", 0, "/topic/ass");
      StompMessageUtil.sendToTopic(message);
      return JSONResult.build(200, "ok", null);
    }
    return JSONResult.build(500, "error", null);
  }

  /**
   * 根据id 删除指定的通知记录
   *
   * @param event 通知id
   * @return 是否删除成功的消息
   */
  @RequestMapping({"/deletenoticebyid"})
  public JSONResult deleteNoticeById(@RequestBody Event event) {
    // 返回受影响的行数 删除 插入 修改 都会返回受影响的行数 行数 > 0 表示成功
    int record = eventService.deleteByPrimaryKey(event.getEventid());
    if (record > 0) {
      return JSONResult.build(200, "删除成功", null);
    } else {
      return JSONResult.build(500, "删除失败", null);
    }
  }

  /**
   * 根据事件id 获取未提交此项文件的协会集合
   *
   * @param eventid
   * @return
   */
  @RequestMapping({"/getAllAssWithoutSubmitByEventId"})
  public JSONResult getAllAssWithoutSubmitByEventId(final int eventid) {
    final List<Association> associations =
        this.eventService.getAllAssWithoutSubmitByEventId(eventid);
    return JSONResult.build(200, "ok", associations);
  }

  /**
   * 修改材料收集事项,并且修改模板文件
   *
   * @param file 模板文件
   * @param request 请求体
   * @return
   */
  @RequestMapping({"/updateFileEventWithFile"})
  public JSONResult updateFileEventWithFile(
      @RequestParam("file") final MultipartFile file, final HttpServletRequest request) {
    final String filename = file.getOriginalFilename();
      // 目录
      final String path = PathUtil.EVENT_TEMPLATE;

    final Event event = new Event();
    final String eventid = request.getParameter("eventid");
    final String eventname = request.getParameter("eventName");
    final String eventstarttime = request.getParameter("eventStarttime");
    final String eventEndtime = request.getParameter("eventEndtime");
    final String enentAuthorid = request.getParameter("enentAuthorid");
    final String type = request.getParameter("type");
    final String templateFileId = request.getParameter("templateFileId");
    event.setEventid(Integer.valueOf(eventid));
    event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
    event.setEventName(eventname);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
    event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
    event.setType(type);
    event.setTemplateFileId(Integer.valueOf(templateFileId));
    this.eventService.updateByPrimaryKey(event);
    try {
      file.transferTo(new File(path + filename));
      final edu.smxy.associationmanagement.domain.File f =
          new edu.smxy.associationmanagement.domain.File();
      f.setId(event.getTemplateFileId());
      f.setFilepath(path);
      f.setFilename(filename);
      this.fileService.updateByPrimaryKey(f);
    } catch (IOException e) {
      e.printStackTrace();
    }
    StompResponseMessage message =
        new StompResponseMessage(
            -1, "管理员", -1, "", "已修改" + event.getEventName() + "的通知内容,请注意查看", 0, "/topic/ass");
    StompMessageUtil.sendToTopic(message);
    return JSONResult.build(200, "更新成功_已修改模板文件", null);
  }

  /**
   * 修改材料收集事项,不修改模板文件
   *
   * @param request
   * @return
   */
  @RequestMapping({"/updateFileEventNoFile"})
  public JSONResult updateFileEventNoFile(final HttpServletRequest request) {
    final Event event = new Event();
    final String eventid = request.getParameter("eventid");
    final String eventname = request.getParameter("eventName");
    final String eventstarttime = request.getParameter("eventStarttime");
    final String eventEndtime = request.getParameter("eventEndtime");
    final String enentAuthorid = request.getParameter("enentAuthorid");
    final String type = request.getParameter("type");
    final String templateFileId = request.getParameter("templateFileId");
    event.setEventid(Integer.valueOf(eventid));
    event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
    event.setEventName(eventname);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
    event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
    event.setType(type);
    event.setTemplateFileId(Integer.valueOf(templateFileId));
    this.eventService.updateByPrimaryKey(event);
    StompResponseMessage message =
        new StompResponseMessage(
            -1, "管理员", -1, "", "已修改" + event.getEventName() + "的通知内容,请注意查看", 0, "/topic/ass");
    StompMessageUtil.sendToTopic(message);
    return JSONResult.build(
        200, "\u66f4\u65b0\u6210\u529f,\u672a\u4fee\u6539\u6a21\u677f\u6587\u4ef6", null);
  }

  @RequestMapping({"/deleteFileEvent"})
  public JSONResult deleteFileEvent(final int eventid) {
    this.fileService.deleteByPrimaryKey(
        this.eventService.selectByPrimaryKey(eventid).getTemplateFileId());
    this.eventService.deleteByPrimaryKey(eventid);
    return JSONResult.build(200, "ok", "");
  }

  /**
   * 添加通知事项
   *
   * @param request
   * @return
   */
  @RequestMapping({"/addNotice"})
  public JSONResult addNotice(final HttpServletRequest request) {
    final Event event = new Event();
    final String eventname = request.getParameter("eventName");
    final String eventstarttime = request.getParameter("eventStarttime");
    final String eventEndtime = request.getParameter("eventEndtime");
    final String eventAuthorid = request.getParameter("enentAuthorid");
    String eventcontent = request.getParameter("eventcontent");
    int eventtype = Integer.valueOf(request.getParameter("eventtype"));
    event.setEnentAuthorid(Integer.valueOf(eventAuthorid));
    event.setEventName(eventname);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
    event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
    event.setEventContent(eventcontent);
    event.seteventType(eventtype);
    final int result = this.eventService.addNotice(event);
    if (result > 0) {
      StompResponseMessage message =
          new StompResponseMessage(
              -1, "管理员", -1, "", "添加" + event.getEventName() + "的通知,请注意查看", 0, "/topic/ass");
      StompMessageUtil.sendToTopic(message);
      return JSONResult.build(200, "ok", null);
    }
    return JSONResult.build(400, "error", null);
  }

  /**
   * 添加材料收集事项 没有模板文件
   *
   * @param request
   * @return
   */
  @RequestMapping({"/addevent"})
  public JSONResult addEvent(final HttpServletRequest request) {
    final Event event = new Event();
    final String eventname = request.getParameter("eventName");
    final String eventstarttime = request.getParameter("eventStarttime");
    final String eventEndtime = request.getParameter("eventEndtime");
    final String eventAuthorid = request.getParameter("enentAuthorid");
    event.setEnentAuthorid(Integer.valueOf(eventAuthorid));
    event.setEventName(eventname);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
    event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
    final int result = this.eventService.insert(event);
    if (result > 0) {
      StompResponseMessage message =
          new StompResponseMessage(
              -1, "管理员", -1, "", "添加" + event.getEventName() + "的材料收集项,请注意查看", 0, "/topic/ass");
      StompMessageUtil.sendToTopic(message);
      return JSONResult.build(200, "ok", null);
    }
    return JSONResult.build(400, "error", null);
  }

  /**
   * 添加材料收集事项,包含模板文件.
   *
   * @param file
   * @param request
   * @return
   */
  @RequestMapping({"/addEventFile"})
  public JSONResult addEventFile(
      @RequestParam("file") final MultipartFile file, final HttpServletRequest request) {
    final String filename = file.getOriginalFilename();
      // 目录
      final String path = PathUtil.EVENT_TEMPLATE;
    
    final Event event = new Event();
    try {
      file.transferTo(new File(path + filename));
      final edu.smxy.associationmanagement.domain.File file2 =
          new edu.smxy.associationmanagement.domain.File();
      file2.setFilename(filename);
      file2.setFilepath(path);
      file2.setCreateTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
      this.fileService.uploadFile(file2);
      event.setTemplateFileId(this.fileService.selectByRecord(file2).getId());
    } catch (IOException e) {
      e.printStackTrace();
    }
    final String eventname = request.getParameter("eventName");
    final String eventstarttime = request.getParameter("eventStarttime");
    final String eventEndtime = request.getParameter("eventEndtime");
    final String enentAuthorid = request.getParameter("enentAuthorid");
    final String type = request.getParameter("type");
    event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
    event.setEventName(eventname);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
    event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
    event.setType(type);
    final int result = this.eventService.insertFile(event);
    if (result > 0) {
      StompResponseMessage message =
          new StompResponseMessage(
              -1, "管理员", -1, "", "添加" + event.getEventName() + "的材料收集项,请注意查看", 0, "/topic/ass");
      StompMessageUtil.sendToTopic(message);
      return JSONResult.build(200, "ok", null);
    }
    return JSONResult.build(400, "error", null);
  }

  /**
   * 获得 当前登陆协会 未提交的收集材料事项列表
   *
   * @param assid 当前登录协会id
   * @return
   */
  @RequestMapping({"/getAllFileNosubmit"})
  public JSONResult getAllFileNosubmit(final String assid) {
    if ("".equals(assid)) {
      return JSONResult.build(500, "当前用户不支持此功能", null);
    }
    final List<Event> list = this.eventService.getAllFileNosubmit(Integer.valueOf(assid));
    return JSONResult.build(200, "ok", list);
  }

  /**
   * 为某个材料收集项提交文件
   *
   * @param file
   * @param assid 协会id
   * @param eventid 事件id
   * @return
   */
  @RequestMapping({"/uploadFiletoEvent"})
  public JSONResult uploadFiletoEvent(
      @RequestParam("file") final MultipartFile file,
      @RequestParam("assid") final String assid,
      @RequestParam("eventid") final String eventid) {
    final String filename = file.getOriginalFilename();
    // 本地目录
      final String path = PathUtil.EVENT_FILE;
    try {
      file.transferTo(new File(path + filename));
      final edu.smxy.associationmanagement.domain.File file2 =
          new edu.smxy.associationmanagement.domain.File();
      file2.setFilename(filename);
      file2.setFilepath(path);
      file2.setAuthorid(Integer.valueOf(assid));
      file2.setEventid(Integer.valueOf(eventid));
      this.fileService.uploadFile(file2);
      return JSONResult.build(200, "ok", null);
    } catch (IOException e) {
      e.printStackTrace();
      return JSONResult.build(200, "ok", null);
    }
  }

  /**
   * 获得所有文件收集事项
   *
   * @return 事件集合
   */
  @RequestMapping({"/getAllFileEvent"})
  public JSONResult getAllFileEvent() {
    return JSONResult.build(200, "ok", this.eventService.getAllFileEvent());
  }
}
