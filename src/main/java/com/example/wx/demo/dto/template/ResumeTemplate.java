package com.example.wx.demo.dto.template;

import com.alibaba.fastjson.annotation.JSONField;

public class ResumeTemplate extends Template{
    private ResumeData data;

    @JSONField(name = "data")
    public ResumeData getData() {
        return data;
    }

    public void setData(ResumeData data) {
        this.data = data;
    }
}
