package com.example.wx.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.wx.demo.dto.button.Button;
import com.example.wx.demo.dto.button.ClickButton;
import com.example.wx.demo.dto.button.PhotoOrAlbumButton;
import com.example.wx.demo.dto.button.SubButton;
import com.example.wx.demo.dto.button.ViewButton;
import com.example.wx.demo.dto.template.ReqIndustry;
import com.example.wx.demo.dto.template.ResIndustry;
import com.example.wx.demo.utility.WeChatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void testToken() {
        System.out.println("access_token:");
        String accessToken = WeChatUtil.getAccessToken();
        System.out.println(accessToken);
    }

    @Test
    void testButton(){
        Button btn = new Button();
        btn.getButton().add(new ClickButton("Click","1"));
        btn.getButton().add(new ViewButton("View","http://www.baidu.com/"));
        SubButton sbtn = new SubButton("sub_button");
        sbtn.getSub_button().add(new PhotoOrAlbumButton("pic_photo_or_album","3_1"));
        sbtn.getSub_button().add(new ClickButton("Click","3_2"));
        sbtn.getSub_button().add(new ViewButton("View","http://www.sinosoft.com.cn/"));
        btn.getButton().add(sbtn);
        System.out.println(JSONObject.toJSONString(btn));
    }

    @Test
    void testIndustry(){
        try {
            ResIndustry resIndustry = WeChatUtil.getIndustry();
            if (null != resIndustry.getErrcode()){
                ReqIndustry reqIndustry = new ReqIndustry("1","9");
                WeChatUtil.setIndustry(JSONObject.toJSONString(reqIndustry));
                resIndustry = WeChatUtil.getIndustry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
