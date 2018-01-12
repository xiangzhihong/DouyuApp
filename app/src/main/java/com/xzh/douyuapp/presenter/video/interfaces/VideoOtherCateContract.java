package com.xzh.douyuapp.presenter.video.interfaces;

import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.video.bean.VideoReClassify;

import java.util.List;

import rx.Observable;


public interface VideoOtherCateContract {
    interface View extends BaseView {
        void getViewVideoOtherCate(List<VideoReClassify> cateLists);
    }

    interface Model extends BaseModel {
        Observable<List<VideoReClassify>> getModelVideoAllCate(Context context, String cId);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getPresenterVideoOtherCate(String cid);

    }
}
