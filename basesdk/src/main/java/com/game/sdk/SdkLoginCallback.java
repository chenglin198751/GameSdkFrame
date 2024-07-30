package com.game.sdk;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;

@Keep
public abstract class SdkLoginCallback {

    /**
     * loginType 是当前登录渠道，例如vivo oppo
     * result数据结构：
     * {
     *   "code": "0",
     *   "message": "login success",
     *   "data": {
     *     "loginType": "vivo",
     *     "deviceId": "298d3c90053e06b785e2fd9c21432e0a",
     *     "uid": "2c148a1a795db53b49a645a00b886edf",
     *     "sign": "afae334bc8a14abb42f173e0519331fb",
     *     "timestamp": "1644981453",
     *     "user": {
     *       "picture": "http://xxx",
     *       "nickname": "W****an"
     *     }
     *   }
     * }
     */
    @CallSuper
    public void onLogin(@SdkParams.LoginType int statusCode, String result) {
        if (statusCode == SdkParams.LoginCode.LOGIN_SUCCESS) {
//            YdasUtils.onTrackEvent(YdasConstant.EASY_SDK_LOGIN_SUCCESS, map2);
        } else if (statusCode == SdkParams.LoginCode.LOGIN_FAILED) {
//            YdasUtils.onTrackEvent(YdasConstant.EASY_SDK_LOGIN_FAILED, map2);
        } else if (statusCode == SdkParams.LoginCode.LOGIN_CANCEL) {
//            YdasUtils.onTrackEvent(YdasConstant.EASY_SDK_LOGIN_CANCELED, map2);
        }
    }
}
