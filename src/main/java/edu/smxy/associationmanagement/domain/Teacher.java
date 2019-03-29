package edu.smxy.associationmanagement.domain;

public class Teacher {
	private Integer teacherId;
	private String teacherName;
	private String teacherCollege;
	private Integer teacherAssociation;
	private String teacherPhone;
	private String teacherGender;

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(final Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(final String teacherName) {
		this.teacherName = ((teacherName == null) ? null : teacherName.trim());
	}

	public String getTeacherCollege() {
		return this.teacherCollege;
	}

	public void setTeacherCollege(final String teacherCollege) {
		this.teacherCollege = ((teacherCollege == null) ? null : teacherCollege.trim());
	}

	public Integer getTeacherAssociation() {
		return this.teacherAssociation;
	}

	public void setTeacherAssociation(final Integer teacherAssociation) {
		this.teacherAssociation = teacherAssociation;
	}

	public String getTeacherPhone() {
		return this.teacherPhone;
	}

	public void setTeacherPhone(final String teacherPhone) {
		this.teacherPhone = ((teacherPhone == null) ? null : teacherPhone.trim());
	}

	public String getTeacherGender() {
		return this.teacherGender;
	}

	public void setTeacherGender(final String teacherGender) {
		this.teacherGender = ((teacherGender == null) ? null : teacherGender.trim());
	}
}
