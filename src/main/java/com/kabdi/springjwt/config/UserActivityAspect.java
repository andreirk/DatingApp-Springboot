package com.kabdi.springjwt.config;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



import com.kabdi.springjwt.repository.UserRepository;
import com.kabdi.springjwt.security.JwtAuthenticationFilter;
import com.kabdi.springjwt.security.JwtTokenProvider;
import com.kabdi.springjwt.security.UserPrincipal;

@Aspect
@Configuration
public class UserActivityAspect {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired(required = false) 
	private HttpServletRequest request;
	 

	@Around("execution(* com.kabdi.springjwt.controller.*.*(..))")
	public Object aroundRestCall(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("Going to call the method : " + joinPoint.getSignature());
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Method execution time: " + elapsedTime + " milliseconds.");

		return proceed;
	}

	/*@After("execution(* com.kabdi.springjwt.controller.MessagesController.*(..)) "
			+ "|| execution(* com.kabdi.springjwt.controller.PhotosController.*(..))"
			+ "|| execution(* com.kabdi.springjwt.controller.UsersController.*(..))")*/
	@After("execution(* com.kabdi.springjwt.controller..*(..))  && !execution(* com.kabdi.springjwt.controller.AuthController.*(..)) ")
	public void afterUserRestCall(JoinPoint joinPoint) {
		
		System.out.println("Going to call the method update user last active");
		long start = System.currentTimeMillis();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		userRepository.updateUserLastActive(new Date(), userPrincipal.getId());
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
	}

}
