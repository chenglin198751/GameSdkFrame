package com.game.sdk;

import androidx.annotation.CallSuper;
import androidx.annotation.Keep;

@Keep
public abstract class SdkInitListener {
    @CallSuper
    public void onInitSucceeded(String message) {
    }

    @CallSuper
    public void onInitFailed(String message) {
    }
}
