package edu.smxy.associationmanagement.domain;

import java.util.*;

public class Activity
{
    private Integer id;
    private String activityName;
    private String activityLocation;
    private Date activityTime;
    private String activityOrganizer;
    private Integer activityAssid;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getActivityName() {
        return this.activityName;
    }
    
    public void setActivityName(final String activityName) {
        this.activityName = ((activityName == null) ? null : activityName.trim());
    }
    
    public String getActivityLocation() {
        return this.activityLocation;
    }
    
    public void setActivityLocation(final String activityLocation) {
        this.activityLocation = ((activityLocation == null) ? null : activityLocation.trim());
    }
    
    public Date getActivityTime() {
        return this.activityTime;
    }
    
    public void setActivityTime(final Date activityTime) {
        this.activityTime = activityTime;
    }
    
    public String getActivityOrganizer() {
        return this.activityOrganizer;
    }
    
    public void setActivityOrganizer(final String activityOrganizer) {
        this.activityOrganizer = ((activityOrganizer == null) ? null : activityOrganizer.trim());
    }
    
    public Integer getActivityAssid() {
        return this.activityAssid;
    }
    
    public void setActivityAssid(final Integer activityAssid) {
        this.activityAssid = activityAssid;
    }
}
