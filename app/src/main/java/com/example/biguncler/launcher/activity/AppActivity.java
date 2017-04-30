package com.example.biguncler.launcher.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.adapter.GridAdapter;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.biz.AppManager;
import com.example.biguncler.launcher.biz.BitmapManager;
import com.example.biguncler.launcher.biz.ScaleAnimationMap;
import com.example.biguncler.launcher.mode.AppMode;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.BgStyle;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends BaseActivity {
    private ImageView ivBlurSearch, ivBlurCenter;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private Button btText;
    private LinearLayout layoutApp;


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
            finish();
            overridePendingTransition(R.anim.text_3,R.anim.text_4);
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
        finish();
        overridePendingTransition(R.anim.text_3,R.anim.text_4);
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

}
