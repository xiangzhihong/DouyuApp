package com.xzh.douyuapp.view.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;


import com.flyco.tablayout.SlidingTabLayout;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseFragment;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.home.HomeCateListModelLogic;
import com.xzh.douyuapp.model.logic.home.bean.HomeCateList;
import com.xzh.douyuapp.presenter.home.impl.HomeCateListPresenterImp;
import com.xzh.douyuapp.presenter.home.interfaces.HomeCateListContract;
import com.xzh.douyuapp.ui.pagestatemanager.PageManager;
import com.xzh.douyuapp.view.home.adapter.HomeAllListAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment<HomeCateListModelLogic, HomeCateListPresenterImp> implements HomeCateListContract
        .View {

    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_scanner)
    ImageView imgScanner;
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.sliding_tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private HomeAllListAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView(Bundle bundle) {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getHomeCateList();
    }


    @OnClick(R.id.img_message)
    public void message() {

    }

    @OnClick(R.id.img_history)
    public void history() {

    }

    @OnClick(R.id.img_scanner)
    public void scanner() {

    }

    @OnClick(R.id.img_search)
    public void search() {

    }

    @Override
    public void getHomeAllList(List<HomeCateList> cateLists) {
        List<String> mTitles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<Fragment>();
        mTitles.add("推荐");
        fragments.add(RecommendHomeFragment.getInstance());
        for (int i = 0; i < cateLists.size(); i++) {
            mTitles.add(cateLists.get(i).getTitle());
            fragments.add(OtherHomeFragment.getInstance(cateLists.get(i)));
        }
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        mAdapter = new HomeAllListAdapter(getFragmentManager(), cateLists,mTitles);
        mAdapter = new HomeAllListAdapter(getFragmentManager(), mTitles, fragments);
        viewpager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewpager);

    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

}
