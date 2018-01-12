package com.xzh.douyuapp.presenter.live.interfaces;


import android.content.Context;

import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.live.bean.LiveSportsAllList;

import java.util.List;

import rx.Observable;


public interface LiveSportsColumnAllListContract {
      interface View extends BaseView {
          void getViewLiveSportsColumnAllListColumn(List<LiveSportsAllList> mLiveAllList);
          void getViewLiveSportsColumnAllListLoadMore(List<LiveSportsAllList> mLiveAllList);
      }
      interface Model extends BaseModel {
            Observable<List<LiveSportsAllList>> getModelLiveSportsColumnAllList(Context context, int offset, int limit);
      }
      abstract class Presenter extends BasePresenter<View,Model> {
          //          刷新数据
          public abstract void getPresenterLiveSportsColumnAllList(int offset,int limit );
          //          加载更多
          public abstract  void  getPresenterLiveSportsColumnAllListLoadMore(int offset,int limit);

      }

}
