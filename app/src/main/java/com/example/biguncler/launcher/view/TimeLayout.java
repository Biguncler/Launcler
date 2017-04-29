package com.example.biguncler.launcher.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.biz.BitmapManager;
import com.example.biguncler.launcher.util.GlideCircleTransform;
import com.example.biguncler.launcher.util.WallpaperUtil;

import java.io.ByteArrayOutputStream;

/**
 * Created by Biguncler on 28/12/2016.
 */

public class TimeLayout extends LinearLayout {
    private Context context;
    private ImageView ivSecond;
    public TimeLayout(Context context) {
        super(context);
        init(context);
    }

    public TimeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TimeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context=context;
        LinearLayout frameLayout= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.time_layout,this);
        ivSecond= (ImageView) frameLayout.findViewById(R.id.view_iv_time_second);
        setImageBitmap(ivSecond,new BitmapManager(context).getTimeCropBitmap(WallpaperUtil.getWallpaper(context)));

        ivSecond.post(new Runnable() {
            private float degree=0.3f;
            @Override
            public void run() {
                if(degree>360){
                    degree=0.3f;
                }
                ivSecond.setRotation(degree);
                degree=degree+0.3f;
                ivSecond.postDelayed(this,50);
            }
        });

    }



    private void setImageBitmap(ImageView imageView,Bitmap bitmap){
        ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, output);//把bitmap100%高质量压缩 到 output对象里
        byte[] result = output.toByteArray();//转换成功了
        Glide.with(context).load(result).transform(new GlideCircleTransform(context)).into(imageView);
    }




}
