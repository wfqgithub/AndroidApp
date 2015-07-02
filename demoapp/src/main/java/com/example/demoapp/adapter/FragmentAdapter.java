package com.example.demoapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/2.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private String[] tabs;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void setData(ArrayList<Fragment> mFragments) {
        this.mFragments = mFragments;
    }

    public void setTabs(String[] tabs) {
        this.tabs = tabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
