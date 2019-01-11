package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Event;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ResponseBody
@RestController
@EnableAutoConfiguration
public class EventController {
    @Autowired
    EventService eventService;

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
    public  JSONResult  updateEvent(HttpServletRequest request){
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
    @RequestMapping("/addevent")
    public JSONResult addEvent(HttpServletRequest request) {
        Event event = new Event();
        String eventname = request.getParameter("eventName");
        String eventstarttime = request.getParameter("eventStarttime");
        String eventEndtime = request.getParameter("eventEndtime");
        String enentAuthorid = request.getParameter("enentAuthorid");
        event.setEnentAuthorid(Integer.valueOf(enentAuthorid));
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
}
