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

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public Integer getAssociationid() {
        return associationid;
    }

    public void setAssociationid(Integer associationid) {
        this.associationid = associationid;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName == null ? null : associationName.trim();
    }

    public Integer getPresidentid() {
        return presidentid;
    }

    public void setPresidentid(Integer presidentid) {
        this.presidentid = presidentid;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}