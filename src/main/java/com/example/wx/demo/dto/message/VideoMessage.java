package com.example.wx.demo.dto.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage{
    @XStreamAlias("Video")
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public VideoMessage(Map<String, String> requestMap,Video video) {
        super(requestMap);
        this.setMsgType("video");
        this.video = video;
    }
}
