package org.wzxy.breeze.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionController {
    @Autowired
    private Logger logger;
    @Autowired
    private  ResponseResult Result ;

    @ExceptionHandler(value = Exception.class)       //拦截所有的异常
    public ResponseResult exceptionHandler(HttpServletRequest httpServletRequest, Exception e){
        // 参数校验异常
        if(e instanceof BindException){
            logger.error(e.getMessage());
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            String msg="";
            for(ObjectError err:errors){
                msg+=err.getDefaultMessage()+"~";
            }

            Result.setData(null);
            Result.setStatus(ResponseCode.getFailcode());
            Result.setMessage(msg);
            return Result;
        }else  if (e instanceof AuthenticationException){
            logger.info("该用户账号或密码错误");
            Result.setData(null);
            Result.setStatus(ResponseCode.getFailcode());
            Result.setMessage("账号或密码错误");
            return Result;
        }else  if (e instanceof AuthorizationException){
            logger.info("该用户无权限访问");
            Result.setData(null);
            Result.setStatus(ResponseCode.getFailcode());
            Result.setMessage("无权限访问");
            return Result;
        }else {
            //其他异常
            logger.error(e.getMessage());
            Result.setData(null);
            Result.setStatus(ResponseCode.getErrorcode());
            Result.setMessage("服务器出错了！请联系管理员处理~");
            return Result;
        }
    }



}
