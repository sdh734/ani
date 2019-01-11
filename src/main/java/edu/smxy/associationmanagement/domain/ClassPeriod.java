package edu.smxy.associationmanagement.domain;

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