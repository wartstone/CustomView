package com.hill.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by hill on 16/11/28.
 */

public class PlayStripeLayout extends FrameLayout {
    private static String TAG = "Hill/PlayStripeLayout";

    private Context mContext;

    private Paint mPaint;
    private Bitmap mBitmap;
    private Shader mShader;

    public PlayStripeLayout(Context context) {
        this(context, null);
    }

    public PlayStripeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayStripeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_playstripe, this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.stripe);
        mShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mShader);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Log.e(TAG, "drawChild");
        Rect rect = new Rect(0, 0, getMeasuredWidth() , getMeasuredHeight());

        RectF rectF = new RectF(rect.left, rect.top, rect.right, rect.bottom);
        canvas.drawRoundRect(rectF, 60, 60, mPaint);

        boolean b = super.drawChild(canvas, child, drawingTime);

        Log.e(TAG, "drawChild done");
        return b;
    }
}
