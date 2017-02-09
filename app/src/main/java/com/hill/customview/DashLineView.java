package com.hill.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hill on 16/11/29.
 */

public class DashLineView extends View {
    private static String TAG = "Hill/DashLineView";

    private Context mContext;

    private Paint mPaint;
    private Path mPath;

    private int lineColor;
    private PathEffect mPathEffect = new DashPathEffect(new float[]{3,8,3,3}, 1);

    public DashLineView(Context context) {
        this(context, null);
    }

    public DashLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.DashLineStyle);
        lineColor = typedArray.getInteger(R.styleable.DashLineStyle_lineColor, 0xffff0000);
        int effect = typedArray.getInteger(R.styleable.DashLineStyle_pathEffect, 3);
        mPathEffect = new DashPathEffect(new float[]{30, 80, 90, 18}, 0);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(lineColor);
    }

    private void initView() {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight() / 2;

        //canvas.drawLine(520, height, width, height * 5, mPaint);

        drawDotLine(canvas, width, height);
    }

    private void drawDotLine(Canvas canvas, int width, int y) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(2);

        mPath = new Path();
        mPath.moveTo(0, y);
        mPath.lineTo(width, y * 2);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);
    }
}
