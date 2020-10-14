package org.tool.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public  class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "isAccountNonExpired", nullable = false)
	private boolean isAccountNonExpired;
	
	@Column(name = "isAccountNonLocked", nullable = false)
	private boolean isAccountNonLocked;
	
	@Column(name = "isCredentialsNonExpired", nullable = false)
	private boolean isCredentialsNonExpired;

	@Column(name = "isEnabled", nullable = false)
	private boolean isEnabled;

	
	
	public User() {

	}



	public User(String username, String password, String role, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}



	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}



	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}



	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}



	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}



	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}



	public boolean isEnabled() {
		return isEnabled;
	}



	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked=" + isAccountNonLocked
				+ ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", isEnabled=" + isEnabled + "]";
	}


	


	
	
	
	
	
	

}
