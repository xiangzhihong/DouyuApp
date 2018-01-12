package com.xzh.douyuapp.presenter.home.interfaces;

import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreTwoCate;

import java.util.List;

import rx.Observable;


public interface HomeColumnMoreListContract {
    interface View extends BaseView {
        void getViewHomeColumnMoreTwoCate(List<HomeColumnMoreTwoCate> mHomeColumnMoreTwoCate);

    }
    interface  Model extends BaseModel {

        Observable<List<HomeColumnMoreTwoCate>> getModelHomeColumnMoreTwoCate(Context context, String cate_id);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        /**
         *  获取栏目 更多 二级分类
         */
      public abstract void getPresenterHomeColumnMoreTwoCate(String cate_id);
    }
}
