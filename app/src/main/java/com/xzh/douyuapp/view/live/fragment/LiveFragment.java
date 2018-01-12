package com.xzh.douyuapp.view.live.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.flyco.tablayout.SlidingTabLayout;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseFragment;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.live.LiveOtherColumnModelLogic;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherColumn;
import com.xzh.douyuapp.presenter.live.impl.LiveOtherColumnPresenterImp;
import com.xzh.douyuapp.presenter.live.interfaces.LiveOtherColumnContract;
import com.xzh.douyuapp.view.live.adapter.LiveAllCloumnAdapter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LiveFragment extends BaseFragment<LiveOtherColumnModelLogic, LiveOtherColumnPresenterImp> implements LiveOtherColumnContract.View {


    @BindView(R.id.live_sliding_tab)
    SlidingTabLayout liveSlidingTab;
    @BindView(R.id.live_viewpager)
    ViewPager liveViewpager;

    private String[] mTilte;
    private LiveAllCloumnAdapter mLiveAllColumnAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
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

    /**
     * 懒加载
     */
    @Override
    protected void lazyFetchData() {
        mPresenter.getPresenterLiveOtherColumn();
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    /**
     * 获取全部栏目分类
     *
     * @param mLiveOtherColumns
     */
    @Override
    public void getViewLiveOtherColumn(List<LiveOtherColumn> mLiveOtherColumns) {
        mTilte = new String[mLiveOtherColumns.size() + 3];
        mTilte[0] = "常用";
        mTilte[1] = "全部";
        for (int i = 0; i < mLiveOtherColumns.size(); i++) {
            mTilte[i + 2] = mLiveOtherColumns.get(i).getCate_name();
        }
        mTilte[mTilte.length - 1] = "体育直播";
        liveViewpager.setOffscreenPageLimit(mTilte.length);
        mLiveAllColumnAdapter = new LiveAllCloumnAdapter(getChildFragmentManager(), mLiveOtherColumns, mTilte);
        liveViewpager.setAdapter(mLiveAllColumnAdapter);
        mLiveAllColumnAdapter.notifyDataSetChanged();
        liveSlidingTab.setViewPager(liveViewpager, mTilte);
        liveSlidingTab.setCurrentTab(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
