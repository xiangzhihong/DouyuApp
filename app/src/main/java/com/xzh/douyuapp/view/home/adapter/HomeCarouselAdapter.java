package com.xzh.douyuapp.view.home.adapter;


import android.view.ViewTreeObserver;


import com.facebook.drawee.view.SimpleDraweeView;
import com.xzh.douyuapp.utils.FrescoUtils;


import cn.bingoogolapple.bgabanner.BGABanner;


public class HomeCarouselAdapter implements BGABanner.Adapter<SimpleDraweeView, String> {
    @Override
    public void fillBannerItem(BGABanner banner, SimpleDraweeView itemView, String model, int position) {
        itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                                                     @Override
                                                                     public void onGlobalLayout() {
                                                                         if (itemView.getWidth() != 0 && itemView.getHeight() != 0) {
                                                                             FrescoUtils.showThumb(itemView,model, itemView.getWidth(),itemView.getHeight());
                                                                         }
                                                                     }
                                                                 });
    }
}
