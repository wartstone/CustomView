package com.hill.customview;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by hill on 16/12/28.
 */

public class CustomViewPagerScroller extends Scroller {
    private int mDuration = 100;

    public CustomViewPagerScroller(Context context) {
        super(context);
    }

    public CustomViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public CustomViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setTime(int scrollerTime){
        mDuration = scrollerTime;
    }
}
