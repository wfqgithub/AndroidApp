package com.example.demoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.demoapp.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvRegist;
    private Toolbar mTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        tvRegist = (TextView) findViewById(R.id.tv_regist);
        tvRegist.setOnClickListener(this);
        mTb  = (Toolbar) findViewById(R.id.toolbar);
        mTb.setTitle("fuck");
        mTb.setNavigationIcon(R.mipmap.ic_launcher);
        mTb.setNavigationOnClickListener(this);
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
        switch (view.getId()){
            case R.id.tv_regist:
                jumpToRegAct();
                break;
        }
    }

    private void jumpToRegAct(){
        Intent mIntent = new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(mIntent);
    }
}
