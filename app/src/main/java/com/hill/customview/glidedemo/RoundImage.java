package com.hill.customview.glidedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.hill.customview.R;

/**
 * Created by hill on 17/3/28.
 */

public class RoundImage extends ImageView {
    private String TAG = "hill/RoundImage";

    private Context mContext;

    private Paint mPaint;
    private Shader mShader;

    public RoundImage(Context context) {
        this(context, null);
    }

    public RoundImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width==height) {
            canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, getMeasuredWidth()<getMeasuredHeight()?getMeasuredWidth()/2:getMeasuredHeight()/2, mPaint);
        }
        else {
            canvas.drawRoundRect(new RectF(10, 10, width-10, height-10), 10, 10, mPaint);
        }
    }
}
