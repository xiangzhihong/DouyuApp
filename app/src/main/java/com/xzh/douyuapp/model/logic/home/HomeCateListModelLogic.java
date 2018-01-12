package com.xzh.douyuapp.model.logic.home;

import android.content.Context;


import com.xzh.douyuapp.api.home.HomeApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.home.bean.HomeCateList;
import com.xzh.douyuapp.net.http.HttpUtils;

import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.home.interfaces.HomeCateListContract;

import java.util.List;

import rx.Observable;

public class HomeCateListModelLogic implements HomeCateListContract.Model {

    @Override
    public Observable<List<HomeCateList>> getHomeCateList(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeCateList(ParamsMapUtils.getDefaultParams())
                .compose(new DefaultTransformer<List<HomeCateList>>());
    }
}
