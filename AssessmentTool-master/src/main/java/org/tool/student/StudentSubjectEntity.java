package org.tool.student;
  
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "s_subjects")
@JsonInclude(Include.NON_EMPTY)
public class StudentSubjectEntity {
	
	
	
	@Id
	@Column(name = "ss_id")
	private String id;
	
	
	
	@Column(name = "name")
	private String name;
	
	
	
	
	@ManyToMany(mappedBy = "subjectList",  cascade = CascadeType.ALL) 
    private List<StudentEntity> studentsList = new ArrayList<StudentEntity>();		//  a subject will have many students

	
	
	
	public StudentSubjectEntity() {
	}
	
	
	public StudentSubjectEntity(String name) {
		this.name = name;
	}


	public StudentSubjectEntity(String id, String name, List<StudentEntity> studentsList) {
		super();
		this.id = id;
		this.name = name;
		this.studentsList = studentsList;
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


	public List<StudentEntity> getStudentsList() {
		return studentsList;
	}


	public void setStudentsList(List<StudentEntity> studentsList) {
		this.studentsList = studentsList;
	}


	@Override
	public String toString() {
		return "StudentSubjectEntity [id=" + id + ", name=" + name + ", studentsList=" + studentsList + "]";
	}


	


	


	

	
	

}
