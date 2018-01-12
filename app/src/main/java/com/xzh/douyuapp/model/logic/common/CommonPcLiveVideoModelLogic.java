package com.xzh.douyuapp.model.logic.common;

import android.content.Context;


import com.xzh.douyuapp.api.NetWorkApi;
import com.xzh.douyuapp.presenter.common.interfaces.CommonPcLiveVideoContract;
import com.xzh.douyuapp.utils.MD5Util;

import okhttp3.Request;


public class CommonPcLiveVideoModelLogic implements CommonPcLiveVideoContract.Model {

    @Override
    public Request getModelPcLiveVideoInfo(Context context, String room_id) {
        /**
         * 房间加密处理
         */
        int time = (int)(System.currentTimeMillis() / 1000) ;
        String str = "lapi/live/thirdPart/getPlay/" + room_id + "?aid=pcclient&rate=0&time=" + time + "9TUk5fjjUjg9qIMH3sdnh";
        String auth = MD5Util.getToMd5Low32(str);
//        L.e("地址为:"+NetWorkApi.baseUrl + NetWorkApi.getLiveVideo + room_id+"?"+tempParams.toString());
        Request requestPost = new Request.Builder()
                .url(NetWorkApi.oldBaseUrl+NetWorkApi.getOldLiveVideo+ room_id + "?rate=0")
                .get()
                .addHeader("aid","pcclient")
                .addHeader("auth",auth)
                .addHeader("time",time+"")
                .build();
        return requestPost;
    }
}
