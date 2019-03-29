package edu.smxy.associationmanagement.domain;

import java.util.Date;

public class Suggest {
	private Integer id;
	private Integer type;
	private Integer authorid;
	private Integer receiveid;
	private Date createtime;
	private String content;
	private int anonymous;

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(final Integer type) {
		this.type = type;
	}

	public Integer getAuthorid() {
		return this.authorid;
	}

	public void setAuthorid(final Integer authorid) {
		this.authorid = authorid;
	}

	public Integer getReceiveid() {
		return this.receiveid;
	}

	public void setReceiveid(final Integer receiveid) {
		this.receiveid = receiveid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(final Date createtime) {
		this.createtime = createtime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = ((content == null) ? null : content.trim());
	}
}
