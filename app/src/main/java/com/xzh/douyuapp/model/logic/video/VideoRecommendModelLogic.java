package com.xzh.douyuapp.model.logic.video;

import android.content.Context;


import com.xzh.douyuapp.api.video.VideoApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.video.bean.VideoHotAuthorColumn;
import com.xzh.douyuapp.model.logic.video.bean.VideoHotColumn;
import com.xzh.douyuapp.model.logic.video.bean.VideoRecommendHotCate;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.video.interfaces.VideoRerecommendContract;

import java.util.List;

import rx.Observable;



public class VideoRecommendModelLogic implements VideoRerecommendContract.Model {
    @Override
    public Observable<List<VideoHotColumn>> getModelVideoHotColumn(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl("http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoHotColumn(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoHotColumn>>());

    }

    @Override
    public Observable<List<VideoHotAuthorColumn>> getModelVideoHotAuthorColumn(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoHotAuther(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoHotAuthorColumn>>());
    }

    @Override
    public Observable<List<VideoRecommendHotCate>> getModelVideoHotCate(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoHotCate(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoRecommendHotCate>>());
    }
}
