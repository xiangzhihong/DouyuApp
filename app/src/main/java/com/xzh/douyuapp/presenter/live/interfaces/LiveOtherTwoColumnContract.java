package com.xzh.douyuapp.presenter.live.interfaces;


import android.content.Context;

import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherTwoColumn;

import java.util.List;

import rx.Observable;


public interface LiveOtherTwoColumnContract {
      interface View extends BaseView {

            void getViewLiveOtherTwoColumn(List<LiveOtherTwoColumn> mLiveOtherTwoCloumn);
      }
      interface Model extends BaseModel {
            Observable<List<LiveOtherTwoColumn>> getModelLiveOtherTwoColumn(Context context, String mCloumnName);
      }
      abstract class Presenter extends BasePresenter<View,Model> {

            public  abstract void getPresenterLiveOtherTwoColumn(String mCloumnName);

      }

}
