package com.xzh.douyuapp.api.me;



import com.xzh.douyuapp.model.logic.me.bean.PersonInfoBean;
import com.xzh.douyuapp.net.response.HttpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.xzh.douyuapp.api.NetWorkApi.getPersonInfo;


public interface MeApi {
    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getPersonInfo)
    Observable<HttpResponse<PersonInfoBean>> getPersonInfos(@QueryMap Map<String, String> params) ;
}
