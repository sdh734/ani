package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;

import javax.naming.Name;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 18:48
 **/
@ExcelSheet(name = "指导情况")
public class ClassPeriodResult {
    @ExcelField(name = "指导协会")
    private String classperiodAssciation;
    @ExcelField(name = "指导时长")
    private Double classperiodTime;
    @ExcelField(name = "指导教师")
    private String classperiodTeacher;
    @ExcelField(name = "指导日期")
    private String classperiodDate;

    public ClassPeriodResult() {
    }
    public ClassPeriodResult(ClassPeriod classPeriod) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.classperiodDate=simpleDateFormat.format(classPeriod.getClassperiodDate());
        this.classperiodTime=classPeriod.getClassperiodTime();
    }
    @Override
    public String toString() {
        return "ClassPeriodResult{" +
                "classperiodAssciation='" + classperiodAssciation + '\'' +
                ", classperiodTime=" + classperiodTime +
                ", classperiodTeacher='" + classperiodTeacher + '\'' +
                ", classperiodDate=" + classperiodDate +
                '}';
    }

    public ClassPeriodResult(String classperiodAssciation, Double classperiodTime, String classperiodTeacher, String classperiodDate) {
        this.classperiodAssciation = classperiodAssciation;
        this.classperiodTime = classperiodTime;
        this.classperiodTeacher = classperiodTeacher;
        this.classperiodDate = classperiodDate;
    }

    public String getClassperiodAssciation() {
        return classperiodAssciation;
    }

    public void setClassperiodAssciation(String classperiodAssciation) {
        this.classperiodAssciation = classperiodAssciation;
    }

    public Double getClassperiodTime() {
        return classperiodTime;
    }

    public void setClassperiodTime(Double classperiodTime) {
        this.classperiodTime = classperiodTime;
    }

    public String getClassperiodTeacher() {
        return classperiodTeacher;
    }

    public void setClassperiodTeacher(String classperiodTeacher) {
        this.classperiodTeacher = classperiodTeacher;
    }

    public String getClassperiodDate() {
        return classperiodDate;
    }

    public void setClassperiodDate(String classperiodDate) {
        this.classperiodDate = classperiodDate;
    }
}
