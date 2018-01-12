package com.xzh.basepopup;

import android.os.Build;
import android.view.Gravity;
import android.view.View;


class PopupWindowProxy extends BasePopupWindowProxy {
    private static final String TAG = "PopupWindowProxy";

    public PopupWindowProxy(View contentView, int width, int height, PopupController mController) {
        super(contentView, width, height, mController);
    }


    public void showAsDropDownProxy(View anchor, int xoff, int yoff, int gravity) {
        PopupCompatManager.showAsDropDown(this, anchor, xoff, yoff, gravity);
    }

    public void showAsDropDownProxy(View anchor, int xoff, int yoff) {
        PopupCompatManager.showAsDropDown(this, anchor, xoff, yoff, Gravity.TOP | Gravity.START);
    }

    public void showAtLocationProxy(View parent, int gravity, int x, int y) {
        PopupCompatManager.showAtLocation(this, parent, gravity, x, y);
    }


    @Override
    public void callSuperShowAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            super.showAsDropDown(anchor, xoff, yoff, gravity);
        } else {
            super.showAsDropDown(anchor, xoff, yoff);
        }
    }

    @Override
    public void callSuperShowAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public boolean callSuperIsShowing() {
        return super.isShowing();
    }
}
