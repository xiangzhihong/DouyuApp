package com.xzh.douyuapp.model.logic.home;

import android.content.Context;


import com.xzh.douyuapp.api.home.HomeApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.home.bean.HomeRecommendHotCate;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.home.interfaces.HomeCateContract;

import java.util.List;

import rx.Observable;


public class HomeCateModelLogic  implements HomeCateContract.Model {

    @Override
    public Observable<List<HomeRecommendHotCate>> getHomeCate(Context context, String identification) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeCate(ParamsMapUtils.getHomeCate(identification))
                .compose(new DefaultTransformer<List<HomeRecommendHotCate>>());
    }
}
