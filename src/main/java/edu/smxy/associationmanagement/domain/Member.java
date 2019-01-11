package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.ExcelSheet;

public class Member {
    private Integer id;

    private Integer associationid;

    private String name;

    private String gender;

    private String college;

    private String phone;

    private String position;

    private String studentid;

    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssociationid() {
        return associationid;
    }

    public void setAssociationid(Integer associationid) {
        this.associationid = associationid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
}