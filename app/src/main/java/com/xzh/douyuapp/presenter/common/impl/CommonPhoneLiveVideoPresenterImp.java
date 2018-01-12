package com.xzh.douyuapp.presenter.common.impl;


import android.util.Log;

import com.google.gson.Gson;
import com.xzh.douyuapp.model.logic.common.bean.OldLiveVideoInfo;
import com.xzh.douyuapp.presenter.common.interfaces.CommonPhoneLiveVideoContract;
import com.zhy.autolayout.utils.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class CommonPhoneLiveVideoPresenterImp extends CommonPhoneLiveVideoContract.Presenter {
    @Override
    public void getPresenterPhoneLiveVideoInfo(String room_id) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        client.newCall(mModel.getModelPhoneLiveVideoInfo(mContext, room_id)).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("error",e.getMessage()+"---");
                L.e("错误信息:"+e.getMessage());
                mView.showErrorWithStatus(e.getMessage());
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                Log.e("onResponse",response.body().string());
                String json =response.body().string().toString();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("error")==0) {
                        Gson gson = new Gson();
                        OldLiveVideoInfo mLiveVideoInfo = gson.fromJson(json, OldLiveVideoInfo.class);
                        mView.getViewPhoneLiveVideoInfo(mLiveVideoInfo);
                    } else {
                        mView.showErrorWithStatus("获取数据失败!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
