package com.game.sdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.game.sdk.frame.R;

public class DemoMainActivity extends Activity {
    int mIndex = 0;
    TextView mDebugTips;// 测试代码，CP忽略

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDebugTips = findViewById(R.id.demo_result_tips);

        findViewById(R.id.demo_btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDebugTips.setText("");
            }
        });

        // 初始化SDK

    }

    // 测试代码，CP勿接
    private void appendDebugMsg(String message) {
        if (mDebugTips.length() <= 0) {
            mDebugTips.append((mIndex++ + "、") + message);
        } else {
            mDebugTips.append("\n" + (mIndex++ + "、") + message);
        }
    }
}