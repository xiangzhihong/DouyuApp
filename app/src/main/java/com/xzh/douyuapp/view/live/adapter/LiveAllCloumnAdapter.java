package com.xzh.douyuapp.view.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.xzh.douyuapp.model.logic.live.bean.LiveOtherColumn;
import com.xzh.douyuapp.view.live.fragment.LiveAllColumnFragment;
import com.xzh.douyuapp.view.live.fragment.LiveCommonColumnFragment;
import com.xzh.douyuapp.view.live.fragment.LiveOtherColumnFragment;
import com.xzh.douyuapp.view.live.fragment.LiveSportsColumnFragment;

import java.util.List;


public class LiveAllCloumnAdapter extends FragmentStatePagerAdapter {

    private List<LiveOtherColumn> mliveOtherColumns;
    private String[] mTiltle;
    private FragmentManager mFragmentManager;

    public LiveAllCloumnAdapter(FragmentManager fm, List<LiveOtherColumn> mliveOtherColumns, String[] title) {
        super(fm);
        this.mFragmentManager = fm;
        this.mliveOtherColumns = mliveOtherColumns;
        this.mTiltle = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTiltle[position];
    }

    @Override
    public int getCount() {
        return mTiltle.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return LiveCommonColumnFragment.getInstance();
        } else if (position == 1) {
            return LiveAllColumnFragment.getInstance();
        } else if (position == mTiltle.length - 1) {
            return LiveSportsColumnFragment.getInstance();
        } else {
//            其他一级栏目分类
            return LiveOtherColumnFragment.getInstance(mliveOtherColumns.get(position - 2), position - 2);
        }
    }
}
