package com.hill.customview.glidedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

    private RoundDrawable mDrawable;
    private int mRadius;

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

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImage, defStyleAttr, 0);
        mRadius = typedArray.getInt(R.styleable.RoundImage_riv_radius, 0);

        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        mDrawable.draw(canvas);


        Drawable drawable = getDrawable();
        if(drawable == null)
            return;

        if(getWidth() == 0 || getHeight() == 0)
            return;

        if(! (drawable instanceof BitmapDrawable))
            return;

        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        if(b == null)
            return;

        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        int w = getWidth();

        Bitmap circleBitmap = getCircleBitmap(bitmap, w);
        canvas.drawBitmap(circleBitmap, 0, 0, null);
    }

    private Bitmap getCircleBitmap(Bitmap bitmap, int radius) {
        Bitmap sbitmap;
        if(bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            sbitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        else
            sbitmap = bitmap;

        Bitmap output = Bitmap.createBitmap(2 * radius, 2 * radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#BAB399"));

        final Rect rect = new Rect(0, 0, 2 * radius, 2 * radius);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(sbitmap.getWidth() / 2 + 0.7f, sbitmap.getHeight() / 2 + 0.7f, sbitmap.getWidth() / 2 + 0.1f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbitmap, rect, rect, paint);

        return output;
    }
}
