package com.hill.customview.photo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by hill on 17/1/23.
 */

public class PhotoPickerViewGp extends LinearLayout{
    private final String TAG = "Hill/PhotoPickerViewGp";

    private Context mContext;

    public PhotoPickerViewGp(Context context) {
        this(context, null);
    }
    public PhotoPickerViewGp(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PhotoPickerViewGp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {

    }
}
