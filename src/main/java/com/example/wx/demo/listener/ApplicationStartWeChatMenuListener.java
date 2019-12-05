package com.example.wx.demo.listener;

import com.example.wx.demo.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartWeChatMenuListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private WeChatService weChatService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        weChatService.menuInit();
    }
}
