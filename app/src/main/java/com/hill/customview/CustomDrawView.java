package com.hill.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hill on 16/12/21.
 */

public class CustomDrawView extends View {
    private String TAG = "Hill/HotpotView";

    private Context mContext;

    private Paint mPaint;

    public CustomDrawView(Context context) {
        this(context, null);
    }
    public CustomDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CustomDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeWidth(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制圆形
        //canvas.drawCircle(500, 600, 200, mPaint);

        //drawColor是直接将View显示区域用某个颜色填充满
//        canvas.drawColor(Color.GREEN);
//
        //绘制弧线区域
//        RectF rect = new RectF(500, 500, 800, 900);
//        canvas.drawArc(rect, //弧线所使用的矩形区域大小
//                0,  //开始角度
//                270, //扫过的角度
//                true, //是否使用中心
//                mPaint);

        //canvas.drawLine(100, 100, 750, 850, mPaint);

//        RectF rect = new RectF(500, 500, 800, 900);
//        canvas.drawOval(rect, mPaint);

        //按照既定点 绘制文本内容
//        canvas.drawPosText("Android", new float[]{
//                10,10, //第一个字母在坐标10,10
//                20,20, //第二个字母在坐标20,20
//                40,40,
//                50,50,
//                70,70,
//                80,80,
//                100,100
//        }, mPaint);

//        RectF rect = new RectF(100, 200, 600, 800);
//        //canvas.drawRect(rect, mPaint);
//
//        canvas.drawRoundRect(rect, 100, 150, mPaint);

//        Path path = new Path(); //定义一条路径
//        path.moveTo(100, 100); //移动到 坐标10,10
//        path.lineTo(500, 600);
//        path.lineTo(200,800);
//        path.lineTo(100, 100);
//
//        canvas.drawPath(path, mPaint);

//        Path path = new Path(); //定义一条路径
//        path.moveTo(100, 100); //移动到 坐标100,100
//        path.lineTo(500, 600);
//        path.lineTo(200,800);
//        path.lineTo(100, 100);
//
//        canvas.drawTextOnPath("Android777开发者博客", path, 50, 100, mPaint);



        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(canvas.getWidth()/2, 200); //将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, 100, mPaint); //画圆圈

        //使用path绘制路径文字
        canvas.save();
        canvas.translate(-75, -75);
        Path path = new Path();
        path.addArc(new RectF(0,0,150,150), -180, 180);
        Paint citePaint = new Paint(mPaint);
        citePaint.setTextSize(14);
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("http://www.android777.com", path, 28, 0, citePaint);
        canvas.restore();

        Paint tmpPaint = new Paint(mPaint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(1);

        float  y=100;
        int count = 60; //总刻度数

        for(int i=0 ; i <count ; i++){
            if(i%5 == 0){
                canvas.drawLine(0f, y, 0, y+12f, mPaint);
                canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);

            }else{
                canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
            }
            canvas.rotate(360/count,0f,0f); //旋转画纸
        }

        //绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        canvas.drawLine(0, 10, 0, -65, mPaint);
    }
}
