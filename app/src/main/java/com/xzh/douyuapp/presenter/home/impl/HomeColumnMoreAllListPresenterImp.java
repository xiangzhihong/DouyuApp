package com.xzh.douyuapp.presenter.home.impl;



import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreAllList;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.home.interfaces.HomeColumnMoreAllListContract;

import java.util.List;


public class HomeColumnMoreAllListPresenterImp extends HomeColumnMoreAllListContract.Presenter {
    @Override
    public void getPresenterColumnMoreAllList(String cate_id, int offset, int limint) {
        addSubscribe(mModel.getModelHomeColumnMoreAllList(mContext, cate_id, offset, limint).subscribe(new RxSubscriber<List<HomeColumnMoreAllList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreAllList> mHomeColumnMoreAllList) {
                mView.getViewHomeColumnAllList(mHomeColumnMoreAllList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }

    @Override
    public void getPresenterColumnMoreAllListLoadMore(String cate_id, int offset, int limit) {
        addSubscribe(mModel.getModelHomeColumnMoreAllList(mContext, cate_id, offset, limit).subscribe(new RxSubscriber<List<HomeColumnMoreAllList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreAllList> mHomeColumnMoreAllList) {
                mView.getViewHomeColumnAllListLoadMore(mHomeColumnMoreAllList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
