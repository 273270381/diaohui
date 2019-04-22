package com.arcgis.mymap.Geological;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.arcgis.mymap.DataLineActivity;
import com.arcgis.mymap.InportLineActivity;
import com.arcgis.mymap.InportPointActivity;
import com.arcgis.mymap.PhotoPointActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DataAdapter;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.utils.GeoReadExcelUtils;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class GeoDataManagerActivity extends AppCompatActivity{
    ImageButton back;
    ListView listView;
    Button inport;
    public RadioButton bt1;
    public RadioButton bt2;
    public RadioButton bt3;
    public RadioButton bt4;
    public RadioButton bt5;
    public RadioButton bt6;
    public RadioButton fbt1;
    public RadioButton fbt2;
    public boolean flage = false;
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public int pposition;
    private static final int REQUEST_CHOOSER = 1234;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datamanager);
        intview();
        setlistener();
        list.add("地形地貌点");
        list.add("地层岩性点");
        list.add("水文地质点");
        list.add("构筑物点");
        list.add("线元素");
        list.add("面元素");
        //list.add("导入的点元素.xls");
        //list.add("导入的线元素.xls");
        //list.add("导入的面元素.xls");
        list.add("图片坐标");
        DataAdapter adapter=new DataAdapter(GeoDataManagerActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent i=new Intent(GeoDataManagerActivity.this, GeodataDXDMPointActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent dcyx = new Intent(GeoDataManagerActivity.this,GeodataDCYXPointActivity.class);
                        startActivity(dcyx);
                        break;
                    case 2:
                        Intent swdz = new Intent(GeoDataManagerActivity.this,GeodataSWDZPointActivity.class);
                        startActivity(swdz);
                        break;
                    case 3:
                        Intent gzwd = new Intent(GeoDataManagerActivity.this,GeodataGZWDPointActivity.class);
                        startActivity(gzwd);
                        break;
                    case 4:
                        Intent a=new Intent(GeoDataManagerActivity.this, GeoDataLineActivity.class);
                        startActivity(a);
                        break;
                    case 5:
                        Intent b=new Intent(GeoDataManagerActivity.this, GeoDataSurfaceActivity.class);
                        startActivity(b);
                        break;
//                    case 6:
//                        Intent c=new Intent(GeoDataManagerActivity.this, GeoInportPointActivity.class);
//                        startActivity(c);
//                        break;
//                    case 7:
//                        Intent d=new Intent(GeoDataManagerActivity.this, GeoInportLineActivity.class);
//                        startActivity(d);
//                        break;
//                    case 8:
//                        Intent e=new Intent(GeoDataManagerActivity.this, GeoInportSurfaceActivity.class);
//                        startActivity(e);
//                        break;
                    case 6:
                        Intent f=new Intent(GeoDataManagerActivity.this, GeoPhotoActivity.class);
                        startActivity(f);
                        break;
                }
            }
        });
    }

    private void setlistener() {
        ClickListener listener=new ClickListener();
        back.setOnClickListener(listener);
        inport.setOnClickListener(listener);
    }
    private void intview() {
        back= (ImageButton) findViewById(R.id.back);
        listView= (ListView) findViewById(R.id.listview);
        inport = (Button) findViewById(R.id.inport);
        pposition= Integer.parseInt(getIntent().getStringExtra("pposition"));
        dbHelper=new MyDatabaseHelper(GeoDataManagerActivity.this,"pointsStore.db",null,5);
        db=dbHelper.getWritableDatabase();
    }
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.inport:
                    LinearLayout l = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_inport_format,null);
                    AlertDialog dialog = new AlertDialog.Builder(GeoDataManagerActivity.this)
                            .setTitle("导入")
                            .setView(l)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (fbt2.isChecked()){
                                        flage = true;
                                    }
                                    LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.alert_inport_layout2,null);
                                    AlertDialog dialog2=new AlertDialog.Builder(GeoDataManagerActivity.this)
                                            .setTitle("导入Excel")
                                            .setView(linearLayout)
                                            .setNegativeButton("取消",null)
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    FileUtils.mFileFileterBySuffixs.acceptSuffixs("xls");
                                                    Intent intent=new Intent(GeoDataManagerActivity.this,FileChooserActivity.class);
                                                    startActivityForResult(intent, REQUEST_CHOOSER);
                                                }
                                            }).show();
                                    bt1= (RadioButton) dialog2.findViewById(R.id.excel1);
                                    bt2= (RadioButton) dialog2.findViewById(R.id.excel2);
                                    bt3= (RadioButton) dialog2.findViewById(R.id.excel3);
                                    bt4= (RadioButton) dialog2.findViewById(R.id.excel4);
                                    bt5= (RadioButton) dialog2.findViewById(R.id.excel5);
                                    bt6= (RadioButton) dialog2.findViewById(R.id.excel6);
                                    Button btnpositive=dialog2.getButton(AlertDialog.BUTTON_POSITIVE);
                                    Button btnnegative=dialog2.getButton(AlertDialog.BUTTON_NEGATIVE);
                                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                                }
                            }).show();
                    fbt1 = (RadioButton) dialog.findViewById(R.id.format1);
                    fbt2 = (RadioButton) dialog.findViewById(R.id.format2);
                    Button positive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button negative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    positive.setTextColor(getResources().getColor(R.color.color29));
                    negative.setTextColor(getResources().getColor(R.color.color29));
                    break;
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    String path = FileUtils.getPath(this, uri);
                    if (path != null && FileUtils.isLocal(path)) {
                        File file = new File(path);
                        String f=file.toString();
                        GeoReadExcelUtils excelUtils = new GeoReadExcelUtils();
                        if (bt1.isChecked()){
                            if (flage == true){
                                flage = false;
                                String sql = "delete from Geodxdmpoints"+String.valueOf(pposition);
                                db.execSQL(sql);
                            }
                            excelUtils.writeDxdmPointExcel(GeoDataManagerActivity.this,String.valueOf(pposition),f);
                            ToastNotRepeat.show(GeoDataManagerActivity.this,"导入成功");
                        }else if(bt2.isChecked()){
                            if (flage == true){
                                flage = false;
                                String sql = "delete from Geodcyxpoints"+String.valueOf(pposition);
                                db.execSQL(sql);
                            }
                            excelUtils.writeDcyxPointExcel(GeoDataManagerActivity.this,String.valueOf(pposition),f);
                            ToastNotRepeat.show(GeoDataManagerActivity.this,"导入成功");
                        }else if(bt3.isChecked()){
                            if (flage == true){
                                flage = false;
                                String sql = "delete from Geoswdzpoints"+String.valueOf(pposition);
                                db.execSQL(sql);
                            }
                            excelUtils.writeSwdzPointExcel(GeoDataManagerActivity.this,String.valueOf(pposition),f);
                            ToastNotRepeat.show(GeoDataManagerActivity.this,"导入成功");
                        }else if(bt4.isChecked()) {
                            if (flage == true) {
                                flage = false;
                                String sql = "delete from Geogzwdpoints" + String.valueOf(pposition);
                                db.execSQL(sql);
                            }
                            excelUtils.writeGzwdPointExcel(GeoDataManagerActivity.this, String.valueOf(pposition), f);
                            ToastNotRepeat.show(GeoDataManagerActivity.this, "导入成功");
                        } else if (bt5.isChecked()){
                            if (flage == true){
                                flage = false;
                                String sql = "delete from Geomorelines"+String.valueOf(pposition);
                                db.execSQL(sql);
                            }
                            excelUtils.writeLineExcel(GeoDataManagerActivity.this,String.valueOf(pposition),f);
                            ToastNotRepeat.show(GeoDataManagerActivity.this,"导入成功");
                        }else if(bt6.isChecked()){
                            if (flage == true){
                                flage = false;
                                String sql = "delete from Geosurface"+String.valueOf(pposition);
                                db.execSQL(sql);
                            }
                            excelUtils.writeSurfaceExcel(GeoDataManagerActivity.this,String.valueOf(pposition),f);
                            ToastNotRepeat.show(GeoDataManagerActivity.this,"导入成功");
                        }
                    }
                }
                break;
        }
    }
}
