package com.xzh.douyuapp.model.logic.live;

import android.content.Context;


import com.xzh.douyuapp.api.live.LiveApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherTwoColumn;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.live.interfaces.LiveOtherTwoColumnContract;

import java.util.List;

import rx.Observable;


public class LiveOtherTwoColumnModelLogic implements LiveOtherTwoColumnContract.Model{

    @Override
    public Observable<List<LiveOtherTwoColumn>> getModelLiveOtherTwoColumn(Context context, String mCloumnName) {
        return  HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveOtherTwoColumn(ParamsMapUtils.getLiveOtherTwoColumn(mCloumnName))
//               进行预处理
                .compose(new DefaultTransformer<List<LiveOtherTwoColumn>>());
    }
}
