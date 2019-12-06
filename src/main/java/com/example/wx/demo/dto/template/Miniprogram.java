package com.example.wx.demo.dto.template;

import com.alibaba.fastjson.annotation.JSONField;

public class Miniprogram {
    private String appid;

    private String pagepath;

    @JSONField(name = "appid")
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @JSONField(name = "pagepath")
    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
