package com.xzh.douyuapp.presenter.live.impl;


import com.xzh.douyuapp.model.logic.live.bean.LiveSportsAllList;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.live.interfaces.LiveSportsColumnAllListContract;

import java.util.List;


public class LiveSportsColumnAllListPresenterImp extends LiveSportsColumnAllListContract.Presenter {
//     刷新数据
    @Override
    public void getPresenterLiveSportsColumnAllList(int offset, int limit) {
        addSubscribe(mModel.getModelLiveSportsColumnAllList(mContext,offset,limit).subscribe(new RxSubscriber<List<LiveSportsAllList>>() {
            @Override
            public void onSuccess(List<LiveSportsAllList> mLiveAllList) {
                mView.getViewLiveSportsColumnAllListColumn(mLiveAllList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
//加载更多
    @Override
    public void getPresenterLiveSportsColumnAllListLoadMore(int offset, int limit) {
        addSubscribe(mModel.getModelLiveSportsColumnAllList(mContext,offset,limit).subscribe(new RxSubscriber<List<LiveSportsAllList>>() {
            @Override
            public void onSuccess(List<LiveSportsAllList> mLiveAllList) {
                mView.getViewLiveSportsColumnAllListLoadMore(mLiveAllList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
