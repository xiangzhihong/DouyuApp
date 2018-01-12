package com.xzh.douyuapp.model.logic.video;

import android.content.Context;


import com.xzh.douyuapp.api.video.VideoApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.video.bean.VideoCateList;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.video.interfaces.VideoAllCateListContract;

import java.util.List;

import rx.Observable;



public class VideoCateListLogic implements VideoAllCateListContract.Model {
    @Override
    public Observable<List<VideoCateList>> getModelVideoAllCate(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoCateList(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoCateList>>());

    }
}
