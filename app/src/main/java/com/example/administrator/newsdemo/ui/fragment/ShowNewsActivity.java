package com.example.administrator.newsdemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.administrator.newsdemo.R;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class ShowNewsActivity extends AppCompatActivity {
    private WebView mWebView;
    private String url;
    private LikeButton mLikeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        initData();
      initView();

    }

    private void initData() {
        Intent intent=getIntent();
        url=intent.getStringExtra("url");

    }


    private void initView() {
        mLikeButton= (LikeButton) findViewById(R.id.star_button);
        mLikeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                //收藏
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //取消收藏
            }
        });

                mWebView = (WebView) findViewById(R.id.webview);
        //设置浏览器的属性，让webview支持javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        //让目标网页仍然在当前webview显示
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                if(i == 100) {
                    Toast.makeText(ShowNewsActivity.this, "网页加载完成", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if(mWebView.canGoBack())
            {
                mWebView.goBack();//返回上一页面
                return true;
            }
            else
            {
                System.exit(0);//退出程序
            }

        }

        return super.onKeyDown(keyCode, event);
    }


}
