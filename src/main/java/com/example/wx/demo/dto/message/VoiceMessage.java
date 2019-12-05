package com.example.wx.demo.dto.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{

    @XStreamAlias("Voice")
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public VoiceMessage(Map<String, String> requestMap, Voice voice){
        super(requestMap);
        //设置为文本消息的magtype
        this.setMsgType("voice");
        this.voice = voice;
    }
}
