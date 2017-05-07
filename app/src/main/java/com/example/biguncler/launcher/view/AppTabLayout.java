package com.example.biguncler.launcher.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.biguncler.launcher.R;
import com.example.biguncler.launcher.activity.AppActivity;
import com.example.biguncler.launcher.adapter.GridAdapter;
import com.example.biguncler.launcher.application.MyApplication;
import com.example.biguncler.launcher.biz.ScaleAnimationMap;
import com.example.biguncler.launcher.mode.AppMode;
import com.example.biguncler.launcher.util.AnimatorUtil;
import com.example.biguncler.launcher.util.AppUtil;
import com.example.biguncler.launcher.util.Constant_my;

/**
 * Created by Biguncler on 12/10/16.
 */

public class AppTabLayout extends LinearLayout {
    private Context context;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private View vCortana;


    public AppTabLayout(Context context) {
        super(context);
        init(context);
    }

    public AppTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public AppTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        this.context=context;
        initView();
        initListener();

    }


    private void initView(){
        LinearLayout layoutParent= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.app_tab_layout,this);
        gridView= (GridView) layoutParent.findViewById(R.id.view_gv);
        gridAdapter = new GridAdapter(context, MyApplication.tabApps);
        gridView.setAdapter(gridAdapter);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setNumColumns(MyApplication.tabApps.size()<6?MyApplication.tabApps.size():5);
        gridView.post(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<MyApplication.tabApps.size();i++){
                    if(Constant_my.APP_PACKAGE_CORTANA.equals(MyApplication.tabApps.get(i).getPackageName())){
                        vCortana=((ViewGroup)gridView.getChildAt(i)).getChildAt(0);
                        gridView.post(runnable);
                        break;
                    }
                }
            }
        });
    }
    /**
     * 初始化监听
     */
    private void initListener() {
        // 单击启动对应app
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                String pk = gridAdapter.getList().get(i).getPackageName();
                // 此处做个特殊处理。当启动小娜时，启动小娜的快捷方式
                if(pk.equals(Constant_my.APP_PACKAGE_CORTANA)){
                    Intent intent=new Intent().setComponent(new ComponentName(Constant_my.APP_PACKAGE_CORTANA,"com.microsoft.bing.dss.widget.CortanaWidgetActivity"));
                    intent.setFlags(335593472);
                    context.startActivity(intent);
                    ((Activity)context).overridePendingTransition(new ScaleAnimationMap(context).getScaleAnimationId(view),0);
                }else{
                    boolean result = AppUtil.luanchApp(context, pk,view);
                    if (!result) {
                        Toast.makeText(context, "启动失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        // 长按删除对应app
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*String pk = gridAdapter.getList().get(i).getPackageName();
                boolean result = AppUtil.uninstallApp(context, pk);*/
                return true;
            }
        });
    }


    public void updateGridView(){
        gridView.setNumColumns(MyApplication.tabApps.size()<6?MyApplication.tabApps.size():5);
        gridAdapter.setList(MyApplication.tabApps);
        gridAdapter.notifyDataSetChanged();
    }


    private void startCortanaAnim(View view){
        ObjectAnimator zoomOutY=AnimatorUtil.getInstance().getObjectAnimator(view,AnimatorUtil.SCALE_Y,1,0.2f);
        zoomOutY.setInterpolator(new AnticipateInterpolator());
        ObjectAnimator zoomOutX=AnimatorUtil.getInstance().getObjectAnimator(view,AnimatorUtil.SCALE_X,1,0.2f);
        zoomOutX.setInterpolator(new AnticipateInterpolator());
        ObjectAnimator zoomInY=AnimatorUtil.getInstance().getObjectAnimator(view,AnimatorUtil.SCALE_Y,0.2f,1);
        zoomInY.setInterpolator(new OvershootInterpolator());
        ObjectAnimator zoomInX=AnimatorUtil.getInstance().getObjectAnimator(view,AnimatorUtil.SCALE_X,0.2f,1);
        zoomInX.setInterpolator(new OvershootInterpolator());
        ObjectAnimator ratotionY=AnimatorUtil.getInstance().getObjectAnimator(view,AnimatorUtil.ROTATION_Y,0,360*10);
        ratotionY.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.play(zoomOutX).before(zoomInX);
        animatorSet.play(zoomOutX).with(zoomOutY);
        animatorSet.play(zoomInX).with(zoomInY);
        animatorSet.play(ratotionY).after(zoomInX);
        animatorSet.start();
    }


    Runnable runnable=new Runnable() {
        @Override
        public void run() {
          startCortanaAnim(vCortana);
            gridView.postDelayed(runnable,5*60*1000);
        }
    };
}
