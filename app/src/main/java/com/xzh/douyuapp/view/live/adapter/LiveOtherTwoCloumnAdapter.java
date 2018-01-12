package com.xzh.douyuapp.view.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.xzh.douyuapp.model.logic.live.bean.LiveOtherTwoColumn;
import com.xzh.douyuapp.view.live.fragment.LiveOtherTwoColumnFragment;

import java.util.List;


public class LiveOtherTwoCloumnAdapter extends FragmentStatePagerAdapter {

    private List<LiveOtherTwoColumn> mliveOtherTwoColumns;
    private  String[] mTiltle;
    private FragmentManager mFragmentManager;

    public LiveOtherTwoCloumnAdapter(FragmentManager fm, List<LiveOtherTwoColumn> mliveOtherTwoColumns, String[] title)
    {
        super(fm);
        this.mFragmentManager=fm;
        this.mliveOtherTwoColumns=mliveOtherTwoColumns;
        this.mTiltle=title;
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
        return LiveOtherTwoColumnFragment.getInstance(mliveOtherTwoColumns.get(position),position);
    }
}
