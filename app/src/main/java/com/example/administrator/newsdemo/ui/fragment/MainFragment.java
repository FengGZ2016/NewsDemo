package com.example.administrator.newsdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.adapter.ContentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class MainFragment extends Fragment{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> titleList;
    private List<Fragment> contentList;
    private ContentPagerAdapter contentAdapter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_main,null);
        initContent();
        init();
        initTabLayout();
        return view;
    }

    private void init() {
        mTabLayout= (TabLayout) view.findViewById(R.id.tl_tab);
        mViewPager= (ViewPager)view.findViewById(R.id.vp_content);
        contentAdapter = new ContentPagerAdapter(getChildFragmentManager(),titleList,contentList);
        mViewPager.setAdapter(contentAdapter);

    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabTextColors(ContextCompat.getColor(getContext(),R.color.colorAccent), ContextCompat.getColor(getContext(), R.color.dot_dark_screen4));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.dot_dark_screen4));
        ViewCompat.setElevation(mTabLayout, 10);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 初始化数据源，标题和内容
     * */
    private void initContent() {
        //标题
        titleList=new ArrayList<>();
        titleList.add("头条");
        titleList.add("娱乐");
        titleList.add("体育");
        titleList.add("科技");
        titleList.add("社会");
        titleList.add("军事");
        titleList.add("财经");
        titleList.add("时尚");
        titleList.add("国内");
        titleList.add("国际");

        contentList=new ArrayList<>();
        NewsFragment fa = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("NEWSTYPE", 1);
        fa.setArguments(bundle);

        NewsFragment fb = new NewsFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("NEWSTYPE", 2);
        fb.setArguments(bundle2);

        NewsFragment fc = new NewsFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("NEWSTYPE", 3);
        fc.setArguments(bundle3);

        NewsFragment fd = new NewsFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putInt("NEWSTYPE", 4);
        fd.setArguments(bundle4);

        NewsFragment fe = new NewsFragment();
        Bundle bundle5= new Bundle();
        bundle5.putInt("NEWSTYPE", 5);
        fe.setArguments(bundle5);

        NewsFragment ff = new NewsFragment();
        Bundle bundle6 = new Bundle();
        bundle6.putInt("NEWSTYPE", 6);
        ff.setArguments(bundle6);

        NewsFragment fg = new NewsFragment();
        Bundle bundle7 = new Bundle();
        bundle7.putInt("NEWSTYPE", 7);
        fg.setArguments(bundle7);

        NewsFragment fh = new NewsFragment();
        Bundle bundle8 = new Bundle();
        bundle8.putInt("NEWSTYPE", 8);
        fh.setArguments(bundle8);

        NewsFragment fj = new NewsFragment();
        Bundle bundle9 = new Bundle();
        bundle9.putInt("NEWSTYPE", 9);
        fj.setArguments(bundle9);

        NewsFragment fk = new NewsFragment();
        Bundle bundle10 = new Bundle();
        bundle10.putInt("NEWSTYPE", 10);
        fk.setArguments(bundle10);

        contentList.add(fa);
        contentList.add(fb);
        contentList.add(fc);
        contentList.add(fd);
        contentList.add(fe);
        contentList.add(ff);
        contentList.add(fg);
        contentList.add(fh);
        contentList.add(fj);
        contentList.add(fk);

    }
}
