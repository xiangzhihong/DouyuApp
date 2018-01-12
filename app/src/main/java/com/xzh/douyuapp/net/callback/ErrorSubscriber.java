package com.xzh.douyuapp.net.callback;


import com.xzh.douyuapp.net.exception.ResponeThrowable;
import com.zhy.autolayout.utils.L;

import rx.Subscriber;



public abstract class ErrorSubscriber<T> extends Subscriber<T> {
    @Override
    public void onError(Throwable e) {
        L.e("错误信息:"+e.getMessage());
        if(e instanceof ResponeThrowable){
            onError((ResponeThrowable)e);
        }else{
            onError(new ResponeThrowable(e,1000));
        }
    }
    /**
     * 错误回调
     */
    protected abstract void onError(ResponeThrowable ex);
}

