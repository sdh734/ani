package edu.smxy.associationmanagement.domain;

public class File {
	private Integer id;
	private String filename;
	private String filepath;
	private Integer eventid;
	private Integer authorid;
	private Integer applyType;
	private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String filename) {
		this.filename = ((filename == null) ? null : filename.trim());
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(final String filepath) {
		this.filepath = ((filepath == null) ? null : filepath.trim());
	}

	public Integer getEventid() {
		return this.eventid;
	}

	public void setEventid(final Integer eventid) {
		this.eventid = eventid;
	}

	public Integer getAuthorid() {
		return this.authorid;
	}

	public void setAuthorid(final Integer authorid) {
		this.authorid = authorid;
	}
}
