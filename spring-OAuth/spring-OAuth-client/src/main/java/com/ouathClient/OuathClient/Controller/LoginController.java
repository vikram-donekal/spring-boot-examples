package com.ouathClient.OuathClient.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouathClient.OuathClient.DTO.TokenDTO;
import com.ouathClient.OuathClient.service.loginService;

@RestController
public class LoginController {
	

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private loginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login (HttpServletRequest request,@RequestParam(value="refresh_token",required=false) String refreshToken){
		LOGGER.info(" Inside  LOGIN Controller");
		
		try{
			String authorization ="Authorization";
			if (request.getHeader(authorization) == null){
				return new ResponseEntity<>("401 Unauthorized  ",HttpStatus.UNAUTHORIZED);
			}
			if (refreshToken == null){
				ObjectMapper mapper = new ObjectMapper();
			 	TokenDTO tokenResponseDTO= mapper.readValue(loginService.getNewToken(request.getHeader(authorization)), TokenDTO.class);
			 	return new ResponseEntity<>(tokenResponseDTO,HttpStatus.OK);
			 }else
			 {
				ObjectMapper mapper = new ObjectMapper();
     		 	TokenDTO tokenResponseDTO= mapper.readValue(loginService.refreshToken(request.getHeader(authorization),refreshToken), TokenDTO.class);
			 	return new ResponseEntity<>(tokenResponseDTO,HttpStatus.OK);
				 
			 }
						
		}catch (HttpClientErrorException e) {
			LOGGER.error("ERROR in Login Controller : "+e.getMessage());
			return new ResponseEntity<>(e.getResponseBodyAsString(),HttpStatus.UNAUTHORIZED);
			
		}catch (IOException e) {
			LOGGER.error("ERROR in Login Controller : "+e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout (HttpServletRequest request
			,@RequestParam(value="access_token",required=true) String access_token
			,@RequestParam(value="refresh_token",required=false) String refresh_token){

		LOGGER.info(" Inside  LOGOUT Controller");
		try{
			
			if (refresh_token == null){
				return new ResponseEntity<> (loginService.revokeToken(access_token),HttpStatus.OK);
			}else
				return new ResponseEntity<> (loginService.revokeToken(access_token,refresh_token),HttpStatus.OK);
		}catch (HttpClientErrorException ex) {
			LOGGER.error("ERROR inside LogOut Controller : "+ex.getMessage());
			return new ResponseEntity<>(ex.getResponseBodyAsString(),HttpStatus.UNAUTHORIZED);
		}
	}
}
