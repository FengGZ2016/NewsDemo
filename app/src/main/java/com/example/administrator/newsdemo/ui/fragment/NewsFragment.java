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
import com.example.administrator.newsdemo.adapter.NewsAdapter;
import com.example.administrator.newsdemo.model.NewData;
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
 * Created by Administrator on 2017/5/16.
 */

public class NewsFragment extends Fragment{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recycler_content;
    private NewsAdapter mNewsAdapter;
    private List<NewData.ResultBean.DataBean> mDataBeanList=new ArrayList<>();
    private int type;//新闻类型
    private Handler mHandler;
    private  final int TOP=1;
    private  final int YULE=2;
    private  final int TIYU=3;
    private  final int KEJI=4;
    private  final int SHEHUI=5;
    private  final int JUNSHI=6;
    private  final int CAIJING=7;
    private  final int SHISHANG=8;
    private  final int GUONEI=9;
    private  final int GUOJI=10;
    private final String TOPURL="http://v.juhe.cn/toutiao/index?type=top&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String YULEURL="http://v.juhe.cn/toutiao/index?type=yule&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String TIYUURL="http://v.juhe.cn/toutiao/index?type=tiyu&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String KEJIURL="http://v.juhe.cn/toutiao/index?type=keji&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String SHEHUIURL="http://v.juhe.cn/toutiao/index?type=shehui&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String GUONEIURL="http://v.juhe.cn/toutiao/index?type=guonei&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String GUOJIURL="http://v.juhe.cn/toutiao/index?type=guoji&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String JUNSHIURL="http://v.juhe.cn/toutiao/index?type=junshi&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String CAIJINGURL="http://v.juhe.cn/toutiao/index?type=caijing&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";
    private final String SHISHANGURL="http://v.juhe.cn/toutiao/index?type=shishang&key=0709bcfc1cdf4ae283364c1c8bbe2a7d";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取新闻类型
      type=getArguments().getInt("NEWSTYPE");
        //初始化hanlder
        initHanlder();
        //获取网络数据
        getHttpData();

    }

    private void getHttpData() {

     switch (type){
         case TOP:
             sendOkHttpRequest(TOPURL);
             break;
         case YULE:
             sendOkHttpRequest(YULEURL);
             break;
         case TIYU:
             sendOkHttpRequest(TIYUURL);
             break;
         case KEJI:
             sendOkHttpRequest(KEJIURL);
             break;
         case SHEHUI:
             sendOkHttpRequest(SHEHUIURL);
             break;
         case CAIJING:
             sendOkHttpRequest(CAIJINGURL);
             break;
         case SHISHANG:
             sendOkHttpRequest(SHISHANGURL);
             break;
         case JUNSHI:
             sendOkHttpRequest(JUNSHIURL);
             break;
         case GUONEI:
             sendOkHttpRequest(GUONEIURL);
             break;
         case GUOJI:
             sendOkHttpRequest(GUOJIURL);
             break;
         default:
             break;
     }
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
                NewData newData=gson.fromJson(response.body().string(),NewData.class);
                mDataBeanList=newData.getResult().getData();
                mHandler.sendEmptyMessage(1001);
            }
        });
    }

    private void initHanlder() {
        mHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what==1001){
                    //数据发生变化
                    mNewsAdapter.setChang(mDataBeanList);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_news, container, false);
        recycler_content= (RecyclerView) view.findViewById(R.id.recycler_content);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHttpData();
            }
        });
        //初始化RECYCLERVIEW
        initRecyclerView();
        return view;
    }


    private void initRecyclerView() {
        mNewsAdapter=new NewsAdapter(mDataBeanList,getContext());
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recycler_content.setLayoutManager(manager);
        recycler_content.setAdapter(mNewsAdapter);

    }


}
