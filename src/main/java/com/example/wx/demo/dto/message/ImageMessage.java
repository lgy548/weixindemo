package com.example.wx.demo.dto.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{

    @XStreamAlias("Image")
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageMessage(Map<String, String> requestMap, Image image){
        super(requestMap);
        //设置为文本消息的magtype
        this.setMsgType("image");
        this.image = image;
    }
}
