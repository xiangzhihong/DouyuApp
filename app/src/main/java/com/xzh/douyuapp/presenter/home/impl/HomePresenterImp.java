package com.xzh.douyuapp.presenter.home.impl;


import com.xzh.douyuapp.presenter.home.interfaces.HomeContract;

public class HomePresenterImp extends HomeContract.Presenter {
    @Override
    public void message(String msg) {
          mView.getMessge(msg);
    }
    @Override
    public void columnDetail() {

    }


}
