package com.example.administrator.newsdemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.adapter.WeixinAdapter;
import com.example.administrator.newsdemo.model.WeixinData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25.
 */
public class VedioFragment extends Fragment{
    private View view;
    private RecyclerView mRecyclerView;
    private WeixinAdapter mWeixinAdapter;
    private Handler mHandler;
    private List<WeixinData.ResultBean.ListBean> mListBeen=new ArrayList<>();
    private final String address="http://v.juhe.cn/weixin/query?key=b7274bb84db36c41eeab139a8500b82e";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAdapter=new WeixinAdapter(mListBeen,getContext());
        initHanlder();
        getData();
    }

    private void initHanlder() {
        mHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what==1001){
                    //数据发生变化
                    mWeixinAdapter.setChang(mListBeen);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                return false;
            }
        });
    }

    private void getData() {
        sendOkHttpRequest(address);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_vedio,container,false);
        init();
        return view;
    }

    private void init() {
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mRecyclerView= (RecyclerView) view.findViewById(R.id.weixin_recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mWeixinAdapter);
    }

    private  void sendOkHttpRequest(String address) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                WeixinData data=gson.fromJson(response.body().string(),WeixinData.class);
                mListBeen=data.getResult().getList();
                mHandler.sendEmptyMessage(1001);

            }
        });
    }
}
