package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.ExcelField;

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
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssociationid() {
        return associationid;
    }

    public void setAssociationid(Integer associationid) {
        this.associationid = associationid;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }

    public Integer getProgramnumber() {
        return programnumber;
    }

    public void setProgramnumber(Integer programnumber) {
        this.programnumber = programnumber;
    }

    public String getProgrammanager() {
        return programmanager;
    }

    public void setProgrammanager(String programmanager) {
        this.programmanager = programmanager;
    }

    public String getManagerphone() {
        return managerphone;
    }

    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }

    public String getProgramprops() {
        return programprops;
    }

    public void setProgramprops(String programprops) {
        this.programprops = programprops;
    }
}