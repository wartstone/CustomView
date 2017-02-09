package com.hill.customview;

import android.content.Context;

/**
 * Created by hill on 16/11/16.
 */

public class Utils {

    public static int dip2px(Context context, float dpValue) {
        if(context == null) {
            return (int)dpValue;
        } else {
            float scale = context.getResources().getDisplayMetrics().density;
            return (int)(dpValue * scale + 0.5F);
        }
    }
}
