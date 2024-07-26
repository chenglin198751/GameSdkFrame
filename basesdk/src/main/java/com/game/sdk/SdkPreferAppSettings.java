package com.game.sdk;

import android.content.Context;
import android.content.SharedPreferences;


public class SdkPreferAppSettings {
    private static final String SERVER_TIME = "SERVER_TIME";
    private static final String KEY_LOG_TOGGLE = "KEY_LOG_TOGGLE";
    private static final String KEY_DEBUG_TOGGLE = "KEY_DEBUG_TOGGLE";
    private static final String KEY_LOG_VIEW_TOGGLE = "KEY_LOG_VIEW_TOGGLE";

    private static SharedPreferences getPreferences(final Context context) {
        return context.getSharedPreferences("sdk_game2022_settings", Context.MODE_PRIVATE);
    }


    public static void setServerTimeMillisDiff(Context context, long value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(SERVER_TIME, value);
        editor.apply();
    }

    public static long getServerTimeMillisDiff(Context context) {
        return getPreferences(context).getLong(SERVER_TIME, 0);
    }

    public static void setLogEnable(Context context, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_LOG_TOGGLE, value);
        editor.apply();
    }

    public static boolean getLogEnable(Context context) {
        return getPreferences(context).getBoolean(KEY_LOG_TOGGLE, false);
    }

    public static void setDebugEnable(Context context, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_DEBUG_TOGGLE, value);
        editor.apply();
    }

    public static boolean getDebugEnable(Context context) {
        return getPreferences(context).getBoolean(KEY_DEBUG_TOGGLE, false);
    }

    public static void setLogViewEnable(Context context, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_LOG_VIEW_TOGGLE, value);
        editor.apply();
    }

    public static boolean getLogViewEnable(Context context) {
        return getPreferences(context).getBoolean(KEY_LOG_VIEW_TOGGLE, false);
    }
}
