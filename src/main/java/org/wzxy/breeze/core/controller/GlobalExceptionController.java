package org.wzxy.breeze.core.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;

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
            StringBuffer msg= new StringBuffer("");
            for(ObjectError err:errors){
                msg.append(err.getDefaultMessage()+"~");
            }
            Result.setData(null);
            Result.setStatus(ResponseCode.FAIL.getCode());
            Result.setMessage(msg.toString());
            return Result;
        }else  if (e instanceof AuthenticationException){
            logger.info("该用户账号或密码错误");
            Result.setData(null);
            Result.setStatus(ResponseCode.FAIL.getCode());
            Result.setMessage("账号或密码错误");
            return Result;
        }else  if (e instanceof AuthorizationException){
            logger.info("该用户无权限访问");
            Result.setData(null);
            Result.setStatus(ResponseCode.NOPERMSISSION.getCode());
            Result.setMessage("无权限访问");
            return Result;
        }else {
            //其他异常
            logger.error(e.getMessage());
            Result.setData(null);
            Result.setStatus(ResponseCode.ERROR.getCode());
            Result.setMessage("服务器出错了！请联系管理员处理~");
            return Result;
        }
    }



}
