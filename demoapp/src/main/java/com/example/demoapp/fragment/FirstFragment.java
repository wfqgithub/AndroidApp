package com.example.demoapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.demoapp.R;
import com.example.demoapp.Utils.ImageLoaderUtils;
import com.example.demoapp.Utils.L;
import com.example.demoapp.Utils.ToastUtil;
import com.example.demoapp.adapter.AdVpAdapter;
import com.example.demoapp.customView.AutoScrollViewPager;
import com.example.demoapp.model.AdInfo;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "FirstFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewPager mViewpager;
    private Context mContext;
    private View rootView;
    private List<ImageView> mLists = new ArrayList<ImageView>();
    private List<AdInfo> mAdInfo;
    private AdVpAdapter mAdVpAdapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        L.d(TAG, "onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        L.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLists=null;
        mAdInfo=null;
//        mViewpager.stopAutoScroll();
        mContext = null;
        L.d(TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d(TAG, "onDestroyView");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getData();
    }

    private void initView() {
        mContext = getActivity();
        mViewpager = (ViewPager) rootView.findViewById(R.id.ad_vp);
    }


    private void getData() {
        BmobQuery<AdInfo> query = new BmobQuery<AdInfo>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(mContext, new FindListener<AdInfo>() {
            @Override
            public void onSuccess(List<AdInfo> object) {
                // TODO Auto-generated method stub
                ToastUtil.showShort(mContext, "查询成功：共" + object.size() + "条数据。");
                for (AdInfo adInfo : object) {
                    L.d(TAG, adInfo.getPicUrl());
                    mAdInfo = object;
                }
                initViewpager();
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                ToastUtil.showShort(mContext, "查询失败：" + msg);
            }

        });
    }

    private void initViewpager(){
        for(int i = 0 ;i< mAdInfo.size();i++){
            SimpleDraweeView mImageView = new SimpleDraweeView(mContext);
//            ImageLoaderUtils.loadImage(mImageView,mAdInfo.get(i).getPicUrl());
//            ImageLoader.getInstance().displayImage(mAdInfo.get(i).getPicUrl(),mImageView);
//            Picasso.with(mContext).load(mAdInfo.get(i).getPicUrl()).into(mImageView);
            mImageView.setImageURI(Uri.parse(mAdInfo.get(i).getPicUrl()));
            mLists.add(mImageView);
        }
//        mViewpager.setCycle(true);
        mAdVpAdapter = new AdVpAdapter();
        mAdVpAdapter.setData(mLists);
        mViewpager.setAdapter(mAdVpAdapter);
//        mViewpager.startAutoScroll();
    }

}
