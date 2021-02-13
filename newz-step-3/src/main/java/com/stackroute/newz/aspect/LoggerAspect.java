package com.stackroute.newz.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.stackroute.newz.model.UserProfile;


/* Annotate this class with @Aspect and @Component */

@Aspect
@Component
public class LoggerAspect {

	/*
	 * Write loggers for each of the methods of controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	
	Logger applog = LoggerFactory.getLogger(LoggerAspect.class);
	
	@Pointcut("execution(* com.stackroute.newz.controller.UserProfileController.saveUser(..))")
	public void checkSaveUser() {
		
	}
	
	@Pointcut("execution(* com.stackroute.newz.controller.UserProfileController.getUserList(..))")
	public void checkGetUserList() {
		
	}
	
	@Pointcut("execution(* com.stackroute.newz.controller.UserProfileController.updateUserDetails(..))")
	public void checkUpdateUserDetails() {
		
	}
	
	@Pointcut("execution(* com.stackroute.newz.controller.UserProfileController.getUserDetails(..))")
	public void checkGetUserDetails() {
		
	}
	
	@Pointcut("execution(* com.stackroute.newz.controller.UserProfileController.deleteUserDetails(..))")
	public void checkDeleteteUserDetails() {
		
	}
	
	@Before("checkGetUserList()")
	public void logBeforeGetUserList(JoinPoint jp) {
		applog.info("calling for list of users");
	}
	
	@After("checkGetUserList()")
	public void logAfterGetUserList() {
		applog.info("retrieved list of users");
	}
	
	@Before("checkSaveUser()")
	public void logBeforeSaveUser() {
		applog.info("calling for save user");
	}
	
	
	@Around("execution(* com.stackroute.newz.controller.UserProfileController.saveUser(..))")
	public Object logSavedUser(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = pjp.proceed();
		try {
			ResponseEntity response = (ResponseEntity)obj;
			UserProfile user = (UserProfile)response.getBody();
			applog.info("new user get added with name - " + user.getFirstName());
		} catch (Exception e) {
		}
		return obj;
	}
	@AfterThrowing(pointcut="checkSaveUser()",throwing = "error")
	public void logSaveUserException(Throwable error)  {
		applog.info("Exception occured while saving user" + error);
	}
	
	@After("checkSaveUser()")
	public void logAfterSaveUser() {
		applog.info("retrieved saved user");
	}
	
	@Before("checkUpdateUserDetails()")
	public void logBeforeUpdateUserDetails() {
		applog.info("calling for user update");
	}
	
	@After("checkUpdateUserDetails()")
	public void logAfterUpdateUserDetails() {
		applog.info("retrieved updated user");
	}
	
	@Before("checkGetUserDetails()")
	public void logBeforeGetUserDetails() {
		applog.info("calling for specific user");
	}
	
	@After("checkGetUserDetails()")
	public void logAfterGetUserDetails() {
		applog.info("retrieved specific user");
	}
	
	@Before("checkDeleteteUserDetails()")
	public void logBeforeDeleteteUserDetails() {
		applog.info("calling for deleting specific user");
	}
	
	@After("checkDeleteteUserDetails()")
	public void logAfterDeleteteUserDetails() {
		applog.info("deleted specific user");
	}
	
}
