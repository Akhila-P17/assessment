package org.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.tool.auth.LoginUserDetailsService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	@Autowired
	LoginUserDetailsService loginUserDetailsService;
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(loginUserDetailsService);
		//now set password encoder
		//Note that the password in data base should also be in encoded form.
		authProvider.setPasswordEncoder( new BCryptPasswordEncoder(11));
		authProvider.setAuthoritiesMapper(authoritiesMapper());
		
		return authProvider;
		
	}
	
	
	@Bean
	public GrantedAuthoritiesMapper authoritiesMapper() {
		
		SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
		authorityMapper.setConvertToUpperCase(true);
		//authorityMapper.setDefaultAuthority();
		return authorityMapper;
		
	}
	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub

		auth.authenticationProvider(authenticationProvider());  // the above method
	}




	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/treg.html", "/sreg.html", "/homepage.html", "/check/login", "/student/register", "/teacher/register","/forgotpass.html" , "/all/teacher/subject/list", "/generate/**", "/verify/**").permitAll()
				.anyRequest().authenticated()
				.and()
				
				//.httpBasic();
				
				.formLogin()
				.loginPage("/homepage.html").permitAll()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/redirect", true)
				.failureUrl("/homepage.html?error=true")
				
				
				.and()
				.logout().invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/homepage.html").permitAll();

				
			
				
	}

	
	
	
	
	
	
	
}