package edu.smxy.associationmanagement.domain;

import java.util.Date;

public class Association {
	private Integer associationid;
	private String associationName;
	private Integer presidentid;
	private Date creationTime;
	private Boolean isRegistered;
	private Integer count;
	private Integer teacher;
	private Integer registrationFile;
	private Integer tempFile;
	private Integer isDeleted;
	private Integer deleteFile;

	public Integer getDeleteFile() {
		return deleteFile;
	}

	public void setDeleteFile(Integer deleteFile) {
		this.deleteFile = deleteFile;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getTempFile() {
		return tempFile;
	}

	public void setTempFile(Integer tempFile) {
		this.tempFile = tempFile;
	}

	public Integer getRegistrationFile() {
		return registrationFile;
	}

	public void setRegistrationFile(Integer registrationFile) {
		this.registrationFile = registrationFile;
	}

	public Boolean getRegistered() {
		return this.isRegistered;
	}

	public void setRegistered(final Boolean registered) {
		this.isRegistered = registered;
	}

	public Integer getTeacher() {
		return this.teacher;
	}

	public void setTeacher(final Integer teacher) {
		this.teacher = teacher;
	}

	public Integer getAssociationid() {
		return this.associationid;
	}

	public void setAssociationid(final Integer associationid) {
		this.associationid = associationid;
	}

	public String getAssociationName() {
		return this.associationName;
	}

	public void setAssociationName(final String associationName) {
		this.associationName = ((associationName == null) ? null : associationName.trim());
	}

	public Integer getPresidentid() {
		return this.presidentid;
	}

	public void setPresidentid(final Integer presidentid) {
		this.presidentid = presidentid;
	}

	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(final Date creationTime) {
		this.creationTime = creationTime;
	}

	public Boolean getIsRegistered() {
		return this.isRegistered;
	}

	public void setIsRegistered(final Boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(final Integer count) {
		this.count = count;
	}
}
