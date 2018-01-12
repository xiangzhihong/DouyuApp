package com.xzh.douyuapp.presenter.live.interfaces;


import android.content.Context;
import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherList;
import java.util.List;
import rx.Observable;


public interface LiveOtherColumnListContract {
      interface View extends BaseView {
          void getViewLiveOtherColumnList(List<LiveOtherList> mLiveAllList);
          void getViewLiveOtherColumnListLoadMore(List<LiveOtherList> mLiveAllList);

          void getViewLiveFaceScoreColumnList(List<LiveOtherList> mLiveAllList);
          void getViewLiveFaceScoreColumnListLoadMore(List<LiveOtherList> mLiveAllList);
      }
      interface Model extends BaseModel {
            Observable<List<LiveOtherList>> getModelLiveOtherColumnList(Context context, String cate_id, int offset, int limit);
      }
      abstract class Presenter extends BasePresenter<View,Model> {
          //          刷新数据
          public abstract void getPresenterLiveOtherColumnList(String cate_id,int offset,int limit );
          //          加载更多
          public abstract  void  getPresenterLiveOtherColumnListLoadMore(String cate_id,int offset,int limit);

//          颜值列表
          public abstract  void getPresenterLiveFaceScoreColumnList(String cate_id,int offset,int limit);
          public abstract  void getPresenterLiveFaceScoreColumnListLoadMore(String cate_id,int offset,int limit);

      }

}
