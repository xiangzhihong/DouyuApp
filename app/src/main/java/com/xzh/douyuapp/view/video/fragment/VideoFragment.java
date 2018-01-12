package com.xzh.douyuapp.view.video.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;


import com.flyco.tablayout.SlidingTabLayout;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseFragment;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.video.VideoCateListLogic;
import com.xzh.douyuapp.model.logic.video.bean.VideoCateList;
import com.xzh.douyuapp.presenter.video.impl.VideoCateListPresenterImpl;
import com.xzh.douyuapp.presenter.video.interfaces.VideoAllCateListContract;
import com.xzh.douyuapp.view.video.adapter.VideoAllListAdapter;


import java.util.List;

import butterknife.BindView;

public class VideoFragment extends BaseFragment<VideoCateListLogic, VideoCateListPresenterImpl> implements VideoAllCateListContract.View {


    @BindView(R.id.live_sliding_tab)
    SlidingTabLayout liveSlidingTab;
    @BindView(R.id.live_viewpager)
    ViewPager liveViewpager;
    private String[] mTitles;
    private VideoAllListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mPresenter.getPresenterVideoCatelist();
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
        mPresenter.getPresenterVideoCatelist();
    }


    @Override
    public void getViewVideoAllCate(List<VideoCateList> cateLists) {
        /**
         *  默认数据
         */
        mTitles = new String[cateLists.size() + 1];
        mTitles[0] = "推荐";
//        mFragments.add(RecommendHomeFragment.getInstance());
        for (int i = 0; i < cateLists.size(); i++) {
            mTitles[i + 1] = cateLists.get(i).getCate1_name();
//            Bundle bundle=new Bundle();
//             bundle.putSerializable("homecatelist",cateLists.get(i));
//            mFragments.add(OtherHomeFragment.getInstance(bundle));
        }
//        不摧毁Fragment
        liveViewpager.setOffscreenPageLimit(mTitles.length);
        mAdapter = new VideoAllListAdapter(getChildFragmentManager(), cateLists, mTitles);
        liveViewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        liveSlidingTab.setViewPager(liveViewpager, mTitles);
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

}
