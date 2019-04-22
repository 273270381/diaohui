package com.arcgis.mymap.Geological;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.arcgis.mymap.DataExportActivity;
import com.arcgis.mymap.DataLineExportActivity;
import com.arcgis.mymap.DataPlusExportActivity;
import com.arcgis.mymap.DataPlusLineExportActivity;
import com.arcgis.mymap.PhotoExportActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DataAdapter;
import com.arcgis.mymap.utils.DataManagerExportActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class GeoDataManagerExportActivity extends Activity{
    ImageButton back;
    ListView listView;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateexport);
        intview();
        setlistener();
        list.add("点元素");
        list.add("线元素");
        list.add("面元素");
        list.add("导入的点元素.xls");
        list.add("导入的线元素.xls");
        list.add("导入的面元素.xls");
        list.add("图片坐标");
        DataAdapter adapter=new DataAdapter(GeoDataManagerExportActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent i=new Intent(GeoDataManagerExportActivity.this,GeoExportPointActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent a=new Intent(GeoDataManagerExportActivity.this, GeoExportLineActivity.class);
                        startActivity(a);
                        break;
                    case 2:
                        Intent b=new Intent(GeoDataManagerExportActivity.this,GeoExportSurfaceActivity.class);
                        startActivity(b);
                        break;
                    case 3:
                        Intent c=new Intent(GeoDataManagerExportActivity.this, GeoExportExcelPointActivity.class);
                        startActivity(c);
                        break;
                    case 4:
                        Intent d=new Intent(GeoDataManagerExportActivity.this, GeoExportExcelLineActivity.class);
                        startActivity(d);
                        break;
                    case 5:
                        Intent e=new Intent(GeoDataManagerExportActivity.this, GeoExportExcelSurfaceActivity.class);
                        startActivity(e);
                        break;
                    case 6:
                        Intent f=new Intent(GeoDataManagerExportActivity.this, GeoPhotoExportActivity.class);
                        startActivity(f);
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
