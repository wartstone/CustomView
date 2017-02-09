package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by hill on 17/1/11.
 */

public class CustomScrollView extends HorizontalScrollView {
    private final String TAG = "Hill/CustomScrollView";

    private Context mContext;

    public CustomScrollView(Context context) {
        this(context, null);
    }
    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {

    }
}
