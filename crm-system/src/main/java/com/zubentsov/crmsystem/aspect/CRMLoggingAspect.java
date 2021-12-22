package com.zubentsov.crmsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CRMLoggingAspect {

	// setup logger
	private Logger logger = Logger.getLogger(this.getClass().toString());

	// setup pointcut declarations
	@Pointcut("execution(* com.zubentsov.crmsystem.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("execution(* com.zubentsov.crmsystem.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.zubentsov.crmsystem.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("forDaoPackage() || forControllerPackage() || forServicePackage()")
	private void forAppFlow() {
	}

	// add @Before advice

	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {

		// display calling method

		logger.info("======>> in @Before : " + joinPoint.getSignature().toShortString());

		// display the arguments of method

		Object[] args = joinPoint.getArgs();

		for (Object arg : args) {
			logger.info("======>> argument : " + arg.toString());
		}
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void after(JoinPoint joinPoint, Object result) {

		// display calling method

		logger.info("======>> in @After : " + joinPoint.getSignature().toShortString());

		// display the arguments of method

		logger.info("======>> argument : " + result);

	}

}
