package com.example.biguncler.launcher.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.example.biguncler.launcher.util.BitmapUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Biguncler on 12/5/16.
 */

public class WallpaperUtil {




    public static boolean isWallpaperHightLight(Context context){
        int wallpapebright= BitmapUtil.getBitmapBrightness(getWallpaper(context));
        return wallpapebright>160?true:false;
    }

    public static Bitmap getWallpaper(Context context){
        android.app.WallpaperManager wallpaperManager = android.app.WallpaperManager.getInstance(context);
        // 获取当前壁纸
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        // 将Drawable,转成Bitmap
        Bitmap bm = ((BitmapDrawable) wallpaperDrawable).getBitmap();
        return bm;
    }

    public static void setWallpaper(Context context,Bitmap bitmap){
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
