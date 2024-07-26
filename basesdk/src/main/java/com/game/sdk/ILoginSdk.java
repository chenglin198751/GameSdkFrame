package com.game.sdk;

import android.app.Activity;

import androidx.annotation.Keep;


@Keep
public interface ILoginSdk {
    int LOGIN_FACEBOOK = 1;
    int LOGIN_GOOGLE = 2;

    /**
     * @param activity  游戏主Activity
     * @param loginType 登录类型
     * @param callback  登录结果监听
     */
    void login(Activity activity, final int loginType, SdkLoginCallback callback);
}
