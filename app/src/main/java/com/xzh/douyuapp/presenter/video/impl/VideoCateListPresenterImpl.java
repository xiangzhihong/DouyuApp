package com.xzh.douyuapp.presenter.video.impl;




import com.xzh.douyuapp.model.logic.video.bean.VideoCateList;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.video.interfaces.VideoAllCateListContract;

import java.util.List;



public class VideoCateListPresenterImpl extends VideoAllCateListContract.Presenter {

    @Override
    public void getPresenterVideoCatelist() {
        addSubscribe(mModel.getModelVideoAllCate(mContext).subscribe(new RxSubscriber<List<VideoCateList>>() {
            @Override
            public void onSuccess(List<VideoCateList> mVideoCateList) {
                mView.getViewVideoAllCate(mVideoCateList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
