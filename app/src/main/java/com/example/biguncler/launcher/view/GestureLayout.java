package com.example.biguncler.launcher.view;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.activity.AppActivity;
import com.example.biguncler.launcher.activity.MainActivity;
import com.example.biguncler.launcher.activity.SearchAppActivity;
import com.example.biguncler.launcher.activity.SettingActivity;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.broadcastReceiver.MyDeviceAdminReceiver;
import com.example.biguncler.launcher.db.SharedPreferenceDB;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.Arith;

/**
 * Created by Biguncler on 12/3/16.
 */

public class GestureLayout extends LinearLayout {
    public static final double MIN_SCALE=Math.tan(Math.toRadians(55));
    public static final double MIN_DISTANCE=200;
    public static final double MIN_VELOCITY=700;
    private GestureDetector gestureDetector;
    private Context context;

    public GestureLayout(Context context) {
        super(context);
        init(context);
    }

    public GestureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_POINTER_1_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_2_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_1_UP:
                break;
            case MotionEvent.ACTION_POINTER_2_UP:
                break;
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }









    private void init(Context context){
        this.context=context;
        gestureDetector=new GestureDetector(new MyGestureListener());

    }







        class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        // 单击
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("weijunshu","weijunshu/simpleclick");
            return super.onSingleTapConfirmed(e);
        }
        // 双击
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("weijunshu","weijunshu/doubleclick");
            doDoubleClick();
            return super.onDoubleTap(e);
        }
        // 长按
        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("weijunshu","weijunshu/longpress");
            doLongPress();
            // TODO Auto-generated method stub

        }
        // 上下左右
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("weijunshu","weijunshu/velocityX="+velocityX+"/velocityY="+velocityY+"/e1xy="+e1.getX()+"/"+e1.getY()+"/e2xy="+e2.getX()+"/"+e2.getY());

            double distanceX=Math.abs(e1.getX()-e2.getX());
            double distanceY=Math.abs(e1.getY()-e2.getY());

            if(distanceX<distanceY){
                if(Arith.div(distanceX,distanceY,4)<MIN_SCALE&&Arith.div(Math.abs(velocityX),Math.abs(velocityY),4)<MIN_SCALE&&distanceY>MIN_DISTANCE&&Math.abs(velocityY)>MIN_VELOCITY){
                    //down
                    if(velocityY>0){
                        doDown();
                    }else{
                        //up
                        doUp();
                    }
                }


            }else{
                if(Arith.div(distanceY,distanceX,4)<MIN_SCALE&&Arith.div(Math.abs(velocityY),Math.abs(velocityX),4)<MIN_SCALE&&distanceX>MIN_DISTANCE&&Math.abs(velocityX)>MIN_VELOCITY){
                    //left
                    if(velocityX<0){
                       doLeft();
                    }else{
                        //right
                       doRight();
                    }
                }
            }

            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }


        private void doLeft(){
        }

        private void doRight(){
        }

        private void doUp(){
            startVibrate();
            ((MainActivity)context).getHandler().sendEmptyMessage(MainActivity.FLAG_GESTURE_UP);
        }


        private void doDown(){
            startVibrate();
            ((MainActivity)context).getHandler().sendEmptyMessage(MainActivity.FLAG_GESTURE_DOWN);
        }

        private void doDoubleClick(){
            // 设备安全管理服务    2.2之前的版本是没有对外暴露的 只能通过反射技术获取
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);

            // 申请权限
            ComponentName componentName = new ComponentName(context, MyDeviceAdminReceiver.class);
            // 判断该组件是否有系统管理员的权限
            boolean isAdminActive = devicePolicyManager.isAdminActive(componentName);
            if(isAdminActive){

                devicePolicyManager.lockNow(); // 锁屏

                //devicePolicyManager.resetPassword("123", 0); // 设置锁屏密码
//            devicePolicyManager.wipeData(0);  恢复出厂设置  (建议大家不要在真机上测试) 模拟器不支持该操作

            } else {
                Intent intent = new Intent();
                // 指定动作名称
                intent.setAction(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                // 指定给哪个组件授权
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
                context.startActivity(intent);
            }

        }

        private void doSimpleClick(){

        }

        private void doLongPress(){
            Intent intent=new Intent(context, SettingActivity.class);
            context.startActivity(intent);
            ((Activity)context).overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            startVibrate();



        }


        public void startVibrate(){
           Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
            long [] pattern = {200,20};   //200表示先静止200毫秒,然后在震动过20毫秒
            vibrator.vibrate(pattern,-1);
            //vibrator.cancel();
        }

    }




}
