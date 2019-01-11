package edu.smxy.associationmanagement.domain;

public class Teacher {
    private Integer teacherId;

    private String teacherName;

    private String teacherCollege;

    private Integer teacherAssociation;

    private String teacherPhone;

    private String teacherGender;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherCollege() {
        return teacherCollege;
    }

    public void setTeacherCollege(String teacherCollege) {
        this.teacherCollege = teacherCollege == null ? null : teacherCollege.trim();
    }

    public Integer getTeacherAssociation() {
        return teacherAssociation;
    }

    public void setTeacherAssociation(Integer teacherAssociation) {
        this.teacherAssociation = teacherAssociation;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone == null ? null : teacherPhone.trim();
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender == null ? null : teacherGender.trim();
    }
}