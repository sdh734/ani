package edu.smxy.associationmanagement.domain;

import java.util.Date;

public class Event {
    private Integer eventid;

    private String eventName;

    private Date eventStarttime;

    private Date eventEndtime;

    private Integer enentAuthorid;

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public Date getEventStarttime() {
        return eventStarttime;
    }

    public void setEventStarttime(Date eventStarttime) {
        this.eventStarttime = eventStarttime;
    }

    public Date getEventEndtime() {
        return eventEndtime;
    }

    public void setEventEndtime(Date eventEndtime) {
        this.eventEndtime = eventEndtime;
    }

    public Integer getEnentAuthorid() {
        return enentAuthorid;
    }

    public void setEnentAuthorid(Integer enentAuthorid) {
        this.enentAuthorid = enentAuthorid;
    }
}