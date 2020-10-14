package org.tool.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assessment")
public class AssessmentEntity {

	
	@Id
	@Column(name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	
	@Column(name = "test_code")
	private String testCode;
	
	@Column(name = "student_username")
	private String studentUsername;
	
	@Column(name = "qestions_generated")
	private String questionGenerated;
	
	@Column(name = "score")
	private int score;

	public AssessmentEntity() {
		// TODO Auto-generated constructor stub
	}

	public AssessmentEntity(int id, String testCode, String studentUsername, String questionGenerated, int score) {
		super();
		this.id = id;
		this.testCode = testCode;
		this.studentUsername = studentUsername;
		this.questionGenerated = questionGenerated;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getStudentUsername() {
		return studentUsername;
	}

	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}

	public String getQuestionGenerated() {
		return questionGenerated;
	}

	public void setQuestionGenerated(String questionGenerated) {
		this.questionGenerated = questionGenerated;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "AssessmentEntity [id=" + id + ", testCode=" + testCode + ", studentUsername=" + studentUsername
				+ ", questionGenerated=" + questionGenerated + ", score=" + score + "]";
	}

	

	
	
	
	
	

	
	
}
