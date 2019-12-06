package com.example.wx.demo.service;

import java.util.Map;

public interface WeChatService {
    String getResponse(Map<String, String> requestMap);

    void menuInit();

    void industryInit();
}
