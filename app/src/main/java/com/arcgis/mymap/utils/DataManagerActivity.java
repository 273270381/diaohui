package com.arcgis.mymap.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.arcgis.mymap.DataLineActivity;
import com.arcgis.mymap.InportLineActivity;
import com.arcgis.mymap.InportPointActivity;
import com.arcgis.mymap.NewDataActivity;
import com.arcgis.mymap.PhotoPointActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DataAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/9.
 */

public class DataManagerActivity extends Activity {
    ImageButton back;
    ListView listView;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datamanager_hangce);
        intview();
        setlistener();
        list.add("点元素");
        list.add("线元素");
        list.add("导入的点元素.xls");
        list.add("导入的线元素.xls");
        list.add("图片坐标");
        DataAdapter adapter=new DataAdapter(DataManagerActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent i=new Intent(DataManagerActivity.this, NewDataActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent a=new Intent(DataManagerActivity.this, DataLineActivity.class);
                        startActivity(a);
                        break;
                    case 2:
                        Intent b=new Intent(DataManagerActivity.this, InportPointActivity.class);
                        startActivity(b);
                        break;
                    case 3:
                        Intent c=new Intent(DataManagerActivity.this, InportLineActivity.class);
                        startActivity(c);
                        break;
                    case 4:
                        Intent d=new Intent(DataManagerActivity.this, PhotoPointActivity.class);
                        startActivity(d);
                        break;
                }
            }
        });
    }

    private void setlistener() {
        ClickListener listener=new ClickListener();
        back.setOnClickListener(listener);
    }
    private void intview() {
        back= (ImageButton) findViewById(R.id.back);
        listView= (ListView) findViewById(R.id.listview);
    }
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    finish();
                break;
            }
        }
    }

}
