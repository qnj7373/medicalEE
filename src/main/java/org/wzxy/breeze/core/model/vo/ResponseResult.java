package org.wzxy.breeze.core.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.HandleResult;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author 覃能健
 * @create 2020-04
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ResponseResult<T> implements Serializable {
    private String message;
    private String url;
    private T data;
    private T dataBackUp;
    private HashMap<String,T> mapData;
    private int status;

    public ResponseResult() {
    }


    public ResponseResult(String message, String url, T data, T dataBackUp, HashMap<String, T> mapData, int status) {
        this.message = message;
        this.url = url;
        this.data = data;
        this.dataBackUp = dataBackUp;
        this.mapData = mapData;
        this.status = status;
    }


    public void renderResult(HandleResult handler) {
        this.message = handler.getMessage();
        this.status = handler.getStatus();
    }

    public void renderResult(int status,String message, String url) {
        this.message = message;
        this.url = url;
        this.status = status;
    }


    public void renderResult(int status,String message,  T data) {
        this.message = message;
        this.data = data;
        this.status = status;
    }


    public void renderResult(int status,String message, T data, HashMap<String, T> mapData) {
        this.message = message;
        this.data = data;
        this.mapData = mapData;
        this.status = status;
    }


    public void renderResult(int status,String message,T data, T dataBackUp) {
        this.message = message;
        this.data = data;
        this.dataBackUp = dataBackUp;
        this.status = status;
    }




    public void renderResult(int status,String message, String url, T data, T dataBackUp, HashMap<String, T> mapData) {
        this.message = message;
        this.url = url;
        this.data = data;
        this.dataBackUp = dataBackUp;
        this.mapData = mapData;
        this.status = status;
    }




    public void   clean(){
        this.message = "";
        this.url = "";
        this.data = null;
        this.dataBackUp = null;
        this.mapData = null;
        this.status = ResponseCode.NOTING.getCode();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public HashMap<String, T> getMapData() {
        return mapData;
    }

    public void setMapData(HashMap<String, T> mapData) {
        this.mapData = mapData;
    }

    public T getDataBackUp() {
        return dataBackUp;
    }

    public void setDataBackUp(T dataBackUp) {
        this.dataBackUp = dataBackUp;
    }
}
