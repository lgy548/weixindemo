package com.example.wx.demo.controller;

import com.example.wx.demo.service.WeChatService;
import com.example.wx.demo.utility.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


@Controller
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    /**
     * 开发者通过检验signature对请求进行校验。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     * 参数	        描述
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	    随机数
     * echostr	    随机字符串
     * @return
     */
    @GetMapping(value = "/wxaccess")
    @ResponseBody
    public String wxaccess(@RequestParam(value = "signature") String signature,@RequestParam(value = "timestamp") String timestamp,
                         @RequestParam(value = "nonce") String nonce,@RequestParam(value = "echostr") String echostr){
         //校验请求
        if (!WeChatUtil.check(signature,timestamp,nonce)) {
            System.out.println("接入失败");
            return null;
        }
        //原样返回echostr参数内容
        System.out.println("接入成功");
        return echostr;

    }

    /**
     * 接受消息和推送消息
     * @param request
     * @return
     */
    @PostMapping(value = "/wxaccess")
    @ResponseBody
    public String wxaccess(HttpServletRequest request){
        try {
            //处理消息和事件推送
            Map<String,String> requestMap = WeChatUtil.parseRequest(request.getInputStream());
            //准备回复的数据包
            String respXml =  weChatService.getResponse(requestMap);
            System.out.println(respXml);
            return respXml;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
