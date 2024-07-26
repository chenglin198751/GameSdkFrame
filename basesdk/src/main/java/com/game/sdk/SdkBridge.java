package com.game.sdk;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.Keep;


@Keep
public class SdkBridge {
    public static Application application;
    public static Activity activity;
    public static String m2;
    public static String SDK_PLAT;
    public static String LOGIN_CHANNEL;
    public static SdkParams.RegionSubType regionSubType;
    public static OnActivityResultListener onActivityResultListener;
    public static OnNewIntentListener onNewIntentListener;
    public static OnApplicationListener onApplicationListener;
    private static volatile IBaseSdk mEasySdk = null;
    private static volatile ILoginSdk mLoginSdk = null;

    public static IBaseSdk getEasySdk() {
        if (mEasySdk == null) {
            synchronized (SdkBridge.class) {
                if (mEasySdk == null) {
                    mEasySdk = (IBaseSdk) SdkUtils.getClassInstance("com.easy.sdk.EasySdk");
                }
            }
        }
        return mEasySdk;
    }

    public static ILoginSdk getLoginSdk() {
        if (mLoginSdk == null) {
            synchronized (SdkBridge.class) {
                if (mLoginSdk == null) {
                    mLoginSdk = (ILoginSdk) SdkUtils.getClassInstance("com.easy.sdk.EasySdk");
                }
            }
        }
        return mLoginSdk;
    }

}
