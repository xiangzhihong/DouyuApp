package com.xzh.douyuapp.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.xzh.douyuapp.api.NetWorkApi;
import com.xzh.douyuapp.net.config.NetWorkConfiguration;
import com.xzh.douyuapp.net.http.HttpUtils;
import com.xzh.douyuapp.ui.pagestatemanager.PageManager;


public class DYApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(context);
        initQbSdk();
        initOkHttpUtils();
        PageManager.initInApp(context);
    }

    //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
    //TbsDownloader.needDownload(getApplicationContext(), false);
    private void initQbSdk() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.e("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.d("app", "onDownloadFinish is " + i);
            }

            @Override
            public void onInstallFinish(int i) {
                Log.d("app", "onInstallFinish is " + i);
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.d("app", "onDownloadProgress:" + i);
            }
        });
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * 网络配置
     */
    private void initOkHttpUtils() {
        NetWorkConfiguration configuration = new NetWorkConfiguration(this)
                .baseUrl(NetWorkApi.baseUrl)
                .isCache(true)
                .isDiskCache(true)
                .isMemoryCache(true);
        HttpUtils.setConFiguration(configuration);
    }

    public static Context getContext() {
        return context;
    }
}
