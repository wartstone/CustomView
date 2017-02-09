package com.hill.customview.photo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hill.customview.R;

/**
 * Created by hill on 17/1/9.
 */

public class LeaveCommentView extends LinearLayout {
    private final String TAG = "Hill/LeaveCommentView";

    private Context mContext;

    private EditText mContent;

    private String mDefaultEditTxt = "想问大胆问, 你的爱豆会回复你哦~";

    public LeaveCommentView(Context context) {
        this(context, null);
    }
    public LeaveCommentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LeaveCommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        setBackgroundColor(mContext.getResources().getColor(R.color.blur_color));
        LayoutInflater.from(mContext).inflate(R.layout.layout_leave_comment, this);

        mContent = (EditText) findViewById(R.id.lvcmt_edittxt);
    }
}
