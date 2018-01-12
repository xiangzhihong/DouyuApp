package com.xzh.douyuapp.presenter.common.interfaces;


import android.content.Context;
import com.xzh.douyuapp.base.BaseModel;
import com.xzh.douyuapp.base.BasePresenter;
import com.xzh.douyuapp.base.BaseView;
import com.xzh.douyuapp.model.logic.common.bean.OldLiveVideoInfo;

import okhttp3.Request;


public interface CommonPhoneLiveVideoContract {

      interface View extends BaseView {
           void getViewPhoneLiveVideoInfo(OldLiveVideoInfo mLiveVideoInfo);
      }
    interface  Model extends BaseModel {
        Request getModelPhoneLiveVideoInfo(Context context, String room_id);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
              public abstract  void getPresenterPhoneLiveVideoInfo(String room_id);
      }

}
