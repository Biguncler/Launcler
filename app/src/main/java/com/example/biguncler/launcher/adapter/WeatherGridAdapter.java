package com.example.biguncler.launcher.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.mode.AppMode;
import com.example.biguncler.launcher.mode.Weather;
import com.example.biguncler.launcher.util.PixUtil;

import java.util.List;

/**
 * Created by Biguncler on 11/29/16.
 */

public class WeatherGridAdapter extends BaseAdapter{
    private Context context;

    public List<Weather> getList() {
        return list;
    }

    private List<Weather> list;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_weather_adapter,null);
        ImageView imageView= (ImageView) linearLayout.findViewById(R.id.view_iv_item);
        TextView textView= (TextView) linearLayout.findViewById(R.id.view_tv_itme);

        Drawable drawable=context.getResources().getDrawable(list.get(i).getIconId());
        if (MyApplication.isLightTheme) {
            DrawableCompat.setTint(drawable, ContextCompat.getColor(context, android.R.color.white));
        } else {
            DrawableCompat.setTint(drawable, ContextCompat.getColor(context, android.R.color.black));
        }
        imageView.setImageDrawable(drawable);

        String tq=list.get(i).getTq1();
        String qw=list.get(i).getQw1()+"~"+list.get(i).getQw2();
        String text=tq+" "+qw;
        textView.setText(text);
        textView.setTextColor(MyApplication.textColor);


        return linearLayout;
    }

    public WeatherGridAdapter(Context context, List<Weather> list) {
        this.context = context;
        this.list = list;
    }

    public WeatherGridAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }

}
