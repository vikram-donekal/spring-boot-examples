package com.ouathServer.OuathServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequestMapping("/oauth")
public class OauthController {


	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("inMemoryTokenStore")
	private TokenStore tokenStore;

	@PostMapping("/revoke")
	public ResponseEntity<?> revokeAccessToken(@RequestParam(value="access_token",required=true) String  access_token,
			@RequestParam(value="refresh_token",required=false) String  refresh_token){
		
		LOGGER.info("Inside revokeAccessToken Controller");

		if (access_token == null && refresh_token ==null )
			return new ResponseEntity<String>("Expected access and Refresh Token",HttpStatus.BAD_REQUEST);

		String Message ="";
		if (access_token != null  || !(access_token.isEmpty())){
			try{
				LOGGER.info(" Input access_token --->"+access_token);
				tokenStore.removeAccessToken(tokenStore.readAccessToken(access_token));
			}catch (InvalidTokenException ex) {
				LOGGER.error("Invalid Access Token");
				Message ="Invalid Access Token";
				}
		}
		if (refresh_token != null ){
			try{
				tokenStore.removeRefreshToken(tokenStore.readRefreshToken(refresh_token));
			}catch (InvalidTokenException ex) {
				System.out.println("Invalid Refresh Token");
				if (Message.isEmpty())
					Message="Invalid Refresh Token";
				else
					Message="Invalid Refresh and access token";
			}
		}
		if (Message.isEmpty())
			return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		else{
			LOGGER.error(Message);
			return new ResponseEntity<String>(Message,HttpStatus.OK);
		}
			
	}
	
	
	
	
	
}

