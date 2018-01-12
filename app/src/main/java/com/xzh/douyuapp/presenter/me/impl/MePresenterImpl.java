package com.xzh.douyuapp.presenter.me.impl;


import com.xzh.douyuapp.presenter.me.interfaces.MeContract;

public class MePresenterImpl extends MeContract.Presenter{
    @Override
    public void Login() {
        mView.showLoginPopWindow();
    }
}
