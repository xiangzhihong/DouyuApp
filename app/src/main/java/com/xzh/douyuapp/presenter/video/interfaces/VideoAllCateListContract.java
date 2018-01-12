package com.xzh.douyuapp.presenter.video.interfaces;

import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.video.bean.VideoCateList;

import java.util.List;

import rx.Observable;



public interface VideoAllCateListContract {
    interface  View extends BaseView {
        void getViewVideoAllCate(List<VideoCateList> cateLists);
    }
    interface Model extends BaseModel {
        Observable<List<VideoCateList>> getModelVideoAllCate(Context context);

    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void  getPresenterVideoCatelist();
    }
}
