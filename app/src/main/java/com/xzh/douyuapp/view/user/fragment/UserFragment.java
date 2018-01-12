package com.xzh.douyuapp.view.user.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;
import com.xzh.douyuapp.R;
import com.xzh.douyuapp.base.BaseFragment;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.me.MeModelLogic;
import com.xzh.douyuapp.model.logic.me.bean.PersonInfoBean;
import com.xzh.douyuapp.presenter.me.impl.MePresenterImpl;
import com.xzh.douyuapp.presenter.me.interfaces.MeContract;
import com.xzh.douyuapp.ui.popup.LoginPopWindow;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public  class UserFragment extends BaseFragment<MeModelLogic, MePresenterImpl> implements MeContract
        .View {

    @BindView(R.id.iv_avatar)
    SimpleDraweeView ivAvatar;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.rl_not_login)
    RelativeLayout rlNotLogin;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.rl_aready_login)
    RelativeLayout rlAreadyLogin;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.rl_history_watch)
    RelativeLayout rlHistoryWatch;
    @BindView(R.id.iv_icon1)
    ImageView ivIcon1;
    @BindView(R.id.rl_letters)
    RelativeLayout rlLetters;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    @BindView(R.id.rl_my_task)
    RelativeLayout rlMyTask;
    @BindView(R.id.iv_icon3)
    ImageView ivIcon3;
    @BindView(R.id.rl_chongzhi)
    RelativeLayout rlChongzhi;
    @BindView(R.id.iv_anchor)
    ImageView ivAnchor;
    @BindView(R.id.rl_anchor_recruit)
    RelativeLayout rlAnchorRecruit;
    @BindView(R.id.iv_my_video)
    ImageView ivMyVideo;
    @BindView(R.id.rl_my_video)
    RelativeLayout rlMyVideo;
    @BindView(R.id.iv_video_collection)
    ImageView ivVideoCollection;
    @BindView(R.id.rl_video_collection)
    RelativeLayout rlVideoCollection;
    @BindView(R.id.iv_my_account)
    ImageView ivMyAccount;
    @BindView(R.id.rl_my_account)
    RelativeLayout rlMyAccount;
    @BindView(R.id.iv_image_game)
    ImageView ivImageGame;
    @BindView(R.id.rl_game_center)
    RelativeLayout rlGameCenter;
    @BindView(R.id.iv_clock)
    ImageView ivClock;
    @BindView(R.id.rl_clock)
    RelativeLayout rlClock;
    private LoginPopWindow mLoginPopwindow;

    @Override
   public void getViewPersonInfo(PersonInfoBean personInfoBean) {

    }

    @Override
    public void showLoginPopWindow() {
//        mLoginPopwindow.showPopupWindow();
        if(mLoginPopwindow!=null)
        mLoginPopwindow.showPopupWindow();
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mLoginPopwindow=new LoginPopWindow(getActivity());
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btn_login)
    public void login() {
        mPresenter.Login();
    }

    @OnClick(R.id.btn_register)
    public void register(){
        mPresenter.Login();
    }


}
