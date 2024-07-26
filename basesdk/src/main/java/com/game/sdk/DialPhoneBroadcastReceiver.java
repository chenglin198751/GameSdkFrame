package com.game.sdk;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.basesdk.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 在拨号键盘输入 *#*#2022360#*#* 可以打开debug模式
 */
public class DialPhoneBroadcastReceiver extends BroadcastReceiver {
    private static DebugLogAdapter mAdapter;
    private static View mLogView;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SECRET_CODE")) {
            showDebugView();
        }
    }

    public static void showDebugView() {
        Context context = SdkUtils.getActivity();
        View view = View.inflate(context, R.layout.sdk_debug_layout, null);
        final ViewGroup viewGroup = SdkUtils.getActivity().findViewById(android.R.id.content);
        viewGroup.addView(view);
        CheckBox logToggle = view.findViewById(R.id.log_toggle);
        CheckBox debugToggle = view.findViewById(R.id.debug_toggle);
        CheckBox logViewToggle = view.findViewById(R.id.log_view_toggle);

        logToggle.setChecked(SdkPreferAppSettings.getLogEnable(context));
        debugToggle.setChecked(SdkPreferAppSettings.getDebugEnable(context));
        logViewToggle.setChecked(SdkPreferAppSettings.getLogViewEnable(context));

        if (SdkPreferAppSettings.getLogViewEnable(context)) {
            addLogListView(context);
        }

        logToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SdkPreferAppSettings.setLogEnable(context, isChecked);
            }
        });

        debugToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SdkPreferAppSettings.setDebugEnable(context, isChecked);
            }
        });

        logViewToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!logToggle.isChecked()) {
                    ToastUtils.show("日志开关没打开");
                }

                SdkPreferAppSettings.setLogViewEnable(context, isChecked);
                if (isChecked) {
                    addLogListView(context);
                } else {
                    removeLogListView();
                }
            }
        });

        view.findViewById(R.id.close_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view.getParent() != null) {
                    viewGroup.removeView(view);
                }
            }
        });
    }

    public static void addLogListView(Context context) {
        if (mLogView != null && mLogView.getParent() != null) {
            return;
        }

        if (!EnvToggle.isLog || !SdkPreferAppSettings.getLogViewEnable(context)) {
            return;
        }

        final ViewGroup viewGroup = SdkUtils.getActivity().findViewById(android.R.id.content);
        mLogView = View.inflate(context, R.layout.sdk_debug_log_layout, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(SdkUtils.dip2px(300f), -1);
        params.gravity = Gravity.LEFT;
        viewGroup.addView(mLogView, params);
        ListView listView = mLogView.findViewById(R.id.list_view);

        mAdapter = new DebugLogAdapter(context);
        listView.setAdapter(mAdapter);

        //清除日志按钮
        mLogView.findViewById(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clear();
            }
        });

        //复制日志按钮
        mLogView.findViewById(R.id.tv_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                List<String> logList = mAdapter.getData();
                StringBuilder logBuilder = new StringBuilder();
                for (String log : logList) {
                    logBuilder.append(log + "\n");
                }
                ClipData mClipData = ClipData.newPlainText("debug_tips", logBuilder);
                cm.setPrimaryClip(mClipData);
                ToastUtils.show("已复制");
            }
        });

        //关闭日志窗口
        mLogView.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLogListView();
                SdkPreferAppSettings.setLogViewEnable(context, false);
            }
        });

        //移动日志窗口
        TextView tvMove = mLogView.findViewById(R.id.tv_move);
        tvMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout.LayoutParams params1 = (FrameLayout.LayoutParams) mLogView.getLayoutParams();
                if (params1.gravity == Gravity.LEFT) {
                    params1.gravity = Gravity.CENTER_HORIZONTAL;
                } else if (params1.gravity == Gravity.CENTER_HORIZONTAL) {
                    params1.gravity = Gravity.RIGHT;
                } else if (params1.gravity == Gravity.RIGHT) {
                    params1.gravity = Gravity.LEFT;
                }
                mLogView.setLayoutParams(params1);
            }
        });

        //全屏日志窗口
        TextView tvFull = mLogView.findViewById(R.id.tv_full);
        tvFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvMove.getVisibility() == View.VISIBLE) {
                    mLogView.setBackgroundColor(Color.WHITE);
                    tvMove.setVisibility(View.GONE);
                    FrameLayout.LayoutParams params1 = (FrameLayout.LayoutParams) mLogView.getLayoutParams();
                    params1.width = FrameLayout.LayoutParams.MATCH_PARENT;
                    params1.height = FrameLayout.LayoutParams.MATCH_PARENT;
                    mLogView.setLayoutParams(params1);
                    tvFull.setText("退出全屏");
                } else {
                    mLogView.setBackgroundColor(Color.TRANSPARENT);
                    tvMove.setVisibility(View.VISIBLE);
                    FrameLayout.LayoutParams params1 = (FrameLayout.LayoutParams) mLogView.getLayoutParams();
                    params1.width = SdkUtils.dip2px(300f);
                    params1.height = FrameLayout.LayoutParams.MATCH_PARENT;
                    mLogView.setLayoutParams(params1);
                    tvFull.setText("全屏");
                }
            }
        });
    }

    public static void removeLogListView() {
        final ViewGroup viewGroup = SdkUtils.getActivity().findViewById(android.R.id.content);
        if (mLogView != null && mLogView.getParent() != null) {
            viewGroup.removeView(mLogView);
            mLogView = null;
            mAdapter = null;
        }
    }

    public static void addLog(String log) {
        if (mLogView != null && mLogView.getParent() != null && mAdapter != null) {
            if (EnvToggle.isLog && SdkPreferAppSettings.getLogViewEnable(SdkUtils.getApp())) {
                List<String> list = new ArrayList<>();
                list.add(log);

                if (SdkUtils.isUiThread()) {
                    mAdapter.appendDataList(list);
                } else {
                    SdkUtils.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.appendDataList(list);
                        }
                    });
                }
            }
        }
    }
}
