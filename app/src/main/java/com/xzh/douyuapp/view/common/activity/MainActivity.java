package com.xzh.douyuapp.view.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;


import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.ui.NavigateTabBar;
import com.xzh.douyuapp.view.follow.fragment.FollowFragment;
import com.xzh.douyuapp.view.home.fragment.HomeFragment;
import com.xzh.douyuapp.view.live.fragment.LiveFragment;
import com.xzh.douyuapp.view.user.fragment.UserFragment;
import com.xzh.douyuapp.view.video.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends  AppCompatActivity implements BaseView {

    @BindView(R.id.mainTabBar)
    NavigateTabBar mNavigateTabBar;

    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_LIVE= "直播";
    private static final String TAG_PAGE_VIDEO = "视频";
    private static final String TAG_PAGE_FOLLOW = "关注";
    private static final String TAG_PAGE_USER = "我的";
    protected Unbinder unbinder;
    private long exitTime = 0; //退出时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);
        initView();
    }

    private void initView(){
        mNavigateTabBar.addTab(HomeFragment.class, new NavigateTabBar.TabParam(R.mipmap.home_pressed, R.mipmap
                .home_selected, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(LiveFragment.class, new NavigateTabBar.TabParam(R.mipmap.live_pressed, R.mipmap
                .live_selected, TAG_PAGE_LIVE));
        mNavigateTabBar.addTab(VideoFragment.class, new NavigateTabBar.TabParam(R.mipmap.video, R
                .mipmap.video_selected, TAG_PAGE_VIDEO));
        mNavigateTabBar.addTab(FollowFragment.class, new NavigateTabBar.TabParam(R.mipmap.follow_pressed,
                R.mipmap.follow_selected, TAG_PAGE_FOLLOW));
        mNavigateTabBar.addTab(UserFragment.class, new NavigateTabBar.TabParam(R.mipmap.user_pressed, R.mipmap
                .user_selected, TAG_PAGE_USER));
        mNavigateTabBar.setTabSelectListener(new NavigateTabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(NavigateTabBar.ViewHolder holder) {
                switch (holder.tag.toString()) {
                    //首页
                    case TAG_PAGE_HOME:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    //直播
                    case TAG_PAGE_LIVE:

                        mNavigateTabBar.showFragment(holder);
                        break;
                    //视频
                    case TAG_PAGE_VIDEO:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    //关注
                    case TAG_PAGE_FOLLOW:
                        mNavigateTabBar.showFragment(holder);
                        break;
                    //我的
                    case TAG_PAGE_USER:
                        if(mNavigateTabBar!=null)
                            mNavigateTabBar.showFragment(holder);
                        break;
                }
            }
        });
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }
}
