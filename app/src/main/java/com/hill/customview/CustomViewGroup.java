package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hill on 16/11/16.
 */

public class CustomViewGroup extends ViewGroup {
    private String TAG = "Hill/CustomViewGroup";

    private Context mContext;

    private final static int padding = 600;

    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout: changed = " + changed + ", left = " + left + ", top = " + top + ", right = " + right + ", bottom = " + bottom);

//        for (int i = 0, size = getChildCount(); i < size; i++) {
//            View view = getChildAt(i);
//            // 放置子View，宽高都是100
//            view.layout(left, top, left + 300, top + 250);
//            top +=   padding;
//        }

        View view = getChildAt(0);
        // 放置子View，宽高都是100
        view.layout(left, top, left + 300, top + 250);

        int top1 = top +  padding;

        View view1 = getChildAt(1);
        // 放置子View，宽高都是100
        view1.layout(left, top1, left + 300, top1 + 250);

        left +=   padding;

        View view2 = getChildAt(2);
        // 放置子View，宽高都是100
        Log.e(TAG, "ready to layout FrameView");
        view2.layout(left, top, left + 1000, top + 1000);
        Log.e(TAG, "layout FrameView done");


        View view3 = getChildAt(3);
        // 放置子View，宽高都是100
        view3.layout(0, 400, 300, 500);
    }

    private void initView() {

    }
}
