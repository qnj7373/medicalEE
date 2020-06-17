package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 覃能健
 * @create 2020-04
 */
@Controller
public class urlController {

    @GetMapping("/Index")
    public String  Index(){
        return "Index";
        //当浏览器输入/Index时，会返回 Index.html页面
    }


}
