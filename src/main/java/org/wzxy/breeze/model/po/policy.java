package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.policyDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class policy implements Serializable {


    private  int id ;
    /**
     * 年份
     */
    private String runyear;
    /**
     * 封顶线
     */
    private double maxline;
    /**
     * 状态
     */
    private String rate;

    public policy() {
        super();
    }

    public policy(policyDto p ) {
        this.id = p.getId();
        this.runyear = p.getRunyear();
        this.maxline = p.getMaxline();
        this.rate = p.getRate();
    }

    public policy(int id, String runyear, double maxline, String rate) {
        this.id = id;
        this.runyear = runyear;
        this.maxline = maxline;
        this.rate = rate;
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
}
