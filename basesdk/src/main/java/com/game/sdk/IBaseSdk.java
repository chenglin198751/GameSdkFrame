package com.game.sdk;

import android.app.Activity;
import android.text.TextUtils;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;


@Keep
public abstract class IBaseSdk {

    @CallSuper
    public void init(@NonNull Activity Activity, @NonNull SdkInitListener listener) {
        if (SdkBridge.application == null) {
            SdkBridge.application = Activity.getApplication();
        }
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
    public void logout(Activity activity, SdkInitListener listener) {
    }

    /**
     * @param activity 游戏主Activity
     * @param callback 切换登录结果监听
     */
    @CallSuper
    public void switchLogin(Activity activity, SdkLoginCallback callback) {
    }

    /**
     * @param activity  游戏主Activity
     * @param payParams 支付参数结构体
     * @param callback  支付结果监听
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
     * @param activity activity 游戏主Activity
     * @param listener 退弹结果监听
     */
    @CallSuper
    public void exit(Activity activity, SdkInitListener listener) {
    }

    /**
     * @param method SDK是否支持某个方法
     * @return boolean 是否支持
     */
    public boolean isSupportMethod(@SdkParams.MethodType String method) {
        return false;
    }
}
