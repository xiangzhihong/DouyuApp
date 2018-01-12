package com.xzh.douyuapp.presenter.home.interfaces;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;

public interface HomeContract {

      interface View extends BaseView {
           void getMessge(String msg);
      }

    interface  Model extends BaseModel {

    }

      abstract class Presenter extends BasePresenter<View,Model> {
          /**
           *  提示消息
           */
          public   abstract   void message(String msg);

          public  abstract  void columnDetail();
      }


}
