package com.game.sdk;

import android.util.Log;



public class SdkLogUtils {
    public static boolean isDebugLog = SdkUtils.isDebuggable();

    public static void d(String tag, String msg) {
        if (EnvToggle.isLog || isDebugLog) {
            Log.d(tag, msg);
            DialPhoneBroadcastReceiver.addLog(tag + ":" + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (EnvToggle.isLog || isDebugLog) {
            Log.e(tag, msg);
            DialPhoneBroadcastReceiver.addLog(tag + ":" + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (EnvToggle.isLog || isDebugLog) {
            Log.i(tag, msg);
            DialPhoneBroadcastReceiver.addLog(tag + ":" + msg);
        }
    }

    public static void v(String tag, String msg) {
        if (EnvToggle.isLog || isDebugLog) {
            Log.v(tag, msg);
            DialPhoneBroadcastReceiver.addLog(tag + ":" + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (EnvToggle.isLog || isDebugLog) {
            Log.w(tag, msg);
            DialPhoneBroadcastReceiver.addLog(tag + ":" + msg);
        }
    }
}
