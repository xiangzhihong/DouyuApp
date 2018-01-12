package com.xzh.douyuapp.presenter.home.impl;



import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreOtherList;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.home.interfaces.HomeColumnMoreOtherListContract;

import java.util.List;


public class HomeColumnMoreOtherListPresenterImp extends HomeColumnMoreOtherListContract.Presenter {
    @Override
    public void getPresenterColumnMoreOtherList(String cate_id, int offset, int limint) {
        addSubscribe(mModel.getModelHomeColumnMoreOtherList(mContext, cate_id, offset, limint).subscribe(new RxSubscriber<List<HomeColumnMoreOtherList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList) {
                mView.getViewHomeColumnOtherList(mHomeColumnMoreOtherList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }

    @Override
    public void getPresenterColumnMoreOtherListLoadMore(String cate_id, int offset, int limit) {
        addSubscribe(mModel.getModelHomeColumnMoreOtherList(mContext, cate_id, offset, limit).subscribe(new RxSubscriber<List<HomeColumnMoreOtherList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreOtherList> mHomeColumnMoreAllList) {
                mView.getViewHomeColumnOtherListLoadMore(mHomeColumnMoreAllList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
