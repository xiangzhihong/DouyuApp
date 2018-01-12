package com.xzh.douyuapp.view.home.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;


import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.base.SwipeBackActivity;
import com.xzh.douyuapp.model.logic.home.HomeFaceScoreModeLogic;
import com.xzh.douyuapp.model.logic.home.bean.HomeFaceScoreColumn;
import com.xzh.douyuapp.presenter.home.impl.HomeFaceScorePresenterImp;
import com.xzh.douyuapp.presenter.home.interfaces.HomeFaceScoreContract;
import com.xzh.douyuapp.ui.refreshview.XRefreshView;
import com.xzh.douyuapp.view.home.adapter.HomeRecommendFaceScoreColumnAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeRecommendFaceScoreActivity extends SwipeBackActivity<HomeFaceScoreModeLogic, HomeFaceScorePresenterImp> implements HomeFaceScoreContract.View {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.facescore_content_recyclerview)
    RecyclerView facescoreContentRecyclerview;
    @BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;

    private int offset = 0;
    private int limit = 20;//每页加载数量
    private HomeRecommendFaceScoreColumnAdapter mFaceScoreColumnAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_recommend_facescore;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        tvTitle.setText(getIntent().getExtras().getString("title"));
        refresh();
        setXrefeshViewConfig();
        facescoreContentRecyclerview.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        mFaceScoreColumnAdapter = new HomeRecommendFaceScoreColumnAdapter(this);
//        mFaceScoreColumnAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        facescoreContentRecyclerview.setAdapter(mFaceScoreColumnAdapter);
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                offset += limit;
                loadMore(offset, limit);
            }
        });
    }

    /**
     * 配置XRefreshView
     */
    protected void setXrefeshViewConfig() {
        rtefreshContent.setPinnedTime(2000);
        rtefreshContent.setPullLoadEnable(true);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setMoveForHorizontal(true);
        rtefreshContent.setPinnedContent(true);
        rtefreshContent.setSilenceLoadMore();
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {
        offset = 0;
        mPresenter.getPresenterFaceScoreColumn(0, 20);
    }

    @Override
    protected void onEvent() {

    }

    private void loadMore(int offset, int limit) {
        mPresenter.getPresenterFaceScoreLoadMore(offset, limit);
    }

    @Override
    protected BaseView getView() {
        return this;
    }

    @Override
    public void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        mFaceScoreColumnAdapter.getFaceScoreColumn(homeFaceScoreColumns);
    }

    @Override
    public void getViewFaceScoreColumnLoadMore(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        if (rtefreshContent != null) {
            rtefreshContent.stopLoadMore();
        }
        mFaceScoreColumnAdapter.getFaceScoreColumnLoadMore(homeFaceScoreColumns);
    }

    @Override
    public void showErrorWithStatus(String msg) {
        if (rtefreshContent != null) {
            rtefreshContent.stopLoadMore(false);
        }
    }

    @OnClick(R.id.img_back)
    public void back() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
