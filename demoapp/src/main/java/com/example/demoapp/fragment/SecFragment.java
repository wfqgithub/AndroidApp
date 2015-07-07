package com.example.demoapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demoapp.R;
import com.example.demoapp.Utils.L;
import com.example.demoapp.Utils.ToastUtil;
import com.example.demoapp.model.Gril;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SecFragment";
    private RecyclerView mRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private Context mContext;
    private List<Gril> mList = new ArrayList<>();

    private OnFragmentInteractionListener mListener;
    private SwipeRefreshLayout mRefreshLayout;
    private MyRecyclerAdapter myRecyclerAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecFragment newInstance(String param1, String param2) {
        SecFragment fragment = new SecFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SecFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_sec, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        getDate();
    }

    private void initView() {
        mContext = getActivity();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.ryc_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(this);
        myRecyclerAdapter = new MyRecyclerAdapter(mContext);
        mRecyclerView.setAdapter(myRecyclerAdapter);
        mRefreshLayout.setColorSchemeColors(R.color.mainColor);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //设置左右的间隔如果想设置的话自行设置，我这用不到就注释掉了
/*          outRect.left = space;
            outRect.right = space;*/
            if (parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }

    private void setData() {
        myRecyclerAdapter.setDataChange(mList);
        myRecyclerAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }


    private void getDate() {
        BmobQuery<Gril> query = new BmobQuery<Gril>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(mContext, new FindListener<Gril>() {
            @Override
            public void onSuccess(List<Gril> object) {
                // TODO Auto-generated method stub
                ToastUtil.showShort(mContext, "查询成功Gril：共" + object.size() + "条数据。");
                for (Gril gril : object) {
                    L.d(TAG, gril.getUrl() + "title = " + gril.getTitle());
                    mList.add(gril);
                }
                L.d(TAG, "mList.size = " + mList.size());
                setData();
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                ToastUtil.showShort(mContext, "查询失败：" + msg);
            }

        });
    }


    @Override
    public void onRefresh() {
        getDate();
    }


    public  class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Gril> mList;
        private Context mContext = null;
        private LayoutInflater mInflater = null;

        public MyRecyclerAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
        }

        public void setDataChange(List<Gril> mList) {
            this.mList = mList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.layout_item, viewGroup, false);
            ItemViewHolder holder = new ItemViewHolder(view);

            holder.mTitleTv = (TextView) view.findViewById(R.id.title_tv);
            holder.mSimpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.sd_view);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            ((ItemViewHolder) viewHolder).mTitleTv.setText(mList.get(position).getTitle());
            ((ItemViewHolder) viewHolder).mSimpleDraweeView.setImageURI(Uri.parse(mList.get(position
            ).getUrl()));
        }

        @Override
        public int getItemCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;
        }


    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        private TextView mTitleTv = null;
        private SimpleDraweeView mSimpleDraweeView = null;//图片控件
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

}
