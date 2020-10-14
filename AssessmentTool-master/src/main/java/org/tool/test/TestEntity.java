package org.tool.test;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class TestEntity {
	
	@Id
	@Column(name = "test_code")
	private String 	testCode;

	@Column(name = "teacher_username")
	private String teacherUsername;
	
	@Column(name = "subject_code")
	private String subjectCode;
	
	@Column(name = "collection_code")
	private String collectionCode;
	
	@Column(name = "test_name")
	private String testName;
	
	@Column(name = "total_questions")
	private int totalQuestions;
	
	@Column(name = "marks_fcaq")
	private int marksFcaq;			// marks for correctly answered questions
	
	@Column(name = "marks_fwaq")
	private int marksFwap;			// marks for wrongly answered questions
	
	@Column(name = "total_marks") 
	private int totalMarks;
	
	@Basic
	@Column(name = "start_date")
	private Date startDate;
	
	@Basic
	@Column(name = "start_time")
	private Time startTime;
	
	@Basic
	@Column(name = "end_time")
	private Time endTime;
	
	@Column(name = "test_status")
	private String testStatus;

	public TestEntity() {
	}

	public TestEntity(String testCode, String teacherUsername, String subjectCode, String collectionCode,
			String testName, int totalQuestions, int marksFcaq, int marksFwap, int totalMarks, Date startDate,
			Time startTime, Time endTime, String testStatus) {
		super();
		this.testCode = testCode;
		this.teacherUsername = teacherUsername;
		this.subjectCode = subjectCode;
		this.collectionCode = collectionCode;
		this.testName = testName;
		this.totalQuestions = totalQuestions;
		this.marksFcaq = marksFcaq;
		this.marksFwap = marksFwap;
		this.totalMarks = totalMarks;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.testStatus = testStatus;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTeacherUsername() {
		return teacherUsername;
	}

	public void setTeacherUsername(String teacherUsername) {
		this.teacherUsername = teacherUsername;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public int getMarksFcaq() {
		return marksFcaq;
	}

	public void setMarksFcaq(int marksFcaq) {
		this.marksFcaq = marksFcaq;
	}

	public int getMarksFwap() {
		return marksFwap;
	}

	public void setMarksFwap(int marksFwap) {
		this.marksFwap = marksFwap;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	@Override
	public String toString() {
		return "TestEntity [testCode=" + testCode + ", teacherUsername=" + teacherUsername + ", subjectCode="
				+ subjectCode + ", collectionCode=" + collectionCode + ", testName=" + testName + ", totalQuestions="
				+ totalQuestions + ", marksFcaq=" + marksFcaq + ", marksFwap=" + marksFwap + ", totalMarks="
				+ totalMarks + ", startDate=" + startDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", testStatus=" + testStatus + "]";
	}


	
}
