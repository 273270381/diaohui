package com.arcgis.mymap.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arcgis.mymap.R;

/**
 * Created by Administrator on 2018/1/26.
 */
public class MyImageButton extends LinearLayout {

    public MyImageButton(Context context, int imageResId, int textResId) {
        super(context);
        mButtonImage = new ImageView(context);
        mButtonText = new TextView(context);
        setImageResource(imageResId);
        mButtonImage.setPadding(0, 0, 0, 5);
        setText(textResId);
        setTextSize(12);
        setTextColor(0x66000000);
        setTextpositin();
        mButtonText.setPadding(0, 0, 0, 0);
        //设置本布局的属性
        setClickable(true);  //可点击
        setFocusable(false);  //可聚焦
        setBackgroundResource(R.color.color24);  //布局才用普通按钮的背景
        setOrientation(LinearLayout.VERTICAL);  //垂直布局

        //首先添加Image，然后才添加Text
        //添加顺序将会影响布局效果
        addView(mButtonImage);
        addView(mButtonText);
    }
    public void setImageResource(int resId) {
        mButtonImage.setImageResource(resId);
    }
    public void setText(int resId) {
        mButtonText.setText(resId);
    }
    public void setText(CharSequence buttonText) {
        mButtonText.setText(buttonText);
    }
    public void setTextSize(float a){
        mButtonText.setTextSize(a);
    }
    public void setTextColor(int color) {
        mButtonText.setTextColor(color);
    }
    public void setTextpositin(){
        mButtonText.setGravity(Gravity.CENTER);
    }
    private ImageView mButtonImage = null;
    private TextView mButtonText = null;
}

