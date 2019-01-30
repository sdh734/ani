package edu.smxy.associationmanagement.domain;

import java.util.*;

public class Association
{
    private Integer associationid;
    private String associationName;
    private Integer presidentid;
    private Date creationTime;
    private Boolean isRegistered;
    private Integer count;
    private Integer teacher;
    
    public Boolean getRegistered() {
        return this.isRegistered;
    }
    
    public void setRegistered(final Boolean registered) {
        this.isRegistered = registered;
    }
    
    public Integer getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(final Integer teacher) {
        this.teacher = teacher;
    }
    
    public Integer getAssociationid() {
        return this.associationid;
    }
    
    public void setAssociationid(final Integer associationid) {
        this.associationid = associationid;
    }
    
    public String getAssociationName() {
        return this.associationName;
    }
    
    public void setAssociationName(final String associationName) {
        this.associationName = ((associationName == null) ? null : associationName.trim());
    }
    
    public Integer getPresidentid() {
        return this.presidentid;
    }
    
    public void setPresidentid(final Integer presidentid) {
        this.presidentid = presidentid;
    }
    
    public Date getCreationTime() {
        return this.creationTime;
    }
    
    public void setCreationTime(final Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public Boolean getIsRegistered() {
        return this.isRegistered;
    }
    
    public void setIsRegistered(final Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
    
    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(final Integer count) {
        this.count = count;
    }
}
