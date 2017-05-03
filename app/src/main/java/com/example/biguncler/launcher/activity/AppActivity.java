package com.example.biguncler.launcher.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
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
import com.example.biguncler.launcher.util.AnimatorUtil;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.PixUtil;
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
        enterAnimation();
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
            exitAnimation(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    finish();
                }
            });
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
        exitAnimation(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });
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



    private void enterAnimation(){
        AnimatorUtil.getInstance().startAnimator(btText,AnimatorUtil.ALPHA,0,1,200,null,null);
        int height=ScreenUtil.getScreenHeight(this)-(PixUtil.dip2px(this,22+48+2));
        float height2=PixUtil.dip2px(this,85);
        AnimatorUtil.getInstance().startAnimator(layoutCenter,AnimatorUtil.SCALE_Y,height2/height,1,0,height,300,new DecelerateInterpolator(),null);
    }

    private void exitAnimation(AnimatorListenerAdapter listenerAdapter){
        AnimatorUtil.getInstance().startAnimator(btText,AnimatorUtil.ALPHA,1,0,200,null,null);
        int height=ScreenUtil.getScreenHeight(this)-(PixUtil.dip2px(this,22+48+2));
        float height2=PixUtil.dip2px(this,85);
        AnimatorUtil.getInstance().startAnimator(layoutCenter,AnimatorUtil.SCALE_Y,1,height2/height,0,height,300,new AnticipateInterpolator(),listenerAdapter);
    }

}
