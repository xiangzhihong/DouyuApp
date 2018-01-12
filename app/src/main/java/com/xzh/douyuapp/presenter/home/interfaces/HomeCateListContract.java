package com.xzh.douyuapp.presenter.home.interfaces;


import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.home.bean.HomeCateList;

import java.util.List;

import rx.Observable;


public interface HomeCateListContract {
    interface View extends BaseView {
       void getHomeAllList(List<HomeCateList> cateLists);
    }
    interface  Model extends BaseModel {
        Observable getHomeCateList(Context context);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void  getHomeCateList();
    }
}
