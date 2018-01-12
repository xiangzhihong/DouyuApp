package com.xzh.douyuapp.presenter.video.interfaces;


import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.video.bean.VideoOtherColumnList;

import java.util.List;

import rx.Observable;


public interface VideoOtherTwoColumnContract {
      interface View extends BaseView {
            void getViewVideoOtherColumnListLoadMore(List<VideoOtherColumnList> mVideoOtherColumnList);
            void getViewOtherTwoColumn(List<VideoOtherColumnList> mVideoReClassify);
      }
      interface Model extends BaseModel {
            Observable<List<VideoOtherColumnList>> getModelVideoOtherTwoColumn(Context context, String cid1, String cid2, int offset, int limit, String action);
      }
      abstract class Presenter extends BasePresenter<View,Model> {
//  http://apiv2.douyucdn.cn/video/Videoroomlist/getRecVideoList?cid1=1&cid2=5&offset=0&limit=20&action=hot&client_sys=android
            public  abstract void getPresenterVideoOtherTwoColumn(String  cid1,String cid2,int offset,int limit,String action );
            //          刷新数据
            public abstract void getPresenterLiveOtherColumnList(String  cid1,String cid2,int offset,int limit,String action  );
            //          加载更多
            public abstract  void  getPresenterLiveOtherColumnListLoadMore(String  cid1,String cid2,int offset,int limit,String action );
      }

}
