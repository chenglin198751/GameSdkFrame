package com.game.sdk;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;

@Keep
public abstract class SdkResultListener {
    @CallSuper
    public void OnResult(String eventName, boolean isSuccessful, String message) {

        if (SdkParams.Method.INIT.equals(eventName)) {

        } else if (SdkParams.Method.LOGOUT.equals(eventName)) {

        } else if (SdkParams.Method.EXIT.equals(eventName)) {

        }
    }
}
