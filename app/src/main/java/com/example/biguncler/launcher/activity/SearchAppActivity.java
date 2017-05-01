package com.example.biguncler.launcher.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.adapter.GridAdapter;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.biz.AppManager;
import com.example.biguncler.launcher.biz.BitmapManager;
import com.example.biguncler.launcher.mode.AppMode;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.PixUtil;
import com.example.biguncler.launcher.view.InputMethodLayout;

import java.util.ArrayList;

public class SearchAppActivity extends BaseActivity {
    private ImageView ivBlurSearch,ivBlurCenter,ivBlurInput;
    private InputMethodLayout layoutInput;
    private Button btText;
    private GridView gridView;
    private LinearLayout layoutParent;
    private GridAdapter gridAdapter;
    private AppManager appManager;
    private LinearLayout layoutApp;
    private FrameLayout layoutCenter,layoutBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_app);
        appManager=new AppManager();
        initView();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        enterActivity();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化view
     */
    private void initView(){
        layoutParent= (LinearLayout) findViewById(R.id.activity_search_app);
        layoutApp= (LinearLayout) findViewById(R.id.layout_ll_app);
        layoutInput= (InputMethodLayout) findViewById(R.id.layout_ll_input_method);
        ivBlurInput=(ImageView) findViewById(R.id.view_iv_blur_input_method);
        ivBlurSearch=(ImageView) findViewById(R.id.view_iv_blur_search);
        ivBlurCenter=(ImageView) findViewById(R.id.view_iv_blur_center);
        btText= (Button) findViewById(R.id.view_bt_search_app);
        gridView= (GridView) findViewById(R.id.view_gv);
        layoutCenter= (FrameLayout) findViewById(R.id.layout_ll_app_seacher_center);
        layoutBottom= (FrameLayout) findViewById(R.id.layout_fl_search_app_bottom);
        gridAdapter=new GridAdapter(this,new ArrayList<AppMode>());
        gridView.setAdapter(gridAdapter);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        blurBackground();
        setActivityTheme();
    }

    /**
     * 初始化监听
     */
    private void initListener(){
        // 单击启动对应activity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String pk=gridAdapter.getList().get(i).getPackageName();
                    boolean result=AppUtil.luanchApp(SearchAppActivity.this,pk,view);
                    if(!result){
                        Toast.makeText(SearchAppActivity.this,"启动失败",Toast.LENGTH_SHORT).show();;
                    }
            }
        });
        // 长按删除对应activity
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pk=gridAdapter.getList().get(i).getPackageName();
                boolean result=AppUtil.uninstallApp(SearchAppActivity.this,pk,view);
                return true;
            }
        });

        layoutInput.setTextWatcher(new InputMethodLayout.TextWatcher() {
            @Override
            public void onTextChangedListener(String text) {
                btText.setText(text);
                if(TextUtils.isEmpty(text.trim())){
                    gridAdapter.setList(new ArrayList<AppMode>());
                    gridAdapter.notifyDataSetChanged();
                }else {
                    gridAdapter.setList(appManager.getInstalledAppByName(MyApplication.apps, text));
                    gridAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onGOClickListener() {
                if(TextUtils.isEmpty(layoutInput.getText())) return;
                if(gridAdapter.getList().size()>0){
                    boolean result=AppUtil.luanchApp(SearchAppActivity.this,gridAdapter.getList().get(0).getPackageName(),gridView.getChildAt(0));
                    if(!result){
                        Toast.makeText(SearchAppActivity.this,"启动失败",Toast.LENGTH_SHORT).show();;
                    }
                }
            }
        });
        layoutParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                   exitAcitivity();
                }
                return true;
            }
        });

    }


   /* *//**
     * 将app添加到最近的使用的app列表里
     * @param appMode
     *//*
    private void addLastApps(AppMode appMode){
        for(AppMode appMode1:MyApplication.lastApps){
            if(appMode.getPackageName().equals(appMode1.getPackageName())){
                MyApplication.lastApps.remove(appMode1);
                break;
            }
        }
        MyApplication.lastApps.add(appMode);
        if(MyApplication.lastApps.size()==5) MyApplication.lastApps.remove(0);
    }*/

    /**
     * 将app从最近的使用的app列表里删除
     * @param appMode
     *//*
    private void removeLastApp(AppMode  appMode){
        for(AppMode appMode1:MyApplication.lastApps){
            if(appMode.getPackageName().equals(appMode1.getPackageName())){
                MyApplication.lastApps.remove(appMode1);
                return;
            }
        }
    }*/
    /**
     * 模糊化tab的背景
     */
    private void blurBackground(){
        BitmapManager bitmapManager=new BitmapManager(SearchAppActivity.this);

        if(MyApplication.bitmaptop!=null){
            ivBlurSearch.setImageBitmap(MyApplication.bitmaptop);
        }

        if(MyApplication.bitmapCentersearch!=null){
            ivBlurCenter.setImageBitmap(MyApplication.bitmapCentersearch);
        }

        if(MyApplication.bitmapCenterInput!=null){
            ivBlurInput.setImageBitmap(MyApplication.bitmapCenterInput);
        }




    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            exitAcitivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPackageAdded() {

    }

    @Override
    protected void onPackageRemoved() {

    }

    @Override
    protected void onPressHome() {
        super.onPressHome();
        exitAcitivity();
    }



    @Override
    protected void onWallpaperChanged() {
        super.onWallpaperChanged();
        blurBackground();
        setActivityTheme();
        gridAdapter.setList(new ArrayList<AppMode>());
        gridAdapter.notifyDataSetChanged();
    }

    private void setActivityTheme(){
            btText.setTextColor(MyApplication.textColor);
            btText.setHintTextColor(MyApplication.textColor);
            layoutApp.setBackgroundColor(MyApplication.tintColor);
            gridView.setBackgroundColor(MyApplication.tintColor);
            layoutInput.setBackgroundColor(MyApplication.tintColor);
            layoutInput.setTextsColor(MyApplication.textColor);
    }



    private void enterScaleAnimator(View target,String propertyName,float start,float end,float pivotX,float pivotY,int time){
        target.setPivotX(pivotX);
        target.setPivotY(pivotY);
        ObjectAnimator animator=ObjectAnimator.ofFloat(target,propertyName,start,end);
        animator.setDuration(time);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }

    private void enterActivity(){
        enterScaleAnimator(layoutCenter,"scaleY",0,1,0,0,400);
        enterScaleAnimator(layoutBottom,"scaleY",0,1,0, PixUtil.dip2px(this,250),400);
        enterScaleAnimator(btText,"scaleX",0,1,0,0,400);
    }

    private void exitAcitivity(){
        exitScaleAnimatorWithFinish(layoutCenter,"scaleY",1,0,0,0,400);
        exitScaleAnimatorWithFinish(layoutBottom,"scaleY",1,0,0,PixUtil.dip2px(SearchAppActivity.this,250),400);
        exitScaleAnimator(btText,"scaleX",1,0,0,0,400);
    }


    private void exitScaleAnimatorWithFinish(View target,String propertyName,float start,float end,float pivotX,float pivotY,int time){
        target.setPivotX(pivotX);
        target.setPivotY(pivotY);
        ObjectAnimator animator=ObjectAnimator.ofFloat(target,propertyName,start,end);
        animator.setDuration(time);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });
        animator.start();
    }

    private void exitScaleAnimator(View target,String propertyName,float start,float end,float pivotX,float pivotY,int time){
        target.setPivotX(pivotX);
        target.setPivotY(pivotY);
        ObjectAnimator animator=ObjectAnimator.ofFloat(target,propertyName,start,end);
        animator.setDuration(time);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.start();
    }





}
