package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class s201_xx implements Serializable {

    private  int id ;
    @NotBlank(message = "名称不能为空")
    private  String name;
    private  String table;
    private int nowPage;
    private int pageSize;


    public s201_xx() {
    }

    public s201_xx(int id, String name, int nowPage, int pageSize) {
        this.id = id;
        this.name = name;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public s201_xx(int id, String name, String table, int nowPage, int pageSize) {
        this.id = id;
        this.name = name;
        this.table = table;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
