package com.example.demoapp.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2015/7/1.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(getApplicationContext(),"4337ee3603e21e3bb03da51cb17fe750");
    }
}
