package com.xzh.douyuapp.presenter.live.impl;



import com.xzh.douyuapp.model.logic.live.bean.LiveAllList;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.live.interfaces.LiveAllListContract;

import java.util.List;


public class LiveAllListPresenterImp extends LiveAllListContract.Presenter {
//     刷新数据
    @Override
    public void getPresenterListAllList(int offset, int limit) {
        addSubscribe(mModel.getModelLiveAllList(mContext,offset,limit).subscribe(new RxSubscriber<List<LiveAllList>>() {
            @Override
            public void onSuccess(List<LiveAllList> mLiveAllList) {
                mView.getViewLiveAllListColumn(mLiveAllList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
//加载更多
    @Override
    public void getPresenterListAllListLoadMore(int offset, int limit) {
        addSubscribe(mModel.getModelLiveAllList(mContext,offset,limit).subscribe(new RxSubscriber<List<LiveAllList>>() {
            @Override
            public void onSuccess(List<LiveAllList> mLiveAllList) {
                mView.getViewLiveAllListLoadMore(mLiveAllList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
