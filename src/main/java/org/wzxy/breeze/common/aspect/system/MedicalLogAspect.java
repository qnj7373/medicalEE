package org.wzxy.breeze.common.aspect.system;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.common.utils.getUId;
import org.wzxy.breeze.core.service.serviceImpl.getStatusService;

import java.lang.reflect.Method;

/**
 * @author 覃能健
 * @create 2020-07
 */
@Aspect
@Component
public class MedicalLogAspect {

    @Autowired
    private Logger logger;

    @Pointcut("@annotation(org.wzxy.breeze.common.annotation.MedicalLog)")
    public void log(){}


    @Before("log()")
    public void printLog(JoinPoint joinPoint){
        logger.info("用户： "+getUId.getid()+"---"+this.getMethodLogValue(joinPoint));
    }



    private String getMethodLogValue(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(MedicalLog.class)){
            //获取方法上注解中表明的操作
            MedicalLog medicalLog = method.getAnnotation(MedicalLog.class);
            return medicalLog.description();
        }
        return "未知操作";
    }


}
