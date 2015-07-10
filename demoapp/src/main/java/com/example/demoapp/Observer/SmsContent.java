package com.example.demoapp.Observer;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.widget.EditText;

import com.example.demoapp.Utils.L;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/7/10.
 */
public class SmsContent extends ContentObserver {
    private static final String TAG = "SmsContent";

    public static final String SMS_URI_INBOX = "content://sms/inbox";
    private Activity activity = null;
    private String smsContent = "";
    private EditText verifyText = null;
    public SmsContent(Activity activity, Handler handler, EditText verifyText) {
        super(handler);
        this.activity = activity;
        this.verifyText = verifyText;
    }
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        L.i(TAG,"onChange");
        Cursor cursor = null;// 光标
        // 读取收件箱中指定号码的短信
        cursor = activity.managedQuery(Uri.parse(SMS_URI_INBOX), new String[] { "_id", "address", "body", "read" }, "address=? and read=?",
                new String[] { "10690333071629", "1" }, "date asc");
        System.out.println("cursor ==null");
        if (cursor != null) {// 如果短信为未读模式
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                String smsbody = cursor.getString(cursor.getColumnIndex("body"));
                System.out.println("smsbody=======================" + smsbody);
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(smsbody.toString());
                smsContent = m.replaceAll("").trim().toString();
                verifyText.setText(smsContent);
            }
        }
    }
}
