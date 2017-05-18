package com.example.administrator.newsdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.newsdemo.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> titleList;
    private List<Fragment> contentList;
    private ContentPagerAdapter contentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toobar= (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toobar);
        initContent();
        initView();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabTextColors(ContextCompat.getColor(this,R.color.colorAccent), ContextCompat.getColor(this, R.color.dot_dark_screen4));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.dot_dark_screen4));
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

    private void initView() {
        mTabLayout= (TabLayout) findViewById(R.id.tl_tab);
        mViewPager= (ViewPager) findViewById(R.id.vp_content);
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(contentAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab_layout, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_add:
//                titleList.add("Tab " + titleList.size());
//                contentList.add(NewsFragment.newInstance(titleList.get(titleList.size()-1)));
//                contentAdapter.notifyDataSetChanged();
//                mTabLayout.setupWithViewPager(mViewPager);
                return true;

            case R.id.tab_mode_fixed:
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);
                return true;

            case R.id.tab_mode_scrollable:
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     *适配器
     * */
    class ContentPagerAdapter extends FragmentPagerAdapter {


        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return contentList.get(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
