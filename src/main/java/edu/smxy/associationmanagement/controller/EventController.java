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

    @RequestMapping({"/getEventlistInTime"})
    public JSONResult getEventlistInTime() {
        final List<Event> events = (List<Event>) this.eventService.getAllEventInTime();
        return JSONResult.build(200, "\u67e5\u8be2\u6210\u529f", (Object) events);
    }

    @RequestMapping({"/getAllEventList"})
    public JSONResult getAllEventList() {
        final List<Event> events = (List<Event>) this.eventService.getAllEvent();
        return JSONResult.build(200, "\u67e5\u8be2\u6210\u529f", (Object) events);
    }

    @RequestMapping({"/updateEvent"})
    public JSONResult updateEvent(final HttpServletRequest request) {
        final Event event = new Event();
        final String eventid = request.getParameter("eventid");
        final String eventname = request.getParameter("eventName");
        final String eventstarttime = request.getParameter("eventStarttime");
        final String eventEndtime = request.getParameter("eventEndtime");
        final String enentAuthorid = request.getParameter("enentAuthorid");
        event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
        event.setEventName(eventname);
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setEventStarttime(formatter.parse(eventstarttime, new ParsePosition(0)));
        event.setEventEndtime(formatter.parse(eventEndtime, new ParsePosition(0)));
        event.setEventid(Integer.valueOf(eventid));
        final int result = this.eventService.updateByPrimaryKey(event);
        if (result > 0) {
            return JSONResult.build(200, "ok", (Object) null);
        }
        return JSONResult.build(400, "error", (Object) null);
    }

    @RequestMapping({"/getAllAssWithoutSubmitByEventId"})
    public JSONResult getAllAssWithoutSubmitByEventId(final int eventid) {
        final List<Association> associations = (List<Association>) this.eventService.getAllAssWithoutSubmitByEventId(eventid);
        return JSONResult.build(200, "ok", (Object) associations);
    }

    @RequestMapping({"/updateFileEventWithFile"})
    public JSONResult updateFileEventWithFile(@RequestParam("file") final MultipartFile file, final HttpServletRequest request) {
        final String filename = file.getOriginalFilename();
        final String path = "/www/wwwroot/ass/upload/";
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
            final edu.smxy.associationmanagement.domain.File f = new edu.smxy.associationmanagement.domain.File();
            f.setId(event.getTemplateFileId());
            f.setFilepath(path);
            f.setFilename(filename);
            this.fileService.updateByPrimaryKey(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONResult.build(200, "\u66f4\u65b0\u6210\u529f,\u5df2\u4fee\u6539\u6a21\u677f\u6587\u4ef6", (Object) null);
    }

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
        return JSONResult.build(200, "\u66f4\u65b0\u6210\u529f,\u672a\u4fee\u6539\u6a21\u677f\u6587\u4ef6", (Object) null);
    }

    @RequestMapping({"/deleteFileEvent"})
    public JSONResult deleteFileEvent(final int eventid) {
        this.fileService.deleteByPrimaryKey(this.eventService.selectByPrimaryKey(eventid).getTemplateFileId());
        this.eventService.deleteByPrimaryKey(eventid);
        return JSONResult.build(200, "ok", (Object) "");
    }

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
            return JSONResult.build(200, "ok", (Object) null);
        }
        return JSONResult.build(400, "error", (Object) null);
    }

    @RequestMapping({"/addEventFile"})
    public JSONResult addEventFile(@RequestParam("file") final MultipartFile file, final HttpServletRequest request) {
        final String filename = file.getOriginalFilename();
        final String path = "/www/wwwroot/ass/upload/";
        final Event event = new Event();
        try {
            file.transferTo(new File(path + filename));
            final edu.smxy.associationmanagement.domain.File file2 = new edu.smxy.associationmanagement.domain.File();
            file2.setFilename(filename);
            file2.setFilepath(path);
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
            return JSONResult.build(200, "ok", (Object) null);
        }
        return JSONResult.build(400, "error", (Object) null);
    }

    @RequestMapping({"/getAllFileNosubmit"})
    public JSONResult getAllFileNosubmit(final String assid) {
        if ("".equals(assid)) {
            return JSONResult.build(500, "\u5f53\u524d\u7528\u6237\u4e0d\u652f\u6301\u6b64\u529f\u80fd", (Object) null);
        }
        final List<Event> list = (List<Event>) this.eventService.getAllFileNosubmit(Integer.valueOf(assid));
        return JSONResult.build(200, "ok", (Object) list);
    }

    @RequestMapping({"/uploadFiletoEvent"})
    public JSONResult uploadFiletoEvent(@RequestParam("file") final MultipartFile file, @RequestParam("assid") final String assid, @RequestParam("eventid") final String eventid) {
        final String filename = file.getOriginalFilename();
        final String path = "/www/wwwroot/ass/upload/";
        try {
            file.transferTo(new File(path + filename));
            final edu.smxy.associationmanagement.domain.File file2 = new edu.smxy.associationmanagement.domain.File();
            file2.setFilename(filename);
            file2.setFilepath(path);
            file2.setAuthorid(Integer.valueOf(assid));
            file2.setEventid(Integer.valueOf(eventid));
            this.fileService.uploadFile(file2);
            return JSONResult.build(200, "ok", (Object) null);
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.build(200, "ok", (Object) null);
        }
    }

    @RequestMapping({"/getAllFileEvent"})
    public JSONResult getAllFileEvent() {
        return JSONResult.build(200, "ok", (Object) this.eventService.getAllFileEvent());
    }
}
