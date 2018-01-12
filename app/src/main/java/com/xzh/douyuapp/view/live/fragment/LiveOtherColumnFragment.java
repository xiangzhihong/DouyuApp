package com.xzh.douyuapp.view.live.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseFragment;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.live.LiveOtherTwoColumnModelLogic;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherColumn;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherTwoColumn;
import com.xzh.douyuapp.presenter.live.impl.LiveOtherTwoColumnPresenterImp;
import com.xzh.douyuapp.presenter.live.interfaces.LiveOtherTwoColumnContract;
import com.xzh.douyuapp.ui.popup.PopupLiveList;
import com.xzh.douyuapp.view.live.adapter.LiveOtherTwoCloumnAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.xzh.douyuapp.R.id.img_popup_live;


public class LiveOtherColumnFragment extends BaseFragment<LiveOtherTwoColumnModelLogic, LiveOtherTwoColumnPresenterImp> implements LiveOtherTwoColumnContract.View {
    private static List<LiveOtherColumnFragment> mLiveOtherColumnFragment = new ArrayList<LiveOtherColumnFragment>();
    @BindView(R.id.twocolumn_tablayout)
    SlidingTabLayout twocolumnTablayout;
    @BindView(R.id.livetwocolumn_viewpager)
    ViewPager livetwocolumnViewpager;
    @BindView(R.id.rl_twocolumn_bar)
    RelativeLayout rlTwocolumnBar;
    @BindView(img_popup_live)
    ImageView imgPopupLive;
    private LiveOtherColumn mLiveOtherColumn;
    private LiveOtherTwoCloumnAdapter mLiveOtherTwoColumnAdapter;
    private String[] mTilte;

    private PopupLiveList mPopupLiveList;

    private List<LiveOtherTwoColumn> mLiveOtherTwoColumn;

    public static LiveOtherColumnFragment getInstance(LiveOtherColumn mLiveOtherColumn, int position) {
        LiveOtherColumnFragment rf = new LiveOtherColumnFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mLiveOtherColumn", mLiveOtherColumn);
        bundle.putInt("position", position);
        mLiveOtherColumnFragment.add(position, rf);
        rf.setArguments(bundle);
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_othercolumn;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mLiveOtherTwoColumn = new ArrayList<LiveOtherTwoColumn>();
//        imgPopupLive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mLiveOtherTwoColumn != null) {
//                    Toast.makeText(getContext(), "直播", Toast.LENGTH_LONG).show();
//                    PopupLiveList liveList = new PopupLiveList(getActivity());
//                    liveList.showPopupWindow();
////            PopupLiveList.Builder builder = new PopupLiveList.Builder(getActivity());
////            builder.addList(mLiveOtherTwoColumn);
////            mPopupLiveList = builder.build();
////            mPopupLiveList.showPopupWindow();
////            mPopupLiveList.setOnListPopupItemClickListener(what -> {
////
////
//            });
//                }
//            }
//        });
    }

    @Override
    protected void onEvent() {

    }
//
//    /**
//     * 弹框 筛选列表
//     */
//    @OnClick(img_popup_live)
//    public void popupLiveList() {
//        if (mLiveOtherTwoColumn != null) {
//            Toast.makeText(getContext(), "直播", Toast.LENGTH_LONG).show();
//            PopupLiveList liveList = new PopupLiveList(getActivity());
//            liveList.showPopupWindow();
////            PopupLiveList.Builder builder = new PopupLiveList.Builder(getActivity());
////            builder.addList(mLiveOtherTwoColumn);
////            mPopupLiveList = builder.build();
////            mPopupLiveList.showPopupWindow();
////            mPopupLiveList.setOnListPopupItemClickListener(what -> {
////
////
////            });
//        }
//    }

    @Override
    protected BaseView getViewImp() {
        Bundle arguments = getArguments();
        return mLiveOtherColumnFragment.get(arguments.getInt("position"));
    }

    @Override
    protected void lazyFetchData() {
        Bundle arguments = getArguments();
        mLiveOtherColumn = (LiveOtherColumn) arguments.getSerializable("mLiveOtherColumn");
        mPresenter.getPresenterLiveOtherTwoColumn(mLiveOtherColumn.getShort_name());

    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    /**
     * 获取二级分类 栏目
     *
     * @param mLiveOtherTwoCloumn
     */
    @Override
    public void getViewLiveOtherTwoColumn(List<LiveOtherTwoColumn> mLiveOtherTwoCloumn) {
        mTilte = new String[mLiveOtherTwoCloumn.size()];
        for (int i = 0; i < mLiveOtherTwoCloumn.size(); i++) {
            mTilte[i] = mLiveOtherTwoCloumn.get(i).getTag_name();
        }
        if (mTilte.length <= 1) {
            rlTwocolumnBar.setVisibility(View.GONE);
        }
        this.mLiveOtherTwoColumn.clear();
        this.mLiveOtherTwoColumn.addAll(mLiveOtherTwoCloumn);

        livetwocolumnViewpager.setOffscreenPageLimit(mTilte.length);
        mLiveOtherTwoColumnAdapter = new LiveOtherTwoCloumnAdapter(getChildFragmentManager(), mLiveOtherTwoCloumn, mTilte);
        livetwocolumnViewpager.setAdapter(mLiveOtherTwoColumnAdapter);
        mLiveOtherTwoColumnAdapter.notifyDataSetChanged();
        twocolumnTablayout.setViewPager(livetwocolumnViewpager, mTilte);
    }
}
