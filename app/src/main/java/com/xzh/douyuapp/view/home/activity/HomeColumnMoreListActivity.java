package com.xzh.douyuapp.view.home.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.flyco.tablayout.SlidingTabLayout;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.base.SwipeBackActivity;
import com.xzh.douyuapp.model.logic.home.HomeColumnMoreModelLogic;
import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreTwoCate;
import com.xzh.douyuapp.presenter.home.impl.HomeColumnMorePresenterImp;
import com.xzh.douyuapp.presenter.home.interfaces.HomeColumnMoreListContract;
import com.xzh.douyuapp.view.home.adapter.HomeColumnMoreTwoCateAdapter;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeColumnMoreListActivity extends SwipeBackActivity<HomeColumnMoreModelLogic, HomeColumnMorePresenterImp> implements HomeColumnMoreListContract.View {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_popup_live)
    ImageView imgPopupLive;
    @BindView(R.id.rl_bar)
    RelativeLayout rlBar;
    @BindView(R.id.twocolumn_tablayout)
    SlidingTabLayout twocolumnTablayout;
    @BindView(R.id.rl_twocolumn_bar)
    RelativeLayout rlTwocolumnBar;
    @BindView(R.id.livetwocolumn_viewpager)
    ViewPager livetwocolumnViewpager;

    private String[] mTilte;
    private HomeColumnMoreTwoCateAdapter mHomeColumnMoreTwoCateAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_column_more_list;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        tvTitle.setText(getIntent().getExtras().getString("title"));
        mPresenter.getPresenterHomeColumnMoreTwoCate(getIntent().getExtras().getString("cate_id"));
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getView() {
        return this;
    }

    @OnClick(R.id.img_back)
    public void back() {
        finish();
    }

    @Override
    public void getViewHomeColumnMoreTwoCate(List<HomeColumnMoreTwoCate> mHomeColumnMoreTwoCate) {
        mTilte = new String[mHomeColumnMoreTwoCate.size() + 1];
        mTilte[0] = "全部";
        for (int i = 0; i < mHomeColumnMoreTwoCate.size(); i++) {
            mTilte[i + 1] = mHomeColumnMoreTwoCate.get(i).getName();
        }
        if (mTilte.length <= 1) {
            rlTwocolumnBar.setVisibility(View.GONE);
        }
        livetwocolumnViewpager.setOffscreenPageLimit(mTilte.length);
        mHomeColumnMoreTwoCateAdapter = new HomeColumnMoreTwoCateAdapter(getSupportFragmentManager(), getIntent().getExtras().getString("cate_id"), mHomeColumnMoreTwoCate, mTilte);
        livetwocolumnViewpager.setAdapter(mHomeColumnMoreTwoCateAdapter);
        mHomeColumnMoreTwoCateAdapter.notifyDataSetChanged();
        twocolumnTablayout.setViewPager(livetwocolumnViewpager, mTilte);
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }
}
