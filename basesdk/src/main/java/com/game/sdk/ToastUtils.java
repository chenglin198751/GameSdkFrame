package com.game.sdk;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basesdk.R;


/**
 * Created by chenglin on 2017-7-24.
 */

public class ToastUtils {

    public static void show(int resId) {
        show(SdkUtils.getApp().getString(resId));
    }

    public static void show(final String text) {
        if (TextUtils.isEmpty(text)){
            return;
        }

        if (SdkUtils.isUiThread()) {
            showToast(text);
        } else {
            SdkUtils.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    showToast(text);
                }
            });
        }
    }

    private static void showToast(String text) {
        TextView tvMessage;
        Toast toast = new Toast(SdkUtils.getApp());

        View view = View.inflate(SdkUtils.getApp(), R.layout.sdk_toast_layout, null);
        tvMessage = view.findViewById(R.id.message);
        tvMessage.setText(text);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}
