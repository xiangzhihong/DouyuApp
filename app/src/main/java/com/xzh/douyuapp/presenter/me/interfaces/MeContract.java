package com.xzh.douyuapp.presenter.me.interfaces;

import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.me.bean.PersonInfoBean;

import rx.Observable;


public interface MeContract {
    interface View extends BaseView {
        void getViewPersonInfo(PersonInfoBean personInfoBean);
        void showLoginPopWindow();

    }
    interface Model extends BaseModel {
        Observable<PersonInfoBean> getModelPersonInfo(Context context, String userName, String passWord);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract  void Login();
    }
}
