package com.arcgis.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arcgis.mymap.Geological.GeoCoorTransActivity;
import com.arcgis.mymap.utils.ConverUtils;
import com.arcgis.mymap.utils.CoorTransActivity;
import com.arcgis.mymap.utils.ImageTextView;
import com.arcgis.mymap.utils.ToastNotRepeat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/18.
 */

public class ConvertTools extends AppCompatActivity implements View.OnClickListener{
    private ImageTextView imageTextView1;
    private ImageTextView imageTextView2;
    private ImageTextView imageTextView3;
    private String data="";
    private String position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converttools);
        intview();
    }

    private void intview() {
        imageTextView1 = (ImageTextView) findViewById(R.id.zhuanhuan);
        imageTextView2 = (ImageTextView) findViewById(R.id.chanshu_7);
        imageTextView3 = (ImageTextView) findViewById(R.id.chanshu_4);
        imageTextView1.setImageResource(R.mipmap.ic_10);
        imageTextView2.setImageResource(R.mipmap.ic_20);
        imageTextView3.setImageResource(R.mipmap.ic_param4);
        imageTextView1.setTextViewText("坐标转换");
        imageTextView2.setTextViewText("七参数");
        imageTextView3.setTextViewText("四参数");
        imageTextView1.setOnClickListener(this);
        imageTextView2.setOnClickListener(this);
        imageTextView3.setOnClickListener(this);
        Intent i = getIntent();
        data = i.getStringExtra("data");
        position = i.getStringExtra("position");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuanhuan:
                if (data.equals("hangce")){
                    Intent n = new Intent(this,CoorTransActivity.class);
                    Log.i("TAG","hangce");
                    startActivity(n);
                }else if (data.equals("dikan")){
                    Intent n = new Intent(this, GeoCoorTransActivity.class);
                    Log.i("TAG","dikan");
                    startActivity(n);
                }
                break;
            case R.id.chanshu_7:
                if (data.equals("hangce")){
                    Intent i = new Intent(this,ActivitySevenParam.class);
                    i.putExtra("form","Newproject");
                    i.putExtra("position",position);
                    Log.i("TAG","hangce");
                    startActivity(i);
                }else if(data.equals("dikan")){
                    Intent i = new Intent(this,ActivitySevenParam.class);
                    i.putExtra("form","Geonewproject");
                    i.putExtra("position",position);
                    Log.i("TAG","dikan");
                    startActivity(i);
                }
                break;
            case R.id.chanshu_4:
                Intent a = new Intent(this,ActivityFourParam.class);
                startActivity(a);
                break;
        }
    }
}
