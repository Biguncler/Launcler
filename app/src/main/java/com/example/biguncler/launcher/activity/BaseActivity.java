package com.example.biguncler.launcher.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.util.StatusBarUtil;
import com.example.biguncler.launcher.util.WallpaperUtil;



/**
 * Created by Biguncler on 12/3/16.
 */

public class BaseActivity extends Activity {
    public static final String ACTION_APP_INSTALLED="com.biguncler.launcher.app_installed";
    public static final String ACTION_APP_UNINSTALLED="com.biguncler.launcher.app_uninstalled";
    public static final String ACTION_WALLPEPER_UPDATED="com.biguncler.launcher.wallpaper_updated";
    public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setMiuiStatusBarDarkMode(this, !MyApplication.isLightTheme);
        StatusBarUtil.setMeizuStatusBarDarkIcon(this,!MyApplication.isLightTheme);
        StatusBarUtil.setStatusTextColor(this);
        /*//透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
*/
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/
        registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    /**
     * 当壁纸更换
     */
    protected  void onWallpaperChanged(){
        StatusBarUtil.setMiuiStatusBarDarkMode(this, !MyApplication.isLightTheme);
        StatusBarUtil.setMeizuStatusBarDarkIcon(this,!MyApplication.isLightTheme);
        StatusBarUtil.setStatusTextColor(this);

    }

    /**
     * 当应用被安装
     */
    protected  void onPackageAdded(){

    }

    /**
     * 当应用被卸载
     */
    protected  void onPackageRemoved(){

    }

    /**
     * 按了home键
     */
    protected  void onPressHome(){

    }

    /**
     * 长按home键
     */
    protected  void onLongPressHome(){

    }

    /**
     * 电池
     */
    protected void onBatteryChanged(Intent intent){

    }

    /**
     * 屏幕关闭
     * @param intent
     */
    protected  void onScreenOff(Intent intent){

    }

    /**
     * 日期
     * @param intent
     */
    protected void onDateChange(Intent intent){

    }

    /**
     * 分钟改变
     * @param intent
     */
    protected void onTimeTick(Intent intent){

    }
    /**
     *注册广播
     */
    private void registerReceiver() {
        receiver = new MainActivity.MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_APP_INSTALLED);
        intentFilter.addAction(ACTION_APP_UNINSTALLED);
        intentFilter.addAction(ACTION_WALLPEPER_UPDATED);
        intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        //intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        //intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        //intentFilter.addAction(Intent.ACTION_TIME_TICK);
        //intentFilter.addAction(ACTION_ADD_SHORTCUT);
        registerReceiver(receiver, intentFilter);


    }


    /**
     * 取消广播注册
     */
    private void unregisterReceiver() {
        unregisterReceiver(receiver);
    }

    /**
     * app安装和卸载,壁纸更换广播接收器
     */
    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(action.equals(ACTION_APP_INSTALLED)){
                onPackageAdded();
            }else if(action.equals(ACTION_APP_UNINSTALLED)){
                onPackageRemoved();
            }else if(action.equals(ACTION_WALLPEPER_UPDATED)){
                onWallpaperChanged();
            }else if(action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){

                String SYSTEM_REASON = "reason";
                String SYSTEM_HOME_KEY = "homekey";
                String SYSTEM_HOME_KEY_LONG = "recentapps";

                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home键
                   onPressHome();
                }else if(TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){
                    Log.i("weijunshu","weijunshugg/lognpress");
                    //表示长按home键
                    onLongPressHome();
                }
            }else if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                onBatteryChanged(intent);
            }else if(action.equals(Intent.ACTION_SCREEN_OFF)){
                onScreenOff(intent);
            }else if(action.equals(Intent.ACTION_DATE_CHANGED)){
               onDateChange(intent);
            }else if(action.equals(Intent.ACTION_TIME_TICK)){
                onTimeTick(intent);
            }else if(action.equals(ACTION_ADD_SHORTCUT)){
                Toast.makeText(BaseActivity.this,"aaaa",Toast.LENGTH_SHORT).show();
                Intent shortcut_intent = intent.getParcelableExtra(Intent.EXTRA_SHORTCUT_INTENT);

                ComponentName componentName=shortcut_intent.getComponent();
                String a=componentName.getClassName();
                String b=componentName.getPackageName();


                // startActivity(new Intent().setComponent(new ComponentName(b,a)));
                startActivity(shortcut_intent);
            }

            }
    }
}
