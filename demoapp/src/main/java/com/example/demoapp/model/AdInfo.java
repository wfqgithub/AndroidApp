package com.example.demoapp.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2015/7/3.
 */
public class AdInfo extends BmobObject {

    private BmobFile adPic;

    private String picUrl;

    public BmobFile getAdPic() {
        return adPic;
    }

    public void setAdPic(BmobFile adPic) {
        this.adPic = adPic;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
