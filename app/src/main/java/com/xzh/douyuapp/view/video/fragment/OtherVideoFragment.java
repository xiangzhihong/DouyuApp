package com.xzh.douyuapp.view.video.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseFragment;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.video.VideoOtherCateListLogic;
import com.xzh.douyuapp.model.logic.video.bean.VideoCateList;
import com.xzh.douyuapp.model.logic.video.bean.VideoReClassify;
import com.xzh.douyuapp.presenter.video.impl.VideoOtherCatePresenterImpl;
import com.xzh.douyuapp.presenter.video.interfaces.VideoOtherCateContract;
import com.xzh.douyuapp.view.video.adapter.VideoReClassifyListAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;



public class OtherVideoFragment extends BaseFragment<VideoOtherCateListLogic, VideoOtherCatePresenterImpl> implements VideoOtherCateContract.View {
    @BindView(R.id.twocolumn_tablayout)
    SlidingTabLayout liveSlidingTab;
    @BindView(R.id.live_viewpager)
    ViewPager liveViewpager;
    private String[] mTitles;
    private VideoReClassifyListAdapter mAdapter;
    private static List<OtherVideoFragment> mVideoOtherColumnFragment = new ArrayList<OtherVideoFragment>();

    private VideoCateList mVideoOtherColumn;

    public static OtherVideoFragment getInstance(VideoCateList videoCateList, int position) {

        OtherVideoFragment rf = new OtherVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mVideoCateList", videoCateList);
        bundle.putInt("position", position);
        mVideoOtherColumnFragment.add(position, rf);
        rf.setArguments(bundle);
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_other;
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

        Bundle arguments = getArguments();
        mVideoOtherColumn = (VideoCateList) arguments.getSerializable("mVideoCateList");
        mPresenter.getPresenterVideoOtherCate(mVideoOtherColumn.getCid1());
    }

    @Override
    public void getViewVideoOtherCate(List<VideoReClassify> cateLists) {
        /**
         *  默认数据
         */
        mTitles = new String[cateLists.size()];

//        mFragments.add(RecommendHomeFragment.getInstance());
        for (int i=0;i<cateLists.size();i++)
        {
            mTitles[i]=cateLists.get(i).getCate2_name();
//            Bundle bundle=new Bundle();
//             bundle.putSerializable("homecatelist",cateLists.get(i));
//            mFragments.add(OtherHomeFragment.getInstance(bundle));
        }
        if (mTitles.length <= 1) {
            liveSlidingTab.setVisibility(View.GONE);
        }
//        不摧毁Fragment
        liveViewpager.setOffscreenPageLimit(mTitles.length);
        mAdapter = new VideoReClassifyListAdapter(getChildFragmentManager(),cateLists,mTitles);
        liveViewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        liveSlidingTab.setViewPager(liveViewpager,mTitles);
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

}
