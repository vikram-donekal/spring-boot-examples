package com.ouathServer.OuathServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@SpringBootConfiguration
@EnableAuthorizationServer
public class AuthorizationServerConfig  implements  AuthorizationServerConfigurer{
	
	private static String REALM= "OAUTH_REALM";
	
	
	@Value("${client.id}")
	private String client_id;
	 
	@Value("${client.secret}") 
	private String client_secret;
	
	@Value("${access.token.validity}") 
	private Integer access_token_validity;
	
	@Value("${refresh.token.validity}") 
	private Integer refresh_token_validity;
	
	
	@Autowired
	private UserApprovalHandler userApprovalHandler;
	
	@Autowired
	@Qualifier("CustomeClientDeatilsService")
	private ClientDetailsService clientDetails;
	
	@Autowired
	@Qualifier("inMemoryTokenStore")
	private TokenStore tokenStore;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManger;
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.realm(REALM+"/client");
		oauthServer.tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
		
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetails);
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endPoints) throws Exception {
		endPoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
			.authenticationManager(authenticationManger).accessTokenConverter(new DefaultAccessTokenConverter());
		
	}
	
	@Bean(name="CustomeClientDeatilsService")
	@Autowired
	public ClientDetailsService getClientDeatils() throws Exception{
		return new InMemoryClientDetailsServiceBuilder().withClient(client_id)
				.authorizedGrantTypes("client_credentails","password","refresh_token")
				.authorities("ROLE_TRUSTED_CLIENT")
				.scopes("read","write","trust")
				.secret(client_secret)
				.accessTokenValiditySeconds(access_token_validity)
				.refreshTokenValiditySeconds(refresh_token_validity)
				.and().build();		
	}
	
	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler getUserApproavlHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetails));
		handler.setClientDetailsService(clientDetails);
		return handler;
	}
	
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore){
		TokenApprovalStore store= new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
		
	}	
	
	@Bean(name="inMemoryTokenStore")
	public TokenStore getTokenStore(){
		return new InMemoryTokenStore();
	}

}
