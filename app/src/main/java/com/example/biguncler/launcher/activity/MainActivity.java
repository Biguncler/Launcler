package com.example.biguncler.launcher.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.biz.BitmapManager;
import com.example.biguncler.launcher.biz.MemoryManager;
import com.example.biguncler.launcher.biz.ScaleAnimationMap;
import com.example.biguncler.launcher.biz.WaveHelper;
import com.example.biguncler.launcher.db.SharedPreferenceDB;
import com.example.biguncler.launcher.mode.Weather;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.Arith;
import com.example.biguncler.launcher.util.BgStyle;
import com.example.biguncler.launcher.util.BitmapUtil;
import com.example.biguncler.launcher.util.ChineseNumber;
import com.example.biguncler.launcher.util.FastBlur;
import com.example.biguncler.launcher.util.Month;
import com.example.biguncler.launcher.util.PixUtil;
import com.example.biguncler.launcher.util.WallpaperUtil;
import com.example.biguncler.launcher.util.Week;
import com.example.biguncler.launcher.view.AppTabLayout;
import com.example.biguncler.launcher.view.RippleView;
import com.example.biguncler.launcher.view.WaveView;
import com.example.biguncler.launcher.view.WeatherLayout;
import com.hp.hpl.sparta.Text;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends BaseActivity {
    public static final int FLAG_GESTURE_UP=1;
    public static final int FLAG_GESTURE_DOWN=2;
    private ImageView ivBlurTab, ivBlurSearch,ivStatusBarBg;
    private Button btSearch;
    private AppTabLayout layoutTab;
    private LinearLayout layoutSearch;
    private FrameLayout layoutBottom;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        layoutBottom.postDelayed(new Runnable() {
            @Override
            public void run() {
                enterScaleAnimator(layoutBottom,"scaleY",0,1,0,PixUtil.dip2px(MainActivity.this,85),200);
                enterScaleAnimator(btSearch,"scaleX",0,1,0,0,200);
            }
        }, 100);
    }

    @Override
    protected void onPause() {
        super.onPause();
       /* exitScaleAnimator(layoutBottom,"scaleY",1,0,0,PixUtil.dip2px(MainActivity.this,85),200);
        exitScaleAnimator(btSearch,"scaleX",1,0,0,0,200);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化View
     */
    private void initView() {
        ivBlurTab = (ImageView) findViewById(R.id.view_iv_blur_tab);
        ivBlurSearch = (ImageView) findViewById(R.id.view_iv_blur_search);
        ivStatusBarBg= (ImageView) findViewById(R.id.view_iv_status_bar);
        btSearch = (Button) findViewById(R.id.view_bt_search);
        layoutTab = (AppTabLayout) findViewById(R.id.layout_ll_tab);
        layoutSearch= (LinearLayout) findViewById(R.id.layout_ll_search);
        layoutBottom= (FrameLayout) findViewById(R.id.layout_fl_bottom);
        blurBackground();
        setActivityTheme();

    }

    /**
     * 初始化监听
     */
    private void initListener() {
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 启动百度搜索
                boolean result = AppUtil.luanchApp(MainActivity.this, MainActivity.this.getString(R.string.baidu_search),view);
                if (!result) {
                    Toast.makeText(MainActivity.this, "App is not installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 模糊化tab的背景
     */
    private void blurBackground() {
       // if(MyApplication.bgStyle.equals(BgStyle.BG_TINT))return;
        ivBlurTab.post(new Runnable() {
            @Override
            public void run() {
                if(MyApplication.bitmapBottom==null||MyApplication.bitmaptop==null){
                    ivBlurTab.postDelayed(this,500);
                }else{
                    ivBlurSearch.setImageBitmap(MyApplication.bitmaptop);
                    ivBlurTab.setImageBitmap(MyApplication.bitmapBottom);
                }
            }
        });
    }

    private void setActivityTheme() {
        getWindow().setBackgroundDrawable(new BitmapDrawable(WallpaperUtil.getWallpaper(this)));
        layoutSearch.setBackgroundColor(MyApplication.tintColor);
        layoutTab.setBackgroundColor(MyApplication.tintColor);
        btSearch.setTextColor(MyApplication.textColor);
        btSearch.setHintTextColor(MyApplication.textColor);
        layoutTab.updateGridView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ivStatusBarBg.setBackgroundColor(MyApplication.statusBarBg);
        }
        btSearch.setText(getDate());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onWallpaperChanged() {
        super.onWallpaperChanged();
        blurBackground();
        setActivityTheme();

    }

    @Override
    protected void onBatteryChanged(Intent intent) {

        super.onBatteryChanged(intent);
      /*  //获取当前电量
        int curentLevel = intent.getIntExtra("level", 0);
        //电量的总刻度
        int totalLevel = intent.getIntExtra("scale", 100);
        float level=(float) Arith.div(curentLevel,totalLevel,2);

        // 判断电池是否在充电状态
        int status=intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);*/
    }


    @Override
    protected void onPackageAdded() {
        super.onPackageAdded();
        layoutTab.updateGridView();
    }

    @Override
    protected void onPackageRemoved() {
        super.onPackageRemoved();
        layoutTab.updateGridView();
    }

    @Override
    protected void onTimeTick(Intent intent) {
        super.onTimeTick(intent);
        btSearch.setText(getDate());
    }

    private String getDate(){
        try{
            Calendar calendar= Calendar.getInstance();
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH)+1;
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            int week=calendar.get(Calendar.DAY_OF_WEEK);
            String str_month=month>10?(month+""):("0"+month);
            String str_day=day>10?(day+""):("0"+day);
            String date=Week.getWeek(week)+" , "+year+"-"+str_month+"-"+str_day+" , "+getTime();
            return date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }


    /**
     * 获取当前的时间
     * @return
     */
    private String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        return format.format(new Date(System.currentTimeMillis()));
    }

    private void enterScaleAnimator(View target,String propertyName,float start,float end,float pivotX,float pivotY,int time){
        target.setPivotX(pivotX);
        target.setPivotY(pivotY);
        ObjectAnimator animator=ObjectAnimator.ofFloat(target,propertyName,start,end);
        animator.setDuration(time);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }

    private void exitScaleAnimator(View target,String propertyName,float start,float end,float pivotX,float pivotY,int time,AnimatorListenerAdapter listenerAdapter){
        target.setPivotX(pivotX);
        target.setPivotY(pivotY);
        ObjectAnimator animator=ObjectAnimator.ofFloat(target,propertyName,start,end);
        animator.setDuration(time);
        if(listenerAdapter!=null)
        animator.addListener(listenerAdapter);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.start();
    }


    public Handler getHandler() {
        return handler;
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case FLAG_GESTURE_DOWN:
                    exitScaleAnimator(btSearch,"scaleX",1,0,0,0,200,null);
                    exitScaleAnimator(layoutBottom, "scaleY", 1, 0, 0, PixUtil.dip2px(MainActivity.this, 85), 200, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            Intent intent=new Intent(MainActivity.this, SearchAppActivity.class);
                            MainActivity.this.startActivity(intent);
                        }
                    });

                    break;
                case FLAG_GESTURE_UP:
                    exitScaleAnimator(btSearch,"scaleX",1,0,0,0,200,null);
                    exitScaleAnimator(layoutBottom, "scaleY", 1, 0, 0, PixUtil.dip2px(MainActivity.this, 85), 200, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            Intent intent=new Intent(MainActivity.this, AppActivity.class);
                            MainActivity.this.startActivity(intent);
                        }
                    });
                    break;

            }
        }
    };
}

