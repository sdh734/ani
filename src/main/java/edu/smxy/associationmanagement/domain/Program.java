package edu.smxy.associationmanagement.domain;

public class Program {
	private Integer id;
	private Integer associationid;
	private Integer eventid;
	private String programname;
	private Integer programnumber;
	private String programmanager;
	private String managerphone;
	private String programprops;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getAssociationid() {
		return this.associationid;
	}

	public void setAssociationid(final Integer associationid) {
		this.associationid = associationid;
	}

	public Integer getEventid() {
		return this.eventid;
	}

	public void setEventid(final Integer eventid) {
		this.eventid = eventid;
	}

	public String getProgramname() {
		return this.programname;
	}

	public void setProgramname(final String programname) {
		this.programname = programname;
	}

	public Integer getProgramnumber() {
		return this.programnumber;
	}

	public void setProgramnumber(final Integer programnumber) {
		this.programnumber = programnumber;
	}

	public String getProgrammanager() {
		return this.programmanager;
	}

	public void setProgrammanager(final String programmanager) {
		this.programmanager = programmanager;
	}

	public String getManagerphone() {
		return this.managerphone;
	}

	public void setManagerphone(final String managerphone) {
		this.managerphone = managerphone;
	}

	public String getProgramprops() {
		return this.programprops;
	}

	public void setProgramprops(final String programprops) {
		this.programprops = programprops;
	}
}
