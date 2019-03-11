package com.ouathClient.OuathClient.service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class loginService {


	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Value("${oauth.server.url}")
	private String oauth_server_url;

	@Value("${client.id}")
	private String client_id;

	@Value("${client.secret}") 
	private String client_secret;


	@SuppressWarnings("unused")
	private String getRestGetResponse(String oauthServerUrl) throws HttpClientErrorException {		
		RestTemplate restTemplate = new RestTemplateBuilder().build();	
		LOGGER.info("API Call to Server ( getRestGetResponse )  : "+oauthServerUrl);
		return restTemplate.getForObject(oauthServerUrl, String.class);
	}
	private String getRestPostResponse(String oauthServerUrl) throws HttpClientErrorException {		
		RestTemplate restTemplate = new RestTemplateBuilder().build();	
		LOGGER.info("API Call to Server (getRestPostResponse)  : "+oauthServerUrl);
		return restTemplate.postForObject(oauthServerUrl,null, String.class);
	}

	private String getRestResponse (String oauthServerUrl,String oauthCredentialHeader) throws HttpClientErrorException   {

		RestTemplate restTemplate = new RestTemplateBuilder().build();
		MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", oauthCredentialHeader);

		LOGGER.info("API Call to Server (getRestResponse)  : "+oauthServerUrl);
		return restTemplate.postForObject(oauthServerUrl, new HttpEntity(headers), String.class);
	}

	public String getNewToken(String credentialHeaders) throws UnsupportedEncodingException {
		LOGGER.info("Inside Get New Token");

		String decodeHeader =new String (Base64.decodeBase64(credentialHeaders.split(" ")[1]),"UTF-8");
		String userName=decodeHeader.split(":")[0];
		String Password=decodeHeader.split(":")[1];
		final String authServerUrl= "http://"+oauth_server_url+"/oauth/token?grant_type=password&username="+userName+"&password="+Password;
		final String oauthCredentialHeader="Basic "+Base64.encodeBase64String((client_id+ ":"+client_secret).getBytes("UTF-8"));

		return getRestResponse(authServerUrl, oauthCredentialHeader);

	}

	public String refreshToken(String credentialHeaders, String refreshToken) throws UnsupportedEncodingException {
		final String authServerUrl= "http://"+oauth_server_url+"/oauth/token?grant_type=refresh_token&refresh_token="+refreshToken;

		final String oauthCredentialHeader="Basic "+Base64.encodeBase64String((client_id+ ":"+client_secret).getBytes("UTF-8"));
		LOGGER.info("API Call to Server : "+authServerUrl);
		return getRestResponse(authServerUrl, oauthCredentialHeader);

	}

	public Object revokeToken(String access_token) {
		final String authServerUrl= "http://"+oauth_server_url+"/oauth/revoke?access_token="+access_token;
		return getRestPostResponse(authServerUrl);
	}
	public Object revokeToken(String access_token,String refresh_token) {
		final String authServerUrl= "http://"+oauth_server_url+"/oauth/revoke?access_token="+access_token+"&refresh_token="+refresh_token;		
		return getRestPostResponse(authServerUrl);
	}
}







