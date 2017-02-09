package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by hill on 16/11/3.
 */

public class CartItemDetailView extends LinearLayout {
    private String TAG = "CartItemDetailView";
    private Context mContext;

    public CartItemDetailView(Context context) {
        this(context, null);
    }

    public CartItemDetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CartItemDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_cartitemdetail, this);
    }
}
