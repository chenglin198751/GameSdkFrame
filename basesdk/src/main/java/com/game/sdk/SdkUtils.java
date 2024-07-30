package com.game.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;

import androidx.annotation.NonNull;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

public class SdkUtils {
    private static Handler mHandler;
    private static String mMainActName;
    private static int isDebuggable = -1;


    /**
     * 获取当前应用的Application
     * 先使用ActivityThread里获取Application的方法，如果没有获取到，
     * 再使用AppGlobals里面的获取Application的方法
     *
     * @return
     */
    public static @NonNull Application getApp() {
        if (SdkBridge.application == null) {
            throw new NullPointerException("The game application is null");
        }
        return SdkBridge.application;
    }

    public static Activity getActivity() {
        if (SdkBridge.activity == null) {
            throw new NullPointerException("The game activity is null");
        }
        return SdkBridge.activity;
    }

    /**
     * 解决：Android 从 View 中获取 Activity 时遇到 TintContextWrapper cannot be cast to 的问题
     */
    public static Activity getActivityFromContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    /**
     * 将dip转化为px *
     */
    public static int dip2px(float dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, getApp().getResources().getDisplayMetrics());
    }

    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line).append("\n");
            }
            if (result.length() > 0) {
                result.deleteCharAt(result.length() - 1);
            }
            bufReader.close();
            inputReader.close();

            //有时服务端返回json带了bom头，会导致解析生效
            String tempStr = result.toString();
            if (!TextUtils.isEmpty(tempStr) && tempStr.startsWith("\ufeff")) {
                tempStr = tempStr.substring(1);
            }

            return tempStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final class MHandlerHolder {
        private static final Handler mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 创建一个全局Handler，可以用来执行一些post任务等
     */
    public static Handler getUiHandler() {
        return MHandlerHolder.mHandler;
    }

    /**
     * 判断当前线程是不是UI线程
     */
    public static boolean isUiThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 创建一个全局Handler，可以用来执行一些post任务等
     */
    public static Handler getHandler() {
        if (mHandler == null) {
            synchronized (SdkUtils.class) {
                if (mHandler == null) {
                    mHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return mHandler;
    }

    public static String getString(String stringId) {
        try {
            int str_id = SdkUtils.getApp().getResources().getIdentifier(stringId, "string", SdkUtils.getApp().getPackageName());
            return SdkUtils.getApp().getString(str_id);
        } catch (Throwable t) {
            return "";
        }
    }

    public static String getLauncherMainActivityName(Context context) {
        if (!TextUtils.isEmpty(mMainActName)) {
            return mMainActName;
        }

        try {
            PackageInfo packageinfo = null;
            packageinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageinfo == null) {
                return null;
            }

            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(packageinfo.packageName);
            List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);

            ResolveInfo resolveinfo = resolveinfoList.iterator().next();
            if (resolveinfo != null) {
                mMainActName = resolveinfo.activityInfo.name;
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return mMainActName;
    }

    public static Object getClassInstance(String clzName) {
        try {
            Class localClass = Class.forName(clzName);
            return localClass.newInstance();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static String getStringMeta(Context context, String key) {
        Bundle metaData = getMetaData(context);
        String strVal = (metaData != null) ? metaData.getString(key) : null;
        return (strVal != null) ? strVal : "";
    }

    private static Bundle getMetaData(Context context) {
        if (context == null) {
            return null;
        }

        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData;
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        return null;
    }

    /**
     * 四舍五入保留指定位数的小数
     */
    public static float formatFloat(float f, int scale) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(scale, RoundingMode.HALF_UP).floatValue();
    }

    /**
     * 判断是否为直接通过开发工具运行起来的状态
     */
    public static boolean isDebuggable() {
        if (isDebuggable == -1) {
            try {
                PackageManager pm = SdkUtils.getApp().getPackageManager();
                PackageInfo packageInfo = pm.getPackageInfo(SdkUtils.getApp().getPackageName(), 0);
                boolean is_debug = (0 != (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
                if (is_debug) {
                    isDebuggable = 1;
                } else {
                    isDebuggable = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
                isDebuggable = 0;
            }
        }

        return isDebuggable == 1;
    }
}
