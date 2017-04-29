package com.example.biguncler.launcher.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.example.biguncler.launcher.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Biguncler on 15/12/2016.
 */

public class WaveLinearLayout extends LinearLayout {
    private Context context;

    private float mInitialRadius; // 初始波纹半径
    private float mMaxRadiusRate = 0.85f; // 如果没有设置mMaxRadius，可mMaxRadius = 最小长度 * mMaxRadiusRate;
    private float mMaxRadius; // 最大波纹半径
    private long mDuration = 500; // 一个波纹从创建到消失的持续时间
    private int mSpeed = 500; // 波纹的创建速度，每500ms创建一个
    private Interpolator mInterpolator = new LinearOutSlowInInterpolator();

    private List<Circle> mCircleList = new ArrayList<Circle>();
    private boolean mIsRunning;

    private boolean mMaxRadiusSet;

    private Paint mPaint;
    private long mLastCreateTime;


    public WaveLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public WaveLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public WaveLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context){
        this.context=context;
        setWillNotDraw(false);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.color_press));
        mPaint.setStyle(Paint.Style.FILL);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        Iterator<Circle> iterator = mCircleList.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            if (System.currentTimeMillis() - circle.mCreateTime < mDuration) {
                mPaint.setAlpha(circle.getAlpha());
                canvas.drawCircle(circle.getX(), circle.getY(), circle.getCurrentRadius(), mPaint);
            } else {
                iterator.remove();
            }
        }
        if (mCircleList.size() > 0) {
            postInvalidateDelayed(10);
        }



        super.onDraw(canvas);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int count=event.getPointerCount();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(int i=0;i<count;i++){
                    mCircleList.add(new Circle(event.getX(i),event.getY(i)));
                }
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                for(int i=0;i<count;i++){
                    mCircleList.add(new Circle(event.getX(i),event.getY(i)));
                }
                break;

            case MotionEvent.ACTION_UP:
                break;

        }
        return super.dispatchTouchEvent(event);
    }











    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!mMaxRadiusSet) {
            mMaxRadius = Math.min(w, h) * mMaxRadiusRate / 6.0f;
        }
    }


    private class Circle {
        private long mCreateTime;
        private float x;
        private float y;

        public Circle(float x,float y) {
            this.mCreateTime = System.currentTimeMillis();
            this.x=x;
            this.y=y;
        }

        public int getAlpha() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 255);
        }

        public float getCurrentRadius() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return mInitialRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitialRadius);
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }


    }







}
