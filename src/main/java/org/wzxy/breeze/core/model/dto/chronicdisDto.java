package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.chronicdis;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class chronicdisDto implements Serializable {

    /**
     * 疾病编码
     */
    private int illcode;
    /**
     * 疾病名称
     */
    @NotBlank(message = "疾病名称不能为空")
    private String illname;



    private int nowPage;
    private int pageSize;

    public chronicdisDto() {
    }

    public chronicdisDto(chronicdis c) {
        this.illcode = c.getIllcode();
        this.illname = c.getIllname();
    }

    public chronicdisDto(int illcode, String illname, int nowPage, int pageSize) {
        this.illcode = illcode;
        this.illname = illname;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
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
