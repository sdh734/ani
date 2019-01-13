package edu.smxy.associationmanagement.domain;

import java.util.Date;

/**
 * @author  SDH
 * 协会指导情况表
 *
 */
public class ClassPeriod {

    private Integer classperiodId;

    private Integer classperiodAssciation;

    private Double classperiodTime;

    private Integer classperiodTeacher;

    private Date classperiodDate;

    public Date getClassperiodDate() {
        return classperiodDate;
    }

    public void setClassperiodDate(Date classperiodDate) {
        this.classperiodDate = classperiodDate;
    }

    public Integer getClassperiodId() {
        return classperiodId;
    }

    public void setClassperiodId(Integer classperiodId) {
        this.classperiodId = classperiodId;
    }

    public Integer getClassperiodAssciation() {
        return classperiodAssciation;
    }

    public void setClassperiodAssciation(Integer classperiodAssciation) {
        this.classperiodAssciation = classperiodAssciation;
    }

    public Double getClassperiodTime() {
        return classperiodTime;
    }

    public void setClassperiodTime(Double classperiodTime) {
        this.classperiodTime = classperiodTime;
    }

    public Integer getClassperiodTeacher() {
        return classperiodTeacher;
    }

    public void setClassperiodTeacher(Integer classperiodTeacher) {
        this.classperiodTeacher = classperiodTeacher;
    }
}