package com.game.sdk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basesdk.R;


public class DebugLogAdapter extends BaseListViewAdapter<String> {
    private Context mContext;

    public DebugLogAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.sdk_debug_log_item, null);
            convertView.setClickable(true);
        }

        TextView content = convertView.findViewById(R.id.content);
        content.setText(getItem(position));

        return convertView;
    }
}