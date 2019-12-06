package com.example.wx.demo.dto.template;

import com.alibaba.fastjson.annotation.JSONField;

public class ResIndustry {

    private String errcode;

    private String errmsg;

    private Industry primary_industry;

    private Industry secondary_industry;

    @JSONField(name = "errcode")
    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    @JSONField(name = "getErrmsg")
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @JSONField(name = "getPrimary_industry")
    public Industry getPrimary_industry() {
        return primary_industry;
    }

    public void setPrimary_industry(Industry primary_industry) {
        this.primary_industry = primary_industry;
    }

    @JSONField(name = "getSecondary_industry")
    public Industry getSecondary_industry() {
        return secondary_industry;
    }

    public void setSecondary_industry(Industry secondary_industry) {
        this.secondary_industry = secondary_industry;
    }

}
