package com.stackroute.user.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */

@Aspect
@Component
public class LoggerAspect {

	/*
	 * Write loggers for each of the methods of NewsController, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	private Logger log = org.slf4j.LoggerFactory.getLogger(LoggerAspect.class);
	
	 @Before("execution(* com.stackroute.user.controller.UserAuthController.*(..))")
	    public void logBefore(JoinPoint point) {
	        log.info(point.getSignature().getName() + " before called...");
	    }
	 
	 @After("execution(* com.stackroute.user.controller.UserAuthController.*(..))")
	    public void logAfter(JoinPoint point) {
	        log.info(point.getSignature().getName() + " after called...");
	    }
	 
	 @AfterReturning("execution(* com.stackroute.user.controller.UserAuthController.*(..))")
	    public void logAfterReturning(JoinPoint point) {
	        log.info(point.getSignature().getName() + " after returning called...");
	    }
	 
	 @AfterThrowing("execution(* com.stackroute.user.controller.UserAuthController.*(..))")
	    public void afterThrowing(JoinPoint point) {
	        log.info(point.getSignature().getName() + " afterThrowing called...");
	      
	    }


}
