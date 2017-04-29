package com.example.biguncler.launcher.biz;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by wwx285110 on 4/27/2016.
 */
public class MemoryManager {
    private ActivityManager.MemoryInfo mInfo;

    public MemoryManager(Context context) {
        ActivityManager aManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        mInfo = new ActivityManager.MemoryInfo();
        aManager.getMemoryInfo(mInfo);
    }

    /**
     * 获取可用RAM，返回值单位为B
     * @return
     */
    public long getAvailableRAM(){
        return mInfo==null?0:mInfo.availMem;
    }

    /**
     * 获取总RAM，返回值单位为B
     * @return
     */
    public long getTotalRAM(){
        return mInfo==null?0:mInfo.totalMem;
    }

    /**
     *  获取可用ROM，返回值单位为B
     * @return
     */
    public long getAvailableROM(){
        long size=0;
        try{
            File f= Environment.getDataDirectory();
            StatFs stat= new StatFs(f.getPath());
            size=(long)(stat.getBlockSize())*(long)(stat.getAvailableBlocks());
        }catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 获取总ROM，返回值单位为B
     * @return
     */
    public long getTotalROM(){
        long size=0;
        try{
            File f= Environment.getDataDirectory();
            StatFs stat= new StatFs(f.getPath());
            size=(long)(stat.getBlockSize())*(long)(stat.getBlockCount());
        }catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }
}
