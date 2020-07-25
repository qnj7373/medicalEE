package org.wzxy.breeze.core.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.common.bean.MedicalToken;
import org.wzxy.breeze.common.utils.getUId;
import org.wzxy.breeze.common.utils.tokenByUUID;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.model.vo.loginUser;
import org.wzxy.breeze.core.service.Iservice.IMenuService;
import org.wzxy.breeze.core.service.Iservice.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
@RestController
@RequestMapping("/system")
public class LoginController {
    @Autowired
    private IUserService UserService;
    @Autowired
    private IMenuService MenuService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private  ResponseResult Result ;
    private ModelAndView mv = new ModelAndView();

    @GetMapping("/notLogin")
    @MedicalLog(description = "用户未登录")
    public ModelAndView notLogin(){
        mv.setViewName("login");
        return mv;
    }

    @PostMapping("/login")
    @MedicalLog(description = "用户登录")
    public ResponseResult login(User loginUser){
            //判断该账号此前是否登录过,如果已登录过那么将踢出先前登录的帐号
               if (UserService.hasLogin(String.valueOf(loginUser.getUid()))){
                   UserService.exitUser(String.valueOf(loginUser.getUid()));
               }
            //添加用户认证信息
            UsernamePasswordToken upToken = new UsernamePasswordToken(
                    String.valueOf(loginUser.getUid()),
                    loginUser.getUpwd()
            );
            Subject subject = SecurityUtils.getSubject();
            //进行验证，这里捕获异常放至全局异常处理控制器，然后返回相应的信息
            subject.login(upToken);
            //改用前后端分离
            String token = tokenByUUID.madeToken();
            redisTemplate.opsForValue().set(token,loginUser, Duration.ofMinutes(30L));
            //以用户账号作为key存入token，以实现账号唯一登录的效果
            redisTemplate.opsForValue().set(String.valueOf(loginUser.getUid()), token,Duration.ofMinutes(30L));

            Result.renderResult(ResponseCode.OK.getCode(),
                    "登录成功了~欢迎你!",
                    new MedicalToken(token)
            );

        return Result;

    }

    @PostMapping("/logout")
    @MedicalLog(description = "用户登出")
    public ResponseResult login(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Result.renderResult(UserService.userLogout(token));
        return Result;

    }



    @GetMapping("/getMenusIndex")
    @MedicalLog(description = "用户获取菜单")
    public ResponseResult getMenusIndex() {

            Result.renderResult(ResponseCode.OK.getCode(),
                    "获取菜单成功！",
                    MenuService.getMenusIndex(getUId.getid()));
            return Result;

    }

}
