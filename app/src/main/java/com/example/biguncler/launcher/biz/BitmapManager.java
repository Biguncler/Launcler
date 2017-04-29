package com.example.biguncler.launcher.biz;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.util.Arith;
import com.example.biguncler.launcher.util.BitmapUtil;
import com.example.biguncler.launcher.util.FastBlur;
import com.example.biguncler.launcher.util.PixUtil;
import com.example.biguncler.launcher.util.ScreenUtil;
import com.example.biguncler.launcher.util.StatusBarUtil;
import com.example.biguncler.launcher.util.WallpaperUtil;

import java.io.ByteArrayOutputStream;

/**
 * Created by Biguncler on 12/3/16.
 */

public class BitmapManager {
    private Context context;

    public BitmapManager(Context context) {
        this.context = context;
    }


    /**
     * 获取状态栏的背景bitmap
     * @return
     */
    public Bitmap getStatubarCropBitmap(){
        Bitmap wallpaper=WallpaperUtil.getWallpaper(context);
        int height= StatusBarUtil.getStatusBarHeight(context);
        int width= ScreenUtil.getScreenWidth(context);
        int startX=0;
        int startY=0;
        return BitmapUtil.cropBitmap(wallpaper,startX,startY,width,height);
    }

    /**
     * 截取tab的背景bitmap，tab高度100dp
     * @param bitmap
     * @return
     */
    public Bitmap getTabCropBitmap(Bitmap bitmap){
        int height= PixUtil.dip2px(context,85);
        int width= ScreenUtil.getScreenWidth(context);
        int startX=0;
        int startY=ScreenUtil.getScreenHeight(context)-height;
        return BitmapUtil.cropBitmap(bitmap,startX,startY,width,height);

    }




    /**
     * 截取search的背景bitmap，search高度48dp，宽度屏幕的宽，距离top28dp
     * @param bitmap
     * @return
     */
    public Bitmap getSearchCropBitmap(Bitmap bitmap){
        int width=ScreenUtil.getScreenWidth(context);
        int height=PixUtil.dip2px(context,48);
        int startX=0;
        int startY=PixUtil.dip2px(context,22);
        return BitmapUtil.cropBitmap(bitmap,startX,startY,width,height);

    }


    /**
     * 截取app列表的背景bitmap
     * @param bitmap
     * @return
     */
    public Bitmap getCenterCropBitmap(Bitmap bitmap){
        int width=ScreenUtil.getScreenWidth(context);
        int height=ScreenUtil.getScreenHeight(context)-(PixUtil.dip2px(context,22+48+2));
        int startX=0;
        int startY=PixUtil.dip2px(context,22+48+2);
        return BitmapUtil.cropBitmap(bitmap,startX,startY,width,height);

    }


    /**
     * 截取app search列表的背景bitmap
     * @param bitmap
     * @return
     */
    public Bitmap getCenterLittleCropBitmap(Bitmap bitmap){
        int width=ScreenUtil.getScreenWidth(context);
        int height=PixUtil.dip2px(context,93);
        int startX=0;
        int startY=PixUtil.dip2px(context,22+48+2);
        return BitmapUtil.cropBitmap(bitmap,startX,startY,width,height);

    }





    /**
     * 截取app input的背景bitmap
     * @param bitmap
     * @return
     */
    public Bitmap getInputMethodCropBitmap(Bitmap bitmap){
        int width=ScreenUtil.getScreenWidth(context);
        int height=PixUtil.dip2px(context,250);
        int startX=0;
        int startY=ScreenUtil.getScreenHeight(context)-height;
        return BitmapUtil.cropBitmap(bitmap,startX,startY,width,height);

    }


    /**
     * 截取app Setting的背景bitmap
     * @param bitmap
     * @return
     */
    public Bitmap getTimeCropBitmap(Bitmap bitmap){
        int width=PixUtil.dip2px(context,200);
        int height=PixUtil.dip2px(context,200);
        int startX=ScreenUtil.getScreenWidth(context)/2-PixUtil.dip2px(context,100);
        int startY=ScreenUtil.getScreenHeight(context)/2-PixUtil.dip2px(context,100);
        return BitmapUtil.cropBitmap(bitmap,startX,startY,width,height);

    }


    /**
     * 截取app Setting的背景bitmap
     * @param bitmap
     * @return
     */
    public Bitmap getSettingCropBitmap(Bitmap bitmap){
        int width=ScreenUtil.getScreenWidth(context);
        int height=PixUtil.dip2px(context,200);
        int startX=0;
        int startY=(ScreenUtil.getScreenHeight(context)-height)/2;
        return BitmapUtil.cropBitmap(bitmap,startX,startY,width,height);

    }



}
