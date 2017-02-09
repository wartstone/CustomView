package com.hill.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hill on 16/11/15.
 */

public class FrameView extends FrameLayout {
    private String TAG = "Hill/FrameView";

    private Context mContext;
    private FrameLayout mRoot;

    private View mAnimView, mLayoutView;
    //private TextView mStartAnimText;
    private TextView mGetMeasureText;

    public FrameView(Context context) {
        this(context, null);
    }

    public FrameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_frame, this);
        mRoot = this;

        mAnimView = findViewById(R.id.frame_animview);
        mLayoutView = findViewById(R.id.frame_layoutview);
        //mStartAnimText = (TextView) findViewById(R.id.frame_startanim);
        mGetMeasureText = (TextView) findViewById(R.id.frame_getmeasure);

//        mStartAnimText.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Log.e(TAG, "mStartAnimText onClick");
//                startAnim();
//            }
//        });

        mGetMeasureText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRoot.getLayoutParams();
                params.height = 900;
                mRoot.setLayoutParams(params);
                mRoot.requestLayout();

                Log.e(TAG, "mGetMeasureText onClick: this.getHeight = " + mRoot.getHeight() + ", this.getMeasuredHeight = " + mRoot.getMeasuredHeight());
            }
        });

        //Log.e(TAG, "this.getHeight = " + this.getHeight() + ", this.getMeasuredHeight = " + this.getMeasuredHeight());


//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenWidth = dm.widthPixels;
//        int screenHeigh = dm.heightPixels;
    }

    private int finalHeight = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "[onMeasure]: widthMeasureSpec = " + widthMeasureSpec + ", heightMeasureSpec = " + heightMeasureSpec);

        int measureViewHeigth = mGetMeasureText.getMeasuredHeight();
        int measureViewWidth = mGetMeasureText.getMeasuredWidth();
        Log.e(TAG, "[onMeasure]: (measureViewHeigth) = " + measureViewHeigth + ", (measureViewWidth) = " + measureViewWidth);


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mAnimViewX = Utils.dip2px(mContext, 133);
        left1 = mAnimViewX;
        top1 = mAnimView.getMeasuredHeight() + 100;
        right1 = left1 + mLayoutView.getMeasuredWidth();
        bottom1 = top1 + mLayoutView.getMeasuredHeight();

        finalHeight = mRoot.getMeasuredHeight();

        measureChild(getChildAt(0), MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(bottom1, MeasureSpec.EXACTLY));

        //setMeasuredDimension(500, 500);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(bottom1, MeasureSpec.EXACTLY));

        Log.e(TAG, "[onMeasure] done");
    }

    int left1, top1, right1, bottom1;
    int mAnimViewX;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "[onLayout]: changed = " + changed + ", left = " + left + ", top = " + top + ", right = " + right + ", bottom = " + bottom);

        super.onLayout(changed, left, top, right, bottom);

        mAnimView.layout(mAnimViewX, 0, mAnimViewX + mAnimView.getMeasuredWidth(), mAnimView.getMeasuredHeight());


        //Log.e(TAG, "left1 = " + left1 + ", top1 = " + top1 + ", right1 = " + right1 + ", bottom1 = " + bottom1);
        mLayoutView.layout(left1, top1, right1, bottom1);

        requestLayout();

        Log.e(TAG, "[onLayout]done");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "[onDraw]");
        super.onDraw(canvas);


        //invalidate();
        Log.e(TAG, "[onDraw] done");
    }

    private void startAnim() {
//        Log.e(TAG, "startAnim");

        Log.e(TAG, "ready to measure");
        mAnimView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Log.e(TAG, "measure done");


        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRoot.getLayoutParams();
        params.height = 900;
        mRoot.setLayoutParams(params);
        mRoot.requestLayout();



        //final int finalHeight = 1000;
        final int oriHeight = mAnimView.getHeight();

        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                float animValue = (float) animation.getAnimatedValue();

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mAnimView.getLayoutParams();
                params.height = (int) (oriHeight + (finalHeight - oriHeight) * animValue);
                Log.e(TAG, "params.height = " + params.height);



                mAnimView.setLayoutParams(params);
//                mAnimView.setScaleX(1 - animValue);
//                mAnimView.setScaleY(1 - animValue);
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });

        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());

        // set target
        animator.setTarget(mAnimView);
        animator.start();
    }
}
