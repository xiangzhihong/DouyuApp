package com.xzh.douyuapp.model.logic.video;

import android.content.Context;


import com.xzh.douyuapp.api.video.VideoApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.video.bean.VideoOtherColumnList;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.video.interfaces.VideoOtherTwoColumnContract;

import java.util.List;

import rx.Observable;


public class VideoTwoColumnModelLogic implements VideoOtherTwoColumnContract.Model{
    @Override
    public Observable<List<VideoOtherColumnList>> getModelVideoOtherTwoColumn(Context context, String  cid1, String cid2, int offset, int limit, String action ) {
        return HttpUtils.getInstance(context)

                .setLoadDiskCache(true)
                .getRetofitClinet()
                .setBaseUrl("http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoOtherColumnList(ParamsMapUtils.getVideoOtherList(cid1,cid2,offset,limit,action))
                .compose(new DefaultTransformer<List<VideoOtherColumnList>>());
    }
}
