package com.hill.customview.customdrawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.hill.customview.R;

/**
 * Created by hill on 17/3/30.
 */

public class StateBorderImageView extends ImageView {
    private final String TAG = "hill/StateBorderIV";

    private StateBorderDrawable mBorder;

    public StateBorderImageView(Context context) {
        super(context);

        init(context, null, 0, 0);
    }

    public StateBorderImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0, 0);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        setWillNotDraw(false);

        int[][] states = new int[][] {
                {-android.R.attr.state_pressed},
                {android.R.attr.state_pressed}
        };

        int[] colors = new int[] {
                context.getResources().getColor(R.color.vote_blue),
                context.getResources().getColor(R.color.red),
        };

        ColorStateList colorStateList = new ColorStateList(states, colors);
        mBorder = new StateBorderDrawable(colorStateList, getPaddingLeft(), getPaddingLeft()/2, getResources().getDrawable(R.mipmap.cartitem_img));
        mBorder.setCallback(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "[onSizeChanged]");
        super.onSizeChanged(w, h, oldw, oldh);
        mBorder.setBounds(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBorder.draw(canvas);
    }

    @Override
    protected void drawableStateChanged() {
        Log.d(TAG, "[drawableStateChanged]");
        super.drawableStateChanged();
        mBorder.setState(getDrawableState());
    }

    @Override
    protected boolean verifyDrawable(Drawable drawable) {
        Log.d(TAG, "[verifyDrawable]");
        return super.verifyDrawable(drawable) || drawable == mBorder;
    }
}
