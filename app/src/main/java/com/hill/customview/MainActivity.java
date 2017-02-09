package com.hill.customview;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private String TAG = "Hill/MainActivity";

    private CustomViewGroup mGp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPagerView viewPagerView = (ViewPagerView) findViewById(R.id.viewpagerview);
//        viewPagerView.init(this);

        //SortDemo demo = new SortDemo();

//        final AnimationLayout layout = (AnimationLayout) findViewById(R.id.goods_layout);
//        findViewById(R.id.start_goods).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                layout.showClip(500, 400);
//            }
//        });

//        mGp = (CustomViewGroup)findViewById(R.id.main_customViewGp);
//
//        TextView a = (TextView) findViewById(R.id.main_txt);
//        a.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "a onclick");
//                View view = mGp.getChildAt(2);
//                view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            }
//        });
    }
}
