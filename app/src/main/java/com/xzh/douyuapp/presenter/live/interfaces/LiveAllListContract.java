package com.xzh.douyuapp.presenter.live.interfaces;


import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.live.bean.LiveAllList;

import java.util.List;

import rx.Observable;


public interface LiveAllListContract {
      interface View extends BaseView {
          //        颜值栏目
          void getViewLiveAllListColumn(List<LiveAllList> mLiveAllList);
          void getViewLiveAllListLoadMore(List<LiveAllList> mLiveAllList);
      }
      interface Model extends BaseModel {
            Observable<List<LiveAllList>> getModelLiveAllList(Context context, int offset, int limit);
      }
      abstract class Presenter extends BasePresenter<View,Model> {
          //          刷新数据
          public abstract void getPresenterListAllList(int offset,int limit );
          //          加载更多
          public abstract  void  getPresenterListAllListLoadMore(int offset,int limit);

      }

}
