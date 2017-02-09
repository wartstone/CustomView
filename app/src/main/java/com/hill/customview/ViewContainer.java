package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by hill on 16/11/16.
 */

public class ViewContainer extends RelativeLayout {
    private String TAG = "Hill/ViewContainer";

    private Context mContext;
    
    public ViewContainer(Context context) {
        this(context, null);
    }

    public ViewContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "[onMeasure]: widthMeasureSpec = " + widthMeasureSpec + ", heightMeasureSpec = " + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.e(TAG, "[onMeasure] done");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "[onLayout]: changed = " + changed + ", left = " + left + ", top = " + top + ", right = " + right + ", bottom = " + bottom);
        super.onLayout(changed, left, top, right, bottom);

        Log.e(TAG, "[onLayout] done");
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_container, this);
    }
}
