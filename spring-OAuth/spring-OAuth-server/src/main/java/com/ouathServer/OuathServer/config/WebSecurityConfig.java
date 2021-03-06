package com.ouathServer.OuathServer.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringBootConfiguration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends  WebSecurityConfigurerAdapter {

	
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Autowired	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
   
		    auth.inMemoryAuthentication().withUser("admin").password("admin").roles("user");
	}

	@Override 
	protected void configure (HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/oauth/revoke**").anonymous()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	@Qualifier("authenticationManagerBean")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
}
