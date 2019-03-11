package com.aop.RestFull_Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Aspect
@Configuration
public class Aop {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Before(value="execution (public * com.aop.RestFull_Aop.studentController.*(..))")
	public void beforeAdivice(JoinPoint joinpoint){
		LOGGER.info("Before Method @Before "+joinpoint.getSignature());
		
	}
	@After(value="execution (public * com.aop.RestFull_Aop.studentController.*(..))")
	public void aferAdivice(JoinPoint joinpoint){
		LOGGER.info("After Method @After "+joinpoint.getSignature());
	}


	@AfterReturning(value="execution (public * com.aop.RestFull_Aop.studentController.*(..))",returning="returnValue")
	public void aferAdiviceReturning(JoinPoint joinpoint,ResponseEntity<?> returnValue){
		LOGGER.info("After Method @AfterReturning "+joinpoint.getSignature());
		
		LOGGER.info("Status code  of Rest call :"+returnValue.getStatusCodeValue());
	}
	
	@Around(value="execution (public * com.aop.RestFull_Aop.studentController.*(..))")
	public Object Around(ProceedingJoinPoint joinpoint) throws Throwable{
		System.out.println("Before  Method @Around"+joinpoint.getSignature());
		
		LOGGER.info("OUR CUSTOM LOGIC BEFORE METHOD CALL ");
		LOGGER.info(" METHOD CALL TO METHOD");
		try {
			 ResponseEntity<?> returnObject = (ResponseEntity<Object>) joinpoint.proceed();
			 
			 
			 if (returnObject.toString().indexOf("200 OK ") != -1){
				 LOGGER.info(" OUTPUT  @Around:" +returnObject.getBody());
			 }
			 return returnObject;
			
		} catch ( Throwable e) {
			LOGGER.info(" ERROR IN CALLING PROCCED IN AOP  @Around " + e.getMessage());
			e.printStackTrace();
		}
		return joinpoint.proceed();
		
	}
	
	

}
