package com.kabdi.springjwt.config;

import java.lang.annotation.Annotation;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.el.stream.Optional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kabdi.springjwt.model.User;
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
		/*Object[] args = joinPoint.getArgs();
		CodeSignature methodSignature = (CodeSignature) joinPoint.getSignature();
		String[] sigParamNames = methodSignature.getParameterNames(); 
		if(args.length>0){
			System.out.println("Arguments userId found with value : " + args[0]);
			if(!userRepository.existsById((Integer) args[0])){
				return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
			}

			int currentUserId = jwtTokenProvider.getUserIdFromJWT(jwtAuthenticationFilter.getJwtFromRequest(request));
			if (currentUserId != (Integer) args[0]) {
				return new ResponseEntity("User not authorized", HttpStatus.UNAUTHORIZED);
			}
		}*/
		System.out.println("Method execution completed.");
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Method execution time: " + elapsedTime + " milliseconds.");

		return proceed;
	}

	/*@After("execution(* com.kabdi.springjwt.controller.*.*(..))")
	public void afterUserRestCall(JoinPoint joinPoint) {
		// Advice
		System.out.println("Going to call the method : " + joinPoint.getSignature());
		long start = System.currentTimeMillis();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		userRepository.updateUserLastActive(new Date(), userPrincipal.getId());
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
	}*/

}
