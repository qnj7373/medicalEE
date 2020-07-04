package org.wzxy.breeze.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.wzxy.breeze.common.annotation.MedicalLog;

/**
 * @author 覃能健
 * @create 2020-04
 */
@Controller
public class urlController {

    @GetMapping("/Index")
    @MedicalLog(description = "url转向")
    //当浏览器输入/Index时，会返回 Index.html页面
    public String  Index(){
        return "Index";

    }


}
