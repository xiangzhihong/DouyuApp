package com.xzh.douyuapp.presenter.home.interfaces;


import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.home.bean.HomeFaceScoreColumn;

import java.util.List;

import rx.Observable;


public interface HomeFaceScoreContract {
      interface View extends BaseView {
          //        颜值栏目
          void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns);
          void getViewFaceScoreColumnLoadMore(List<HomeFaceScoreColumn> homeFaceScoreColumns);
      }
    interface  Model extends BaseModel {
        Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(Context context, int offset, int limit);
    }
      abstract class Presenter extends BasePresenter<View,Model> {
//          刷新数据
          public abstract void getPresenterFaceScoreColumn(int offset,int limit );
//          加载更多
          public abstract  void  getPresenterFaceScoreLoadMore(int offset,int limit);
      }


}
