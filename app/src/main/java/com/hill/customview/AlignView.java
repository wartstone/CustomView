package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by hill on 16/11/15.
 */

public class AlignView extends RelativeLayout {
    private String TAG = "AlignView";

    private Context mContext;

    public AlignView(Context context) {
        this(context, null);
    }

    public AlignView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_align, this);
    }
}
