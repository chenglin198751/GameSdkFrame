package com.game.sdk;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Keep;

@Keep
public interface OnApplicationListener {
    void attachBaseContext(Context base);

    void onCreate(Application application);
}
