package edu.smxy.associationmanagement.domain;

import java.util.Date;

public class WFEntity {
    private Integer id;

    private Integer authorId;

    private Integer status;

    private Date createTime;

    private String title;

    private Integer typeId;

    private String dataTablename;

    private Integer dataTableid;

	private Integer isClose;

	public Integer getIsClose() {
		return isClose;
	}

	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDataTablename() {
        return dataTablename;
    }

    public void setDataTablename(String dataTablename) {
        this.dataTablename = dataTablename == null ? null : dataTablename.trim();
    }

    public Integer getDataTableid() {
        return dataTableid;
    }

    public void setDataTableid(Integer dataTableid) {
        this.dataTableid = dataTableid;
    }
}