package com.hill.customview;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hill on 16/11/8.
 */

public class SmallGoodsItem extends RelativeLayout {
    private String TAG = "SmallGoodsItem";

    private Context mContext;

    private RelativeLayout mRoot;
    private TextView mGoodsText;
    private ImageView mGoodsImg;

    public SmallGoodsItem(Context context) {
        this(context, null);
    }

    public SmallGoodsItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmallGoodsItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mRoot = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_smallgoodsitem, this);
        mGoodsText = (TextView) findViewById(R.id.smallgoodsitem_txt);
        mGoodsImg = (ImageView) findViewById(R.id.smallgoodsitem_img);

        mRoot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "mRoot clicked");
                //anim1();
                //flipanim();
                flipanim2();
            }
        });
    }

    private void anim1() {
        Log.e(TAG, "anim1");

        Log.e(TAG, "mRoot.getParent().getwidth() = " + ((ViewGroup)mRoot.getParent()).getWidth() + ", mRoot.getwidth() = " + mRoot.getWidth());
        ValueAnimator animator = ValueAnimator.ofInt(0, ((ViewGroup)mRoot.getParent()).getWidth() - mRoot.getWidth());
//        ValueAnimator animator1 = ValueAnimator.ofPropertyValuesHolder()
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                Log.e(TAG, "onAnimationUpdate");

                int animatorValue = (int) animation.getAnimatedValue();
                MarginLayoutParams params = (MarginLayoutParams) mRoot.getLayoutParams();
                params.leftMargin = animatorValue;
                mRoot.setLayoutParams(params);

//                mRoot.setAlpha();

                Log.e(TAG, "onAnimationUpdate, params.leftMargin = " + animatorValue);
            }
        });

        animator.setDuration(1000);
//        animator.setRepeatCount(1);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
        //animator.setInterpolator(new AccelerateInterpolator());
        animator.setInterpolator(new DecelerateInterpolator());

        // set target
        animator.setTarget(mRoot);
        animator.start();
    }

    // objectanimator
    private void flipanim() {
        Log.e(TAG, "flipanim");

        ObjectAnimator animator = ObjectAnimator.ofFloat(mRoot, "rotationY", 0.0f, 720.0f);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(1000);

        animator.start();
    }

    private void flipanim2() {
        Log.e(TAG, "flipanim2");

        //ValueAnimator animator = ValueAnimator.ofInt(0, ((ViewGroup)mRoot.getParent()).getWidth() - mRoot.getWidth());
        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("rotationY", 0.0f, 720.0f);
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(holder, holder1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                Log.e(TAG, "flipanim2: onAnimationUpdate");

                mGoodsImg.setRotationY((float)animation.getAnimatedValue("rotationY"));
                mGoodsText.setAlpha((float)animation.getAnimatedValue("alpha"));

//                mRoot.setAlpha();

//                Log.e(TAG, "flipanim2: onAnimationUpdate, params.leftMargin = " + animatorValue);
            }
        });

        animator.setDuration(1000);
//        animator.setRepeatCount(1);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
        //animator.setInterpolator(new AccelerateInterpolator());
        animator.setInterpolator(new AccelerateInterpolator());

        // set target
        animator.setTarget(mRoot);
        animator.start();
        mGoodsText.setVisibility(View.GONE);
    }
}
