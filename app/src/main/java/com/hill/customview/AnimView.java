package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

/**
 * Created by hill on 16/11/9.
 */

public class AnimView extends FrameLayout {
    private String TAG = "AnimView";
    private Context mContext;

    public AnimView(Context context) {
        this(context, null);
    }

    public AnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_anim, this);

        View view = new View(mContext);
        view.setBackgroundColor(mContext.getResources().getColor(R.color.vote_blue));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(Utils.dip2px(mContext, 100), Utils.dip2px(mContext, 100));
        params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        addView(view, params);

        Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.scale_in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(anim);
    }
}
