package org.wzxy.breeze.common.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.wzxy.breeze.common.utils.MedicalStringUtils;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author 覃能健
 * @create 2020-07
 */
public class permissionFilter implements Filter {

    @Autowired
    private ResponseResult Result ;
    @Autowired
    private Logger logger ;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            String token = request.getHeader("Authorization");
            if(!MedicalStringUtils.isNull(token)&&MedicalStringUtils.isBlank(token)){
                Long expire = redisTemplate.getExpire(token);
                if (expire>0){
                    //重置token在redis中的存在时间实现续签
                    redisTemplate.expire(token, 30L, TimeUnit.MINUTES);
                    //放行
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    noLoginBack( servletResponse);
                }
            }else{
                noLoginBack( servletResponse);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("服务异常!!! "+e.getMessage());
            errorBack(servletResponse);
        }

    }

    private  void  noLoginBack(ServletResponse servletResponse)throws IOException{
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Result.clean();
        Result.renderResult(ResponseCode.NOLOGIN.getCode(), "用户未登录或登录状态已过期，请重新登录！",null);
        String withoutToken = JSONObject.toJSONString(Result);
        response.setContentType("json/text;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(withoutToken);
        writer.close();
    }

    private  void  errorBack(ServletResponse servletResponse)throws IOException{
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Result.clean();
        Result.renderResult(ResponseCode.ERROR.getCode(), "服务器出错了！请联系管理员处理~", null);
        String error = JSONObject.toJSONString(Result);
        response.setContentType("json/text;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(error);
        writer.close();
    }

}
