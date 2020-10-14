package org.tool.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	private User user;

	public LoginUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(this.user.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.isEnabled();
	}
	
	
	
	
	

}
