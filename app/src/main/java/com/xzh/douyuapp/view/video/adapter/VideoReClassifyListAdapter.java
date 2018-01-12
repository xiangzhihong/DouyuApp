package com.xzh.douyuapp.view.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.xzh.douyuapp.model.logic.video.bean.VideoReClassify;
import com.xzh.douyuapp.view.video.fragment.VideoOtherTwoColumnFragment;

import java.util.List;


public class VideoReClassifyListAdapter extends FragmentStatePagerAdapter {

    private  List<VideoReClassify> mHomeCateLists;
    private  String[] mTiltle;
    private FragmentManager mFragmentManager;

    public VideoReClassifyListAdapter(FragmentManager fm, List<VideoReClassify> homeCateLists, String[] title)
    {
        super(fm);
        this.mFragmentManager=fm;
        this.mHomeCateLists=homeCateLists;
        this.mTiltle=title;
    }
    @Override
    public int getCount() {
        return mTiltle.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTiltle[position];
    }

    @Override
    public Fragment getItem(int position) {

            return VideoOtherTwoColumnFragment.getInstance(mHomeCateLists.get(position),position);
    }


}
