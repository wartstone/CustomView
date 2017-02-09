package com.hill.customview;

/**
 * Created by hill on 16/12/28.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hill.customview.ViewPagerTransformAnim.ABaseTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CarouselViewEx extends LinearLayout{
    private String TAG = "Hill/CarouselViewEx";

    private ViewPager mViewPager;
    private ArrayList<View> mPageViewList=new ArrayList<View>();//数据�?  
    private ImageView mImageView;
    private ImageView[] mImageViews;
    //主布�?��部指示当前页面的小圆点视图，LinearLayout  
    private ViewGroup indicatorViewGroup;
    private LayoutInflater mInflater;//定义LayoutInflater  
    private CarouselPagerAdapterEx mCarouselPagerAdapter;//适配�?
    private Timer mTimer=null;//定时�?  
    public int currentIndex=0;//当前显示View页面的序号  
    private Handler mHandler;//处理更换图片消息  
    private CustomViewPagerScroller scroller=null;
    private Context mContext;

    public CarouselViewEx(Context context) {
        super(context);
        init(context);
    }
    public CarouselViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        mContext=context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_carouselviewex, this);
        mViewPager = (ViewPager) findViewById(R.id.marquee_image_viewpager);
        indicatorViewGroup = (ViewGroup) findViewById(R.id.marquee_image_bottomviewgroup);
        mViewPager.setOnPageChangeListener(new pageChangeListener());
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    stopAutoScroller();
                    setScrollerTime(100);
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    startAutoScroller();
                }
                return false;
            }
        });
        mCarouselPagerAdapter =new CarouselPagerAdapterEx();
        mViewPager.setAdapter(mCarouselPagerAdapter);
        initHandle();
    }

    /**
     * 设置滑动动画 
     *  mViewPager.setPageTransformer(true,new CubeOutTransformer()); 
     mViewPager.setPageTransformer(true,new AccordionTransformer()); 
     mViewPager.setPageTransformer(true,new FlipHorizontalTransformer()); 
     mViewPager.setPageTransformer(true,new RotateUpTransformer()); 
     mViewPager.setPageTransformer(true,new ZoomOutTranformer()); 
     mViewPager.setPageTransformer(true,new ZoomOutSlideTransformer()); 
     mViewPager.setPageTransformer(true,new TabletTransformer()); 
     */
    public void setScrollerAnimation(ABaseTransformer animation){
        mViewPager.setPageTransformer(true,animation);
    }
    /**
     * 开始自动滚动 
     */
    public boolean startAutoScroller(){
        return startTime();
    }
    /**
     * 停止自动滚动 
     */
    public boolean stopAutoScroller(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer=null;
            return true;
        }else{
            return false;
        }
    }
    /**
     * 初始化Handle 
     */
    public void initHandle(){
        mHandler = new Handler() {
            @SuppressLint("NewApi") public void handleMessage(Message msg) {
                if(msg.what==1){
                    setScrollerTime(700);
                    mViewPager.setCurrentItem(currentIndex);
                    if(mPageViewList.size()-1==currentIndex){
                        currentIndex=0;
                    }else{
                        currentIndex++;
                    }
                }
            };
        };
    }
    /**
     * 设置滑动时间 
     */
    public void setScrollerTime(int scrollerTime){
        try {
            if(scroller!=null){
                scroller.setTime(scrollerTime);
            }else{
                Field mScroller;
                mScroller = ViewPager.class.getDeclaredField("mScroller");
                mScroller.setAccessible(true);
                scroller= new CustomViewPagerScroller(mViewPager.getContext(),new AccelerateInterpolator());
                scroller.setTime(scrollerTime);
                mScroller.set(mViewPager, scroller);
            }
        } catch (Exception e) {
        }
    }
    /**
     * 创建底部的导航条 
     */
    public void createNavBar(){
        mImageViews = new ImageView[mPageViewList.size()];
        for (int i = 0; i < mImageViews.length; i++) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new LayoutParams(20,20));
            mImageView.setPadding(20, 0, 20, 0);
            if (i == 0) {
                mImageView.setBackgroundResource(R.mipmap.dot_focus);
            } else {
                mImageView.setBackgroundResource(R.mipmap.dot_blur);
            }

            mImageViews[i] = mImageView;

            //把指示作用的远点图片加入底部的视图中  
            indicatorViewGroup.addView(mImageViews[i]);
        }
    }

    class pageChangeListener implements OnPageChangeListener{
        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub  
            for (int i = 0; i < mImageViews.length; i++) {
                if(i == arg0) {
                    mImageViews[i].setBackgroundResource(R.mipmap.dot_focus);
                } else {
                    mImageViews[i].setBackgroundResource(R.mipmap.dot_blur);
                }
            }
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub  

        }
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub  

        }
    }
    /**
     * 启动计时 
     */
    private boolean startTime(){
        if(mTimer==null){
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg=new Message();
                    msg.what=1;
                    mHandler.sendMessage(msg);
                }
            },2000,2000); //每2秒执行一次  
            return true;
        }else{
            return false;
        }
    }
    /**
     * 设置数据 
     * @param
     */
    public void setData(ArrayList<View> pageViewList){
        if(pageViewList!=null){
            this.mPageViewList=pageViewList;
            mCarouselPagerAdapter.setData(mPageViewList);//添加数据
            mCarouselPagerAdapter.notifyDataSetChanged();//通知数据发生改变�?
            createNavBar();//根据数据，创建导航条   
        }
    }
    /**
     * 清理全部数据 
     */
    public void clearData(){
        if(this.mPageViewList!=null){
            this.mPageViewList.clear();
        }
        mViewPager.removeAllViews();
        indicatorViewGroup.removeAllViews();
    }
}  