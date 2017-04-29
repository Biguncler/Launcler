package com.example.biguncler.launcher.util;

/**
 * Created by Biguncler on 23/01/2017.
 */

public class ColorUtil {
    /**
     * 从16进制的color中取红色
     * @param color
     * @return
     */
    public static int getRed(int color){
        return (color & 0xff0000) >> 16;
    }

    /**
     * 从16进制的color中取绿色
     * @param color
     * @return
     */
    public static int getGreen(int color){
        return (color & 0x00ff00) >> 8;
    }

    /**
     * 从16进制的color中取蓝色
     * @param color
     * @return
     */
    public static int getBlue(int color){
        return (color & 0x0000ff);
    }

    /**
     * 从16进制的color中取rgb值
     * @param color
     * @return
     */
    public static int[] getRGB(int color){
        int[] rgb=new int[3];
        rgb[0]=getRed(color);
        rgb[1]=getGreen(color);
        rgb[2]=getBlue(color);
        return rgb;
    }


}
