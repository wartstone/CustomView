package com.hill.customview.customdrawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by hill on 17/3/30.
 */

public class StateBorderDrawable extends Drawable {
    private final String TAG = "hill/StateBorderDr";

    private Paint mPaint;
    private RectF mRect;
    private Path mPath;
    private int mColor;
    private int mBorderWidth;
    private int mBorderRadius;
    private ColorStateList mColorStateList;

    private Drawable mInnerImg;

    public StateBorderDrawable(ColorStateList colorStateList, int borderWidth, int borderRadius, Drawable innerDr) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);

        mRect = new RectF();

        mColorStateList = colorStateList;
        mColor = mColorStateList.getDefaultColor();
        mBorderWidth = borderWidth;
        mBorderRadius = borderRadius;

        mInnerImg = innerDr;
    }

    @Override
    public boolean isStateful() {
        return true;
    }

    @Override
    public boolean onStateChange(int[] state) {
        Log.d(TAG, "[onStateChange]");
        int color = mColorStateList.getColorForState(state, mColor);
        if(color != mColor) {
            mColor = color;
            invalidateSelf();
        }

        return false;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        Log.d(TAG, "[onBoundsChange]");
        mPath.reset();

        mRect.set(bounds.left, bounds.top, bounds.right, bounds.bottom);
        mPath.addRoundRect(mRect, 2 * mBorderRadius, 2 * mBorderRadius, Path.Direction.CW);
        mRect.set(bounds.left + mBorderWidth, bounds.top + mBorderWidth, bounds.right - mBorderWidth, bounds.bottom - mBorderWidth);
        mPath.addRoundRect(mRect, mBorderRadius, mBorderRadius, Path.Direction.CW);

        mInnerImg.setBounds(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    @Override public void draw(Canvas canvas) {
        mInnerImg.draw(canvas);

        mPaint.setColor(mColor);
        canvas.drawPath(mPath, mPaint);
    }

    @Override public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
