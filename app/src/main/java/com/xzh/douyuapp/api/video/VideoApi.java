package com.xzh.douyuapp.api.video;



import com.xzh.douyuapp.model.logic.video.bean.VideoCateList;
import com.xzh.douyuapp.model.logic.video.bean.VideoHotAuthorColumn;
import com.xzh.douyuapp.model.logic.video.bean.VideoHotColumn;
import com.xzh.douyuapp.model.logic.video.bean.VideoOtherColumnList;
import com.xzh.douyuapp.model.logic.video.bean.VideoReClassify;
import com.xzh.douyuapp.model.logic.video.bean.VideoRecommendHotCate;
import com.xzh.douyuapp.net.response.HttpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.xzh.douyuapp.api.NetWorkApi.getVideoCateList;
import static com.xzh.douyuapp.api.NetWorkApi.getVideoHotAutherColumn;
import static com.xzh.douyuapp.api.NetWorkApi.getVideoHotColumn;
import static com.xzh.douyuapp.api.NetWorkApi.getVideoOtherList;
import static com.xzh.douyuapp.api.NetWorkApi.getVideoReCateList;
import static com.xzh.douyuapp.api.NetWorkApi.getVideoRecommendHotCate;


public interface VideoApi {


    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getVideoHotColumn)
    Observable<HttpResponse<List<VideoHotColumn>>> getVideoHotColumn(@QueryMap Map<String, String> params) ;


    /**
     *    推荐---颜值
     * @return
     */
    @GET(getVideoHotAutherColumn)
    Observable<HttpResponse<List<VideoHotAuthorColumn>>> getVideoHotAuther(@QueryMap Map<String, String> params);

    /**
     *    推荐---热门 种类
     * @return
     */
    @GET(getVideoRecommendHotCate)
    Observable<HttpResponse<List<VideoRecommendHotCate>>> getVideoHotCate(@QueryMap Map<String, String> params);

    /**
     *    推荐---全部分类
     * @return
     */
    @GET(getVideoCateList)
    Observable<HttpResponse<List<VideoCateList>>> getVideoCateList(@QueryMap Map<String, String> params);

    /**
     *    视频---二级分类
     * @return
     */
    @GET(getVideoReCateList)
    Observable<HttpResponse<List<VideoReClassify>>> getVideoReCateList(@QueryMap Map<String, String> params);

    /**
     *    视频---二级列表
     * @return
    */
    @GET(getVideoOtherList)
    Observable<HttpResponse<List<VideoOtherColumnList>>> getVideoOtherColumnList(@QueryMap Map<String, String> params);
}
