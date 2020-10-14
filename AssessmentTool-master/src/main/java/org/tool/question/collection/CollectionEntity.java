package org.tool.question.collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "collection")
public class CollectionEntity {
	
	
	@Id
	@Column(name = "collection_code")
	private String collectionCode;
	
	@Column(name = "collection_name")
	private String collectionName;
	
	@Column(name = "teacher_username")
	private String teacherUsername;
	
	@Column(name = "subject_code")
	private String subjectCode;
	
	

	public CollectionEntity() {
	}

	public CollectionEntity(String teacherUsername, String subjectCode, String collectionName, String collectionCode) {
		super();
		this.teacherUsername = teacherUsername;
		this.subjectCode = subjectCode;
		this.collectionName = collectionName;
		this.collectionCode = collectionCode;
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

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	@Override
	public String toString() {
		return "CollectionEntity [teacherUsername=" + teacherUsername + ", subjectCode=" + subjectCode
				+ ", collectionName=" + collectionName + ", collectionCode=" + collectionCode + "]";
	}

	
	
	
	
	
	 
	
	

}
