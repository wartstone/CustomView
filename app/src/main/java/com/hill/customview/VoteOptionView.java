package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hill on 16/11/15.
 */

public class VoteOptionView extends LinearLayout {
    private String TAG = "VoteOptionView";

    private Context mContext;

    private TextView mOptionText;
    private TextView mPercentText;
    private View mPercentLine;
    private ImageView mImageView;

    public VoteOptionView(Context context) {
        this(context, null);
    }

    public VoteOptionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoteOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_voteoption, this);

//        mOptionText = new TextView(mContext);
//        mOptionText.setText("无时无刻都要保持优雅的井宝");
//        mOptionText.setTextColor(getResources().getColor(R.color.white));
//        mOptionText.setTextSize(13);
//        mOptionText.setGravity(8);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        params.addRule(RelativeLayout.CENTER_VERTICAL);
//        addView(mOptionText, params);
//
//        mImageView = new ImageView(mContext);
//        mImageView.setImageResource(R.mipmap.icon_choosegrey);
//        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        params.addRule(RelativeLayout.CENTER_VERTICAL);
//        addView(mImageView, params);
//
//        mPercentText = new TextView(mContext);
//        mPercentText.setTextColor(getResources().getColor(R.color.white));
//        mPercentText.setTextSize(12);
//        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.CENTER_VERTICAL);
//        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        params.rightMargin = dip2px(mContext, 20);
//        addView(mPercentText, params);
    }

    public static int dip2px(Context context, float dpValue) {
        if(context == null) {
            return (int)dpValue;
        } else {
            float scale = context.getResources().getDisplayMetrics().density;
            return (int)(dpValue * scale + 0.5F);
        }
    }
}
