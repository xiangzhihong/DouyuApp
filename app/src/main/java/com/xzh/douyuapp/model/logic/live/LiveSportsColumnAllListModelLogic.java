package com.xzh.douyuapp.model.logic.live;

import android.content.Context;


import com.xzh.douyuapp.api.live.LiveApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.live.bean.LiveSportsAllList;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.live.interfaces.LiveSportsColumnAllListContract;

import java.util.List;

import rx.Observable;


public class LiveSportsColumnAllListModelLogic implements LiveSportsColumnAllListContract.Model {

    /**
     *   获取全部视频
     * @param context
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Observable<List<LiveSportsAllList>> getModelLiveSportsColumnAllList(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveSportsAllList(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<LiveSportsAllList>>());
    }
}
