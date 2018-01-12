package com.xzh.douyuapp.model.logic.home;

import android.content.Context;


import com.xzh.douyuapp.api.home.HomeApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.home.bean.HomeFaceScoreColumn;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.home.interfaces.HomeFaceScoreContract;

import java.util.List;

import rx.Observable;


public class HomeFaceScoreModeLogic implements HomeFaceScoreContract.Model {

    @Override
    public Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getFaceScoreColumn(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeFaceScoreColumn>>());
    }
}
