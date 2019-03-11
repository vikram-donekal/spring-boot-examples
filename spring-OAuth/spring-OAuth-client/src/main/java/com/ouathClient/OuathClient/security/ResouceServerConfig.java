package com.ouathClient.OuathClient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@SpringBootConfiguration
@EnableResourceServer
public class ResouceServerConfig implements ResourceServerConfigurer {
	
	public static final String RESOURCE_ID= "vikram_api";
	
	
	@Autowired
	Environment env;
	
	@Value("${client.id}")
	private String client_id;
	 
	@Value("${client.secret}") 
	private String client_secret;
	
    @Value("${oauth.server.url}")
	private String oauth_server_url;
	
	
	
	@Autowired
	private ResourceServerTokenServices tokenService;



	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenService).resourceId(RESOURCE_ID).stateless(true);
	}



	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors()
		.and()
		.authorizeRequests()
		.antMatchers("/login").anonymous()
		.antMatchers("/swagger**").anonymous()
		.antMatchers("/v2/api-docs/").anonymous()
		.anyRequest().authenticated();
		
	}
	
	
	@Primary
	@Bean
	public ResourceServerTokenServices getTokenService(){
		final String ouathServerURL= "http://"+oauth_server_url+"/oauth/check_token";
		
		RemoteTokenServices tokenService= new RemoteTokenServices();
		tokenService.setClientId(client_id);
		tokenService.setClientSecret(client_secret);
		tokenService.setCheckTokenEndpointUrl(ouathServerURL);
		
		return tokenService;
		
	}

	
	
	
	

}
