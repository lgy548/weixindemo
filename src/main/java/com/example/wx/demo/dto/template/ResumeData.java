package com.example.wx.demo.dto.template;

import com.alibaba.fastjson.annotation.JSONField;

public class ResumeData {
    private Data first;

    private Data company;

    private Data time;

    private Data result;

    private Data remark;

    @JSONField(name = "first")
    public Data getFirst() {
        return first;
    }

    public void setFirst(Data first) {
        this.first = first;
    }

    @JSONField(name = "company")
    public Data getCompany() {
        return company;
    }

    public void setCompany(Data company) {
        this.company = company;
    }

    @JSONField(name = "time")
    public Data getTime() {
        return time;
    }

    public void setTime(Data time) {
        this.time = time;
    }

    @JSONField(name = "result")
    public Data getResult() {
        return result;
    }

    public void setResult(Data result) {
        this.result = result;
    }

    @JSONField(name = "remark")
    public Data getRemark() {
        return remark;
    }

    public void setRemark(Data remark) {
        this.remark = remark;
    }
}
