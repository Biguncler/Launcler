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
import com.example.biguncler.launcher.util.GlideCircleTransform;
import com.example.biguncler.launcher.util.PixUtil;


import java.util.List;

/**
 * Created by Biguncler on 11/29/16.
 */

public class GridAdapter extends BaseAdapter{
    private Context context;

    public List<AppMode> getList() {
        return list;
    }

    private List<AppMode> list;
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
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_adapter,null);
        ImageView imageView= (ImageView) linearLayout.findViewById(R.id.view_iv_item);
        TextView textView= (TextView) linearLayout.findViewById(R.id.view_tv_itme);

        Drawable drawable = list.get(i).getIcon();
        imageView.setImageDrawable(drawable);
        textView.setText(list.get(i).getAppName());
        textView.setTextColor(MyApplication.textColor);

        int padding=PixUtil.dip2px(context,4);
        imageView.setPadding(padding,padding,padding,padding);

        return linearLayout;
    }

    public GridAdapter(Context context, List<AppMode> list) {
        this.context = context;
        this.list = list;
    }

    public GridAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<AppMode> list) {
        this.list = list;
    }

}
