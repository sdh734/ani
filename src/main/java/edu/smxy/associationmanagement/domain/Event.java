package edu.smxy.associationmanagement.domain;

import java.util.Date;

public class Event {
	private Integer eventid;
	private String eventName;
	private Date eventStarttime;
	private Date eventEndtime;
	private Integer enentAuthorid;
	private String type;
	private Integer templateFileId;
	private String eventContent;
	private int eventType;

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public int geteventType() {
		return eventType;
	}

	public void seteventType(int eventType) {
		this.eventType = eventType;
	}

	public Integer getTemplateFileId() {
		return this.templateFileId;
	}

	public void setTemplateFileId(final Integer templateFileId) {
		this.templateFileId = templateFileId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Integer getEventid() {
		return this.eventid;
	}

	public void setEventid(final Integer eventid) {
		this.eventid = eventid;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(final String eventName) {
		this.eventName = ((eventName == null) ? null : eventName.trim());
	}

	public Date getEventStarttime() {
		return this.eventStarttime;
	}

	public void setEventStarttime(final Date eventStarttime) {
		this.eventStarttime = eventStarttime;
	}

	public Date getEventEndtime() {
		return this.eventEndtime;
	}

	public void setEventEndtime(final Date eventEndtime) {
		this.eventEndtime = eventEndtime;
	}

	public Integer getEnentAuthorid() {
		return this.enentAuthorid;
	}

	public void setEnentAuthorid(final Integer enentAuthorid) {
		this.enentAuthorid = enentAuthorid;
	}

	@Override
	public String toString() {
		return "Event{"
				+ "eventid="
				+ eventid
				+ ", eventName='"
				+ eventName
				+ '\''
				+ ", eventStarttime="
				+ eventStarttime
				+ ", eventEndtime="
				+ eventEndtime
				+ ", enentAuthorid="
				+ enentAuthorid
				+ ", type='"
				+ type
				+ '\''
				+ ", templateFileId="
				+ templateFileId
				+ ", eventContent='"
				+ eventContent
				+ '\''
				+ ", eventType="
				+ eventType
				+ '}';
	}
}
