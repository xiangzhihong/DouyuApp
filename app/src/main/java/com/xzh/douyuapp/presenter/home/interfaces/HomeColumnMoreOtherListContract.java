package com.xzh.douyuapp.presenter.home.interfaces;

import android.content.Context;


import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreOtherList;

import java.util.List;

import rx.Observable;


public interface HomeColumnMoreOtherListContract {
    interface View extends BaseView {
        void getViewHomeColumnOtherList(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList);
        void getViewHomeColumnOtherListLoadMore(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList);
    }

    interface Model extends BaseModel {

        Observable<List<HomeColumnMoreOtherList>> getModelHomeColumnMoreOtherList(Context context, String cate_id, int offset, int limit);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 获取全部列表
         */
        public abstract void getPresenterColumnMoreOtherList(String cate_id, int offset, int limit);
        /**
         *  加载更多
         */
        public abstract  void getPresenterColumnMoreOtherListLoadMore(String cate_id,int offset,int limit);

    }
}
