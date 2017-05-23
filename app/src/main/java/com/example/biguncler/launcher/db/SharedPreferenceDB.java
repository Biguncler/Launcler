package com.example.biguncler.launcher.db;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Biguncler on 12/11/16.
 */

public class SharedPreferenceDB {

    public static final String ICON_STYLE="ICON_STYLE";
    public static final String BACKGROUD_STYLE="BACKGROUD_STYLE";
    public static final String ICON_BG_STYLE="ICON_BG_STYLE";
    public static final String ANIAMTION_STYLE="ANIAMTION_STYLE";
    public static final String SHOW_BATTERY_LEVEL="SHOW_BATTERY_LEVEL";
    public static final String LAUNCHER_LOOCK_SCREEN="LAUNCHER_LOOCK_SCREEN";
    public static final String RECENT_APP="RECENT_APP";





    public static void save(Context context, String key,String data){
        SharedPreferences sp=context.getSharedPreferences("Session",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key,data);
        editor.commit();
    }


    public static  String get(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences("Session",Context.MODE_PRIVATE);
        return sp.getString(key, "");

    }
}
