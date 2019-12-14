package com.app.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class LogginAspect {

	@Pointcut(value = "execution(* com.app.*.*.*(..))")
	public void myPointcut() {
		// point cut for intercept request for com.affinion.gce.member
	}

	@Before("myPointcut()")
	public void before(JoinPoint joinPoint) {
		log.info("Start Class :" + joinPoint.getTarget().getClass() + " :: Method Name: "
				+ joinPoint.getSignature().getName() + " ::Arguments:" + Arrays.toString(joinPoint.getArgs()));
	}

	@After("myPointcut()")
	public void after(JoinPoint joinPoint) {
		log.info("End Class :" + joinPoint.getTarget().getClass() + ":: Method Name: "
				+ joinPoint.getSignature().getName());
	}
	
    @AfterThrowing(pointcut = "execution(* com.affinion.gce.member.*.*.*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint,Exception ex) {
    	log.error("Error Class :" + joinPoint.getTarget().getClass() + ":: Method Name: "
				+ joinPoint.getSignature().getName()+"::Error: "+ex.getMessage());
    }

}
