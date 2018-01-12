package com.xzh.basepopup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.xzh.basepopup.blur.PopupBlurOption;
import com.xzh.basepopup.util.InputMethodUtils;
import com.xzh.basepopup.util.SimpleAnimationUtils;
import com.xzh.basepopup.util.log.LogTag;
import com.xzh.basepopup.util.log.LogUtil;

import java.lang.ref.WeakReference;

public abstract class BasePopupWindow implements BasePopup, PopupWindow.OnDismissListener, PopupController {
    private static final String TAG = "BasePopupWindow";
    private static final int MAX_RETRY_SHOW_TIME = 3;
    private BasePopupHelper mHelper;

    //元素定义
    private PopupWindowProxy mPopupWindow;
    //popup视图
    private View mPopupView;
    private WeakReference<Context> mContext;
    protected View mAnimaView;
    protected View mDismissView;

    private volatile boolean isExitAnimaPlaying = false;

    //重试次数
    private volatile int retryCounter;

    private InnerPopupWindowStateListener mStateListener;

    public BasePopupWindow(Context context) {
        initView(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public BasePopupWindow(Context context, int w, int h) {
        initView(context, w, h);
    }

    private void initView(Context context, int w, int h) {
        mContext = new WeakReference<Context>(context);
        mHelper = new BasePopupHelper();
        mPopupView = onCreatePopupView();
        mAnimaView = initAnimaView();
        if (mAnimaView != null) {
            mHelper.setPopupLayoutId(mAnimaView.getId());
        }
        checkPopupAnimaView();

        //默认占满全屏
        mPopupWindow = new PopupWindowProxy(mPopupView, w, h, this);
        mPopupWindow.setOnDismissListener(this);
        mPopupWindow.bindPopupHelper(mHelper);
        setDismissWhenTouchOutside(true);

        preMeasurePopupView(w, h);

        //默认是渐入动画
        setNeedPopupFade(Build.VERSION.SDK_INT <= 22);

        //=============================================================为外层的view添加点击事件，并设置点击消失
        mDismissView = getClickToDismissView();
        if (mDismissView != null && !(mDismissView instanceof AdapterView)) {
            mDismissView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        if (mAnimaView != null && !(mAnimaView instanceof AdapterView)) {
            mAnimaView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        //=============================================================元素获取
        mHelper.setShowAnimation(initShowAnimation())
                .setShowAnimator(initShowAnimator())
                .setExitAnimation(initExitAnimation())
                .setExitAnimator(initExitAnimator());
    }

    private void checkPopupAnimaView() {
        //处理popupview与animaview相同的情况
        //当popupView与animaView相同的时候，处理位置信息会出问题，因此这里需要对mAnimaView再包裹一层
        if (mPopupView != null && mAnimaView != null && mPopupView == mAnimaView) {
            try {
                mPopupView = new FrameLayout(getContext());
                final int mPopupLayoutId = mHelper.getPopupLayoutId();
                if (mPopupLayoutId == 0) {
                    ((FrameLayout) mPopupView).addView(mAnimaView);
                } else {
                    mAnimaView = View.inflate(getContext(), mPopupLayoutId, (FrameLayout) mPopupView);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void preMeasurePopupView(int w, int h) {
        if (mPopupView != null) {
            //修复可能出现的android 4.3的measure空指针问题
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                int contentViewHeight = ViewGroup.LayoutParams.MATCH_PARENT;
                final ViewGroup.LayoutParams layoutParams = mPopupView.getLayoutParams();
                if (layoutParams != null && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    contentViewHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, contentViewHeight);
                mPopupView.setLayoutParams(p);
            }
            mPopupView.measure(w, h);
            mHelper.setPopupViewWidth(mPopupView.getMeasuredWidth())
                    .setPopupViewHeight(mPopupView.getMeasuredHeight());
            mPopupView.setFocusableInTouchMode(true);
        }
    }


    //------------------------------------------抽象-----------------------------------------------

    /**
     * PopupWindow展示出来后，需要执行动画的View.一般为蒙层之上的View
     *
     * @return 展示的动画
     */
    protected abstract Animation initShowAnimation();

    /**
     * 设置一个点击后触发dismiss PopupWindow的View，一般为蒙层
     *
     * @return 点击dismiss的控件
     */
    public abstract View getClickToDismissView();

    /**
     * 设置展示动画View的属性动画
     *
     * @return
     */
    protected Animator initShowAnimator() {
        return null;
    }

    /**
     * 设置一个拥有输入功能的View，一般为EditTextView
     */
    public EditText getInputView() {
        return null;
    }

    /**
     * 设置PopupWindow销毁时的退出动画
     */
    protected Animation initExitAnimation() {
        return null;
    }

    /**
     * 设置PopupWindow销毁时的退出属性动画
     */
    protected Animator initExitAnimator() {
        return null;
    }

    /**
     * popupwindow是否需要淡入淡出
     */
    public BasePopupWindow setNeedPopupFade(boolean needPopupFadeAnima) {
        mHelper.setNeedPopupFadeAnima(mPopupWindow, needPopupFadeAnima);
        return this;
    }

    public boolean isNeedPopupFade() {
        return mHelper.isNeedPopupFadeAnima();
    }

    /**
     * 设置popup的动画style
     */
    public BasePopupWindow setPopupAnimaStyle(int animaStyleRes) {
        mPopupWindow.setAnimationStyle(animaStyleRes);
        return this;
    }

    //------------------------------------------showPopup-----------------------------------------------

    /**
     * 调用此方法时，PopupWindow将会显示在DecorView
     */
    public void showPopupWindow() {
        if (checkPerformShow(null)) {
            mHelper.setShowAtDown(false);
            tryToShowPopup(null);
        }
    }

    /**
     * 调用此方法时，PopupWindow默认展示在anchorView下方
     *
     * @param anchorViewResid
     */
    public void showPopupWindow(int anchorViewResid) {
        Context context = getContext();
        assert context != null : "context is null";
        if (context instanceof Activity) {
            View v = ((Activity) context).findViewById(anchorViewResid);
            showPopupWindow(v);
        } else {
            Log.e(TAG, "can not get token from context,make sure that context is instance of activity");
        }
    }

    /**
     * 调用此方法时，PopupWindow默认展示在anchorView下方
     *
     * @param v
     */
    public void showPopupWindow(View v) {
        if (checkPerformShow(v)) {
            mHelper.setShowAtDown(true);
            tryToShowPopup(v);
        }
    }

    //------------------------------------------Methods-----------------------------------------------
    private void tryToShowPopup(View v) {
        try {
            if (isShowing()) return;
            int[] offset;
            //传递了view
            if (v != null) {
                offset = calculateOffset(v);
                if (mHelper.isShowAtDown()) {
                    mPopupWindow.showAsDropDownProxy(v, offset[0], offset[1]);
                } else {
                    mPopupWindow.showAtLocationProxy(v, mHelper.getPopupGravity(), offset[0], offset[1]);
                }
            } else {
                //什么都没传递，取顶级view的id
                Context context = getContext();
                assert context != null : "context is null ! please make sure your activity is not be destroyed";
                if (context instanceof Activity) {
                    mPopupWindow.showAtLocationProxy(((Activity) context).findViewById(android.R.id.content),
                            mHelper.getPopupGravity(),
                            mHelper.getOffsetX(),
                            mHelper.getOffsetY());
                } else {
                    Log.e(TAG, "can not get token from context,make sure that context is instance of activity");
                }
            }
            if (mStateListener != null) {
                mStateListener.onTryToShow(mHelper.getShowAnimation() != null || mHelper.getShowAnimator() != null);
            }
            if (mAnimaView != null) {
                if (mHelper.getShowAnimation() != null) {
                    mHelper.getShowAnimation().cancel();
                    mAnimaView.startAnimation(mHelper.getShowAnimation());
                } else if (mHelper.getShowAnimator() != null) {
                    mHelper.getShowAnimator().start();
                }
            }
            //自动弹出键盘
            if (mHelper.isAutoShowInputMethod() && getInputView() != null) {
                getInputView().requestFocus();
                InputMethodUtils.showInputMethod(getInputView(), 350);
            }
            retryCounter = 0;
        } catch (Exception e) {
            if (retryCounter > MAX_RETRY_SHOW_TIME) {
                Log.e(TAG, "show error\n" + e.getMessage());
                e.printStackTrace();
                return;
            }
            retryToShowPopup(v);
        }
    }

    /**
     * 用于修复popup无法在onCreate里面show的问题
     */
    private void retryToShowPopup(final View v) {
        if (retryCounter > MAX_RETRY_SHOW_TIME) return;
        Log.e(TAG, "catch an exception on showing popupwindow ...now retrying to show ... retry count  >>  " + retryCounter);
        if (isShowing()) mPopupWindow.callSuperDismiss();
        Context context = getContext();
        if (context instanceof Activity) {
            Activity act = (Activity) context;
            boolean availabled;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                availabled = !act.isFinishing() && !act.isDestroyed();
            } else {
                availabled = !act.isFinishing();
            }
            if (availabled) {
                View rootView = act.findViewById(android.R.id.content);
                if (rootView == null) return;
                rootView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        retryCounter++;
                        tryToShowPopup(v);
                    }
                }, 350);
            }
        }

    }


    /**
     * 计算popupwindow的偏移量
     *
     * @param anchorView
     * @return
     * @see #showPopupWindow(View)
     */
    private int[] calculateOffset(View anchorView) {
        int[] offset = {mHelper.getOffsetX(), mHelper.getOffsetY()};
        mHelper.getAnchorLocation(anchorView);
        if (mHelper.isAutoLocatePopup()) {
            final boolean onTop = (getScreenHeight() - (mHelper.getAnchorY() + offset[1]) < getHeight());
            if (onTop) {
                offset[1] = -anchorView.getHeight() - getHeight() - offset[1];
                showOnTop(mPopupView, anchorView);
            } else {
                showOnDown(mPopupView, anchorView);
            }
        }
        return offset;

    }

    /**
     * PopupWindow是否需要自适应输入法，为输入法弹出让出区域
     *
     * @param needAdjust <br>
     *                   true for "SOFT_INPUT_ADJUST_RESIZE" mode<br>
     *                   false for "SOFT_INPUT_ADJUST_NOTHING" mode
     */
    public BasePopupWindow setAdjustInputMethod(boolean needAdjust) {
        setAdjustInputMethod(needAdjust, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return this;
    }

    /**
     * @param needAdjust
     * @param flag       The desired mode, see
     *                   {@link WindowManager.LayoutParams#softInputMode}
     *                   for the full list
     * @return
     */
    public BasePopupWindow setAdjustInputMethod(boolean needAdjust, int flag) {
        if (needAdjust) {
            mPopupWindow.setSoftInputMode(flag);
        } else {
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
        return this;
    }

    /**
     * 当PopupWindow展示的时候，这个参数决定了是否自动弹出输入法
     * 如果使用这个方法，您必须保证通过 <strong>getInputView()<strong/>得到一个EditTextView
     */
    public BasePopupWindow setAutoShowInputMethod(boolean autoShow) {
        mHelper.setAutoShowInputMethod(mPopupWindow, autoShow);
        return this;
    }

    /**
     * 这个参数决定点击返回键是否可以取消掉PopupWindow
     * <p>
     * 在android M之后失效
     */
    public BasePopupWindow setBackPressEnable(final boolean backPressEnable) {
        mHelper.setBackPressEnable(mPopupWindow, backPressEnable);
        return this;
    }

    /**
     * 这个方法封装了LayoutInflater.from(context).inflate，方便您设置PopupWindow所用的xml
     *
     * @param resId reference of layout
     * @return root View of the layout
     */
    public View createPopupById(int resId) {
        if (resId != 0) {
            mHelper.setPopupLayoutId(resId);
            return LayoutInflater.from(getContext()).inflate(resId, null);
        } else {
            return null;
        }
    }

    protected View findViewById(int id) {
        if (mPopupView != null && id != 0) {
            return mPopupView.findViewById(id);
        }
        return null;
    }

    /**
     * 是否允许popupwindow覆盖屏幕（包含状态栏）
     */
    public BasePopupWindow setPopupWindowFullScreen(boolean needFullScreen) {
        mHelper.setFullScreen(needFullScreen);
        return this;
    }

    /**
     * 应用模糊脚本...默认模糊背景
     *
     * @param blur true for blur decorView
     * @return
     */
    public BasePopupWindow setBlurBackgroundEnable(boolean blur) {
        return setBlurBackgroundEnable(blur, null);
    }

    /**
     * 应用模糊脚本...默认模糊背景
     *
     * @param blur true for blur decorView
     * @param l    创建blur配置监听
     * @return
     */
    public BasePopupWindow setBlurBackgroundEnable(boolean blur, OnBlurOptionInitListener l) {
        if (!(getContext() instanceof Activity)) {
            LogUtil.trace(LogTag.e, TAG, "无法配置默认模糊脚本，因为context不是activity");
            return this;
        }
        PopupBlurOption option = null;
        if (blur) {
            option = new PopupBlurOption();
            option.setFullScreen(true)
                    .setBlurInDuration(mHelper.getShowAnimationDuration())
                    .setBlurOutDuration(mHelper.getExitAnimationDuration());
            if (l != null) {
                l.onCreateBlurOption(option);
            }
            View decorView = ((Activity) getContext()).getWindow().getDecorView();
            if (decorView instanceof ViewGroup) {
                option.setBlurView(((ViewGroup) decorView).getChildAt(0));
            } else {
                option.setBlurView(decorView);
            }
        }

        return setBlurOption(option);
    }

    /**
     * 应用模糊脚本
     *
     * @param option
     * @return
     */
    public BasePopupWindow setBlurOption(PopupBlurOption option) {
        mHelper.applyBlur(option);
        return this;
    }

    /**
     * 这个方法用于简化您为View设置OnClickListener事件，多个View将会使用同一个点击事件
     */
    protected void setViewClickListener(View.OnClickListener listener, View... views) {
        for (View view : views) {
            if (view != null && listener != null) {
                view.setOnClickListener(listener);
            }
        }
    }

    //------------------------------------------Getter/Setter-----------------------------------------------

    /**
     * PopupWindow是否处于展示状态
     */
    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    public OnDismissListener getOnDismissListener() {
        return mHelper.getOnDismissListener();
    }

    public BasePopupWindow setOnDismissListener(OnDismissListener onDismissListener) {
        mHelper.setOnDismissListener(onDismissListener);
        return this;
    }

    public OnBeforeShowCallback getOnBeforeShowCallback() {
        return mHelper.getOnBeforeShowCallback();
    }

    public BasePopupWindow setOnBeforeShowCallback(OnBeforeShowCallback mOnBeforeShowCallback) {
        mHelper.setOnBeforeShowCallback(mOnBeforeShowCallback);
        return this;
    }

    public BasePopupWindow setShowAnimation(Animation showAnimation) {
        mHelper.setShowAnimation(showAnimation);
        return this;
    }

    public Animation getShowAnimation() {
        return mHelper.getShowAnimation();
    }

    public BasePopupWindow setShowAnimator(Animator showAnimator) {
        mHelper.setShowAnimator(showAnimator);
        return this;
    }

    public Animator getShowAnimator() {
        return mHelper.getShowAnimator();
    }

    public BasePopupWindow setExitAnimation(Animation exitAnimation) {
        mHelper.setExitAnimation(exitAnimation);
        return this;
    }

    public Animation getExitAnimation() {
        return mHelper.getExitAnimation();
    }

    public BasePopupWindow setExitAnimator(Animator exitAnimator) {
        mHelper.setExitAnimator(exitAnimator);
        return this;
    }

    public Animator getExitAnimator() {
        return mHelper.getExitAnimator();
    }

    public Context getContext() {
        return mContext == null ? null : mContext.get();
    }

    /**
     * 获取popupwindow的根布局
     *
     * @return
     */
    public View getPopupWindowView() {
        return mPopupView;
    }

    /**
     * 获取popupwindow实例
     *
     * @return
     */
    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public int getOffsetX() {
        return mHelper.getOffsetX();
    }

    /**
     * 设定x位置的偏移量(中心点在popup的左上角)
     * <p>
     *
     * @param offsetX
     */
    public BasePopupWindow setOffsetX(int offsetX) {
        mHelper.setOffsetX(offsetX);
        return this;
    }

    public int getOffsetY() {
        return mHelper.getOffsetY();
    }

    /**
     * 设定y位置的偏移量(中心点在popup的左上角)
     *
     * @param offsetY
     */
    public BasePopupWindow setOffsetY(int offsetY) {
        mHelper.setOffsetY(offsetY);
        return this;
    }

    public int getPopupGravity() {
        return mHelper.getPopupGravity();
    }

    /**
     * 设置参考点，一般情况下，参考对象指的不是指定的view，而是它的windoToken，可以看作为整个screen
     *
     * @param popupGravity
     */
    public BasePopupWindow setPopupGravity(int popupGravity) {
        mHelper.setPopupGravity(popupGravity);
        return this;
    }

    public boolean isAutoLocatePopup() {
        return mHelper.isAutoLocatePopup();
    }

    public BasePopupWindow setAutoLocatePopup(boolean autoLocatePopup) {
        mHelper.setShowAtDown(true).setAutoLocatePopup(true);
        return this;
    }

    /**
     * 获取poupwindow的高度，当popupwindow没show出来的时候高度会是0，此时则返回pre measure的高度，不一定精准
     *
     * @return
     */
    public int getHeight() {
        int height = mPopupWindow.getHeight();
        return height <= 0 ? mHelper.getPopupViewHeight() : height;
    }

    /**
     * 获取poupwindow的宽度，当popupwindow没show出来的时候高度会是0，此时则返回pre measure的宽度，不一定精准
     *
     * @return
     */
    public int getWidth() {
        int width = mPopupWindow.getWidth();
        return width <= 0 ? mHelper.getPopupViewWidth() : width;
    }

    /**
     * 点击外部是否消失
     * <p>
     * dismiss popup when touch ouside from popup
     *
     * @param dismissWhenTouchOutside true for dismiss
     */
    public BasePopupWindow setDismissWhenTouchOutside(boolean dismissWhenTouchOutside) {
        mHelper.setDismissWhenTouchOutside(mPopupWindow, dismissWhenTouchOutside);
        return this;
    }

    /**
     * popupwindow是否拦截事件，这会影响到返回键dismiss的问题
     *
     * @param touchable ture:popupwindow拦截事件,false：不拦截事件
     * @return
     */
    public BasePopupWindow setInterceptTouchEvent(boolean touchable) {
        mHelper.setInterceptTouchEvent(mPopupWindow, touchable);
        return this;
    }

    public boolean isDismissWhenTouchOutside() {
        return mHelper.isDismissWhenTouchOutside();
    }

    public boolean isInterceptTouchEvent() {
        return mHelper.isInterceptTouchEvent();
    }

    //------------------------------------------状态控制-----------------------------------------------


    /**
     * 内部状态监听
     *
     * @param listener
     */
    void setOnInnerPopupWIndowStateListener(InnerPopupWindowStateListener listener) {
        this.mStateListener = listener;
    }

    /**
     * 取消一个PopupWindow，如果有退出动画，PopupWindow的消失将会在动画结束后执行
     */
    public void dismiss() {
        try {
            mPopupWindow.dismiss();
        } catch (Exception e) {
            Log.e(TAG, "dismiss error");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onBeforeDismiss() {
        return checkPerformDismiss();
    }

    @Override
    public boolean callDismissAtOnce() {
        boolean hasAnima = false;
        if (mHelper.getExitAnimation() != null && mAnimaView != null) {
            if (!isExitAnimaPlaying) {
                mHelper.getExitAnimation().setAnimationListener(mAnimationListener);
                mHelper.getExitAnimation().cancel();
                mAnimaView.startAnimation(mHelper.getExitAnimation());
                isExitAnimaPlaying = true;
                hasAnima = true;
            }
        } else if (mHelper.getExitAnimator() != null) {
            if (!isExitAnimaPlaying) {
                mHelper.getExitAnimator().removeListener(mAnimatorListener);
                mHelper.getExitAnimator().addListener(mAnimatorListener);
                mHelper.getExitAnimator().start();
                isExitAnimaPlaying = true;
                hasAnima = true;
            }
        }
        if (!hasAnima) {
            if (mStateListener != null) {
                mStateListener.onWithAnimaDismiss();
            }
        }
        //如果有动画，则不立刻执行dismiss
        return !hasAnima;
    }

    /**
     * 直接消掉popup而不需要动画
     */
    public void dismissWithOutAnima() {
        if (!checkPerformDismiss()) return;
        if (mHelper.getExitAnimation() != null && mAnimaView != null) {
            mHelper.getExitAnimation().cancel();
        }
        if (mHelper.getExitAnimator() != null) {
            mHelper.getExitAnimator().removeAllListeners();
        }
        mPopupWindow.callSuperDismiss();
        if (mStateListener != null) {
            mStateListener.onWithAnimaDismiss();
        }

    }


    private boolean checkPerformDismiss() {
        boolean callDismiss = true;
        if (mHelper.getOnDismissListener() != null) {
            callDismiss = mHelper.getOnDismissListener().onBeforeDismiss();
        }
        return callDismiss && !isExitAnimaPlaying;
    }

    private boolean checkPerformShow(View v) {
        boolean result = true;
        if (mHelper.getOnBeforeShowCallback() != null) {
            result = mHelper.getOnBeforeShowCallback().onBeforeShow(mPopupView, v,
                    mHelper.getShowAnimation() != null || mHelper.getShowAnimator() != null);
        }
        return result;
    }

    /**
     * 捕捉keyevent
     *
     * @param event
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onDispatchKeyEvent(KeyEvent event) {
        return false;
    }

    /**
     * 捕捉touchevent
     *
     * @param event
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * 捕捉返回键事件
     *
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onBackPressed() {
        if (mHelper.isBackPressEnable()) {
            dismiss();
            return true;
        }
        return false;
    }

    /**
     * popupwindow外的事件点击回调，请注意您的popupwindow大小
     *
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onOutSideTouch() {
        boolean result = false;
        if (mHelper.isDismissWhenTouchOutside()) {
            dismiss();
            result = true;
        } else if (mHelper.isInterceptTouchEvent()) {
            result = true;
        }
        return result;
    }

    //------------------------------------------Anima-----------------------------------------------

    private Animator.AnimatorListener mAnimatorListener = new AnimatorListenerAdapter() {

        @Override
        public void onAnimationStart(Animator animation) {
            isExitAnimaPlaying = true;
            if (mStateListener != null) {
                mStateListener.onAnimaDismissStart();
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mPopupView.post(new Runnable() {
                @Override
                public void run() {
                    mPopupWindow.callSuperDismiss();
                    isExitAnimaPlaying = false;
                }
            });

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            isExitAnimaPlaying = false;
        }

    };

    private Animation.AnimationListener mAnimationListener = new SimpleAnimationUtils.AnimationListenerAdapter() {
        @Override
        public void onAnimationStart(Animation animation) {
            isExitAnimaPlaying = true;
            if (mStateListener != null) {
                mStateListener.onAnimaDismissStart();
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mPopupView.post(new Runnable() {
                @Override
                public void run() {
                    mPopupWindow.callSuperDismiss();
                    isExitAnimaPlaying = false;
                }
            });
        }
    };

    /**
     * 生成TranslateAnimation
     *
     * @param durationMillis 动画显示时间
     * @param start          初始百分比
     * @param end            结束百分比
     */
    protected Animation getTranslateVerticalAnimation(int start, int end, int durationMillis) {
        return SimpleAnimationUtils.getTranslateVerticalAnimation(start, end, durationMillis);
    }

    /**
     * 生成TranslateAnimation（相对于parent）
     *
     * @param durationMillis 动画显示时间
     * @param start          初始百分比(0f~1f)
     * @param end            结束百分比(0f~1f)
     */
    protected Animation getTranslateVerticalAnimation(float start, float end, int durationMillis) {
        return SimpleAnimationUtils.getTranslateVerticalAnimation(start, end, durationMillis);
    }

    /**
     * 生成ScaleAnimation
     * <p>
     * time=300
     */
    protected Animation getScaleAnimation(float fromX,
                                          float toX,
                                          float fromY,
                                          float toY,
                                          int pivotXType,
                                          float pivotXValue,
                                          int pivotYType,
                                          float pivotYValue) {
        return SimpleAnimationUtils.getScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
    }


    /**
     * 生成自定义ScaleAnimation
     */
    protected Animation getDefaultScaleAnimation() {
        return getDefaultScaleAnimation(true);
    }

    /**
     * 生成自定义ScaleAnimation
     *
     * @param in true for scale in
     */
    protected Animation getDefaultScaleAnimation(boolean in) {
        return SimpleAnimationUtils.getDefaultScaleAnimation(in);
    }


    /**
     * 生成默认的AlphaAnimation
     */
    protected Animation getDefaultAlphaAnimation() {
        return getDefaultAlphaAnimation(true);
    }

    /**
     * 生成默认的AlphaAnimation
     *
     * @param in true for alpha in
     */
    protected Animation getDefaultAlphaAnimation(boolean in) {
        return SimpleAnimationUtils.getDefaultAlphaAnimation(in);
    }

    /**
     * 从下方滑动上来
     */
    protected AnimatorSet getDefaultSlideFromBottomAnimationSet() {
        return SimpleAnimationUtils.getDefaultSlideFromBottomAnimationSet(mAnimaView);
    }

    /**
     * 获取屏幕高度(px)
     */
    public int getScreenHeight() {
        return getContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public int getScreenWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }

    View getContentView() {
        return mPopupWindow.getContentView();
    }

    //------------------------------------------callback-----------------------------------------------

    /**
     * 在anchorView上方显示，autoLocatePopup为true时适用
     *
     * @param mPopupView {@link #onCreatePopupView()}返回的View
     * @param anchorView {@link #showPopupWindow(View)}传入的View
     */
    protected void showOnTop(View mPopupView, View anchorView) {

    }

    /**
     * 在anchorView下方显示，autoLocatePopup为true时适用
     *
     * @param mPopupView {@link #onCreatePopupView()}返回的View
     * @param anchorView {@link #showPopupWindow(View)}传入的View
     */
    protected void showOnDown(View mPopupView, View anchorView) {

    }

    @Override
    public void onDismiss() {
        if (mHelper.getOnDismissListener() != null) {
            mHelper.getOnDismissListener().onDismiss();
        }
        isExitAnimaPlaying = false;
    }


    //------------------------------------------tools-----------------------------------------------

    protected float dipToPx(float dip) {
        if (getContext() == null) return dip;
        return dip * getContext().getResources().getDisplayMetrics().density + 0.5f;
    }

    public static void debugLog(boolean printLog) {
        LogUtil.setOpenLog(printLog);
    }

    //------------------------------------------Interface-----------------------------------------------
    public interface OnBeforeShowCallback {
        /**
         * <b>return true for perform show</b>
         *
         * @param popupRootView The rootView of popup,it's usually be your layout
         * @param anchorView    The anchorView whitch popup show
         * @param hasShowAnima  Check if show your popup with anima?
         * @return
         */
        boolean onBeforeShow(View popupRootView, View anchorView, boolean hasShowAnima);


    }

    public interface OnBlurOptionInitListener {
        void onCreateBlurOption(PopupBlurOption option);
    }

    public static abstract class OnDismissListener implements PopupWindow.OnDismissListener {
        /**
         * <b>return true for perform dismiss</b>
         *
         * @return
         */
        public boolean onBeforeDismiss() {
            return true;
        }
    }
}
