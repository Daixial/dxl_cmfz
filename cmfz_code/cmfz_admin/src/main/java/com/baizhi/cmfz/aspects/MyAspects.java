package com.baizhi.cmfz.aspects;
import com.baizhi.cmfz.entity.Logs;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.LogsService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;


@Aspect // 这个注解用在类上 代表切面类
@Configuration//  component  两个作用相同
@Slf4j
public class MyAspects {

   /* @Resource
    private LogsService logsService;


  *//*  @Pointcut("execution(* com.baizhi.cmfz.serviceImpl..*.*save*(..)) || execution(* com.baizhi.cmfz.serviceImpl..*.*update*(..)) || " +
            "execution(* com.baizhi.cmfz.serviceImpl..*.*insert*(..)) || execution(* com.baizhi.cmfz.serviceImpl..*.*modyf*(..)) ||" +
            " execution(* com.baizhi.cmfz.serviceImpl..*.*add*(..)) || execution(* com.baizhi.cmfz.serviceImpl..*.*renewal*(..))")
    public void pc(){}*//*
  @Pointcut("execution(* com.baizhi.cmfz.serviceImpl..*.*(..))")
  public void pc(){}

    @Around("pc()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes  attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        User o = (User) request.getSession().getAttribute("mana");
        Logs logs = new Logs();
        logs.setId(UUID.randomUUID().toString());
        logs.setTime(new Date());
        try {
            if (o.getName()==null) {logs.setUser("未登录");}
            else {logs.setUser(o.getName());}
        } catch (Exception e) {
            logs.setUser("未登录");
        }
        String name = proceedingJoinPoint.getSignature().getName();

            logs.setResource(name.substring(6,name.length()));
            logs.setAction("新增");
            logs.setMessage(proceedingJoinPoint.getSignature().getName());
            logs.setResult("成功");


        Object proceed = proceedingJoinPoint.proceed();
       // logsService.insertLogs(logs);
        return proceed;
    }*/

 /*   @Before("execution(* com.baizhi.cmfz.serviceImpl.*(..))")
    public void before(){
        System.out.println("这是前置通知");
    }*/
}
