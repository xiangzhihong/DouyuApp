package com.xzh.douyuapp.presenter.video.impl;


import com.xzh.douyuapp.model.logic.video.bean.VideoHotAuthorColumn;
import com.xzh.douyuapp.model.logic.video.bean.VideoHotColumn;
import com.xzh.douyuapp.model.logic.video.bean.VideoRecommendHotCate;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.video.interfaces.VideoRerecommendContract;

import java.util.List;


public class VideoRecommendPresenterImp extends VideoRerecommendContract.Presenter{
    @Override
    public void getPresenterVideoHotColumn() {
        addSubscribe(mModel.getModelVideoHotColumn(mContext).subscribe(new RxSubscriber<List<VideoHotColumn>>() {
            @Override
            public void onSuccess(List<VideoHotColumn> mVideoHotColumn) {
                mView.getViewHotColumn(mVideoHotColumn);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterVideoHotAutherColumn(int offset, int limit) {
        addSubscribe(mModel.getModelVideoHotAuthorColumn(mContext, offset,limit).subscribe(new RxSubscriber<List<VideoHotAuthorColumn>>() {
            @Override
            public void onSuccess(List<VideoHotAuthorColumn> videoHotAuthorColumns) {
                mView.getViewHotAutherColumn(videoHotAuthorColumns);
            }

            @Override
            protected void onError(ResponeThrowable ex) {

                mView.showErrorWithStatus(ex.message);
            }

        }));

    }
    @Override
    public void getPresenterVideoHotCate() {
        addSubscribe(mModel.getModelVideoHotCate(mContext).subscribe(new RxSubscriber<List<VideoRecommendHotCate>>() {
            @Override
            public void onSuccess(List<VideoRecommendHotCate> videoRecommendHotCates) {
                mView.getViewHotCate(videoRecommendHotCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }
}
