package com.example.administrator.newsdemo.ui.fragment;

import android.support.v4.app.Fragment;

import com.example.administrator.newsdemo.R;

/**
 * Created by Administrator on 2017/5/24.
 */

public class FragmentFactory {
    private static FragmentFactory sFragmentFactory;
    private Fragment mainFragment;
    private Fragment vedioFragment;
    private Fragment meFragment;


    /**
     * 单例模式
     * @return
     */
    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    /**
     * 根据不同的tabId来生成不同fragment
     *
     * @param tabId
     * @return
     */
    public Fragment getFragment(int tabId) {
        switch (tabId) {
            case R.id.tab_top:
                return getTopFragment();
            case R.id.tab_vidio:
                return getVedioFragment();
            case R.id.tab_me:
                return getMeFragment();
        }
        return null;
    }

    private Fragment getMeFragment() {
        if (meFragment==null){
            meFragment=new MeFragment();
        }
        return meFragment;
    }

    private Fragment getVedioFragment() {
        if (vedioFragment==null){
            vedioFragment=new VedioFragment();
        }
        return vedioFragment;
    }

    private Fragment getTopFragment() {
        if (mainFragment==null){
            mainFragment=new MainFragment();
        }
        return mainFragment;
    }
}
