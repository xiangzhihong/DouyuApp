package com.xzh.douyuapp.presenter.live.impl;



import com.xzh.douyuapp.model.logic.live.bean.LiveOtherTwoColumn;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.live.interfaces.LiveOtherTwoColumnContract;

import java.util.List;


public class LiveOtherTwoColumnPresenterImp extends LiveOtherTwoColumnContract.Presenter {

    @Override
    public void getPresenterLiveOtherTwoColumn(String mCloumnName) {
        addSubscribe(mModel.getModelLiveOtherTwoColumn(mContext,mCloumnName).subscribe(new RxSubscriber<List<LiveOtherTwoColumn>>() {
            @Override
            public void onSuccess(List<LiveOtherTwoColumn> mLiveOtherTwoColumns) {
                mView.getViewLiveOtherTwoColumn(mLiveOtherTwoColumns);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
