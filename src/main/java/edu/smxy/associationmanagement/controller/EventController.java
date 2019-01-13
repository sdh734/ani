package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.Event;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.event.EventService;
import edu.smxy.associationmanagement.services.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;

@ResponseBody
@RestController
@EnableAutoConfiguration
public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    FileService fileService;

    @RequestMapping("/getEventlistInTime")
    public JSONResult getEventlistInTime() {
        List<Event> events = eventService.getAllEventInTime();
        return JSONResult.build(200, "查询成功", events);
    }

    @RequestMapping("/getAllEventList")
    public JSONResult getAllEventList() {
        List<Event> events = eventService.getAllEvent();
        return JSONResult.build(200, "查询成功", events);
    }

    @RequestMapping("/updateEvent")
    public JSONResult updateEvent(HttpServletRequest request) {
        Event event = new Event();
        String eventid = request.getParameter("eventid");
        String eventname = request.getParameter("eventName");
        String eventstarttime = request.getParameter("eventStarttime");
        String eventEndtime = request.getParameter("eventEndtime");
        String enentAuthorid = request.getParameter("enentAuthorid");
        event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
        event.setEventName(eventname);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
        event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
        event.setEventid(Integer.valueOf(eventid));
        int result = eventService.updateByPrimaryKey(event);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(400, "error", null);
        }
    }

    /**
     * 根据eventid 获取所有未提交文件的协会
     *
     * @param eventid 事件id
     * @return 协会的集合
     */
    @RequestMapping("/getAllAssWithoutSubmitByEventId")
    public JSONResult getAllAssWithoutSubmitByEventId(int eventid) {
        List<Association> associations = eventService.getAllAssWithoutSubmitByEventId(eventid);
        return JSONResult.build(200, "ok", associations);
    }

    /**
     * 更新FileEvent并且更新模板文件
     *
     * @param file    更新的模板文件
     * @param request 各种参数
     * @return
     */
    @RequestMapping("/updateFileEventWithFile")
    public JSONResult updateFileEventWithFile(
            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String filename = file.getOriginalFilename();
        String path = "G:\\upload\\";
        Event event = new Event();
        String eventid = request.getParameter("eventid");
        String eventname = request.getParameter("eventName");
        String eventstarttime = request.getParameter("eventStarttime");
        String eventEndtime = request.getParameter("eventEndtime");
        String enentAuthorid = request.getParameter("enentAuthorid");
        String type = request.getParameter("type");
        String templateFileId = request.getParameter("templateFileId");
        event.setEventid(Integer.valueOf(eventid));
        event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
        event.setEventName(eventname);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
        event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
        event.setType(type);
        event.setTemplateFileId(Integer.valueOf(templateFileId));
        eventService.updateByPrimaryKey(event);
        try {
            file.transferTo(new File(path + filename));
            edu.smxy.associationmanagement.domain.File f =
                    new edu.smxy.associationmanagement.domain.File();
            f.setId(event.getTemplateFileId());
            f.setFilepath(path);
            f.setFilename(filename);
            fileService.updateByPrimaryKey(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONResult.build(200, "更新成功,已修改模板文件", null);
    }

    /**
     * 更新FileEvent 不更新模板文件
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateFileEventNoFile")
    public JSONResult updateFileEventNoFile(HttpServletRequest request) {
        Event event = new Event();
        String eventid = request.getParameter("eventid");
        String eventname = request.getParameter("eventName");
        String eventstarttime = request.getParameter("eventStarttime");
        String eventEndtime = request.getParameter("eventEndtime");
        String enentAuthorid = request.getParameter("enentAuthorid");
        String type = request.getParameter("type");
        String templateFileId = request.getParameter("templateFileId");
        event.setEventid(Integer.valueOf(eventid));
        event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
        event.setEventName(eventname);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
        event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
        event.setType(type);
        event.setTemplateFileId(Integer.valueOf(templateFileId));
        eventService.updateByPrimaryKey(event);
        return JSONResult.build(200, "更新成功,未修改模板文件", null);
    }

    @RequestMapping("/deleteFileEvent")
    public JSONResult deleteFileEvent(int eventid) {
        fileService.deleteByPrimaryKey(eventService.selectByPrimaryKey(eventid).getTemplateFileId());
        eventService.deleteByPrimaryKey(eventid);
        return JSONResult.build(200, "ok", "");
    }

    @RequestMapping("/addevent")
    public JSONResult addEvent(HttpServletRequest request) {
        Event event = new Event();
        String eventname = request.getParameter("eventName");
        String eventstarttime = request.getParameter("eventStarttime");
        String eventEndtime = request.getParameter("eventEndtime");
        String eventAuthorid = request.getParameter("enentAuthorid");
        event.setEnentAuthorid(Integer.valueOf(eventAuthorid));
        event.setEventName(eventname);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
        event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
        int result = eventService.insert(event);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(400, "error", null);
        }
    }

    @RequestMapping("/addEventFile")
    public JSONResult addEventFile(
            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String filename = file.getOriginalFilename();
        String path = "G:\\upload\\";
        Event event = new Event();
        try {
            file.transferTo(new File(path + filename));
            edu.smxy.associationmanagement.domain.File file1 =
                    new edu.smxy.associationmanagement.domain.File();
            file1.setFilename(filename);
            file1.setFilepath(path);
            fileService.uploadFile(file1);
            event.setTemplateFileId(fileService.selectByRecord(file1).getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String eventname = request.getParameter("eventName");
        String eventstarttime = request.getParameter("eventStarttime");
        String eventEndtime = request.getParameter("eventEndtime");
        String enentAuthorid = request.getParameter("enentAuthorid");
        String type = request.getParameter("type");
        event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
        event.setEventName(eventname);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
        event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
        event.setType(type);
        int result = eventService.insertFile(event);
        if (result > 0) {
            return JSONResult.build(200, "ok", null);
        } else {
            return JSONResult.build(400, "error", null);
        }
    }

    @RequestMapping("/getAllFileNosubmit")
    public JSONResult getAllFileNosubmit(String assid) {
        if ("".equals(assid)) {
            return JSONResult.build(500, "当前用户不支持此功能", null);
        } else {
            List<Event> list = eventService.getAllFileNosubmit(Integer.valueOf(assid));
            return JSONResult.build(200, "ok", list);
        }
    }

    @RequestMapping("/uploadFiletoEvent")
    public JSONResult uploadFiletoEvent(
            @RequestParam("file") MultipartFile file,
            @RequestParam("assid") String assid,
            @RequestParam("eventid") String eventid) {
        String filename = file.getOriginalFilename();
        String path = "G:\\upload\\";
        try {
            file.transferTo(new File(path + filename));
            edu.smxy.associationmanagement.domain.File file1 =
                    new edu.smxy.associationmanagement.domain.File();
            file1.setFilename(filename);
            file1.setFilepath(path);
            file1.setAuthorid(Integer.valueOf(assid));
            file1.setEventid(Integer.valueOf(eventid));
            fileService.uploadFile(file1);
            return JSONResult.build(200, "ok", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONResult.build(200, "ok", null);
    }

    @RequestMapping("/getAllFileEvent")
    public JSONResult getAllFileEvent() {
        return JSONResult.build(200, "ok", eventService.getAllFileEvent());
    }
}
