package com.arcgis.mymap;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.arcgis.mymap.Geological.GeologicalMapActivity;
import com.arcgis.mymap.Geological.GeoprojectActivity;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/4/13.
 */

public class ProjectActivity extends AppCompatActivity {
    private ConvenientBanner convenientBanner;
    private Button bt1,bt2;
    private List<Integer> imgs=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainproject);
        intviews();
        setViews();
        setConvenientBanner();
        addlistener();
    }

    private void addlistener() {
        Clicklistener clicklistener=new Clicklistener();
        bt1.setOnClickListener(clicklistener);
        bt2.setOnClickListener(clicklistener);
    }

    private void setViews() {
        convenientBanner= (ConvenientBanner) findViewById(R.id.convenientBanner);
        imgs.add(R.mipmap.city13);
        imgs.add(R.mipmap.city14);
        imgs.add(R.mipmap.city15);
        imgs.add(R.mipmap.city16);
    }

    private void intviews() {
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
    }


    private void setConvenientBanner() {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        },imgs).setPointViewVisible(true)//设置指示器是否可见
                .setPageIndicator(new int[]{R.mipmap.yuandianbantou,R.mipmap.yuandian});//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        convenientBanner.setManualPageable(true);//设置手动影响（设置了该项无法手动切换）
        convenientBanner.startTurning(2000);     //设置自动切换（同时设置了切换时间间隔）
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);//设置指示器位置（左、中、右）
    }
    protected class Clicklistener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.bt1:
                    Intent a = new Intent(ProjectActivity.this,ProjectManagementActivity.class);
                    startActivity(a);
                break;
                case R.id.bt2:
                    Intent b = new Intent(ProjectActivity.this,GeoprojectActivity.class);
                    startActivity(b);
                break;
            }
        }
    }
    public class LocalImageHolderView implements Holder<Integer>{
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            exitByTwoClick(); //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit=false;
    private void exitByTwoClick() {
        Timer tExit=null;
        if(isExit==false){
            isExit=true;//准备退出
            ToastNotRepeat.show(this,"再按一次退出程序");
            tExit=new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit=false;//取消退出
                }
            },2000);//// 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        }else {
            finish();
            System.exit(0);
        }
    }
}

