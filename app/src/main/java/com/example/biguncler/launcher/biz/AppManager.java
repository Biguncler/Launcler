package com.example.biguncler.launcher.biz;

import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.Log;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.mode.AppMode;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.CharUtil;
import com.example.biguncler.launcher.util.Constant_android;
import com.example.biguncler.launcher.util.Constant_my;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Biguncler on 12/2/16.
 */

public class AppManager {
    /**
     * 根据app的名称获取相应的app
     *
     * @param apps
     * @param appName
     * @return
     */
    public List<AppMode> getInstalledAppByName(List<AppMode> apps, String appName) {
        List<AppMode> list = new ArrayList<>();
        if (TextUtils.isEmpty(appName)) return list;
        if (appName.equals(".")) return apps;
        for (AppMode appMode : apps) {
            if (appMode.getAppName().toUpperCase().contains(appName.toUpperCase())) {
                list.add(appMode);
            }
        }
        return list;
    }

    /**
     * 获取安装在手机中具有launcher的app
     *
     * @return
     */
    public List<AppMode> getInstalledApp(Context context) {
        List<AppMode> apps = new ArrayList<AppMode>();
        List<ResolveInfo> resolveInfos = AppUtil.getEnableLauncherApp(context);
        for (ResolveInfo resolveInfo : resolveInfos) {
            // 设置应用程序的包名
            String pakageName = resolveInfo.activityInfo.packageName;
            // 设置应用程序名字
            String appName = resolveInfo.activityInfo.loadLabel(context.getPackageManager()).toString().toUpperCase();
            if (CharUtil.isChinese(appName)) {
                appName = CharUtil.getPinYinHeadChar(appName).toUpperCase();
            }
            if (appName.contains(" ")) {
                appName = appName.replace(" ", "_");
            }
            // 设置图片
            Drawable icon = resolveInfo.activityInfo.loadIcon(context.getPackageManager());

            AppMode appMode = new AppMode(pakageName, appName, icon);
            apps.add(appMode);

            String str = "public static final String APP_PACKAGE_" + appName.toUpperCase() + "=\"" + pakageName + "\";";
            Log.i("weijunshu", "$=" + str);
        }
        return apps;
    }


    public List<AppMode> getTabApps(Context context) {
        AppMode phone = new AppMode();
        AppMode message = new AppMode();
        AppMode cortana = new AppMode();
        AppMode browser = new AppMode();
        AppMode chrome = new AppMode();
        AppMode camera = new AppMode();
        AppMode camera_x = new AppMode();

        List<AppMode> list = new ArrayList<>();
        for (AppMode appMode : MyApplication.apps) {
            if (Constant_android.APP_PACKAGE_PHONE.equals(appMode.getPackageName())) {
                phone.setAppName(appMode.getAppName());
                phone.setPackageName(appMode.getPackageName());
                Drawable icon=context.getResources().getDrawable(R.drawable.icon_white_phone);
                if (MyApplication.isLightTheme) {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.white));
                } else {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.black));
                }
                phone.setIcon(icon);
            }
            if (Constant_android.APP_PACKAGE_MESSAGING.equals(appMode.getPackageName())) {
                message.setAppName(appMode.getAppName());
                message.setPackageName(appMode.getPackageName());
                Drawable icon=context.getResources().getDrawable(R.drawable.icon_white_message);
                if (MyApplication.isLightTheme) {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.white));
                } else {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.black));
                }
                message.setIcon(icon);
            }
            if (Constant_android.APP_PACKAGE_CAMERA.equals(appMode.getPackageName())) {
                camera.setAppName(appMode.getAppName());
                camera.setPackageName(appMode.getPackageName());
                Drawable icon=context.getResources().getDrawable(R.drawable.icon_white_camera);
                if (MyApplication.isLightTheme) {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.white));
                } else {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.black));
                }
                camera.setIcon(icon);
            }
            if (Constant_android.APP_PACKAGE_BROWSER.equals(appMode.getPackageName())) {
                browser.setAppName(appMode.getAppName());
                browser.setPackageName(appMode.getPackageName());
                Drawable icon=context.getResources().getDrawable(R.drawable.icon_white_chrome);
                if (MyApplication.isLightTheme) {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.white));
                } else {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.black));
                }
                browser.setIcon(icon);
            }
            if (Constant_my.APP_PACKAGE_CHROME.equals(appMode.getPackageName())) {
                chrome.setAppName(appMode.getAppName());
                chrome.setPackageName(appMode.getPackageName());
                Drawable icon=context.getResources().getDrawable(R.drawable.icon_white_chrome);
                if (MyApplication.isLightTheme) {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.white));
                } else {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.black));
                }
                chrome.setIcon(icon);
            }
            if (Constant_my.APP_PACKAGE_CORTANA.equals(appMode.getPackageName())) {
                cortana.setAppName(appMode.getAppName());
                cortana.setPackageName(appMode.getPackageName());
                Drawable icon=context.getResources().getDrawable(R.drawable.icon_white_cortana);
                if (MyApplication.isLightTheme) {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.white));
                } else {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.black));
                }
                cortana.setIcon(icon);
            }
            if ((appMode.getAppName().toUpperCase().equals("XJ") || appMode.getAppName().toUpperCase().equals("CAMERA")) && appMode.getPackageName().toUpperCase().contains("CAMERA")) {
                camera_x.setAppName(appMode.getAppName());
                camera_x.setPackageName(appMode.getPackageName());
                Drawable icon=context.getResources().getDrawable(R.drawable.icon_white_camera);
                if (MyApplication.isLightTheme) {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.white));
                } else {
                    DrawableCompat.setTint(icon, ContextCompat.getColor(context, android.R.color.black));
                }
                camera_x.setIcon(icon);

            }
        }


        if (!TextUtils.isEmpty(phone.getPackageName())) {
            list.add(phone);
        }
        if (!TextUtils.isEmpty(message.getPackageName())) {
            list.add(message);
        }
        if (!TextUtils.isEmpty(cortana.getPackageName())) {
            list.add(cortana);
        }
        if (!TextUtils.isEmpty(camera.getPackageName())) {
            list.add(camera);
        } else {
            if (!TextUtils.isEmpty(camera_x.getPackageName())) {
                list.add(camera_x);
            }
        }

        if (!TextUtils.isEmpty(chrome.getPackageName())) {
            list.add(chrome);
        } else {
            if (!TextUtils.isEmpty(browser.getPackageName())) {
                list.add(browser);
            }
        }


        return list;
    }

    /**
     * 获取最近使用的app
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public List<AppMode> getRecetnUseApp(Context context) {
        List<AppMode> recentApps = new ArrayList<>();
        List<UsageStats> usageStatses = AppUtil.getRecentUseApp(context);
        if (usageStatses.isEmpty()) {
            // 开启权限申请界面
            context.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            return recentApps;
        } else {
            for (UsageStats usageStats : usageStatses) {
                // lastTImeUsed=0为用户不能点击的系统应用，
                if (usageStats.getLastTimeUsed() == 0) continue;
                // launcher 不显示
                if(usageStats.getPackageName().equals("com.example.biguncler.launcher")) continue;
                // 只显示前8个
                if(recentApps.size()==8) break;
                for (AppMode appMode : MyApplication.apps) {
                    if (usageStats.getPackageName().equals(appMode.getPackageName())) {
                        recentApps.add(appMode);
                    }
                }
            }
            return recentApps;
        }
    }

}
