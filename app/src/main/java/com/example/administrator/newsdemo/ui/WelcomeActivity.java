package com.example.administrator.newsdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.newsdemo.MainActivity;
import com.example.administrator.newsdemo.R;
import com.example.administrator.newsdemo.util.SplashUtil;

public class WelcomeActivity extends AppCompatActivity {
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mHandler=new Handler();
        // 如果是第一次运行  就显示引导页
        if(SplashUtil.getIsFirstRun(WelcomeActivity.this)) {
            startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
            SplashUtil.setIsFristRun(WelcomeActivity.this, false);
        }else {
                //停3秒进入主界面
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            },3000);

        }

    }
}
