package com.xzh.douyuapp.model.logic.live;

import android.content.Context;


import com.xzh.douyuapp.api.live.LiveApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherList;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.live.interfaces.LiveOtherColumnListContract;

import java.util.List;

import rx.Observable;

import static android.R.attr.offset;


public class LiveOtherColumnListModelLogic implements LiveOtherColumnListContract.Model {
    /**
     * 获取全部视频
     *
     * @param context
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Observable<List<LiveOtherList>> getModelLiveOtherColumnList(Context context, String cate_id, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveOtherList(cate_id, ParamsMapUtils.getHomeFaceScoreColumn(offset, limit))
//               进行预处理
                .compose(new DefaultTransformer<List<LiveOtherList>>());
    }
}
