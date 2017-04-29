package com.example.biguncler.launcher.biz;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.util.Arith;
import com.example.biguncler.launcher.util.ScreenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Biguncler on 27/04/2017.
 */

public class ScaleAnimationMap {
    public static Map<String, Integer> map=new HashMap<>();
    static {
        map.put("zoom_in_x1_y1", R.anim.zoom_in_x1_y1);
        map.put("zoom_in_x1_y2", R.anim.zoom_in_x1_y2);
        map.put("zoom_in_x1_y3", R.anim.zoom_in_x1_y3);
        map.put("zoom_in_x1_y4", R.anim.zoom_in_x1_y4);
        map.put("zoom_in_x1_y5", R.anim.zoom_in_x1_y5);
        map.put("zoom_in_x1_y6", R.anim.zoom_in_x1_y6);
        map.put("zoom_in_x1_y7", R.anim.zoom_in_x1_y7);
        map.put("zoom_in_x1_y8", R.anim.zoom_in_x1_y8);
        map.put("zoom_in_x1_y9", R.anim.zoom_in_x1_y9);
        map.put("zoom_in_x1_y10", R.anim.zoom_in_x1_y10);
        map.put("zoom_in_x1_y11", R.anim.zoom_in_x1_y11);
        map.put("zoom_in_x1_y12", R.anim.zoom_in_x1_y12);
        map.put("zoom_in_x1_y13", R.anim.zoom_in_x1_y13);
        map.put("zoom_in_x1_y14", R.anim.zoom_in_x1_y14);
        map.put("zoom_in_x1_y15", R.anim.zoom_in_x1_y15);
        map.put("zoom_in_x1_y16", R.anim.zoom_in_x1_y16);
        map.put("zoom_in_x1_y17", R.anim.zoom_in_x1_y17);
        map.put("zoom_in_x1_y18", R.anim.zoom_in_x1_y18);
        map.put("zoom_in_x1_y19", R.anim.zoom_in_x1_y19);
        map.put("zoom_in_x1_y20", R.anim.zoom_in_x1_y20);
        map.put("zoom_in_x2_y1", R.anim.zoom_in_x2_y1);
        map.put("zoom_in_x2_y2", R.anim.zoom_in_x2_y2);
        map.put("zoom_in_x2_y3", R.anim.zoom_in_x2_y3);
        map.put("zoom_in_x2_y4", R.anim.zoom_in_x2_y4);
        map.put("zoom_in_x2_y5", R.anim.zoom_in_x2_y5);
        map.put("zoom_in_x2_y6", R.anim.zoom_in_x2_y6);
        map.put("zoom_in_x2_y7", R.anim.zoom_in_x2_y7);
        map.put("zoom_in_x2_y8", R.anim.zoom_in_x2_y8);
        map.put("zoom_in_x2_y9", R.anim.zoom_in_x2_y9);
        map.put("zoom_in_x2_y10", R.anim.zoom_in_x2_y10);
        map.put("zoom_in_x2_y11", R.anim.zoom_in_x2_y11);
        map.put("zoom_in_x2_y12", R.anim.zoom_in_x2_y12);
        map.put("zoom_in_x2_y13", R.anim.zoom_in_x2_y13);
        map.put("zoom_in_x2_y14", R.anim.zoom_in_x2_y14);
        map.put("zoom_in_x2_y15", R.anim.zoom_in_x2_y15);
        map.put("zoom_in_x2_y16", R.anim.zoom_in_x2_y16);
        map.put("zoom_in_x2_y17", R.anim.zoom_in_x2_y17);
        map.put("zoom_in_x2_y18", R.anim.zoom_in_x2_y18);
        map.put("zoom_in_x2_y19", R.anim.zoom_in_x2_y19);
        map.put("zoom_in_x2_y20", R.anim.zoom_in_x2_y20);
        map.put("zoom_in_x3_y1", R.anim.zoom_in_x3_y1);
        map.put("zoom_in_x3_y2", R.anim.zoom_in_x3_y2);
        map.put("zoom_in_x3_y3", R.anim.zoom_in_x3_y3);
        map.put("zoom_in_x3_y4", R.anim.zoom_in_x3_y4);
        map.put("zoom_in_x3_y5", R.anim.zoom_in_x3_y5);
        map.put("zoom_in_x3_y6", R.anim.zoom_in_x3_y6);
        map.put("zoom_in_x3_y7", R.anim.zoom_in_x3_y7);
        map.put("zoom_in_x3_y8", R.anim.zoom_in_x3_y8);
        map.put("zoom_in_x3_y9", R.anim.zoom_in_x3_y9);
        map.put("zoom_in_x3_y10", R.anim.zoom_in_x3_y10);
        map.put("zoom_in_x3_y11", R.anim.zoom_in_x3_y11);
        map.put("zoom_in_x3_y12", R.anim.zoom_in_x3_y12);
        map.put("zoom_in_x3_y13", R.anim.zoom_in_x3_y13);
        map.put("zoom_in_x3_y14", R.anim.zoom_in_x3_y14);
        map.put("zoom_in_x3_y15", R.anim.zoom_in_x3_y15);
        map.put("zoom_in_x3_y16", R.anim.zoom_in_x3_y16);
        map.put("zoom_in_x3_y17", R.anim.zoom_in_x3_y17);
        map.put("zoom_in_x3_y18", R.anim.zoom_in_x3_y18);
        map.put("zoom_in_x3_y19", R.anim.zoom_in_x3_y19);
        map.put("zoom_in_x3_y20", R.anim.zoom_in_x3_y20);
        map.put("zoom_in_x4_y1", R.anim.zoom_in_x4_y1);
        map.put("zoom_in_x4_y2", R.anim.zoom_in_x4_y2);
        map.put("zoom_in_x4_y3", R.anim.zoom_in_x4_y3);
        map.put("zoom_in_x4_y4", R.anim.zoom_in_x4_y4);
        map.put("zoom_in_x4_y5", R.anim.zoom_in_x4_y5);
        map.put("zoom_in_x4_y6", R.anim.zoom_in_x4_y6);
        map.put("zoom_in_x4_y7", R.anim.zoom_in_x4_y7);
        map.put("zoom_in_x4_y8", R.anim.zoom_in_x4_y8);
        map.put("zoom_in_x4_y9", R.anim.zoom_in_x4_y9);
        map.put("zoom_in_x4_y10", R.anim.zoom_in_x4_y10);
        map.put("zoom_in_x4_y11", R.anim.zoom_in_x4_y11);
        map.put("zoom_in_x4_y12", R.anim.zoom_in_x4_y12);
        map.put("zoom_in_x4_y13", R.anim.zoom_in_x4_y13);
        map.put("zoom_in_x4_y14", R.anim.zoom_in_x4_y14);
        map.put("zoom_in_x4_y15", R.anim.zoom_in_x4_y15);
        map.put("zoom_in_x4_y16", R.anim.zoom_in_x4_y16);
        map.put("zoom_in_x4_y17", R.anim.zoom_in_x4_y17);
        map.put("zoom_in_x4_y18", R.anim.zoom_in_x4_y18);
        map.put("zoom_in_x4_y19", R.anim.zoom_in_x4_y19);
        map.put("zoom_in_x4_y20", R.anim.zoom_in_x4_y20);
        map.put("zoom_in_x5_y1", R.anim.zoom_in_x5_y1);
        map.put("zoom_in_x5_y2", R.anim.zoom_in_x5_y2);
        map.put("zoom_in_x5_y3", R.anim.zoom_in_x5_y3);
        map.put("zoom_in_x5_y4", R.anim.zoom_in_x5_y4);
        map.put("zoom_in_x5_y5", R.anim.zoom_in_x5_y5);
        map.put("zoom_in_x5_y6", R.anim.zoom_in_x5_y6);
        map.put("zoom_in_x5_y7", R.anim.zoom_in_x5_y7);
        map.put("zoom_in_x5_y8", R.anim.zoom_in_x5_y8);
        map.put("zoom_in_x5_y9", R.anim.zoom_in_x5_y9);
        map.put("zoom_in_x5_y10", R.anim.zoom_in_x5_y10);
        map.put("zoom_in_x5_y11", R.anim.zoom_in_x5_y11);
        map.put("zoom_in_x5_y12", R.anim.zoom_in_x5_y12);
        map.put("zoom_in_x5_y13", R.anim.zoom_in_x5_y13);
        map.put("zoom_in_x5_y14", R.anim.zoom_in_x5_y14);
        map.put("zoom_in_x5_y15", R.anim.zoom_in_x5_y15);
        map.put("zoom_in_x5_y16", R.anim.zoom_in_x5_y16);
        map.put("zoom_in_x5_y17", R.anim.zoom_in_x5_y17);
        map.put("zoom_in_x5_y18", R.anim.zoom_in_x5_y18);
        map.put("zoom_in_x5_y19", R.anim.zoom_in_x5_y19);
        map.put("zoom_in_x5_y20", R.anim.zoom_in_x5_y20);
        map.put("zoom_in_x6_y1", R.anim.zoom_in_x6_y1);
        map.put("zoom_in_x6_y2", R.anim.zoom_in_x6_y2);
        map.put("zoom_in_x6_y3", R.anim.zoom_in_x6_y3);
        map.put("zoom_in_x6_y4", R.anim.zoom_in_x6_y4);
        map.put("zoom_in_x6_y5", R.anim.zoom_in_x6_y5);
        map.put("zoom_in_x6_y6", R.anim.zoom_in_x6_y6);
        map.put("zoom_in_x6_y7", R.anim.zoom_in_x6_y7);
        map.put("zoom_in_x6_y8", R.anim.zoom_in_x6_y8);
        map.put("zoom_in_x6_y9", R.anim.zoom_in_x6_y9);
        map.put("zoom_in_x6_y10", R.anim.zoom_in_x6_y10);
        map.put("zoom_in_x6_y11", R.anim.zoom_in_x6_y11);
        map.put("zoom_in_x6_y12", R.anim.zoom_in_x6_y12);
        map.put("zoom_in_x6_y13", R.anim.zoom_in_x6_y13);
        map.put("zoom_in_x6_y14", R.anim.zoom_in_x6_y14);
        map.put("zoom_in_x6_y15", R.anim.zoom_in_x6_y15);
        map.put("zoom_in_x6_y16", R.anim.zoom_in_x6_y16);
        map.put("zoom_in_x6_y17", R.anim.zoom_in_x6_y17);
        map.put("zoom_in_x6_y18", R.anim.zoom_in_x6_y18);
        map.put("zoom_in_x6_y19", R.anim.zoom_in_x6_y19);
        map.put("zoom_in_x6_y20", R.anim.zoom_in_x6_y20);
        map.put("zoom_in_x7_y1", R.anim.zoom_in_x7_y1);
        map.put("zoom_in_x7_y2", R.anim.zoom_in_x7_y2);
        map.put("zoom_in_x7_y3", R.anim.zoom_in_x7_y3);
        map.put("zoom_in_x7_y4", R.anim.zoom_in_x7_y4);
        map.put("zoom_in_x7_y5", R.anim.zoom_in_x7_y5);
        map.put("zoom_in_x7_y6", R.anim.zoom_in_x7_y6);
        map.put("zoom_in_x7_y7", R.anim.zoom_in_x7_y7);
        map.put("zoom_in_x7_y8", R.anim.zoom_in_x7_y8);
        map.put("zoom_in_x7_y9", R.anim.zoom_in_x7_y9);
        map.put("zoom_in_x7_y10", R.anim.zoom_in_x7_y10);
        map.put("zoom_in_x7_y11", R.anim.zoom_in_x7_y11);
        map.put("zoom_in_x7_y12", R.anim.zoom_in_x7_y12);
        map.put("zoom_in_x7_y13", R.anim.zoom_in_x7_y13);
        map.put("zoom_in_x7_y14", R.anim.zoom_in_x7_y14);
        map.put("zoom_in_x7_y15", R.anim.zoom_in_x7_y15);
        map.put("zoom_in_x7_y16", R.anim.zoom_in_x7_y16);
        map.put("zoom_in_x7_y17", R.anim.zoom_in_x7_y17);
        map.put("zoom_in_x7_y18", R.anim.zoom_in_x7_y18);
        map.put("zoom_in_x7_y19", R.anim.zoom_in_x7_y19);
        map.put("zoom_in_x7_y20", R.anim.zoom_in_x7_y20);
        map.put("zoom_in_x8_y1", R.anim.zoom_in_x8_y1);
        map.put("zoom_in_x8_y2", R.anim.zoom_in_x8_y2);
        map.put("zoom_in_x8_y3", R.anim.zoom_in_x8_y3);
        map.put("zoom_in_x8_y4", R.anim.zoom_in_x8_y4);
        map.put("zoom_in_x8_y5", R.anim.zoom_in_x8_y5);
        map.put("zoom_in_x8_y6", R.anim.zoom_in_x8_y6);
        map.put("zoom_in_x8_y7", R.anim.zoom_in_x8_y7);
        map.put("zoom_in_x8_y8", R.anim.zoom_in_x8_y8);
        map.put("zoom_in_x8_y9", R.anim.zoom_in_x8_y9);
        map.put("zoom_in_x8_y10", R.anim.zoom_in_x8_y10);
        map.put("zoom_in_x8_y11", R.anim.zoom_in_x8_y11);
        map.put("zoom_in_x8_y12", R.anim.zoom_in_x8_y12);
        map.put("zoom_in_x8_y13", R.anim.zoom_in_x8_y13);
        map.put("zoom_in_x8_y14", R.anim.zoom_in_x8_y14);
        map.put("zoom_in_x8_y15", R.anim.zoom_in_x8_y15);
        map.put("zoom_in_x8_y16", R.anim.zoom_in_x8_y16);
        map.put("zoom_in_x8_y17", R.anim.zoom_in_x8_y17);
        map.put("zoom_in_x8_y18", R.anim.zoom_in_x8_y18);
        map.put("zoom_in_x8_y19", R.anim.zoom_in_x8_y19);
        map.put("zoom_in_x8_y20", R.anim.zoom_in_x8_y20);
        map.put("zoom_in_x9_y1", R.anim.zoom_in_x9_y1);
        map.put("zoom_in_x9_y2", R.anim.zoom_in_x9_y2);
        map.put("zoom_in_x9_y3", R.anim.zoom_in_x9_y3);
        map.put("zoom_in_x9_y4", R.anim.zoom_in_x9_y4);
        map.put("zoom_in_x9_y5", R.anim.zoom_in_x9_y5);
        map.put("zoom_in_x9_y6", R.anim.zoom_in_x9_y6);
        map.put("zoom_in_x9_y7", R.anim.zoom_in_x9_y7);
        map.put("zoom_in_x9_y8", R.anim.zoom_in_x9_y8);
        map.put("zoom_in_x9_y9", R.anim.zoom_in_x9_y9);
        map.put("zoom_in_x9_y10", R.anim.zoom_in_x9_y10);
        map.put("zoom_in_x9_y11", R.anim.zoom_in_x9_y11);
        map.put("zoom_in_x9_y12", R.anim.zoom_in_x9_y12);
        map.put("zoom_in_x9_y13", R.anim.zoom_in_x9_y13);
        map.put("zoom_in_x9_y14", R.anim.zoom_in_x9_y14);
        map.put("zoom_in_x9_y15", R.anim.zoom_in_x9_y15);
        map.put("zoom_in_x9_y16", R.anim.zoom_in_x9_y16);
        map.put("zoom_in_x9_y17", R.anim.zoom_in_x9_y17);
        map.put("zoom_in_x9_y18", R.anim.zoom_in_x9_y18);
        map.put("zoom_in_x9_y19", R.anim.zoom_in_x9_y19);
        map.put("zoom_in_x9_y20", R.anim.zoom_in_x9_y20);
        map.put("zoom_in_x10_y1", R.anim.zoom_in_x10_y1);
        map.put("zoom_in_x10_y2", R.anim.zoom_in_x10_y2);
        map.put("zoom_in_x10_y3", R.anim.zoom_in_x10_y3);
        map.put("zoom_in_x10_y4", R.anim.zoom_in_x10_y4);
        map.put("zoom_in_x10_y5", R.anim.zoom_in_x10_y5);
        map.put("zoom_in_x10_y6", R.anim.zoom_in_x10_y6);
        map.put("zoom_in_x10_y7", R.anim.zoom_in_x10_y7);
        map.put("zoom_in_x10_y8", R.anim.zoom_in_x10_y8);
        map.put("zoom_in_x10_y9", R.anim.zoom_in_x10_y9);
        map.put("zoom_in_x10_y10", R.anim.zoom_in_x10_y10);
        map.put("zoom_in_x10_y11", R.anim.zoom_in_x10_y11);
        map.put("zoom_in_x10_y12", R.anim.zoom_in_x10_y12);
        map.put("zoom_in_x10_y13", R.anim.zoom_in_x10_y13);
        map.put("zoom_in_x10_y14", R.anim.zoom_in_x10_y14);
        map.put("zoom_in_x10_y15", R.anim.zoom_in_x10_y15);
        map.put("zoom_in_x10_y16", R.anim.zoom_in_x10_y16);
        map.put("zoom_in_x10_y17", R.anim.zoom_in_x10_y17);
        map.put("zoom_in_x10_y18", R.anim.zoom_in_x10_y18);
        map.put("zoom_in_x10_y19", R.anim.zoom_in_x10_y19);
        map.put("zoom_in_x10_y20", R.anim.zoom_in_x10_y20);

       /* for(int i=1;i<11;i++){
            for(int j=1;j<21;j++){
                String key="\"zoom_in_x"+i+"_y"+j+"\"";
                String value="R.anim.zoom_in_x"+i+"_y"+j;
                Log.i("ANIMATION_MAP","map.put("+key+","+value+");");
            }
        }*/
    }

    private Context context;

    public ScaleAnimationMap(Context context) {
        this.context = context;
    }


    public  int getScaleAnimationId(View view){
        int[] location=new int[2];
        view.getLocationOnScreen(location);
        int h=view.getHeight();
        int w=view.getWidth();

        int x=location[0]+(w/2);
        int y=location[1]+(h/2);
        int height=ScreenUtil.getScreenHeight(context);
        int width=ScreenUtil.getScreenWidth(context);

        int widthOffset=(int)Arith.div(width,10,3);
        int x1=((int)Arith.div(x,widthOffset,3))+1;

        int heightOffset=(int)Arith.div(height,20,3);
        int y1=((int)Arith.div(y,heightOffset,3))+1;

        String key="zoom_in_x"+x1+"_y"+y1;
        //Toast.makeText(context, "x="+x1+"/n"+"y="+y1, Toast.LENGTH_SHORT).show();

        return map.get(key);
    }

}
