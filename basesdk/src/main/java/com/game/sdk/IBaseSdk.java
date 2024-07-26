package com.game.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;


@Keep
public abstract class IBaseSdk {
    /**
     * @param context       Context
     * @param isDebugLog    是否开启日志，上线前一定要关闭
     * @param regionSubType 开服服务器，枚举类型，比如日韩服，全球服。
     * @param listener      初始化结果监听
     */
    @CallSuper
    public void init(Context context, boolean isDebugLog, SdkParams.RegionSubType regionSubType, SdkResultListener listener) {
        if (SdkBridge.application == null) {
            if (context instanceof Activity) {
                Activity act = (Activity) context;
                SdkBridge.application = act.getApplication();
            } else if (context instanceof Application) {
                SdkBridge.application = (Application) context;
            } else if (context instanceof ContextWrapper) {
                Activity act = SdkUtils.getActivityFromContext(context);
                SdkBridge.application = act.getApplication();
            }
        }

        if (regionSubType == SdkParams.RegionSubType.NONE) {
            throw new RuntimeException("Initialization parameter RegionSubType cannot be set to RegionSubType.NONE");
        }

        SdkBridge.regionSubType = regionSubType;
        SdkLogUtils.isDebugLog = isDebugLog;
    }

    /**
     * @param activity 游戏主Activity
     */
    @CallSuper
    public void setActivity(Activity activity) {
        SdkBridge.activity = activity;
    }

    /**
     * @param activity 游戏主Activity
     * @param callback 登录结果监听
     */
    @CallSuper
    public void login(Activity activity, SdkLoginCallback callback) {
        if (SdkBridge.activity == null) {
            throw new NullPointerException("please invoke EasySdk.setActivity(activity) method");
        }
    }

    /**
     * @param activity 游戏主Activity
     * @param listener 退出登录结果监听
     */
    @CallSuper
    public void logout(Activity activity, SdkResultListener listener) {
    }

    /**
     * @param activity 游戏主Activity
     * @param callback 切换登录结果监听
     */
    @CallSuper
    public void switchLogin(Activity activity, SdkLoginCallback callback) {
    }

    /**
     * @param activity 游戏主Activity
     * @param payParams 支付参数结构体
     * @param callback 支付结果监听
     */
    @CallSuper
    public void pay(Activity activity, PayParams payParams, SdkPayCallback callback) {
        if (TextUtils.isEmpty(payParams.productId)) {
            throw new NullPointerException("when paying,productId cannot be null");
        } else if (TextUtils.isEmpty(payParams.appOrderId)) {
            throw new NullPointerException("when paying,appOrderId cannot be null");
        } else if (TextUtils.isEmpty(payParams.appUserId)) {
            throw new NullPointerException("when paying,appUserId cannot be null");
        }
    }

    /**
     * @param activity  activity 游戏主Activity
     * @param listener 退弹结果监听
     */
    @CallSuper
    public void exit(Activity activity, SdkResultListener listener) {
    }

    /**
     * @param method SDK是否支持某个方法
     * @return boolean 是否支持
     */
    public boolean isSupportMethod(@SdkParams.MethodType String method) {
        return false;
    }
}
