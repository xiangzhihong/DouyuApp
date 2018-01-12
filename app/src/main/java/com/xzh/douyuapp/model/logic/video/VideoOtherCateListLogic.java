package com.xzh.douyuapp.model.logic.video;

import android.content.Context;


import com.xzh.douyuapp.api.video.VideoApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.video.bean.VideoReClassify;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.video.interfaces.VideoOtherCateContract;

import java.util.List;

import rx.Observable;



public class VideoOtherCateListLogic implements VideoOtherCateContract.Model{
    @Override
    public Observable<List<VideoReClassify>> getModelVideoAllCate(Context context , String  cid) {

        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoReCateList(ParamsMapUtils.getVideoOtherTwoColumn(cid))

//               进行预处理
                .compose(new DefaultTransformer<List<VideoReClassify>>());
    }
}
