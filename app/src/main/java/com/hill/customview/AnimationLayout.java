package com.hill.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by huyaonan on 16/11/2.
 */
public class AnimationLayout extends FrameLayout {

    private Context mContext;

    private TextView mDes;
//    private ClipLayout mClipLayout;
    private com.makeramen.roundedimageview.RoundedImageView mImageView;

    private int SMALL_SIZE = 200;
    private int BIG_SIZE_WIDTH = 0;
    private int BIG_SIZE_HEIGHT = 0;
    private int TARGET_X = 0;
    private int TARGET_Y = 0;

    public AnimationLayout(Context context) {
        this(context, null);
    }

    public AnimationLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        mDes = new TextView(mContext);
        mDes.setBackgroundColor(Color.BLUE);
        addView(mDes);
        mDes.setAlpha(0f);

        mImageView = new RoundedImageView(mContext);
        addView(mImageView, new LayoutParams(SMALL_SIZE, SMALL_SIZE));
        mImageView.setAlpha(0f);
        mImageView.setCornerRadius(SMALL_SIZE/2);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(R.mipmap.cartitem_img);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getWidth();
        int height= getHeight();

        BIG_SIZE_HEIGHT = height/2;
        BIG_SIZE_WIDTH = height/2*3/4;

        TARGET_X = width/4;
        TARGET_Y = height/4;

        mDes.measure(MeasureSpec.makeMeasureSpec(width/2-BIG_SIZE_WIDTH, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(BIG_SIZE_HEIGHT, MeasureSpec.EXACTLY));
    }

    private int startX=0;
    private int startY=0;

    public void showClip(int x, int y) {
        startX = x;
        startY = y;

        mImageView.setTranslationX(x);
        mImageView.setTranslationY(y);
        mImageView.setAlpha(1f);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnim(BIG_SIZE_WIDTH, BIG_SIZE_HEIGHT, TARGET_X, TARGET_Y);
            }
        });
        mDes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    private void showDes() {
        mDes.setTranslationY(TARGET_Y);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float x = TARGET_X + BIG_SIZE_WIDTH*value;

                mDes.setAlpha(value);
                mDes.setTranslationX(x);
            }
        });
        animator.start();
    }

    private void close() {
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float x = TARGET_X + BIG_SIZE_WIDTH*value;

                mDes.setAlpha(value);
                mDes.setTranslationX(x);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                reverse(SMALL_SIZE, SMALL_SIZE, startX, startY);
            }
        });
        animator.start();
    }

    public void reverse(final int width, final int height, final int x, final int y) {
        final float startX = mImageView.getTranslationX();
        final float startY = mImageView.getTranslationY();
        final int startWidth = mImageView.getWidth();
        final int startHeight = mImageView.getHeight();
        final int targetRadius = width/2;
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();

                float targetX = startX + (x - startX) * value;
                float targetY = startY + (y - startY) * value;
                int targetWidth = (int) (startWidth + (width -startWidth) * value);
                int targetHeight = (int) (startHeight + (height -startHeight) * value);

                LayoutParams params = (LayoutParams) mImageView.getLayoutParams();
                params.width = targetWidth;
                params.height = targetHeight;
                mImageView.setTranslationX(targetX);
                mImageView.setTranslationY(targetY);
                mImageView.setLayoutParams(params);

                mImageView.setRotationY(-360*(1-value));

                float radius = targetRadius*value;
                mImageView.setCornerRadius(radius);
            }
        });
        animator.start();
    }

    public void startAnim(final int width, final int height, final int x, final int y) {
        final float startX = mImageView.getTranslationX();
        final float startY = mImageView.getTranslationY();
        final int startWidth = mImageView.getWidth();
        final int startHeight = mImageView.getHeight();
        final int startRadius = mImageView.getWidth()/2;
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();

                float targetX = startX + (x - startX) * value;
                float targetY = startY + (y - startY) * value;
                int targetWidth = (int) (startWidth + (width -startWidth) * value);
                int targetHeight = (int) (startHeight + (height -startHeight) * value);

                LayoutParams params = (LayoutParams) mImageView.getLayoutParams();
                params.width = targetWidth;
                params.height = targetHeight;
                mImageView.setTranslationX(targetX);
                mImageView.setTranslationY(targetY);
                mImageView.setLayoutParams(params);

                mImageView.setRotationY(360*value);

                float radius = startRadius*(1-value);
                mImageView.setCornerRadius(radius);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                showDes();
            }
        });
        animator.start();
    }

}
