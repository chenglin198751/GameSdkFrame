package com.game.sdk;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Keep;

@Keep
public interface OnActivityResultListener {
    void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data);
}
