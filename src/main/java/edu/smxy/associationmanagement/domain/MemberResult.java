package edu.smxy.associationmanagement.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class MemberResult extends BaseRowModel {
	@ExcelProperty(
			value = {"协会名称"},
			index = 0)
	private String associationname;

	@ExcelProperty(
			value = {"姓名"},
			index = 1)
	private String name;

	@ExcelProperty(
			value = {"性别"},
			index = 2)
	private String gender;

	@ExcelProperty(
			value = {"学院"},
			index = 3)
	private String college;

	@ExcelProperty(
			value = {"学号"},
			index = 4)
	private String studentid;

	@ExcelProperty(
			value = {"联系电话"},
			index = 5)
	private String phone;

	@ExcelProperty(
			value = {"部门"},
			index = 6)
	private String department;

	@ExcelProperty(
			value = {"职务"},
			index = 7)
	private String position;

	public MemberResult() {
	}

	public MemberResult(
			final String associationname,
			final String name,
			final String gender,
			final String college,
			final String studentid,
			final String phone,
			final String department,
			final String position) {
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
		return "MemberResult{associationname='"
				+ this.associationname
				+ '\''
				+ ", name='"
				+ this.name
				+ '\''
				+ ", gender='"
				+ this.gender
				+ '\''
				+ ", college='"
				+ this.college
				+ '\''
				+ ", studentid='"
				+ this.studentid
				+ '\''
				+ ", phone='"
				+ this.phone
				+ '\''
				+ ", department='"
				+ this.department
				+ '\''
				+ ", position='"
				+ this.position
				+ '\''
				+ '}';
	}
}
