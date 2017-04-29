package com.example.biguncler.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Biguncler on 16/01/2017.
 */

public class LockScreenLayout extends LinearLayout{
    private Context context;
    private int lastY;
    private int lastX;
    public LockScreenLayout(Context context) {
        super(context);
        init(context);
    }

    public LockScreenLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LockScreenLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
       this.context=context;
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY= (int) event.getY();
                lastX= (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int offX = (int)event.getX() - lastX;
                int offY = (int)event.getY() - lastY;
                Log.i("weijunshu","weijunshu/lock/distance="+offY+"touchY="+event.getY());
                layout(getLeft(),getTop()+offY,getRight(),getBottom()+offY);
                break;
            case MotionEvent.ACTION_UP:
                lastY=0;
                break;
        }

        return true;
    }*/
}
