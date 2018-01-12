package com.xzh.douyuapp.presenter.live.impl;


import com.xzh.douyuapp.model.logic.live.bean.LiveOtherColumn;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.live.interfaces.LiveOtherColumnContract;

import java.util.List;


public class LiveOtherColumnPresenterImp extends LiveOtherColumnContract.Presenter {

    @Override
    public void getPresenterLiveOtherColumn() {
        addSubscribe(mModel.getModelLiveOtherColumn(mContext).subscribe(new RxSubscriber<List<LiveOtherColumn>>() {
            @Override
            public void onSuccess(List<LiveOtherColumn> mLiveOtherColumns) {
                mView.getViewLiveOtherColumn(mLiveOtherColumns);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
