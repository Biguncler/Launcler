package com.example.biguncler.launcher.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.adapter.GridAdapter;
import com.example.biguncler.launcher.adapter.WeatherGridAdapter;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.mode.Weather;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.Constant_my;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Biguncler on 12/10/16.
 */

public class WeatherLayout extends LinearLayout {
    private Context context;
    private GridView gridView;
    private WeatherGridAdapter gridAdapter;


    public WeatherLayout(Context context) {
        super(context);
        init(context);
    }

    public WeatherLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public WeatherLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        this.context=context;
        initView();

    }


    private void initView(){
        LinearLayout layoutParent= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.weather_layout,this);
        gridView= (GridView) layoutParent.findViewById(R.id.view_gv);
        gridAdapter = new WeatherGridAdapter(context, getDefaultDate());
        gridView.setAdapter(gridAdapter);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }



    public void updateGridView(){
        gridAdapter.notifyDataSetChanged();
    }

    public void updateGridView(List<Weather> weathers){
        gridAdapter.setList(weathers==null?getDefaultDate():weathers);
        gridAdapter.notifyDataSetChanged();
    }


    private List<Weather> getDefaultDate(){
        List<Weather> weathers=new ArrayList<>();

        Weather weather1=new Weather();
        weather1.setIconId(R.drawable.icon_weather_004);
        weather1.setTq1("xxx");
        weather1.setTq2("xxx");
        weather1.setQw1("00");
        weather1.setQw2("00");
        weather1.setDate("1000");


        Weather weather2=new Weather();
        weather2.setIconId(R.drawable.icon_weather_004);
        weather2.setTq1("xxx");
        weather2.setTq2("xxx");
        weather2.setQw1("00");
        weather2.setQw2("00");
        weather2.setDate("1000");


        Weather weather3=new Weather();
        weather3.setIconId(R.drawable.icon_weather_004);
        weather3.setTq1("xxx");
        weather3.setTq2("xxx");
        weather3.setQw1("00");
        weather3.setQw2("00");
        weather3.setDate("1000");

        weathers.add(weather1);
        weathers.add(weather2);
        weathers.add(weather3);

        return weathers;

    }
}
