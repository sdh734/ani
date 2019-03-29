package edu.smxy.associationmanagement.domain;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;

import java.text.SimpleDateFormat;

@ExcelSheet(name = "指导情况表")
public class ClassPeriodResult {
	@ExcelField(name = "指导协会")
	private String classperiodAssciation;

	@ExcelField(name = "指导时长")
	private Double classperiodTime;

	@ExcelField(name = "指导老师")
	private String classperiodTeacher;

	@ExcelField(name = "指导日期")
	private String classperiodDate;

	public ClassPeriodResult() {
	}

	public ClassPeriodResult(final ClassPeriod classPeriod) {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.classperiodDate = simpleDateFormat.format(classPeriod.getClassperiodDate());
		this.classperiodTime = classPeriod.getClassperiodTime();
	}

	public ClassPeriodResult(
			final String classperiodAssciation,
			final Double classperiodTime,
			final String classperiodTeacher,
			final String classperiodDate) {
		this.classperiodAssciation = classperiodAssciation;
		this.classperiodTime = classperiodTime;
		this.classperiodTeacher = classperiodTeacher;
		this.classperiodDate = classperiodDate;
	}

	@Override
	public String toString() {
		return "ClassPeriodResult{classperiodAssciation='"
				+ this.classperiodAssciation
				+ '\''
				+ ", classperiodTime="
				+ this.classperiodTime
				+ ", classperiodTeacher='"
				+ this.classperiodTeacher
				+ '\''
				+ ", classperiodDate="
				+ this.classperiodDate
				+ '}';
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
