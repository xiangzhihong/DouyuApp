package com.xzh.douyuapp.view.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseFragment;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.home.HomeCateModelLogic;
import com.xzh.douyuapp.model.logic.home.bean.HomeCateList;
import com.xzh.douyuapp.model.logic.home.bean.HomeRecommendHotCate;
import com.xzh.douyuapp.presenter.home.impl.HomeCatePresenterImp;
import com.xzh.douyuapp.presenter.home.interfaces.HomeCateContract;
import com.xzh.douyuapp.ui.refreshview.XRefreshView;
import com.xzh.douyuapp.view.home.adapter.HomeNgBarAdapter;
import com.xzh.douyuapp.view.home.adapter.HomeNgBarViewPagerAdapter;
import com.xzh.douyuapp.view.home.adapter.HomeOtherAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class OtherHomeFragment extends BaseFragment<HomeCateModelLogic, HomeCatePresenterImp> implements HomeCateContract.View, ViewPager.OnPageChangeListener {

    private ImageView[] mIvpoints;
    private View haderView;//添加HaderView
    ViewPager ngbarViewpager;

    private HomeCateList mHomeCate;

    @BindView(R.id.other_content_recyclerview)
    RecyclerView other_content_recyclerview;
    @BindView(R.id.rtefresh_content)
    XRefreshView rtefreshContent;

    private HomeOtherAdapter adapter;

    private static List<OtherHomeFragment> mOtherHomeFraments = new ArrayList<OtherHomeFragment>();

    private HomeNgBarViewPagerAdapter homeNgBarViewPagerAdapter;
    private HomeNgBarAdapter homeNgBarAdapter;

    public static OtherHomeFragment getInstance(HomeCateList args) {
        OtherHomeFragment mInstance = new OtherHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("homecatelist", args);
        bundle.putString("type", args.getShow_order());
//        bundle.putInt("position", position);
        mInstance.setArguments(bundle);
//        if (!mInstance.isAdded())
        mOtherHomeFraments.add(mInstance);
        return mInstance;
    }

    public static OtherHomeFragment getInstance(Bundle bundle) {
        OtherHomeFragment mInstance = new OtherHomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("homecatelist", args);
//        bundle.putString("type", args.getShow_order());
//        bundle.putInt("position", position - 1);
        mInstance.setArguments(bundle);
        mOtherHomeFraments.add(bundle.getInt("position"), mInstance);
        return mInstance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_otherlist;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        refresh();
        setXrefeshViewConfig();

    }

    @Override
    protected void onEvent() {
        if (rtefreshContent==null)return;
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bundle arguments = getArguments();
                        mHomeCate = (HomeCateList) arguments.getSerializable("homecatelist");
                        String show_order = arguments.getString("type");
                        if (show_order.equals(mHomeCate.getShow_order())) {
                            mPresenter.getHomeCateRefresh(mHomeCate.getIdentification());
                        }
                    }
                }, 500);
            }
        });
    }

    private void refresh() {
        Bundle arguments = getArguments();
        mHomeCate = (HomeCateList) arguments.getSerializable("homecatelist");
        String show_order = arguments.getString("type");
        if (show_order.equals(mHomeCate.getShow_order())) {
            mPresenter.getHomeCate(mHomeCate.getIdentification());
        }
    }

    @Override
    protected BaseView getViewImp() {
        Bundle arguments = getArguments();
        return mOtherHomeFraments.get(arguments.getInt("position"));
//        return this;
    }

    final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }

        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            final RecyclerView.ViewHolder recycledView = super.getRecycledView(viewType);
            return recycledView;
        }
    };

    /**
     * 配置XRefreshView
     */
    protected void setXrefeshViewConfig() {
        rtefreshContent.setPinnedTime(2000);
        rtefreshContent.setPullLoadEnable(false);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setMoveForHorizontal(true);
        rtefreshContent.setPinnedContent(true);
    }

    /**
     * 进行懒加载   只进行加载一次
     */
    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void getOtherList(List<HomeRecommendHotCate> homeCates) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        getOtherColumnView(homeCates);
        getNgBarView(homeCates);
    }

    @Override
    public void getOtherListRefresh(List<HomeRecommendHotCate> homeCates) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        List<HomeRecommendHotCate> homeRecommendHotCates = new ArrayList<HomeRecommendHotCate>();
        homeRecommendHotCates.addAll(homeCates);
        for (int i = 0; i < homeRecommendHotCates.size(); i++) {
            if (homeRecommendHotCates.get(i).getRoom_list().size() < 4) {
                homeRecommendHotCates.remove(i);
            }
        }
        if (adapter != null) {
            adapter.getAllColumn(homeCates);
        }
    }

    private void getOtherColumnView(List<HomeRecommendHotCate> homeCates) {
        List<HomeRecommendHotCate> homeRecommendHotCates = new ArrayList<HomeRecommendHotCate>();
        homeRecommendHotCates.addAll(homeCates);
        for (int i = 0; i < homeRecommendHotCates.size(); i++) {
            if (homeRecommendHotCates.get(i).getRoom_list().size() < 4) {
                homeRecommendHotCates.remove(i);
            }
        }
        /**
         *  栏目 列表
         */
        adapter = new HomeOtherAdapter(getContext(), homeRecommendHotCates);
        other_content_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        pool.setMaxRecycledViews(adapter.getItemViewType(0), 500);
        other_content_recyclerview.setRecycledViewPool(pool);
        other_content_recyclerview.setAdapter(adapter);
    }

    public void getNgBarView(List<HomeRecommendHotCate> homeCates) {

        int mTotalPage;
        int mPageSize = 8;
       // GridView 作为一个View对象添加到ViewPager集合中
        List<View> mViewPageList;
        int mCurrentPage;
        //小圆点
        ViewGroup mPoints;

        //导航栏
        haderView = adapter.setHeaderView(R.layout.view_viewpager, other_content_recyclerview);
        ngbarViewpager = (ViewPager) haderView.findViewById(R.id.ngbar_viewpager);
        Bundle arguments = getArguments();
        ngbarViewpager.removeOnPageChangeListener(mOtherHomeFraments.get(arguments.getInt("position")));
        ngbarViewpager.addOnPageChangeListener(mOtherHomeFraments.get(arguments.getInt("position")));
        mPoints = (ViewGroup) haderView.findViewById(R.id.points);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        显示总的页数  Math.ceil 先上取整
        mTotalPage = (int) Math.ceil(homeCates.size() * 1.0 / mPageSize);
        mViewPageList = new ArrayList<>();
//        移除最热栏目
        homeCates.remove(0);
        /**
         *  创建 多个GredView
         */
        for (int i = 0; i < mTotalPage; i++) {
            if (i <= 1) {
                GridView gridView = (GridView) inflater.inflate(R.layout.view_layout_gridview, null);
                homeNgBarAdapter = new HomeNgBarAdapter(getContext(), homeCates, i, mPageSize);
                gridView.setAdapter(homeNgBarAdapter);
                homeNgBarAdapter.notifyDataSetChanged();
//            每一个GridView 作为一个View 对象添加到ViewPage集合中
                mViewPageList.add(gridView);
            }
        }
        homeNgBarViewPagerAdapter = new HomeNgBarViewPagerAdapter(mViewPageList);
        ngbarViewpager.setAdapter(homeNgBarViewPagerAdapter);
        homeNgBarViewPagerAdapter.notifyDataSetChanged();

        /**
         *  处理小圆点 指示器
         */
//        创建小圆点
        mIvpoints = null;
        mIvpoints = new ImageView[2];
        for (int i = 0; i < mIvpoints.length; i++) {
            if (i <= 1) {
                ImageView imgView = new ImageView(getActivity());
//            设置小圆点宽和高
                imgView.setLayoutParams(new ViewGroup.LayoutParams(5, 5));
//            默认设置
                if (i == 0) {
                    imgView.setBackgroundResource(R.mipmap.page__selected_indicator);
                } else {
                    imgView.setBackgroundResource(R.mipmap.page__normal_indicator);
                }
                mIvpoints[i] = imgView;
//            设置边距
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT));
                layoutParams.leftMargin = 10;
                layoutParams.rightMargin = 10;
                mPoints.addView(imgView, layoutParams);
            }
        }
        if (mTotalPage == 1) {
            mPoints.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mIvpoints.length; i++) {
            if (i == position) {
                mIvpoints[i].setBackgroundResource(R.mipmap.page__selected_indicator);
            } else {
                mIvpoints[i].setBackgroundResource(R.mipmap.page__normal_indicator);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
