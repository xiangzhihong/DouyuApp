package com.xzh.douyuapp.model.logic.home;

import android.content.Context;


import com.xzh.douyuapp.api.home.HomeApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreOtherList;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.home.interfaces.HomeColumnMoreOtherListContract;

import java.util.List;

import rx.Observable;


public class HomeColumnMoreOtherListModelLogic implements HomeColumnMoreOtherListContract.Model {

    /**
     * 全部直播列表
     *
     * @param context
     * @param cate_id
     * @return
     */
    @Override
    public Observable<List<HomeColumnMoreOtherList>> getModelHomeColumnMoreOtherList(Context context, String cate_id, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreOtherList(ParamsMapUtils.getHomeColumnMoreOtherList(cate_id, offset, limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreOtherList>>());
    }
}
