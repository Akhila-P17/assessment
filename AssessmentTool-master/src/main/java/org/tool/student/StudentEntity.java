package org.tool.student;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;




/*
 * 
 
 @JsonInclude(Include.NON_EMPTY)  is the most important for these two - many to many related entities.
 I tried using other annotations such as managed reference, back reference, identify info etc.
 But using them were bad referenced results. If not used then infinite references.
 So, now I am using json include annotation and manually clear references to tackle infinite references in the controller itself.
 In this way I can even ignore fields that I dont want more dynamically than when using json ignore.
 
  
 * 
 */




@Entity
@Table(name = "student")
@JsonInclude(Include.NON_EMPTY)       
public class StudentEntity {
	

	
	@Id
	@Column(name = "s_id" )
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "usn")
	private String usn;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private BigInteger phone;
	
	@Column(name = "password")
	private String password;
	
	
	
	
	
	

	
	// adding mappedBy attribute here makes this entity as parent
	@ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	
	//Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn
	// therefore we will send @jointable to subject entity
	@JoinTable(
	        name = "students_to_subjects",		
	        joinColumns = {
	            @JoinColumn(name = "s_id")    
	        },
	        inverseJoinColumns = {
	            @JoinColumn(name = "ss_id")	
	        }
	    )
	 private List< StudentSubjectEntity > subjectList = new ArrayList<StudentSubjectEntity>();			// a student will have many subjects therefore a list
	
	
	
	
	
	
	

	public StudentEntity() {
		
	}
	



	public StudentEntity(String id, String name, String usn, String email, BigInteger phone, String password,
			List<StudentSubjectEntity> subjectList) {
		super();
		this.id = id;
		this.name = name;
		this.usn = usn;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.subjectList = subjectList;
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




	public String getUsn() {
		return usn;
	}




	public void setUsn(String usn) {
		this.usn = usn;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public BigInteger getPhone() {
		return phone;
	}




	public void setPhone(BigInteger phone) {
		this.phone = phone;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public List<StudentSubjectEntity> getSubjectList() {
		return subjectList;
	}




	public void setSubjectList(List<StudentSubjectEntity> subjectList) {
		this.subjectList = subjectList;
	}




	public void addSubject(StudentSubjectEntity subject) {
		this.subjectList.add(subject);
	}
	
	
	
	
	public void removeSubject(StudentSubjectEntity subject) {
		this.subjectList.remove(subject);
	}




	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", name=" + name + ", usn=" + usn + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + ", subjectList=" + subjectList + "]";
	}





	
	
	
	
	
	
	
	
	

}
