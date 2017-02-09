package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by hill on 17/1/17.
 */

public class ClipView extends FrameLayout {
    private final String TAG = "Hill/LeaveCommentView";

    private Context mContext;

    public ClipView(Context context) {
        this(context, null);
    }
    public ClipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_clipview, this);
    }
}
