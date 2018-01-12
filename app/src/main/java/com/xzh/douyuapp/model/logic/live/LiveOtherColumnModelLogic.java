package com.xzh.douyuapp.model.logic.live;

import android.content.Context;


import com.xzh.douyuapp.api.live.LiveApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherColumn;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.live.interfaces.LiveOtherColumnContract;

import java.util.List;

import rx.Observable;


public class LiveOtherColumnModelLogic implements LiveOtherColumnContract.Model{

    @Override
    public Observable<List<LiveOtherColumn>> getModelLiveOtherColumn(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveOtherColumn(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<LiveOtherColumn>>());
    }
}
