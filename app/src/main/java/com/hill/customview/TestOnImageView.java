package com.hill.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by hill on 16/12/27.
 */

public class TestOnImageView extends FrameLayout {
    private String TAG = "Hill/TestOnImageView";

    private Context mContext;

    public TestOnImageView(Context context) {
        this(context, null);
    }

    public TestOnImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestOnImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundColor(Color.BLUE);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.goods_0));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(600, 800);
        layoutParams.gravity = Gravity.CENTER;
        addView(imageView, layoutParams);
    }
}
