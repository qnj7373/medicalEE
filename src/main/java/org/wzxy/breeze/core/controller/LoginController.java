package org.wzxy.breeze.core.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.common.utils.getUId;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IMenuService;
import org.wzxy.breeze.core.service.Iservice.IUserService;

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
        //添加用户认证信息
        UsernamePasswordToken upToken = new UsernamePasswordToken(
               String.valueOf(loginUser.getUid())  ,
                loginUser.getUpwd()
        );
            Subject subject = SecurityUtils.getSubject();
            //进行验证，这里捕获异常放至全局异常处理控制器，然后返回相应的信息
            subject.login(upToken);
            subject.getSession().setTimeout(600000L);
            User u = new User();
            u.setUid(loginUser.getUid());
            List<User> userByFactor = UserService.findUserByFactor(u);
            if(userByFactor!=null){
                Result.renderResult(ResponseCode.OK.getCode(),
                        "登录成功了~欢迎你!",
                        "Index");
            }
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
