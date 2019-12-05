package com.example.wx.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.wx.demo.dto.button.Button;
import com.example.wx.demo.dto.button.ClickButton;
import com.example.wx.demo.dto.button.PhotoOrAlbumButton;
import com.example.wx.demo.dto.button.SubButton;
import com.example.wx.demo.dto.button.ViewButton;
import com.example.wx.demo.dto.message.Article;
import com.example.wx.demo.dto.message.BaseMessage;
import com.example.wx.demo.dto.message.NewsMessage;
import com.example.wx.demo.dto.message.TextMessage;
import com.example.wx.demo.service.WeChatService;
import com.example.wx.demo.utility.WeChatUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WeChatServiceImpl implements WeChatService {

    /**
     * 用于处理所有的事件和消息的回复
     * @param requestMap
     * @return
     */
    public String getResponse(Map<String, String> requestMap) {
        BaseMessage msg = null;
        String msgType = requestMap.get("MsgType");
        switch (msgType){
            //处理文本消息
            case "text":
                msg = dealTextMessage(requestMap);
                break;
            case "image":
                msg = dealImageMessage(requestMap);
                break;
            case "voice":
                break;
            case "video":
                break;
            case "shortvideo":
                break;
            case "location":
                break;
            case "link":
                break;
            case "event":
                msg = dealEventMessage(requestMap);
                break;
            default:
                break;
        }

        if (null!=msg){
            //把消息对象处理为xml数据包
            return WeChatUtil.beanToXml(msg);
        }
        return null;
    }

    private BaseMessage dealEventMessage(Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        switch (event){
            case "CLICK":
                return dealClick(requestMap);
            case "VIEW":
                return dealView(requestMap);
            default:
                break;
        }
        return null;
    }

    private BaseMessage dealView(Map<String, String> requestMap) {
        String eventKey = requestMap.get("EventKey");
        switch (eventKey){
            case "http://www.baidu.com/":
                return new TextMessage(requestMap,"百度到了什么呢？");
            case "http://www.sinosoft.com.cn/":
                return new TextMessage(requestMap,"给个对中科软的评价呗！");
            default:
                break;
        }
        return null;
    }

    private BaseMessage dealClick(Map<String, String> requestMap) {
        String eventKey = requestMap.get("EventKey");
        switch (eventKey){
            case "1":
                return new TextMessage(requestMap,"你点击了一级菜单“点一下”");
            case "3_2":
                return new TextMessage(requestMap,"你点击了二级菜单“这里面点一下”");
            default:
                break;
        }
        return null;
    }

    /**
     * 自定义菜单初始化
     */
    @Override
    public void menuInit() {
        Button btn = new Button();
        btn.getButton().add(new ClickButton("点一下","1"));
        btn.getButton().add(new ViewButton("百度","http://www.baidu.com/"));
        SubButton sbtn = new SubButton("其它");
        sbtn.getSub_button().add(new PhotoOrAlbumButton("上传图片","3_1"));
        sbtn.getSub_button().add(new ClickButton("这里面点一下","3_2"));
        sbtn.getSub_button().add(new ViewButton("中科软官网","http://www.sinosoft.com.cn/"));
        btn.getButton().add(sbtn);
        WeChatUtil.menuInit(JSONObject.toJSONString(btn));
    }

    private static BaseMessage dealImageMessage(Map<String, String> requestMap) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("你的图片","自己百度看看你的图片是什么意思",requestMap.get("PicUrl"),"www.baidu.com"));
        NewsMessage nm = new NewsMessage(requestMap,"1",articles);
        return nm;
    }

    /**
     * 处理文本消息
     * @param requestMap
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        TextMessage tm = new TextMessage(requestMap,"你长的丑别和我说话");
        return tm;
    }
}
