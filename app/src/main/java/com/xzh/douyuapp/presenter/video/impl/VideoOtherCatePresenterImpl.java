package com.xzh.douyuapp.presenter.video.impl;



import com.xzh.douyuapp.model.logic.video.bean.VideoReClassify;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.video.interfaces.VideoOtherCateContract;

import java.util.List;



public class VideoOtherCatePresenterImpl extends VideoOtherCateContract.Presenter{
    @Override
    public void getPresenterVideoOtherCate(String cid) {
        addSubscribe(mModel.getModelVideoAllCate(mContext,cid).subscribe(new RxSubscriber<List<VideoReClassify>>() {
            @Override
            public void onSuccess(List<VideoReClassify> videoCateListList) {
                mView.getViewVideoOtherCate(videoCateListList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
