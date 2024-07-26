package com.game.sdk;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;

@Keep
public abstract class SdkPayCallback {
    @CallSuper
    public void onPayResult(@SdkParams.PayType int code, String message) {
    }
}
