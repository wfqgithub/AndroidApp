package com.example.demoapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2015/7/3.
 */
public class AdVpAdapter extends PagerAdapter {
    private List<ImageView> mLists;

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager) container).removeView(mLists.get(position));
    }

    public void setData(List<ImageView> mLists){
        this.mLists = mLists;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mLists.get(position));
        return mLists.get(position);
    }
}
