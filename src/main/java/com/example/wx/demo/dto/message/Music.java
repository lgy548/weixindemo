package com.example.wx.demo.dto.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Music {
    @XStreamAlias("Title")
    private String title;

    @XStreamAlias("Description")
    private String description;

    @XStreamAlias("DusicUrl")
    private String dusicUrl;

    @XStreamAlias("HQMusicUrl")
    private String hQMusicUrl;

    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDusicUrl() {
        return dusicUrl;
    }

    public void setDusicUrl(String dusicUrl) {
        this.dusicUrl = dusicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public Music(String title, String description, String dusicUrl, String hQMusicUrl, String thumbMediaId) {
        super();
        this.title = title;
        this.description = description;
        this.dusicUrl = dusicUrl;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
    }
}
