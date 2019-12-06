package com.example.wx.demo.dto.template;

import com.alibaba.fastjson.annotation.JSONField;

public class Template {
    private String touser;

    private String template_id;

    private String url;

    private Miniprogram miniprogram;

    @JSONField(name = "touser")
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    @JSONField(name = "template_id")
    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    @JSONField(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JSONField(name = "miniprogram")
    public Miniprogram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Miniprogram miniprogram) {
        this.miniprogram = miniprogram;
    }
}
