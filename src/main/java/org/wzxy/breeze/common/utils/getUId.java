package org.wzxy.breeze.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author 覃能健
 * @create 2020-04
 */
public class getUId {

    public static int getid(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipal()!=null){
            int id= Integer.parseInt(subject.getPrincipal().toString());
            return id;
        }
        return 0;
    }

}
