package edu.smxy.associationmanagement.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-13 07:29
 **/
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

    public String getContentreport() {
        return contentreport;
    }

    public void setContentreport(String contentreport) {
        this.contentreport = contentreport;
    }

    public SuggestResult() {
    }

    public SuggestResult(Suggest suggest) {
        this.id = suggest.getId();
        this.authorid = suggest.getAuthorid();
        this.content = suggest.getContent();
        this.createtime = suggest.getCreatetime();
        this.receiveid = suggest.getReceiveid();
        this.type = suggest.getType();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = simpleDateFormat.format(suggest.getCreatetime());
        this.contentreport = suggest.getContent().substring(0, suggest.getContent().length() > 5 ? 5 : suggest.getContent().length());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public Integer getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(Integer receiveid) {
        this.receiveid = receiveid;
    }

    public String getReveivename() {
        return reveivename;
    }

    public void setReveivename(String reveivename) {
        this.reveivename = reveivename;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
