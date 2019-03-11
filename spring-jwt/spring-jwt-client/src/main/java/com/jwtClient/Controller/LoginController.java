package com.jwtClient.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtClient.Dto.TokenDTO;
import com.jwtClient.services.loginService;

@RestController
public class LoginController {
	
	@Autowired
	private loginService loginService;
	

	
	@PostMapping("/login")
	public ResponseEntity<?> login (HttpServletRequest request,@RequestParam(value="refresh_token",required=false) String refreshToken){
		System.out.println("LOGIN Service");
		
		try{
			String authorization ="Authorization";
			if (request.getHeader(authorization) == null){
				return new ResponseEntity<>("401 Unauthorized  ",HttpStatus.UNAUTHORIZED);
			}
			
			if (refreshToken == null){
				 	return new ResponseEntity<>(loginService.getNewToken(request.getHeader(authorization)),HttpStatus.OK);
			 }else
			 {
				return new ResponseEntity<>(loginService.refreshToken(request.getHeader(authorization),refreshToken),HttpStatus.OK);
				 
			 }
						
		}catch (HttpClientErrorException e) {
			System.out.println("ERROR : "+e.getMessage());
			return new ResponseEntity<>(e.getResponseBodyAsString(),HttpStatus.UNAUTHORIZED);
			
		}catch (IOException e) {
			System.out.println("ERROR : "+e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout (HttpServletRequest request
			,@RequestParam(value="access_token",required=true) String access_token
			,@RequestParam(value="refresh_token",required=false) String refresh_token){
		
		
		System.out.println("Logout Service");
		
		try{
			
			if (refresh_token == null){
				return new ResponseEntity<> (loginService.revokeToken(access_token),HttpStatus.OK);
			}else
				return new ResponseEntity<> (loginService.revokeToken(access_token,refresh_token),HttpStatus.OK);
						
		}catch (HttpClientErrorException ex) {
			System.out.println("ERROR : "+ex.getMessage());
			return new ResponseEntity<>(ex.getResponseBodyAsString(),HttpStatus.UNAUTHORIZED);
			
		}
	}
	
	
}
