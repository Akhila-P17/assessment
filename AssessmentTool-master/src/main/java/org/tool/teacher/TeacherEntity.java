package org.tool.teacher;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;





@Entity
@Table(name = "teacher")
@JsonInclude(Include.NON_EMPTY)
public class TeacherEntity {
	

	
	@Id
	@Column(name = "t_id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "password")
	private String password;
	
	
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)   // mappedBy  with the teacher object in TeacherSubjectEntity
	@JsonManagedReference
	private List< TeacherSubjectEntity > subjectList;		// a teacher will have many subjects therefore a list
	
	
	

	public TeacherEntity() {
		
	}
	

	
	

	
	
	public TeacherEntity(String id, String name, String email, long phone, String password,
			List<TeacherSubjectEntity> subjectList) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password ;
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







	public String getEmail() {
		return email;
	}







	public void setEmail(String email) {
		this.email = email;
	}







	public long getPhone() {
		return phone;
	}







	public void setPhone(long phone) {
		this.phone = phone;
	}







	public String getPassword() {
		return  password ;
	}







	public void setPassword(String password) {
		this.password =  password ;
	}







	public List<TeacherSubjectEntity> getSubjectList() {
		return subjectList;
	}







	public void setSubjectList(List<TeacherSubjectEntity> subjectList) {
		this.subjectList = subjectList;
	}


	public void addSubject(TeacherSubjectEntity subject) {
		this.subjectList.add(subject);
	}
	
	public void removeSubject(TeacherSubjectEntity subject) {
		this.subjectList.remove(subject);
	}







	@Override
	public String toString() {
		return "TeacherEntity [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", subjectList=" + subjectList + "]";
	}
	
	
	
	
	
	
	
	
	











	
	
	
	
	
	
	

}
