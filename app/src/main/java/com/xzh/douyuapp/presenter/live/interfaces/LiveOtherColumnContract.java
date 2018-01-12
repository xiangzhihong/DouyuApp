package com.xzh.douyuapp.presenter.live.interfaces;


import android.content.Context;
import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherColumn;

import java.util.List;

import rx.Observable;


public interface LiveOtherColumnContract {
      interface View extends BaseView {
         void   getViewLiveOtherColumn(List<LiveOtherColumn> mLiveOtherColumns);
      }
      interface Model extends BaseModel {
            Observable<List<LiveOtherColumn>> getModelLiveOtherColumn(Context context);
      }
      abstract class Presenter extends BasePresenter<View,Model> {
//            获取直播其他栏目分类
            public abstract void  getPresenterLiveOtherColumn();

      }

}
