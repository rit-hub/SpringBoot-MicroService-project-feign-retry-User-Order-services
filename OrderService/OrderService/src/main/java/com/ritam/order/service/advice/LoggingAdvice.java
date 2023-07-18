package com.ritam.order.service.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);


    @Pointcut(value ="execution(* com.ritam.order.service.*.*.*(..) )")
    public void pointCutMethod(){}
    @Around("pointCutMethod()")
    public  Object appLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();

        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] argsArray =proceedingJoinPoint.getArgs();
        logger.info("Method invoked : " + className + " : " +
                methodName + "()" + "Args : " + mapper.writeValueAsString(argsArray));
        Object object = proceedingJoinPoint.proceed();
        logger.info("After Call + " +  className + " : " +
                methodName + "()" + "Response : " + mapper.writeValueAsString(object));
        return  object;
    }
}
