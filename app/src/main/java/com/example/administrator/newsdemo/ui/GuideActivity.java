package com.example.administrator.newsdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.newsdemo.MainActivity;
import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.util.SplashUtil;

/**
 * 引导页的活动
 * */
public class GuideActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private Button btn_skip;
    private Button btn_next;
    private LinearLayout dotsLayout;
    private TextView[] dots;//圆点
    private int[] layouts;//引导页面
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();

    }

    private int getItem(int i){
        return mViewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen(){
        SplashUtil.setIsFristRun(GuideActivity.this,false);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void init() {
        //去掉信息栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mViewPager= (ViewPager) findViewById(R.id.view_pager);
        btn_skip= (Button) findViewById(R.id.btn_skip);
        btn_next= (Button) findViewById(R.id.btn_next);
        dotsLayout= (LinearLayout) findViewById(R.id.layoutDots);


        //添加欢迎页面
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4,
                R.layout.welcome_slide5
        };

        //添加点
        addBottomDots(0);
        myViewPagerAdapter = new MyViewPagerAdapter();
        //给ViewPager设置适配器
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager .addOnPageChangeListener(viewPagerPageChangeListener);


        /**
         * 下一步
         * */
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if(current < layouts.length){
                    mViewPager.setCurrentItem(current);
                }else{
                    launchHomeScreen();
                }
            }
        });


        /**
         * 跳过
         * */
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            launchHomeScreen();
            }
        });
    }


    /**
     * 添加点
     * */
    private void addBottomDots(int currentPage) {
            dots = new TextView[layouts.length];

            int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
            int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

            dotsLayout.removeAllViews();
            for(int i = 0; i < dots.length; i++){
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#8226;"));//圆点
                dots[i].setTextSize(35);
                dots[i].setTextColor(colorsInactive[currentPage]);
                dotsLayout.addView(dots[i]);
            }

            if(dots.length > 0){
                dots[currentPage].setTextColor(colorsActive[currentPage]);
            }

    }



    /**
     * 适配器
     * */
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){}

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position],container,false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }



    /**
     * 监听器
     * */
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            //改变下一步按钮text  “NEXT”或“GOT IT”
            if(position == layouts.length - 1){
                btn_next.setText(getString(R.string.start));
                btn_skip.setVisibility(View.GONE);
            }else{
                btn_next.setText(getString(R.string.next));
                btn_skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
