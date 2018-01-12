package com.xzh.douyuapp.model.logic.home;

import android.content.Context;


import com.xzh.douyuapp.api.home.HomeApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreAllList;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.home.interfaces.HomeColumnMoreAllListContract;

import java.util.List;

import rx.Observable;


public class HomeColumnMoreAllListModelLogic implements HomeColumnMoreAllListContract.Model {

    /**
     *  全部直播列表
     * @param context
     * @param cate_id
     * @return
     */
    @Override
    public Observable<List<HomeColumnMoreAllList>> getModelHomeColumnMoreAllList(Context context, String cate_id, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreAllList(cate_id, ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreAllList>>());
    }
}
