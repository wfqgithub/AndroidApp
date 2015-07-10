package com.example.demoapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demoapp.Observer.SmsContent;
import com.example.demoapp.R;
import com.example.demoapp.Utils.L;
import com.example.demoapp.Utils.ToastUtil;
import com.example.demoapp.model.User;

import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";

    private TextView tvRegist;
    private Toolbar mTb;
    private EditText userNameEt, pwdEt;
    private Button SignBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        tvRegist = (TextView) findViewById(R.id.tv_regist);
        tvRegist.setOnClickListener(this);
        mTb = (Toolbar) findViewById(R.id.toolbar);
        mTb.setTitle(getResources().getText(R.string.btn_login));
        mTb.setNavigationOnClickListener(this);
        userNameEt = (EditText) findViewById(R.id.username_et);
        pwdEt = (EditText) findViewById(R.id.pwd_et);
        SignBtn = (Button) findViewById(R.id.btn_login);
        SignBtn.setOnClickListener(this);
//        SmsContent content = new SmsContent(LoginActivity.this, new Handler(), userNameEt);
//        // 注册短信变化监听
//        this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
    }


    private void login() {
        User mUser = new User();
        mUser.setUsername(userNameEt.getText().toString());
        mUser.setPassword(pwdEt.getText().toString());
        mUser.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
                //登录成功
                ToastUtil.showShort(LoginActivity.this, "登录成功");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
//登录失败
                ToastUtil.showShort(LoginActivity.this, "登录失败" + s);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_regist:
                jumpToRegAct();
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void jumpToRegAct() {
        Intent mIntent = new Intent(LoginActivity.this, RegistActivity.class);
        startActivity(mIntent);
    }

}
