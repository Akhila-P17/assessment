package org.tool.question.collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class QuestionEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "question_id")
	private int questionId;
	
	
	@Column(name = "teacher_username")
	private String teacherUsername;
	
	
	@Column(name = "collection_code")
	private String collectionCode;
	
	
	@Column(name = "question_string")
	private String questionString;
	
	
	@Column(name = "choices")
	private String choices;
	
	
	@Column(name = "answer")
	private String answer;
	
	
	@Column(name = "topic")
	private String topic;


	
	
	public QuestionEntity() {
	}




	public QuestionEntity(int questionId, String teacherUsername, String collectionCode, String questionString,
			String choices, String answer, String topic) {
		super();
		this.questionId = questionId;
		this.teacherUsername = teacherUsername;
		this.collectionCode = collectionCode;
		this.questionString = questionString;
		this.choices = choices;
		this.answer = answer;
		this.topic = topic;
	}




	public int getQuestionId() {
		return questionId;
	}




	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}




	public String getTeacherUsername() {
		return teacherUsername;
	}




	public void setTeacherUsername(String teacherUsername) {
		this.teacherUsername = teacherUsername;
	}




	public String getCollectionCode() {
		return collectionCode;
	}




	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}




	public String getQuestionString() {
		return questionString;
	}




	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}




	public String getChoices() {
		return choices;
	}




	public void setChoices(String choices) {
		this.choices = choices;
	}




	public String getAnswer() {
		return answer;
	}




	public void setAnswer(String answer) {
		this.answer = answer;
	}




	public String getTopic() {
		return topic;
	}




	public void setTopic(String topic) {
		this.topic = topic;
	}




	@Override
	public String toString() {
		return "QuestionEntity [questionId=" + questionId + ", teacherUsername=" + teacherUsername + ", collectionCode="
				+ collectionCode + ", questionString=" + questionString + ", choices=" + choices + ", answer=" + answer
				+ ", topic=" + topic + "]";
	}




	



	



	

	
	
	
	 
	
	

}
