package edu.smxy.associationmanagement.domain;

import java.util.Date;

public class ApplyType {
	private Integer id;

	private String name;

	private Date startTime;

	private Date endTime;
	
	private Integer templateFile;
	
	public Integer getTemplateFile() {
		return templateFile;
	}
	
	public void setTemplateFile(Integer templateFile) {
		this.templateFile = templateFile;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
