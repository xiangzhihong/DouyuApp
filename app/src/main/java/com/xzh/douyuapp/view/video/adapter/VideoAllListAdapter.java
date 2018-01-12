package com.xzh.douyuapp.view.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.xzh.douyuapp.model.logic.video.bean.VideoCateList;
import com.xzh.douyuapp.view.video.fragment.OtherVideoFragment;
import com.xzh.douyuapp.view.video.fragment.RecommendVideoFragment;

import java.util.List;


public class VideoAllListAdapter extends FragmentStatePagerAdapter {

    private List<VideoCateList> mVideoCateLists;
    private String[] mTiltle;
    private FragmentManager mFragmentManager;

    public VideoAllListAdapter(FragmentManager fm, List<VideoCateList> videoCateLists, String[] title) {
        super(fm);
        this.mFragmentManager = fm;
        this.mVideoCateLists = videoCateLists;
        this.mTiltle = title;
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
        if (position == 0) {
            return RecommendVideoFragment.getInstance();
        }
        return OtherVideoFragment.getInstance(mVideoCateLists.get(position - 1), position - 1);
    }


}
