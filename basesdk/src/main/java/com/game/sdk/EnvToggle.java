package com.game.sdk;


public class EnvToggle {
    public static final boolean isLog = SdkPreferAppSettings.getLogEnable(SdkUtils.getApp());
    public static final boolean isDebug = SdkPreferAppSettings.getDebugEnable(SdkUtils.getApp());
}
