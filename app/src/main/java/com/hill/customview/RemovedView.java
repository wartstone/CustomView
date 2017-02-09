package com.hill.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hill on 16/11/8.
 */

public class RemovedView extends RelativeLayout {
    private String TAG = "RemovedView";

    private Context mContext;

    private RelativeLayout mRoot;
    private Button mRemoveBtn;

    private View mRemovedView;
    private TextView mFillViewText;


    public RemovedView(Context context) {
        this(context, null);
    }

    public RemovedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RemovedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_removedview, this);

//        mRemoveBtn = (Button) findViewById(R.id.removedview_btn);
//        mRoot = (RelativeLayout) findViewById(R.id.removedview_root);
//        mRemovedView = findViewById(R.id.removedview_view1);
        mFillViewText = (TextView) findViewById(R.id.removedview_filltext);

        RemovedSubView view = new RemovedSubView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, params);

//        mRemoveBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((ViewGroup)mRemovedView.getParent()).removeView(mRemovedView);
//            }
//        });

        mFillViewText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
