package com.example.demoapp.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.demoapp.R;
import com.example.demoapp.Utils.JumpUtils;
import com.example.demoapp.activity.PercentActivity;
import com.example.demoapp.customView.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mTb;

    private CircleImageView mCivIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addListener();
    }


    private void initView(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        mNavigationView = (NavigationView)findViewById(R.id.id_nv_menu);
        mTb = (Toolbar)findViewById(R.id.toolbar);
        mCivIcon = (CircleImageView) findViewById(R.id.header_userIcon_civ);
        setSupportActionBar(mTb);
        setupDrawerContent(mNavigationView);
    }


    private void addListener(){
        mCivIcon.setOnClickListener(this);
    }


    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog");
        builder.setMessage("少数派客户端");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }


    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener()
                {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        showDialog();
                        openPercentAct();
                        return true;
                    }
                });
    }



    private void openPercentAct(){
       Intent mIntent = new Intent(this,PercentActivity.class);
        startActivity(mIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }else if(id == android.R.id.home){
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.header_userIcon_civ:
                JumpUtils.getInstance().jumpToLoginAct(this);
            break;

        }
    }

}
