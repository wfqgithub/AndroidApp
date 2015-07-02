package com.example.demoapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demoapp.R;
import com.example.demoapp.Utils.ToastUtil;
import com.example.demoapp.model.User;

import cn.bmob.v3.listener.SaveListener;

public class RegistActivity extends BaseActivity implements View.OnClickListener{

    private EditText etUserName,etPwd,etRePwd;
    private Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
        addListener();
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.regist_username_et);
        etPwd = (EditText) findViewById(R.id.regist_pwd_et);
        etRePwd = (EditText) findViewById(R.id.regist_repwd_et);
        btnRegist = (Button) findViewById(R.id.regist_btn);
    }


    private void addListener(){
        btnRegist.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regist, menu);
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
        doRegist();
    }


    /**
     * 检查输入的格式是否正确
     */
    private void checkFormat(){

    }

    private void  doRegist(){
        User mUser = new User();
        mUser.setUsername(etUserName.getText().toString().trim());
        mUser.setPassword(etPwd.getText().toString().trim());
        mUser.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.showLong(RegistActivity.this,"注册成功");
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.showLong(RegistActivity.this,s);
            }
        });
    }


}
