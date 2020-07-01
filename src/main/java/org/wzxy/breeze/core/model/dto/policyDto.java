package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.policy;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class policyDto implements Serializable {


    private  int id ;
    /**
     * 年份
     */
    @NotBlank(message = "年度不能为空")
    private String runyear;
    /**
     * 封顶线
     */
    private double maxline;
    /**
     * 状态
     */
    private String rate;

    private int nowPage;
    private int pageSize;


    public policyDto() {
        super();
    }

    public policyDto(policy p ) {
        this.id = p.getId();
        this.runyear = p.getRunyear();
        this.maxline = p.getMaxline();
        this.rate = p.getRate();
    }

    public policyDto(int id, String runyear, double maxline, String rate, int nowPage, int pageSize) {
        this.id = id;
        this.runyear = runyear;
        this.maxline = maxline;
        this.rate = rate;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRunyear() {
        return runyear;
    }

    public void setRunyear(String runyear) {
        this.runyear = runyear;
    }

    public double getMaxline() {
        return maxline;
    }

    public void setMaxline(double maxline) {
        this.maxline = maxline;
    }


    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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
