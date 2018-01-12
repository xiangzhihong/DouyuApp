package com.xzh.douyuapp.net.callback;


public abstract class RxSubscriber<T> extends ErrorSubscriber<T> {

    /**
     * 开始请求网络
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * 请求网络完成
     */
    @Override
    public void onCompleted() {
    }

    /**
     * 获取网络数据
     */
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

}