package com.game.sdk;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;

@Keep
public abstract class SdkInitListener {
    @CallSuper
    public void onSuccess() {
    }

    @CallSuper
    public void onFail(String desc) {
    }
}
