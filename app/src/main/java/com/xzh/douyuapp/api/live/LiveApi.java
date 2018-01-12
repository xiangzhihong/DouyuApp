package com.xzh.douyuapp.api.live;



import com.xzh.douyuapp.model.logic.live.bean.LiveAllList;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherColumn;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherList;
import com.xzh.douyuapp.model.logic.live.bean.LiveOtherTwoColumn;
import com.xzh.douyuapp.model.logic.live.bean.LiveSportsAllList;
import com.xzh.douyuapp.net.response.HttpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.xzh.douyuapp.api.NetWorkApi.getLiveAllList;
import static com.xzh.douyuapp.api.NetWorkApi.getLiveOtherColumn;
import static com.xzh.douyuapp.api.NetWorkApi.getLiveOtherTwoColumn;
import static com.xzh.douyuapp.api.NetWorkApi.getLiveOtherTwoList;
import static com.xzh.douyuapp.api.NetWorkApi.getLiveSportsAllList;


public interface LiveApi {
    /**
     *  直播其他栏目分类
     * @return
     */
    @GET(getLiveOtherColumn)
    Observable<HttpResponse<List<LiveOtherColumn>>> getLiveOtherColumn(@QueryMap Map<String, String> params);

    /**
     *  全部直播
     * @return
     */
    @GET(getLiveAllList)
    Observable<HttpResponse<List<LiveAllList>>> getLiveAllList(@QueryMap Map<String, String> params);

    /**
     *  直播其他栏目二级分类
     * @return
     */
    @GET(getLiveOtherTwoColumn)
    Observable<HttpResponse<List<LiveOtherTwoColumn>>> getLiveOtherTwoColumn(@QueryMap Map<String, String> params);
    /**
     *  直播其他列表页
     * @return
     */
    @GET(getLiveOtherTwoList+"{cate_id}")
    Observable<HttpResponse<List<LiveOtherList>>> getLiveOtherList(@Path("cate_id") String cate_id, @QueryMap Map<String, String> params);
    /**
     *  体育直播
     * @return
     */
    @GET(getLiveSportsAllList)
    Observable<HttpResponse<List<LiveSportsAllList>>> getLiveSportsAllList(@QueryMap Map<String, String> params);

}
