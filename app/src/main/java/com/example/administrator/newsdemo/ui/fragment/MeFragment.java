package com.example.administrator.newsdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.newsdemo.R;

/**
 * Created by Administrator on 2017/5/24.
 */
public class MeFragment extends Fragment{
    private View view;
    private NavigationView mNavigationView;
    private View headerView;
    private ImageView iconImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me,container,false);
        init();
        return view;
    }

    private void init() {
        mNavigationView= (NavigationView) view.findViewById(R.id.navigation_view);
        headerView=mNavigationView.getHeaderView(0);
        iconImage= (ImageView) headerView.findViewById(R.id.icon_image);
        iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击头像
                Toast.makeText(getContext(), "你点击了头像", Toast.LENGTH_SHORT).show();
            }
        });
        //设置默认选中
        mNavigationView.setCheckedItem(R.id.item_myfile);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_myfile:
                    //我的资料
                        Toast.makeText(getContext(), "我的资料", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_mycollect:
                        //我的收藏
                        Toast.makeText(getContext(), "我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_mycomment:
                        //我的评论
                        Toast.makeText(getContext(), "我的评论", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_myintegral:
                        //我的积分
                        Toast.makeText(getContext(), "我的积分", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_setting:
                        //设置
                        Toast.makeText(getContext(), "设置", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
