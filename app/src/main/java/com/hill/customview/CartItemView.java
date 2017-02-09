package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hill on 16/11/3.
 */

public class CartItemView extends LinearLayout {
    private String TAG = "CartItemView";
    private Context mContext;

    private TextView mPurchaseText;
    private boolean bPurchased = false;

    public CartItemView(Context context) {
        this(context, null);
    }

    public CartItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CartItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_cartitem, this);

        mPurchaseText = (TextView) findViewById(R.id.cartitem_purchase);
        mPurchaseText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bPurchased = true;
                mPurchaseText.setBackgroundResource(R.drawable.bg_cartitem_purchased);
                mPurchaseText.setText("已加入购物车");
            }
        });
    }
}
