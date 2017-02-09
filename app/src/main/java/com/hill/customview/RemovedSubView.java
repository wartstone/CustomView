package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by hill on 16/11/8.
 */

public class RemovedSubView extends RelativeLayout {
    private String TAG = "RemovedSubView";

    private Context mContext;

    public RemovedSubView(Context context) {
        this(context, null);
    }

    public RemovedSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemovedSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_removedview, this);
    }
}
