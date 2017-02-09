package com.hill.customview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import static android.R.attr.screenDensity;

/**
 * Created by hill on 16/12/28.
 */

public class ViewFlipper2Activity extends Activity{
    private String TAG = "Hill/ViewFlipper2Aty";

    private ViewFlipper mViewFlipper;
    private float lastX;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewflipper2);
        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
    }

    private float pressDownPoint;
    private int flipperViewCount;

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                Log.d(TAG, "[onTouchEvent] x = " + touchevent.getX());
                lastX = touchevent.getX();

                //Gets the startpoint for you finger
                pressDownPoint = touchevent.getX();

                //Gets how many view there is in the viewflipper
                flipperViewCount = mViewFlipper.getChildCount();

                //Checks if there is a view to the left of the current view
//if there is, it positions it to the left of the current view
                if (mViewFlipper.getDisplayedChild() > 0)
                {
                    View leftChild = mViewFlipper.getChildAt(mViewFlipper.getDisplayedChild() - 1);
                    //You must set the left view to invisible or visible
//or it will not move to the position you tell it
                    leftChild.setVisibility(View.INVISIBLE);
                    leftChild.layout(-mViewFlipper.getMeasuredWidth(),
                            leftChild.getTop(), 0,
                            leftChild.getBottom());
                }

                //Same as above but for the view to the right
                if (mViewFlipper.getDisplayedChild() < flipperViewCount - 1)
                {
                    View rightChild = mViewFlipper.getChildAt(mViewFlipper.getDisplayedChild() + 1);
                    rightChild.setVisibility(View.INVISIBLE);
                    rightChild.layout(mViewFlipper.getMeasuredWidth(),
                            rightChild.getTop(), mViewFlipper.getMeasuredWidth() * 2,
                            rightChild.getBottom());
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            {
//                float currentX = touchevent.getX();
//                if (lastX < currentX)
//                {
//                    if (mViewFlipper.getDisplayedChild()==0) break;
//                    mViewFlipper.setInAnimation(this, R.anim.in_from_left);
//                    mViewFlipper.setOutAnimation(this, R.anim.out_to_right);
//                    mViewFlipper.showNext();
//                }
//                if (lastX > currentX)
//                {
//                    if (mViewFlipper.getDisplayedChild()==1) break;
//                    mViewFlipper.setInAnimation(this, R.anim.in_from_right);
//                    mViewFlipper.setOutAnimation(this, R.anim.out_to_left);
//                    mViewFlipper.showPrevious();
//                }


                float releasePoint = touchevent.getRawX();

                //Calculates if the fling is to the right or left
//The screenDensity variable is simply the density of the device
//Have in mind that this will not flipp the viewflipper if you drag
//your finger less than about 0.5cm (depeding on the device)
//In that case you need to make an animation that takes the view back
//to its original position. Else it will just get stuck where you
//let go with your finger.
                if (Math.abs(pressDownPoint - releasePoint) / screenDensity > 30)
                {
                    if (pressDownPoint > releasePoint)
                    {
                        if (mViewFlipper.getDisplayedChild()==0) break;
                        mViewFlipper.setInAnimation(this, R.anim.in_from_left);
                        mViewFlipper.setOutAnimation(this, R.anim.out_to_right);
                        mViewFlipper.showNext();
                    }
                    else
                    {
                        if (mViewFlipper.getDisplayedChild()==1) break;
                        mViewFlipper.setInAnimation(this, R.anim.in_from_right);
                        mViewFlipper.setOutAnimation(this, R.anim.out_to_left);
                        mViewFlipper.showPrevious();
                    }
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                View currentView = mViewFlipper.getCurrentView();

                //Moves the current view
//screenWidth is based on the current devices screen width
                currentView.layout((int)(touchevent.getRawX() - pressDownPoint),
                        currentView.getTop(), (int)(touchevent.getRawX() - pressDownPoint) + mViewFlipper.getMeasuredWidth(),
                        currentView.getBottom());

                //Moves the view to the left if there is one
                if (mViewFlipper.getDisplayedChild() > 0)
                {
                    View leftChild = mViewFlipper.getChildAt(mViewFlipper.getDisplayedChild() - 1);
                    leftChild.layout((int)(touchevent.getRawX() - pressDownPoint - mViewFlipper.getMeasuredWidth()),
                            leftChild.getTop(), (int)(touchevent.getRawX() - pressDownPoint),
                            leftChild.getBottom());

                    //Sets the left view to visible so it shows
                    if (leftChild.getVisibility() == View.INVISIBLE)
                    {
                        leftChild.setVisibility(View.VISIBLE);
                    }
                }

                //Same as above but for the view to the right
                if (mViewFlipper.getDisplayedChild() < flipperViewCount - 1)
                {
                    View rightChild = mViewFlipper.getChildAt(mViewFlipper.getDisplayedChild() + 1);
                    rightChild.layout((int)(touchevent.getRawX() - pressDownPoint + mViewFlipper.getMeasuredWidth()),
                            rightChild.getTop(), (int)(touchevent.getRawX() - pressDownPoint + (mViewFlipper.getMeasuredWidth() * 2)),
                            rightChild.getBottom());

                    if (rightChild.getVisibility() == View.INVISIBLE)
                    {
                        rightChild.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        return false;
    }
}
