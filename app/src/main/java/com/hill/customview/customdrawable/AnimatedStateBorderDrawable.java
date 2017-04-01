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
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by hill on 17/3/31.
 */

public class AnimatedStateBorderDrawable extends Drawable implements Animatable {
    private final String TAG = "hill/AniStateBorderDr";

    private boolean mRunning = false;
    private long mStartTime;
    private int mAnimDuration;
    int mPrevColor;
    int mMiddleColor;
    int mCurColor;

    private Paint mPaint;
    private RectF mRect;
    private Path mPath;
    private int mBorderWidth;
    private int mBorderRadius;
    private ColorStateList mColorStateList;

    private static final long FRAME_DURATION = 1000 / 60;

    public AnimatedStateBorderDrawable(ColorStateList colorStateList, int borderWidth, int borderRadius, int duration) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);

        mRect = new RectF();

        mColorStateList = colorStateList;
        mCurColor = mColorStateList.getDefaultColor();
        mBorderWidth = borderWidth;
        mBorderRadius = borderRadius;
        mAnimDuration = duration;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        Log.d(TAG, "[onBoundsChange]");
        mPath.reset();

        mRect.set(bounds.left, bounds.top, bounds.right, bounds.bottom);
        mPath.addRoundRect(mRect, 2 * mBorderRadius, 2 * mBorderRadius, Path.Direction.CW);
        mRect.set(bounds.left + mBorderWidth, bounds.top + mBorderWidth, bounds.right - mBorderWidth, bounds.bottom - mBorderWidth);
        mPath.addRoundRect(mRect, mBorderRadius, mBorderRadius, Path.Direction.CW);
    }

    @Override public void draw(Canvas canvas) {
        mPaint.setColor(mRunning ? mMiddleColor : mCurColor);
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

    @Override
    public boolean isStateful() {
        return true;
    }

    @Override
    public boolean onStateChange(int[] state) {
        Log.d(TAG, "[onStateChange]");
        int color = mColorStateList.getColorForState(state, mCurColor);
        if(color != mCurColor) {
            if(mAnimDuration > 0) {
                mPrevColor = isRunning() ? mMiddleColor : mCurColor;
                mCurColor = color;
                start();
            } else {
                mPrevColor = color;
                mCurColor = color;
                invalidateSelf();
            }
            return true;
        }

        return false;
    }

    @Override
    public void start() {
        resetAnimation();
        scheduleSelf(mUpdater, SystemClock.uptimeMillis() +  FRAME_DURATION);
        invalidateSelf();
    }

    @Override
    public void stop() {
        mRunning = false;
        unscheduleSelf(mUpdater);
        invalidateSelf();
    }

    @Override
    public boolean isRunning() {
        return mRunning;
    }

    @Override
    public void scheduleSelf(Runnable what, long when) {
        mRunning = true;
        super.scheduleSelf(what, when);
    }

    @Override
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        stop();
    }

    private void resetAnimation() {
        mStartTime = SystemClock.uptimeMillis();
        mMiddleColor = mPrevColor;
    }

    private final Runnable mUpdater = new Runnable() {
        @Override
        public void run() {
            update();
        }
    };

    private void update() {
        long curTime = SystemClock.uptimeMillis();
        float progress = Math.min(1.0f, (float)(curTime - mStartTime) / mAnimDuration);
        mMiddleColor = getMiddleColor(mPrevColor, mCurColor, progress);

        if(progress == 1f)
            mRunning = false;

        if(mRunning)
            scheduleSelf(mUpdater, SystemClock.uptimeMillis() + FRAME_DURATION);

        invalidateSelf();
    }

    private int getMiddleValue(int prev, int next, float factor){
        return Math.round(prev + (next - prev) * factor);
    }

    public int getMiddleColor(int prevColor, int curColor, float factor){
        if(prevColor == curColor)
            return curColor;

        if(factor == 0f)
            return prevColor;
        else if(factor == 1f)
            return curColor;

        int a = getMiddleValue(Color.alpha(prevColor), Color.alpha(curColor), factor);
        int r = getMiddleValue(Color.red(prevColor), Color.red(curColor), factor);
        int g = getMiddleValue(Color.green(prevColor), Color.green(curColor), factor);
        int b = getMiddleValue(Color.blue(prevColor), Color.blue(curColor), factor);

        return Color.argb(a, r, g, b);
    }

}
