package com.xzh.basepopup;

import android.view.KeyEvent;
import android.view.MotionEvent;


interface PopupController {


    boolean onBeforeDismiss();

    boolean callDismissAtOnce();

    boolean onDispatchKeyEvent(KeyEvent event);

    boolean onTouchEvent(MotionEvent event);

    boolean onBackPressed();

    boolean onOutSideTouch();


}
