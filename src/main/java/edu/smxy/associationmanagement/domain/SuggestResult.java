package edu.smxy.associationmanagement.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SuggestResult {
	private Integer id;
	private Integer type;
	private Integer authorid;
	private String authorname;
	private Integer receiveid;
	private String reveivename;
	private Date createtime;
	private String time;
	private String content;
	private String contentreport;
	private int anonymous;

	public SuggestResult(final Suggest suggest) {
		this.id = suggest.getId();
		this.authorid = suggest.getAuthorid();
		this.content = suggest.getContent();
		this.createtime = suggest.getCreatetime();
		this.receiveid = suggest.getReceiveid();
		this.type = suggest.getType();
		this.anonymous = suggest.getAnonymous();
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.time = simpleDateFormat.format(suggest.getCreatetime());
		this.contentreport =
				suggest
						.getContent()
						.substring(0, (suggest.getContent().length() > 5) ? 5 : suggest.getContent().length());
	}

	public SuggestResult() {
	}

	public int getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	public String getContentreport() {
		return this.contentreport;
	}

	public void setContentreport(final String contentreport) {
		this.contentreport = contentreport;
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

	public String getAuthorname() {
		return this.authorname;
	}

	public void setAuthorname(final String authorname) {
		this.authorname = authorname;
	}

	public Integer getReceiveid() {
		return this.receiveid;
	}

	public void setReceiveid(final Integer receiveid) {
		this.receiveid = receiveid;
	}

	public String getReveivename() {
		return this.reveivename;
	}

	public void setReveivename(final String reveivename) {
		this.reveivename = reveivename;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(final Date createtime) {
		this.createtime = createtime;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = content;
	}
}
