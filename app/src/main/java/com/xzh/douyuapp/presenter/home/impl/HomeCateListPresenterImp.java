package com.xzh.douyuapp.presenter.home.impl;



import com.xzh.douyuapp.model.logic.home.bean.HomeCateList;
import com.xzh.douyuapp.net.callback.RxSubscriber;
import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.xzh.douyuapp.presenter.home.interfaces.HomeCateListContract;

import java.util.List;


public class HomeCateListPresenterImp extends HomeCateListContract.Presenter {

    @Override
    public void getHomeCateList() {
             addSubscribe(mModel.getHomeCateList(mContext).subscribe(new RxSubscriber<List<HomeCateList>>() {
                 @Override
                 public void onSuccess(List<HomeCateList> homeCateListList) {
                    mView.getHomeAllList(homeCateListList);
                 }
                 @Override
                 protected void onError(ResponeThrowable ex) {
//                   mView.showErrorWithStatus(ex.message);
                 }
             }));
    }
}
