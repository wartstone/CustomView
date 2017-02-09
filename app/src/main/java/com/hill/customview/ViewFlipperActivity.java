package com.hill.customview;

/**
 * Created by hill on 16/12/28.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends Activity implements OnGestureListener,
        OnTouchListener {

    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewflipper);

        mGestureDetector = new GestureDetector(this);
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        mViewFlipper.setOnTouchListener(this);
        mViewFlipper.startFlipping();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        if (e2.getX() - e1.getX() > 0) {
            //mViewFlipper.showPrevious();
        } else {
            //mViewFlipper.showNext();
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        mGestureDetector.onTouchEvent(event);
        return true;
    }

}