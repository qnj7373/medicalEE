package org.wzxy.breeze.core.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-04
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
 public  enum  ResponseCode implements Serializable {

    NOTING(0),
    OK(200),
    FAIL(201),
    ERROR(-1),
    NOLOGIN(401),
    NOPERMSISSION(403);

    private int code;


    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
