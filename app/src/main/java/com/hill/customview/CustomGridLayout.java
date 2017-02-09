package com.hill.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by hill on 17/1/4.
 */

public class CustomGridLayout extends GridLayout {
    private static String TAG = "Hill/CustomGridLayout";

    private Context mContext;

    public CustomGridLayout(Context context) {
        this(context, null);
    }

    public CustomGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initView();
    }

    private void initView() {
        removeAllViews();

//        int total = 12;
//        int column = 3;
//        int row = total / column;
//        setColumnCount(column);
//        setRowCount(row + 1);
//        for (int i = 0, c = 0, r = 0; i < total; i++, c++) {
//            if (c == column) {
//                c = 0;
//                r++;
//            }
//            ImageView oImageView = new ImageView(mContext);
//            oImageView.setImageResource(R.mipmap.ic_launcher);
//
//            //oImageView.setLayoutParams(new LayoutParams(100, 100));
//
//            Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1);
//            Spec colspan = GridLayout.spec(GridLayout.UNDEFINED, 1);
//            if (r == 0 && c == 0) {
//                Log.e("", "spec");
//                colspan = GridLayout.spec(GridLayout.UNDEFINED, 2);
//                rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 2);
//            }
//            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
//                    rowSpan, colspan);
//            gridParam.width = 100;
//            gridParam.height = 100;
//            addView(oImageView, gridParam);
//        }



        setRowCount(2);
        setColumnCount(4);
        setOrientation(HORIZONTAL);


        for(int i = 0; i < 8; i++) {
            View view = new View(mContext);
            if(i % 4 == 0)
                view.setBackgroundColor(Color.RED);
            else if(i % 4 == 1)
                view.setBackgroundColor(Color.GREEN);
            else if(i % 4 == 2)
                view.setBackgroundColor(Color.WHITE);
            else if(i % 4 == 3)
                view.setBackgroundColor(Color.DKGRAY);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.topMargin = Utils.dip2px(mContext, 10);
            layoutParams.rightMargin = Utils.dip2px(mContext, 10);
            layoutParams.rowSpec = GridLayout.spec(i % 2 == 0 ? 0 : 1);
            layoutParams.columnSpec = GridLayout.spec(i / 2);
            layoutParams.width = Utils.dip2px(mContext, 50);
            layoutParams.height = Utils.dip2px(mContext, 50);
            //layoutParams.setGravity(Gravity.FILL);
            addView(view, layoutParams);
        }

//        // 1
//        View view = new View(mContext);
//        view.setBackgroundColor(Color.RED);
//        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(0);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
//
//        // 2
//        view = new View(mContext);
//        view.setBackgroundColor(Color.GREEN);
//        layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(1);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
//
//        // 3
//        view = new View(mContext);
//        view.setBackgroundColor(Color.WHITE);
//        layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(2);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
//
//        // 4
//        view = new View(mContext);
//        view.setBackgroundColor(Color.DKGRAY);
//        layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(3);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
//
//        // 5
//        view = new View(mContext);
//        view.setBackgroundColor(Color.RED);
//        layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(4);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
//
//        // 6
//        view = new View(mContext);
//        view.setBackgroundColor(Color.GREEN);
//        layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(5);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
//
//        // 7
//        view = new View(mContext);
//        view.setBackgroundColor(Color.WHITE);
//        layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(6);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
//
//        // 8
//        view = new View(mContext);
//        view.setBackgroundColor(Color.DKGRAY);
//        layoutParams = new GridLayout.LayoutParams();
//        layoutParams.topMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rightMargin = Utils.dip2px(mContext, 10);
//        layoutParams.rowSpec = GridLayout.spec(0);
//        layoutParams.columnSpec = GridLayout.spec(7);
//        layoutParams.width = Utils.dip2px(mContext, 50);
//        layoutParams.height = Utils.dip2px(mContext, 50);
//        addView(view, layoutParams);
    }

    private void initView2() {
        //setBackgroundColor(Color.GREEN);
        GridLayout layout = (GridLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_customgridlayout, this);
    }
}
