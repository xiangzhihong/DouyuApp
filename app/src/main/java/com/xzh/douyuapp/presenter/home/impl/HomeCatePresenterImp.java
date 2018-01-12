package com.xzh.douyuapp.presenter.home.impl;



import com.xzh.douyuapp.model.logic.home.bean.HomeRecommendHotCate;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.home.interfaces.HomeCateContract;

import java.util.List;


public class HomeCatePresenterImp extends HomeCateContract.Presenter {
    /**
     * 导航栏+栏目列表
     *
     * @param identification
     */
    @Override
    public void getHomeCate(String identification) {
        addSubscribe(mModel.getHomeCate(mContext, identification).subscribe(new RxSubscriber<List<HomeRecommendHotCate>>() {
            @Override
            public void onSuccess(List<HomeRecommendHotCate> homeCates) {
                mView.getOtherList(homeCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    /**
     * 刷新
     * <p></p>
     * 导航栏+栏目列表
     *
     * @param identification
     */
    @Override
    public void getHomeCateRefresh(String identification) {
        addSubscribe(mModel.getHomeCate(mContext, identification).subscribe(new RxSubscriber<List<HomeRecommendHotCate>>() {
            @Override
            public void onSuccess(List<HomeRecommendHotCate> homeCates) {
                mView.getOtherListRefresh(homeCates);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
