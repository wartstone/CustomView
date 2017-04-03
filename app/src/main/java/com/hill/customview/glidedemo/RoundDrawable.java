package com.hill.customview.glidedemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;

/**
 * Created by hill on 17/4/1.
 */

public class RoundDrawable extends Drawable {
    private final String TAG = "hill/StateBorderDr";

    private Paint mPaint;
    private Path mPath;

    private int mRadius;

    public RoundDrawable(int radius) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);

        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);

        mRadius = radius;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(0, 0, mRadius, mPaint);
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
