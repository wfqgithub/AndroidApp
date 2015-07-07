package com.example.demoapp.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/7/7.
 */
public class Gril extends BmobObject{

    private String url;

    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Gril{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
