package edu.smxy.associationmanagement.domain;

import com.alibaba.excel.annotation.*;

public class MemberResult
{
    @ExcelProperty(value = { "\u534f\u4f1a\u540d\u79f0" }, index = 0)
    private String associationname;
    @ExcelProperty(value = { "\u59d3\u540d" }, index = 1)
    private String name;
    @ExcelProperty(value = { "\u6027\u522b" }, index = 2)
    private String gender;
    @ExcelProperty(value = { "\u5b66\u9662" }, index = 3)
    private String college;
    @ExcelProperty(value = { "\u5b66\u53f7" }, index = 4)
    private String studentid;
    @ExcelProperty(value = { "\u8054\u7cfb\u65b9\u5f0f" }, index = 5)
    private String phone;
    @ExcelProperty(value = { "\u90e8\u95e8" }, index = 6)
    private String department;
    @ExcelProperty(value = { "\u804c\u4f4d" }, index = 7)
    private String position;
    
    public MemberResult() {
    }
    
    public MemberResult(final String associationname, final String name, final String gender, final String college, final String studentid, final String phone, final String department, final String position) {
        this.associationname = associationname;
        this.name = name;
        this.gender = gender;
        this.college = college;
        this.studentid = studentid;
        this.phone = phone;
        this.department = department;
        this.position = position;
    }
    
    public MemberResult(final Member member) {
        this.college = member.getCollege();
        this.department = member.getDepartment();
        this.gender = member.getGender();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.position = member.getPosition();
        this.studentid = member.getStudentid();
    }
    
    public String getAssociationname() {
        return this.associationname;
    }
    
    public void setAssociationname(final String associationname) {
        this.associationname = associationname;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(final String gender) {
        this.gender = gender;
    }
    
    public String getCollege() {
        return this.college;
    }
    
    public void setCollege(final String college) {
        this.college = college;
    }
    
    public String getStudentid() {
        return this.studentid;
    }
    
    public void setStudentid(final String studentid) {
        this.studentid = studentid;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(final String department) {
        this.department = department;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(final String position) {
        this.position = position;
    }
    
    @Override
    public String toString() {
        return "MemberResult{associationname='" + this.associationname + '\'' + ", name='" + this.name + '\'' + ", gender='" + this.gender + '\'' + ", college='" + this.college + '\'' + ", studentid='" + this.studentid + '\'' + ", phone='" + this.phone + '\'' + ", department='" + this.department + '\'' + ", position='" + this.position + '\'' + '}';
    }
}
