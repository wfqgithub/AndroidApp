package com.example.demoapp.Utils;

import android.content.Context;
import android.content.Intent;

import com.example.demoapp.activity.LoginActivity;

/**
 * Created by Administrator on 2015/7/1.
 */
public class JumpUtils {

    private Intent mIntent;

    private static class JumpUtilsHolder {
        private static final JumpUtils INSTANCE = new JumpUtils();
    }

    private JumpUtils() {
    }

    public static final JumpUtils getInstance() {
        return JumpUtilsHolder.INSTANCE;
    }


    /**
     * 跳转至登录页面
     * @param mContext
     */
    public void jumpToLoginAct(Context mContext) {
        mIntent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(mIntent);
    }
}
