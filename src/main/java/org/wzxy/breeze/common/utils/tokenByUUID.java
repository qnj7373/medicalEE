package org.wzxy.breeze.common.utils;

import java.util.UUID;

/**
 * @author 覃能健
 * @create 2020-07
 */
public class tokenByUUID {

    public  static String madeToken(){
         return UUID.randomUUID().toString();
    }

}
