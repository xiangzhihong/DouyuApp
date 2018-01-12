package com.xzh.douyuapp.presenter.home.interfaces;


import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.home.bean.HomeRecommendHotCate;

import java.util.List;

import rx.Observable;


public interface HomeCateContract {
    interface View extends BaseView {
       void getOtherList(List<HomeRecommendHotCate> homeCates);
        void getOtherListRefresh(List<HomeRecommendHotCate> homeCates);
    }
    interface  Model extends BaseModel {
        Observable<List<HomeRecommendHotCate>> getHomeCate(Context context, String identification);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void  getHomeCate(String identification);
        public  abstract  void getHomeCateRefresh(String identification);
    }
}
