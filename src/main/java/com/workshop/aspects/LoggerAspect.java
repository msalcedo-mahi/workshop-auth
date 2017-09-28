package com.workshop.aspects;

import com.workshop.enums.LogType;
import com.workshop.aspects.logger.LoggerRequestInterface;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Aspect
@Component
public class LoggerAspect {

    @Autowired
    private List<LoggerRequestInterface> loggers;

    @Before("execution(* com.workshop..*Controller.*(..))")
    public void createContextAndValidatePermissions(JoinPoint joinpoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        try{
            Authentication a = SecurityContextHolder.getContext().getAuthentication();
            String username = a.getName();

            for (LoggerRequestInterface logger : loggers) {
                logger.writeRequestInfo(LogType.INFO, "user: " + username + " request:" +
                        request.getRequestURI() + " at: " + new Date().toString());
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}