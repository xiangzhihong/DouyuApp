package com.xzh.douyuapp.view.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;


import com.xzh.douyuapp.model.logic.home.bean.HomeCateList;
import com.xzh.douyuapp.view.home.fragment.OtherHomeFragment;
import com.xzh.douyuapp.view.home.fragment.RecommendHomeFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeAllListAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;
    private FragmentManager fm;

    public HomeAllListAdapter(FragmentManager fm,List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.titles=titles;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return RecommendHomeFragment.getInstance();
        }else {
            return fragments.get(position);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.fragments != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void clear() {
        fragments.clear();
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles!=null&&titles.size()>0)
            return titles.get(position);
        else
            return null;
    }

//    @Override
//    public Fragment getItem(int position) {
//        if (position == 0) {
//            return RecommendHomeFragment.getInstance();
//        }
//        OtherHomeFragment otherHomeFragment = OtherHomeFragment.getInstance(mHomeCateLists.get(position));
//        return otherHomeFragment;
//    }


}
