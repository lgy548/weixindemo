package com.example.wx.demo.dto.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {

    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String, String> requestMap,String content){
        super(requestMap);
        //设置为文本消息的magtype
        this.setMsgType("Text");
        this.content = content;
    }
}
