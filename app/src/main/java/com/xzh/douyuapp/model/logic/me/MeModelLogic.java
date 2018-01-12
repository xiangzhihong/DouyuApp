package com.xzh.douyuapp.model.logic.me;

import android.content.Context;


import com.xzh.douyuapp.api.me.MeApi;
import com.xzh.douyuapp.model.ParamsMapUtils;
import com.xzh.douyuapp.model.logic.me.bean.PersonInfoBean;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.net.transformer.DefaultTransformer;
import com.xzh.douyuapp.presenter.me.interfaces.MeContract;

import java.util.List;

import rx.Observable;



public class MeModelLogic implements MeContract.Model {
    @Override
    public Observable<PersonInfoBean> getModelPersonInfo(Context context, String userName, String passWord) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(false)
                .getRetofitClinet()
                .builder(MeApi.class)
                .getPersonInfos(ParamsMapUtils.getPersonInfo(userName,passWord))
//               进行预处理
                .compose(new DefaultTransformer<PersonInfoBean>());
    }
}
