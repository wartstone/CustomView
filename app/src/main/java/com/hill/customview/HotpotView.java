package com.hill.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hill on 16/12/21.
 */

public class HotpotView extends View {
    private String TAG = "Hill/HotpotView";

    private Context mContext;

    private Paint mPaint, mRipplePaint;

    private int solidColor;
    private int rippleColor;
    private int ALPHA = 102;

    private int mIndexX, mIndexY;
    private float counter = 0;
    private int mIncRadius = 0;
    private boolean bExpand = true;

    private int mSolidRadius, mRippleRadius;

    private float mRadiusRate = 0f;
    private boolean mScaleIn = true;

    public HotpotView(Context context) {
        this(context, null);
    }
    public HotpotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HotpotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        initView();
    }

    private void initView() {
        solidColor = Color.argb(ALPHA, 255, 255, 255);
        rippleColor = Color.argb(0, 255, 255, 255);

        mPaint = new Paint();
        mPaint.setColor(solidColor);
        mPaint.setAntiAlias(true);

        mRipplePaint = new Paint();
        mRipplePaint.setColor(rippleColor);
        mRipplePaint.setAntiAlias(true);

        mIndexX = 700;
        mIndexY = 600;

        mSolidRadius = 100;
        mRippleRadius = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if(bExpand) {
//
//        }
//
//
//        mRipplePaint.setAlpha(ALPHA - mIncRadius);
//
//
//
//        canvas.drawCircle(mIndexX, mIndexY, mSolidRadius, mPaint);
//        canvas.drawCircle(mIndexX, mIndexY, mSolidRadius + mIncRadius, mRipplePaint);

        canvas.drawCircle(mIndexX, mIndexY, mSolidRadius, mPaint);

        float size = counter%mSolidRadius + mSolidRadius;
        mRipplePaint.setAlpha((int) ((1-size*1f/(mSolidRadius))*ALPHA));
        canvas.drawCircle(mIndexX, mIndexY, size*mRadiusRate/5, mRipplePaint);
        counter+=0.75;

        if(mRadiusRate <= 10 && mRadiusRate >= 0) {
            if (mScaleIn)
                mRadiusRate+=1;
            else
                mRadiusRate-=1;
            if(mRadiusRate > 10) mRadiusRate = 10;
            if(mRadiusRate < 0) mRadiusRate = 0;
        }

        if(mRadiusRate == 0 && !mScaleIn && getVisibility() == View.VISIBLE)
            super.setVisibility(GONE);

        invalidate();
    }

    @Override
    public void setVisibility(int state) {
        if(state == View.VISIBLE) {
            super.setVisibility(state);
            mScaleIn = true;
        } else {
            mScaleIn = false;
        }
    }
}
