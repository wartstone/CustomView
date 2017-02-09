package com.hill.customview;

import android.animation.TypeEvaluator;

/**
 * Created by hill on 16/11/11.
 */

public class CustomEvaluator implements TypeEvaluator<Number> {

    @Override
    public Float evaluate(float fraction, Number startValue, Number endValue) {
        return 0f;
    }
}
