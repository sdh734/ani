package edu.smxy.associationmanagement.domain;

public class FileResult {
	private Integer id;
	private String filename;
	private String filepath;
	private Integer eventid;
	private String eventname;
	private Integer authorid;
	private String authorname;


	public FileResult(final File file) {
		this.id = file.getId();
		this.authorid = file.getAuthorid();
		this.eventid = file.getEventid();
		this.filename = file.getFilename();
		this.filepath = file.getFilepath();
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
		this.filename = filename;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(final String filepath) {
		this.filepath = filepath;
	}

	public Integer getEventid() {
		return this.eventid;
	}

	public void setEventid(final Integer eventid) {
		this.eventid = eventid;
	}

	public String getEventname() {
		return this.eventname;
	}

	public void setEventname(final String eventname) {
		this.eventname = eventname;
	}

	public Integer getAuthorid() {
		return this.authorid;
	}

	public void setAuthorid(final Integer authorid) {
		this.authorid = authorid;
	}

	public String getAuthorname() {
		return this.authorname;
	}

	public void setAuthorname(final String authorname) {
		this.authorname = authorname;
	}
}
