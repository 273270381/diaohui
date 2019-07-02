package com.arcgis.mymap.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arcgis.mymap.R;

public class ImageTextView extends RelativeLayout{
    private ImageView imageView;
    private TextView textView;

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.text_image_layout, this);
        imageView=(ImageView) findViewById(R.id.imageView1);
        textView=(TextView)findViewById(R.id.textView1);
    }
    public void setImageResource(int resId){
        imageView.setImageResource(resId);
    }
    public void setTextViewText(String text) {
        textView.setText(text);
    }
}
