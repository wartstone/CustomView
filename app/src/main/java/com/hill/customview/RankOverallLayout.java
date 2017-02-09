package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by hill on 16/11/24.
 */

public class RankOverallLayout extends LinearLayout {
    private String TAG = "Hill/RankOverallLayout";
    private Context mContext;

    public RankOverallLayout(Context context) {
        this(context, null);
    }

    public RankOverallLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RankOverallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {

    }
}
