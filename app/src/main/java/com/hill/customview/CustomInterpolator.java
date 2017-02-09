package com.hill.customview;

import android.animation.TimeInterpolator;

/**
 * Created by hill on 16/11/11.
 */

public class CustomInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        return 0;
    }
}
