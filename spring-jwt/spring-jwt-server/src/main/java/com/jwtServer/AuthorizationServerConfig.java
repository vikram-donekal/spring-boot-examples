package com.jwtServer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${client.id}")
	private String client_id;
	 
	@Value("${client.secret}") 
	private String client_secret;
	
	@Value("${access.token.validity}") 
	private Integer access_token_validity;
	
	@Value("${refresh.token.validity}") 
	private Integer refresh_token_validity;
	

   public static final String RESOURCE_ID= "vikram_api";

   @Autowired
   private TokenStore tokenStore;

   @Autowired
   private JwtAccessTokenConverter accessTokenConverter;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Override
   public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
      configurer.inMemory().withClient(client_id)
			.authorizedGrantTypes("client_credentails","password","refresh_token")
			.authorities("ROLE_TRUSTED_CLIENT")
			.scopes("read","write","trust")
			.secret(client_secret)
			.accessTokenValiditySeconds(access_token_validity)
			.refreshTokenValiditySeconds(refresh_token_validity)
			.and().build();	
   }
   

   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
      enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
      endpoints.tokenStore(tokenStore)
              .accessTokenConverter(accessTokenConverter)
              .tokenEnhancer(enhancerChain)
              .authenticationManager(authenticationManager);
   }

}
