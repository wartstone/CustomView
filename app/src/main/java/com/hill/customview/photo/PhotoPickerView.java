package com.hill.customview.photo;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hill.customview.R;
import com.hill.customview.Utils;

/**
 * Created by hill on 17/1/23.
 */

public class PhotoPickerView extends FrameLayout {
    private final String TAG = "Hill/PhotoPickerView";

    private Context mContext;

    private ImageView mImg;
    private View mPickView;

    public PhotoPickerView(Context context) {
        this(context, null);
    }
    public PhotoPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PhotoPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        setBackgroundColor(mContext.getResources().getColor(R.color.gray));

        mImg = new ImageView(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(Utils.dip2px(mContext, 90), Utils.dip2px(mContext, 90));
        addView(mImg, layoutParams);

        mPickView = new View(mContext);
        layoutParams = new FrameLayout.LayoutParams(Utils.dip2px(mContext, 20), Utils.dip2px(mContext, 20));
        layoutParams.gravity = Gravity.RIGHT | Gravity.TOP;
        layoutParams.rightMargin = Utils.dip2px(mContext, 5);
        layoutParams.topMargin = Utils.dip2px(mContext, 5);
        addView(mImg, layoutParams);
    }
}
