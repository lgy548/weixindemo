package com.example.wx.demo.dto.template;

import com.alibaba.fastjson.annotation.JSONField;

public class Industry {
    private String first_class;

    private String second_class;

    @JSONField(name = "getFirst_class")
    public String getFirst_class() {
        return first_class;
    }

    public void setFirst_class(String first_class) {
        this.first_class = first_class;
    }

    @JSONField(name = "getSecond_class")
    public String getSecond_class() {
        return second_class;
    }

    public void setSecond_class(String second_class) {
        this.second_class = second_class;
    }

}
