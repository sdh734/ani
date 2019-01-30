package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.*;
import java.text.*;

@ExcelSheet(name = "\u6307\u5bfc\u60c5\u51b5")
public class ClassPeriodResult
{
    @ExcelField(name = "\u6307\u5bfc\u534f\u4f1a")
    private String classperiodAssciation;
    @ExcelField(name = "\u6307\u5bfc\u65f6\u957f")
    private Double classperiodTime;
    @ExcelField(name = "\u6307\u5bfc\u6559\u5e08")
    private String classperiodTeacher;
    @ExcelField(name = "\u6307\u5bfc\u65e5\u671f")
    private String classperiodDate;
    
    public ClassPeriodResult() {
    }
    
    public ClassPeriodResult(final ClassPeriod classPeriod) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.classperiodDate = simpleDateFormat.format(classPeriod.getClassperiodDate());
        this.classperiodTime = classPeriod.getClassperiodTime();
    }
    
    @Override
    public String toString() {
        return "ClassPeriodResult{classperiodAssciation='" + this.classperiodAssciation + '\'' + ", classperiodTime=" + this.classperiodTime + ", classperiodTeacher='" + this.classperiodTeacher + '\'' + ", classperiodDate=" + this.classperiodDate + '}';
    }
    
    public ClassPeriodResult(final String classperiodAssciation, final Double classperiodTime, final String classperiodTeacher, final String classperiodDate) {
        this.classperiodAssciation = classperiodAssciation;
        this.classperiodTime = classperiodTime;
        this.classperiodTeacher = classperiodTeacher;
        this.classperiodDate = classperiodDate;
    }
    
    public String getClassperiodAssciation() {
        return this.classperiodAssciation;
    }
    
    public void setClassperiodAssciation(final String classperiodAssciation) {
        this.classperiodAssciation = classperiodAssciation;
    }
    
    public Double getClassperiodTime() {
        return this.classperiodTime;
    }
    
    public void setClassperiodTime(final Double classperiodTime) {
        this.classperiodTime = classperiodTime;
    }
    
    public String getClassperiodTeacher() {
        return this.classperiodTeacher;
    }
    
    public void setClassperiodTeacher(final String classperiodTeacher) {
        this.classperiodTeacher = classperiodTeacher;
    }
    
    public String getClassperiodDate() {
        return this.classperiodDate;
    }
    
    public void setClassperiodDate(final String classperiodDate) {
        this.classperiodDate = classperiodDate;
    }
}
