package com.example.demoapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.Utils.JumpUtils;
import com.example.demoapp.activity.PercentActivity;
import com.example.demoapp.adapter.FragmentAdapter;
import com.example.demoapp.customView.CircleImageView;
import com.example.demoapp.fragment.FirstFragment;
import com.example.demoapp.fragment.SecFragment;
import com.example.demoapp.fragment.ThirdFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener, FirstFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mTb;
    private ActionBarDrawerToggle mAbToggle;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private CircleImageView mCivIcon;
    private String[] tabs;
    private FragmentAdapter fragmentAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addListener();
    }


    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        mTb = (Toolbar) findViewById(R.id.toolbar);
        mCivIcon = (CircleImageView) findViewById(R.id.header_userIcon_civ);
        mTb.setTitle("FUCK");
        setSupportActionBar(mTb);
        setupDrawerContent(mNavigationView);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAbToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mAbToggle.syncState();
        mDrawerLayout.setDrawerListener(mAbToggle);

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.vp);

        initTabs();
        initViewPager();

    }

    private void initTabs() {
        tabs = getResources().getStringArray(R.array.tabName);
    }

    private void initViewPager() {
        fragments.add(FirstFragment.newInstance("1", "2"));
        fragments.add(SecFragment.newInstance("2", "3"));
        fragments.add(ThirdFragment.newInstance("3", "4"));
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.setData(fragments);
        fragmentAdapter.setTabs(tabs);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapter);
    }


    private void addListener() {
        mCivIcon.setOnClickListener(this);
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog");
        builder.setMessage("少数派客户端");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
//                        showDialog();
//                        openPercentAct();
                        openGifAct();
                        return true;
                    }
                });
    }


    private void openPercentAct() {
        Intent mIntent = new Intent(this, PercentActivity.class);
        startActivity(mIntent);
    }

    private void openGifAct() {
        Intent mIntent = new Intent(this, GifActivity.class);
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
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_userIcon_civ:
                JumpUtils.getInstance().jumpToLoginAct(this);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mAbToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mAbToggle.syncState();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }
}
