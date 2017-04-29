package com.example.biguncler.launcher.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.biz.BitmapManager;
import com.example.biguncler.launcher.db.SharedPreferenceDB;
import com.example.biguncler.launcher.util.BgStyle;
import com.example.biguncler.launcher.util.PixUtil;
import com.hp.hpl.sparta.Text;

public class SettingActivity extends BaseActivity {
    private LinearLayout layoutParent,layoutContent;
    private ImageView ivBlurContent;
    private RadioGroup rgBgStyle,rgShowApps;
    private TextView tvBgStyle,tvShowApps;
    private Button btBgSytleBlure,btBgSytleTint, btShowApps,btUnshowApps;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initListener();
        blurBackground();
        setActivityTheme();
    }

    private void initView(){
        layoutParent= (LinearLayout) findViewById(R.id.activity_setting);
        layoutContent= (LinearLayout) findViewById(R.id.layout_ll_setting_content);
        ivBlurContent= (ImageView) findViewById(R.id.view_iv_blur_setting_content);

        tvBgStyle= (TextView) findViewById(R.id.view_tv_bg_style);
        rgBgStyle = (RadioGroup) findViewById(R.id.view_rg_bg_style);
        btBgSytleBlure= (Button) findViewById(R.id.view_bt_bg_style_blur);
        btBgSytleTint= (Button) findViewById(R.id.view_bt_bg_style_tint);
        String bg=SharedPreferenceDB.get(this,SharedPreferenceDB.BACKGROUD_STYLE);
        if(bg.equals(BgStyle.BG_BLUR)){
            rgBgStyle.check(R.id.view_bt_bg_style_blur);
        }else if(bg.equals(BgStyle.BG_TINT)){
            rgBgStyle.check(R.id.view_bt_bg_style_tint);
        }else{
            SharedPreferenceDB.save(this,SharedPreferenceDB.BACKGROUD_STYLE,BgStyle.BG_BLUR);
            rgBgStyle.check(R.id.view_bt_bg_style_blur);
        }

        tvShowApps= (TextView) findViewById(R.id.view_tv_show_apps);
        rgShowApps= (RadioGroup) findViewById(R.id.view_rg_show_apps);
        btShowApps= (Button) findViewById(R.id.view_bt_show_app_true);
        btUnshowApps= (Button) findViewById(R.id.view_bt_show_app_false);
        String isSHowApps=SharedPreferenceDB.get(this,SharedPreferenceDB.SLIDE_UP_TO_SHOW_APPS);
        if(isSHowApps.equals("true")){
            rgShowApps.check(R.id.view_bt_show_app_true);
        }else if(isSHowApps.equals("false")){
            rgShowApps.check(R.id.view_bt_show_app_false);
        }else{
            SharedPreferenceDB.save(this,SharedPreferenceDB.SLIDE_UP_TO_SHOW_APPS,"false");
            rgShowApps.check(R.id.view_bt_show_app_false);
        }
    }




    private void initListener(){
        layoutParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    finish();
                    SettingActivity.this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
                return true;
            }
        });
        rgBgStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.view_bt_bg_style_blur:
                        doClick(SharedPreferenceDB.BACKGROUD_STYLE,BgStyle.BG_BLUR);
                        break;
                    case R.id.view_bt_bg_style_tint:
                        doClick(SharedPreferenceDB.BACKGROUD_STYLE,BgStyle.BG_TINT);
                        break;

                }
            }
        });


        rgShowApps.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.view_bt_show_app_true:
                        doClick(SharedPreferenceDB.SLIDE_UP_TO_SHOW_APPS,"true");
                        break;
                    case R.id.view_bt_show_app_false:
                        doClick(SharedPreferenceDB.SLIDE_UP_TO_SHOW_APPS,"false");
                        break;

                }
            }
        });
    }


    private void doClick(String key,String value) {
        SharedPreferenceDB.save(SettingActivity.this, key,value);
        Intent intent = new Intent(MyApplication.ACTION_EMULATE_WALLPAPER_CHANGED);
        sendBroadcast(intent);
    }


    @Override
    protected void onWallpaperChanged() {
        super.onWallpaperChanged();
        blurBackground();
        setActivityTheme();
    }

    @Override
    protected void onPressHome() {
        super.onPressHome();
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }




    /**
     * 模糊化tab的背景
     */
    private void blurBackground() {
        ivBlurContent.post(new Runnable() {
            @Override
            public void run() {
                if(MyApplication.bitmapSetting==null){
                    ivBlurContent.postDelayed(this,500);
                }else{
                    ivBlurContent.setImageBitmap(MyApplication.bitmapSetting);
                }
            }
        });
    }

    private void setActivityTheme() {
        layoutContent.setBackgroundColor(MyApplication.tintColor);

        tvBgStyle.setTextColor(MyApplication.textColor);
        btBgSytleBlure.setTextColor(MyApplication.textColor);
        btBgSytleTint.setTextColor(MyApplication.textColor);


        tvShowApps.setTextColor(MyApplication.textColor);
        btShowApps.setTextColor(MyApplication.textColor);
        btUnshowApps.setTextColor(MyApplication.textColor);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}


