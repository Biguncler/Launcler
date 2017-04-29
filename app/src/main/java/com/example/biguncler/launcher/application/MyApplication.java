package com.example.biguncler.launcher.application;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.activity.BaseActivity;
import com.example.biguncler.launcher.biz.AppManager;
import com.example.biguncler.launcher.biz.BitmapManager;
import com.example.biguncler.launcher.biz.ScaleAnimationMap;
import com.example.biguncler.launcher.db.SharedPreferenceDB;
import com.example.biguncler.launcher.mode.AppMode;
import com.example.biguncler.launcher.util.BgStyle;
import com.example.biguncler.launcher.util.BitmapUtil;
import com.example.biguncler.launcher.util.FastBlur;
import com.example.biguncler.launcher.util.WallpaperUtil;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Biguncler on 12/2/16.
 */

public class MyApplication extends Application {
    private BroadcastReceiver receiver, wallpaperReceiver;
    public static String ACTION_EMULATE_WALLPAPER_CHANGED="action_emulate_wallpaper_changed";
    // launcher的样式有黑和白
    public static boolean isLightTheme = true;
    // 是否启动模糊背景模式
    public static String bgStyle;
    // tint的颜色
    public static int tintColor;
    // 文字的颜色
    public static int textColor;
    // 状态栏背景颜色
    public static int statusBarBg;
    // 所有app
    public static List<AppMode> apps;
    public static Map<String,String> appMap;
    // maictivity下面的tab中的5个默认的app
    public static List<AppMode> tabApps;
    // 各界面模糊的图片
    public static Bitmap bitmaptop, bitmapBottom, bitmapCentersearch, bitmapCenterInput, bitmapCenterApp,bitmapSetting,bitmapLockScreen;



    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
        updateDate(false);
    }

    /**
     *
     * @param isEmulateWallpaperChanged 是否模拟wallpaperchanged的广播
     */
    private void

    updateDate(boolean isEmulateWallpaperChanged) {
        // 背景样式
        bgStyle =  SharedPreferenceDB.get(this,SharedPreferenceDB.BACKGROUD_STYLE);;
        if(TextUtils.isEmpty(bgStyle)){
            bgStyle=BgStyle.BG_BLUR;
        }
        // 主题
        isLightTheme = !WallpaperUtil.isWallpaperHightLight(this);
        // tint color
        if (bgStyle.equals(BgStyle.BG_BLUR)) {
            if (isLightTheme) {
                tintColor = getResources().getColor(R.color.color_light_theme_tint);
            } else {
                tintColor = getResources().getColor(R.color.color_dark_theme_tint);
            }
        } else {

            try {
                if(isLightTheme){
                    tintColor=Palette.generate(WallpaperUtil.getWallpaper(this)).getDarkVibrantSwatch().getRgb();
                }else{
                    tintColor=Palette.generate(WallpaperUtil.getWallpaper(this)).getLightVibrantSwatch().getRgb();
                }
            }catch (Exception e){
                int[] rgb=BitmapUtil.getBitmapTintColor(WallpaperUtil.getWallpaper(MyApplication.this));
                tintColor=Color.rgb(rgb[0],rgb[1],rgb[2]);
                e.printStackTrace();
            }
        }

        //
        if(!isLightTheme){
            statusBarBg=getResources().getColor(R.color.color_light_theme_status_bar_bg);
        }else{
            statusBarBg=getResources().getColor(R.color.color_dark_theme_status_bar_bg);
        }
        // 字体颜色
        if (isLightTheme) {
            textColor = getResources().getColor(R.color.color_light_theme_text);
        } else {
            textColor = getResources().getColor(R.color.color_dark_theme_text);
        }

        AppManager appManager = new AppManager();
        apps = appManager.getInstalledApp(this);
        setMap();
        tabApps = appManager.getTabApps(this);
        // 毛玻璃图片
        if(!isEmulateWallpaperChanged){
            bitmaptop = null;
            bitmapBottom = null;
            bitmapCentersearch = null;
            bitmapCenterInput = null;
            bitmapCenterApp = null;
            bitmapSetting=null;
            bitmapLockScreen=null;
            getBlurBitmap(WallpaperUtil.getWallpaper(MyApplication.this));
        }
    }


    /**
     * 注册广播
     */
    private void registerReceiver() {
        wallpaperReceiver = new MyApplication.WallpaperReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Intent.ACTION_WALLPAPER_CHANGED);
        intentFilter2.addAction(ACTION_EMULATE_WALLPAPER_CHANGED);
        registerReceiver(wallpaperReceiver, intentFilter2);

        receiver = new MyApplication.MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        registerReceiver(receiver, intentFilter);


    }


    /**
     * 取消广播注册
     */
    private void unregisterReceiver() {
        unregisterReceiver(receiver);
        unregisterReceiver(wallpaperReceiver);
    }

    /**
     * app安装和卸载广播接收器
     */
    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
                AppManager appManager = new AppManager();
                MyApplication.apps = appManager.getInstalledApp(MyApplication.this);
                setMap();
                tabApps = appManager.getTabApps(MyApplication.this);
                sendBroadcast(BaseActivity.ACTION_APP_INSTALLED);
            } else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
                AppManager appManager = new AppManager();
                MyApplication.apps = appManager.getInstalledApp(MyApplication.this);
                tabApps = appManager.getTabApps(MyApplication.this);
                sendBroadcast(BaseActivity.ACTION_APP_UNINSTALLED);

            }
        }
    }

    /**
     * 壁纸更换广播
     */
    public class WallpaperReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_WALLPAPER_CHANGED)) {
                updateDate(false);
                sendBroadcast(BaseActivity.ACTION_WALLPEPER_UPDATED);
            }else if(action.equals(ACTION_EMULATE_WALLPAPER_CHANGED)){
                updateDate(true);
                sendBroadcast(BaseActivity.ACTION_WALLPEPER_UPDATED);
            }
        }
    }


    /**
     * 发送广播
     *
     * @param action
     */
    private void sendBroadcast(String action) {
        Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    /**
     *
     * @param bitmap
     */
    public void getBlurBitmap(final Bitmap bitmap) {
        if (bitmap == null) {
            return;
        } else {
            ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, output);//把bitmap100%高质量压缩 到 output对象里
            byte[] result = output.toByteArray();//转换成功了
            Glide.with(MyApplication.this).load(result).asBitmap()
                    .transform(new FastBlur(MyApplication.this, 300))
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (resource == null) return;
                            new Thread(){
                                @Override
                                public void run() {
                                    BitmapManager manager = new BitmapManager(MyApplication.this);
                                    bitmapLockScreen=resource;
                                    MyApplication.bitmaptop = manager.getSearchCropBitmap(resource);
                                    MyApplication.bitmapCentersearch = manager.getCenterLittleCropBitmap(resource);
                                    MyApplication.bitmapCenterInput = manager.getInputMethodCropBitmap(resource);
                                    MyApplication.bitmapCenterApp = manager.getCenterCropBitmap(resource);
                                    MyApplication.bitmapBottom = manager.getTabCropBitmap(resource);
                                    MyApplication.bitmapSetting = manager.getSettingCropBitmap(resource);
                                }
                            }.start();
                        }
                    });
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    private void setMap(){
        appMap=new HashMap<>();
        for(AppMode appMode:apps){
            appMap.put(appMode.getAppName().toUpperCase(),appMode.getPackageName());
        }
    }

}
