package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;

/**
 * @program: associationmanagement
 * @description: SDH
 * @author: SDH
 * @create: 2019-01-12 01:50
 **/
@ExcelSheet(name = "协会成员")
public class MemberResult {
    @ExcelField(name = "协会名称")
    private String associationname;
    @ExcelField(name = "姓名")
    private String name;
    @ExcelField(name = "性别")
    private String gender;
    @ExcelField(name = "学院")
    private String college;
    @ExcelField(name = "学号")
    private String studentid;
    @ExcelField(name = "联系方式")
    private String phone;
    @ExcelField(name = "部门")
    private String department;
    @ExcelField(name = "职务")
    private String position;

    public MemberResult() {

    }

    public MemberResult(String associationname, String name, String gender, String college, String studentid, String phone, String department, String position) {
        this.associationname = associationname;
        this.name = name;
        this.gender = gender;
        this.college = college;
        this.studentid = studentid;
        this.phone = phone;
        this.department = department;
        this.position = position;
    }

    public MemberResult(Member member) {
        this.college = member.getCollege();
        this.department = member.getDepartment();
        this.gender = member.getGender();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.position = member.getPosition();
        this.studentid = member.getStudentid();
    }

    public String getAssociationname() {
        return associationname;
    }

    public void setAssociationname(String associationname) {
        this.associationname = associationname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "MemberResult{" +
                "associationname='" + associationname + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", college='" + college + '\'' +
                ", studentid='" + studentid + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
