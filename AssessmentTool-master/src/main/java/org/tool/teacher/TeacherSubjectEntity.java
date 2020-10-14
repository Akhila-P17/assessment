package org.tool.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "t_subjects")
@JsonInclude(Include.NON_EMPTY)  
public class TeacherSubjectEntity {
	
	@Id
	@Column(name = "ts_id")
	private String id;
	
	@Column(name = "name")
	private String name;

	
	@ManyToOne
    @JoinColumn(name = "t_id")				// the   " id " here is teacher_id and not subject_id
	@JsonBackReference
    private TeacherEntity teacher;			// a subject can have only a single teacher, therefore not a collection
	
	

	
	
	
	public TeacherSubjectEntity() {
	}
	
	
	public TeacherSubjectEntity(String name) {
		this.name = name;
	}


	public TeacherSubjectEntity(String id, String name, TeacherEntity teacher) {
		super();
		this.id = id;
		this.name = name;
		this.teacher = teacher;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public TeacherEntity getTeacher() {
		return teacher;
	}


	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}


	@Override
	public String toString() {
		return "TeacherSubjectEntity [id=" + id + ", name=" + name + ", teacher=" + teacher + "]";
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
