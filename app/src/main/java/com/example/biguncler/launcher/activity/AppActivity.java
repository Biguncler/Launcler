package com.example.biguncler.launcher.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
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
import com.example.biguncler.launcher.biz.BitmapManager;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.ScreenUtil;

public class AppActivity extends BaseActivity {
    private ImageView ivBlurSearch, ivBlurCenter;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private Button btText;
    private LinearLayout layoutApp;
    private FrameLayout layoutCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        initView();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int pivotx= ScreenUtil.getScreenWidth(this)/2;
        int pivoty=ScreenUtil.getScreenHeight(this)/2;
        enterScaleAnimator(layoutCenter,"scaleY",0,1,pivotx,pivoty,400);
        enterScaleAnimator(layoutCenter,"scaleX",0,1,pivotx,pivoty,400);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化view
     */
    private void initView() {
        layoutApp= (LinearLayout) findViewById(R.id.layout_ll_app);
        ivBlurSearch = (ImageView) findViewById(R.id.view_iv_blur_search);
        ivBlurCenter = (ImageView) findViewById(R.id.view_iv_blur_center);
        btText = (Button) findViewById(R.id.view_bt_title);
        gridView = (GridView) findViewById(R.id.view_gv);
        layoutCenter= (FrameLayout) findViewById(R.id.layout_fl_app_center);
        gridAdapter = new GridAdapter(this, MyApplication.apps);
        gridView.setAdapter(gridAdapter);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        blurBackground();
        setActivityTheme();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        // 单击启动对应app
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pk = gridAdapter.getList().get(i).getPackageName();
                boolean result = AppUtil.luanchApp(AppActivity.this, pk,view);
                if (!result) {
                    Toast.makeText(AppActivity.this, "启动失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 长按删除对应app
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pk = gridAdapter.getList().get(i).getPackageName();
                boolean result = AppUtil.uninstallApp(AppActivity.this, pk,view);
                return true;
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            int pivotx= ScreenUtil.getScreenWidth(this)/2;
            int pivoty=ScreenUtil.getScreenHeight(this)/2;
            exitScaleAnimator(layoutCenter,"scaleY",1,0,pivotx,pivoty,400);
            exitScaleAnimator(layoutCenter,"scaleX",1,0,pivotx,pivoty,400);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onPackageAdded() {
        updateList();
    }

    @Override
    protected void onPackageRemoved() {
        updateList();
    }

    @Override
    protected void onPressHome() {
        super.onPressHome();
        int pivotx= ScreenUtil.getScreenWidth(this)/2;
        int pivoty=ScreenUtil.getScreenHeight(this)/2;
        exitScaleAnimator(layoutCenter,"scaleY",1,0,pivotx,pivoty,400);
        exitScaleAnimator(layoutCenter,"scaleX",1,0,pivotx,pivoty,400);
    }


    @Override
    protected void onWallpaperChanged() {
        super.onWallpaperChanged();
        blurBackground();
        setActivityTheme();
        updateList();
    }

    /**
     * 模糊化tab的背景
     */
    private void blurBackground() {
        BitmapManager bitmapManager = new BitmapManager(AppActivity.this);

        if(MyApplication.bitmaptop!=null){
            ivBlurSearch.setImageBitmap(MyApplication.bitmaptop);
        }

        if(MyApplication.bitmapCenterApp!=null){
            ivBlurCenter.setImageBitmap(MyApplication.bitmapCenterApp);
        }


    }

    private void setActivityTheme() {
        layoutApp.setBackgroundColor(MyApplication.tintColor);
        btText.setTextColor(MyApplication.textColor);
        btText.setHintTextColor(MyApplication.textColor);

        gridView.setBackgroundColor(MyApplication.tintColor);
        //gridAdapter.notifyDataSetChanged();
    }


    private void updateList() {
        gridAdapter.setList(MyApplication.apps);
        gridAdapter.notifyDataSetChanged();
    }

    private void enterScaleAnimator(View target,String propertyName,float start,float end,float pivotX,float pivotY,int time){
        target.setPivotX(pivotX);
        target.setPivotY(pivotY);
        ObjectAnimator animator=ObjectAnimator.ofFloat(target,propertyName,start,end);
        animator.setDuration(time);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }


    private void exitScaleAnimator(View target,String propertyName,float start,float end,float pivotX,float pivotY,int time){
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

}
