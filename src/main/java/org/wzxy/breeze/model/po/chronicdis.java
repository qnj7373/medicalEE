package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.chronicdisDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class chronicdis implements Serializable {

    /**
     * 疾病编码
     */
    private int illcode;
    /**
     * 疾病名称
     */
    private String illname;


    public chronicdis() {
        super();
    }


    public chronicdis(int illcode, String illname) {
        this.illcode = illcode;
        this.illname = illname;
    }

    public chronicdis(chronicdisDto c) {
        this.illcode = c.getIllcode();
        this.illname = c.getIllname();
    }


    public int getIllcode() {
        return illcode;
    }

    public void setIllcode(int illcode) {
        this.illcode = illcode;
    }

    public String getIllname() {
        return illname;
    }

    public void setIllname(String illname) {
        this.illname = illname;
    }
}
