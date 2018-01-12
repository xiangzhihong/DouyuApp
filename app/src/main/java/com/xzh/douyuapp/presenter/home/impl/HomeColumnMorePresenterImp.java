package com.xzh.douyuapp.presenter.home.impl;



import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreTwoCate;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.home.interfaces.HomeColumnMoreListContract;

import java.util.List;


public class HomeColumnMorePresenterImp extends HomeColumnMoreListContract.Presenter {
    @Override
    public void getPresenterHomeColumnMoreTwoCate(String cate_id) {
        addSubscribe(mModel.getModelHomeColumnMoreTwoCate(mContext, cate_id).subscribe(new RxSubscriber<List<HomeColumnMoreTwoCate>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreTwoCate> mHomeColumnMoreTwoCate) {
                mView.getViewHomeColumnMoreTwoCate(mHomeColumnMoreTwoCate);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }
}
