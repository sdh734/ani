package edu.smxy.associationmanagement.domain;

import java.util.*;

public class ClassPeriod
{
    private Integer classperiodId;
    private Integer classperiodAssciation;
    private Double classperiodTime;
    private Integer classperiodTeacher;
    private Date classperiodDate;
    
    public Date getClassperiodDate() {
        return this.classperiodDate;
    }
    
    public void setClassperiodDate(final Date classperiodDate) {
        this.classperiodDate = classperiodDate;
    }
    
    public Integer getClassperiodId() {
        return this.classperiodId;
    }
    
    public void setClassperiodId(final Integer classperiodId) {
        this.classperiodId = classperiodId;
    }
    
    public Integer getClassperiodAssciation() {
        return this.classperiodAssciation;
    }
    
    public void setClassperiodAssciation(final Integer classperiodAssciation) {
        this.classperiodAssciation = classperiodAssciation;
    }
    
    public Double getClassperiodTime() {
        return this.classperiodTime;
    }
    
    public void setClassperiodTime(final Double classperiodTime) {
        this.classperiodTime = classperiodTime;
    }
    
    public Integer getClassperiodTeacher() {
        return this.classperiodTeacher;
    }
    
    public void setClassperiodTeacher(final Integer classperiodTeacher) {
        this.classperiodTeacher = classperiodTeacher;
    }
}
