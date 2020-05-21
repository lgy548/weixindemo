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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

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

    @Test
    void testWeChatMultipartFilePost(){
        WeChatUtil.uploadFile("images/1.jpg","image");
    }

    /**
     *ArrayList()线程不安全测试
     * 配合使用lambda表达式
     * CopyOnWriteArrayList()安全 内部运用ReentrantLock-可重入锁机制（在资源竞争不是很激烈的情况下，Synchronized的性能要优于ReetrantLock，但是在资源竞争很激烈的情况下，Synchronized的性能会下降几十倍，但是ReetrantLock的性能能维持常态）
     */
    @Test
    void lambdaTest(){
        List<String> list = new CopyOnWriteArrayList<>();//企业不可用Collections.synchronizedList(new ArrayList<>());//企业不可用new Vector<>();//不安全new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }


}
