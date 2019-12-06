package com.example.wx.demo.dto.template;

import com.alibaba.fastjson.annotation.JSONField;

public class Data {
    private String value;
    private String color;

    @JSONField(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JSONField(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
