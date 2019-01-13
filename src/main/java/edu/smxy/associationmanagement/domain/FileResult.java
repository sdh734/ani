package edu.smxy.associationmanagement.domain;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 23:52
 **/
public class FileResult {
    private Integer id;

    private String filename;

    private String filepath;

    private Integer eventid;

    private String eventname;

    private Integer authorid;

    private String authorname;

    public FileResult(File file) {
        this.id=file.getId();
        this.authorid = file.getAuthorid();
        this.eventid = file.getEventid();
        this.filename = file.getFilename();
        this.filepath = file.getFilepath();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
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
}
