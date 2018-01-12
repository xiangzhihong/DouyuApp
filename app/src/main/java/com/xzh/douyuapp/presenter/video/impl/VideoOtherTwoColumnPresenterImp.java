package com.xzh.douyuapp.presenter.video.impl;



import com.xzh.douyuapp.model.logic.video.bean.VideoOtherColumnList;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.video.interfaces.VideoOtherTwoColumnContract;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


public class VideoOtherTwoColumnPresenterImp extends VideoOtherTwoColumnContract.Presenter {



    @Override
    public void getPresenterVideoOtherTwoColumn(String  cid1,String cid2,int offset,int limit,String action ) {
        addSubscribe(mModel.getModelVideoOtherTwoColumn(mContext,cid1,cid2,offset,limit,action).subscribe(new RxSubscriber<List<VideoOtherColumnList>>() {
            @Override
            public void onSuccess(List<VideoOtherColumnList> videoReClassifies) {
                mView.getViewOtherTwoColumn(videoReClassifies);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterLiveOtherColumnList(String cid1, String cid2, int offset, int limit, String action) {
        addSubscribe(mModel.getModelVideoOtherTwoColumn(mContext,cid1,cid2,offset,limit,action).subscribe(new RxSubscriber<List<VideoOtherColumnList>>() {
            @Override
            public void onSuccess(List<VideoOtherColumnList> videoReClassifies) {
                mView.getViewOtherTwoColumn(videoReClassifies);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    @Override
    public void getPresenterLiveOtherColumnListLoadMore(String cid1, String cid2, int offset, int limit, String action) {
        addSubscribe(mModel.getModelVideoOtherTwoColumn(mContext,cid1,cid2,offset,limit,action).subscribe(new RxSubscriber<List<VideoOtherColumnList>>() {
            @Override
            public void onSuccess(List<VideoOtherColumnList> videoReClassifies) {
                mView.getViewVideoOtherColumnListLoadMore(videoReClassifies);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
