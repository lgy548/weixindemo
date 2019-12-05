package com.example.wx.demo.dto.token;

public class AccessToken {

    private String accessToken;

    private Long expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public AccessToken(String accessToken, String expireIn) {
        super();
        this.accessToken = accessToken;
        expireTime=System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
    }

    /**
     * 判断是否过期
     * @return
     */
    public boolean isExpired(){
        return System.currentTimeMillis()>expireTime;
    }
}
