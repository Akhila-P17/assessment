package org.tool.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService{

	
	private final UserRepository userRepository;

	
	
	public LoginUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Cannot find username : " + username );
		}
		
		return new LoginUserDetails(user) ;
	}
	
	
	
	
	
	
	 
	
	
	
}
