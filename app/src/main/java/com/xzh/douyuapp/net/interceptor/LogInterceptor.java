package com.xzh.douyuapp.net.interceptor;

import com.zhy.autolayout.utils.L;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class LogInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request=chain.request();
        Response response=chain.proceed(chain.request());
        MediaType mediaType=response.body().contentType();
        String content=response.body().string();
        long t1 = System.nanoTime();
        long t2 = System.nanoTime();
        if(response.body()!=null)
        {
            ResponseBody body=ResponseBody.create(mediaType, content);
            return response.newBuilder().body(body).build();
        }
        return response;

    }
}