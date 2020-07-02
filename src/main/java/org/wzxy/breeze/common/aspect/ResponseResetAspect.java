package org.wzxy.breeze.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wzxy.breeze.core.model.vo.ResponseResult;

/**
 * @author 覃能健
 * @create 2020-07
 */
@Aspect
@Component
public class ResponseResetAspect {

    @Autowired
    private ResponseResult Result ;

    @Pointcut("execution(* org.wzxy.breeze.core.controller.*.*(..))")
    public void ResetResult(){}

    @Before("ResetResult()")
    public void before(JoinPoint joinPoint){
        Result.clean();
    }

}
