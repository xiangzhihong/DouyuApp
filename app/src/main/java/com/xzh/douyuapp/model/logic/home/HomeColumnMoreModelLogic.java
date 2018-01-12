package com.xzh.douyuapp.model.logic.home;

import android.content.Context;


import com.xzh.douyuapp.api.home.HomeApi;
import com.xzh.douyuapp.model.ParamsMapUtils;

import com.xzh.douyuapp.model.logic.home.bean.HomeColumnMoreTwoCate;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.home.interfaces.HomeColumnMoreListContract;

import java.util.List;

import rx.Observable;


public class HomeColumnMoreModelLogic implements HomeColumnMoreListContract.Model {

    /**
     *  栏目 更多 二级分类列表
     * @param context
     * @param cate_id
     * @return
     */
    @Override
    public Observable<List<HomeColumnMoreTwoCate>> getModelHomeColumnMoreTwoCate(Context context, String cate_id) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreCate(ParamsMapUtils.getColumnMoreCate(cate_id))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreTwoCate>>());
    }
}
