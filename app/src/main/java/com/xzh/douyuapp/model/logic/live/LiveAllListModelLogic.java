package com.xzh.douyuapp.model.logic.live;

import android.content.Context;


import com.xzh.douyuapp.api.live.LiveApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.live.bean.LiveAllList;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.live.interfaces.LiveAllListContract;

import java.util.List;

import rx.Observable;


public class LiveAllListModelLogic implements LiveAllListContract.Model {

    /**
     *   获取全部视频
     * @param context
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Observable<List<LiveAllList>> getModelLiveAllList(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveAllList(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<LiveAllList>>());
    }
}
