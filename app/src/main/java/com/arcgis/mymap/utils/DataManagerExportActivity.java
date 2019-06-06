package com.arcgis.mymap.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arcgis.mymap.DataExportActivity;
import com.arcgis.mymap.DataLineActivity;
import com.arcgis.mymap.DataLineExportActivity;
import com.arcgis.mymap.DataPlusExportActivity;
import com.arcgis.mymap.DataPlusLineExportActivity;
import com.arcgis.mymap.Export.ExprotLineUtils;
import com.arcgis.mymap.Export.ExprotUtils;
import com.arcgis.mymap.Export.WriteCASS;
import com.arcgis.mymap.Export.WriteGPX;
import com.arcgis.mymap.Export.WriteKml;
import com.arcgis.mymap.Export.WriteLineCass;
import com.arcgis.mymap.Export.WriteLineGpx;
import com.arcgis.mymap.Export.WriteLineKml;
import com.arcgis.mymap.NewDataActivity;
import com.arcgis.mymap.PhotoExportActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DataAdapter;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/10.
 */

public class DataManagerExportActivity extends Activity {
    ImageButton back;
    ListView listView;
    private Button export_all;
    private List<LitepalPoints> pointsList_1;
    private List<LitepalPoints> pointsList_2;
    private List<LitepalPoints> pointsList_3;
    private List<MoreLines> linesList_1;
    private List<MoreLines> linesList_2;
    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;
    private NewDataActivity dataActivity;
    private DataLineActivity dataLineActivity;
    private List<String> list=new ArrayList<>();
    public String pposition;
    private EditText editText;
    private RadioButton bt1,bt2,bt3,bt4;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    private static final int REQUEST_CHOOSER = 1;
    private String path;
    private TextView exportpath;
    public List<NewProject> projects=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateexport);
        intview();
        setlistener();
        list.add("点元素");
        list.add("线元素");
        list.add("导入的点元素.xls");
        list.add("导入的线元素.xls");
        list.add("图片坐标");
        DataAdapter adapter=new DataAdapter(DataManagerExportActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent i=new Intent(DataManagerExportActivity.this, DataExportActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent a=new Intent(DataManagerExportActivity.this, DataLineExportActivity.class);
                        startActivity(a);
                        break;
                    case 2:
                        Intent b=new Intent(DataManagerExportActivity.this, DataPlusExportActivity.class);
                        startActivity(b);
                        break;
                    case 3:
                        Intent c=new Intent(DataManagerExportActivity.this, DataPlusLineExportActivity.class);
                        startActivity(c);
                        break;
                    case 4:
                        Intent d=new Intent(DataManagerExportActivity.this, PhotoExportActivity.class);
                        startActivity(d);
                        break;
                }
            }
        });
    }

    private void setlistener() {
        DataManagerExportActivity.ClickListener listener=new DataManagerExportActivity.ClickListener();
        back.setOnClickListener(listener);
        export_all.setOnClickListener(listener);
    }
    private void intview() {
        back= (ImageButton) findViewById(R.id.back);
        listView= (ListView) findViewById(R.id.listview);
        export_all = (Button) findViewById(R.id.export_all);
        pointsList_1 = new ArrayList<>();
        pointsList_2 = new ArrayList<>();
        pointsList_3 = new ArrayList<>();
        linesList_1 = new ArrayList<>();
        linesList_2 = new ArrayList<>();
        dataActivity=new NewDataActivity();
        dataLineActivity=new DataLineActivity();
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db=dbHelper.getWritableDatabase();
        //获取表位置
        GetTable getTable=new GetTable();
        pposition=getTable.getTableposition(DataManagerExportActivity.this,db,dbHelper,projects);
        Cursor cursor1 = db.query("Newpoints"+pposition, null, null, null, null, null, null);
        Cursor cursor2 = db.query("Pointexcel"+pposition, null, null, null, null, null, null);
        Cursor cursor3 = db.query("Photopoints"+pposition, null, null, null, null, null, null);
        Cursor cursor4 = db.query("Newlines"+pposition, null, null, null, null, null, null);
        Cursor cursor5 = db.query("Lineexcel"+pposition, null, null, null, null, null, null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pointsList_1=dataActivity.getData(pointsList_1,cursor1);
                    pointsList_2=dataActivity.getData(pointsList_2,cursor2);
                    pointsList_3=dataActivity.getData(pointsList_3,cursor3);
                    linesList_1=dataLineActivity.getData(linesList_1,cursor4);
                    linesList_2=dataLineActivity.getData(linesList_2,cursor5);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
    private void ExportAllData(){
        pointsList_1.addAll(pointsList_2);
        pointsList_1.addAll(pointsList_3);
        linesList_1.addAll(linesList_2);
        showAlert();
    }
    private void showAlert(){
        LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogexport,null);
        AlertDialog dialog=new AlertDialog.Builder(DataManagerExportActivity.this)
                .setTitle("导出数据:")
                .setView(linearLayout)
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String filename=editText.getText().toString();
                        if (filename.equals("")){
                            filename="未命名";
                        }
                        if (bt4.isChecked()){
                            try{
                                ExprotUtils.writeExcel(DataManagerExportActivity.this,pointsList_1,filename+"_points",path);
                                ExprotLineUtils.writelineExcel(DataManagerExportActivity.this,linesList_1,filename+"_lines",path);
                                ToastNotRepeat.show(DataManagerExportActivity.this,"导出成功！");
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else if (bt1.isChecked()) {
                            WriteGPX writeGPX=new WriteGPX();
                            WriteLineGpx writeLineGpx=new WriteLineGpx();
                            try{
                                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date curDate =  new Date(System.currentTimeMillis());
                                String  str  =  formatter.format(curDate);
                                writeGPX.createGpx(filename+"_points",pointsList_1,str,path);
                                writeLineGpx.createlineGpx(filename+"_lines",linesList_1,str,path);
                                ToastNotRepeat.show(DataManagerExportActivity.this,"导出成功！");
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else if (bt2.isChecked()){
                            WriteKml writeKml=new WriteKml();
                            WriteLineKml writeLineKml=new WriteLineKml();
                            try {
                                writeKml.createKml(filename+"_points",pointsList_1,path);
                                writeLineKml.createKml(filename+"_lines",linesList_1,path);
                                ToastNotRepeat.show(DataManagerExportActivity.this,"导出成功！");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            LinearLayout linearLayout1= (LinearLayout) getLayoutInflater().inflate(R.layout.choosesystem,null);
                            final String finalFilename = filename;
                            AlertDialog dialog1=new AlertDialog.Builder(DataManagerExportActivity.this)
                                    .setTitle(" 选择坐标系统：")
                                    .setView(linearLayout1)
                                    .setNegativeButton("取消",null)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (radioButton1.isChecked()){
                                                WriteCASS writeCASS=new WriteCASS();
                                                WriteLineCass writeLineCass=new WriteLineCass();
                                                try{
                                                    writeCASS.creatWgs84(finalFilename+"_points",pointsList_1,path);
                                                    writeLineCass.creatWgs84(finalFilename+"_lines",linesList_1,path);
                                                    ToastNotRepeat.show(DataManagerExportActivity.this,"导出成功！");
                                                }catch(Exception e){
                                                    e.printStackTrace();
                                                }
                                            }
                                            else if (radioButton2.isChecked()){
                                                WriteCASS writeCASS=new WriteCASS();
                                                WriteLineCass writeLineCass=new WriteLineCass();
                                                try{
                                                    writeCASS.createbeijing54(finalFilename+"_points",pointsList_1,path);
                                                    writeLineCass.createbeijing54(finalFilename+"_lines",linesList_1,path);
                                                    ToastNotRepeat.show(DataManagerExportActivity.this,"导出成功！");
                                                }catch(Exception e){
                                                    e.printStackTrace();
                                                }
                                            }
                                            else if (radioButton3.isChecked()){
                                                WriteCASS writeCASS=new WriteCASS();
                                                WriteLineCass writeLineCass=new WriteLineCass();
                                                try{
                                                    writeCASS.createxian80(finalFilename+"_points",pointsList_1,path);
                                                    writeLineCass.createxian80(finalFilename+"_lines",linesList_1,path);
                                                    ToastNotRepeat.show(DataManagerExportActivity.this,"导出成功！");
                                                }catch(Exception e){
                                                    e.printStackTrace();
                                                }
                                            }
                                            else {
                                                WriteCASS writeCASS=new WriteCASS();
                                                WriteLineCass writeLineCass=new WriteLineCass();
                                                try{
                                                    writeCASS.createguojia2000(finalFilename+"_points",pointsList_1,path);
                                                    writeLineCass.createguojia2000(finalFilename+"_lines",linesList_1,path);
                                                    ToastNotRepeat.show(DataManagerExportActivity.this,"导出成功！");
                                                }catch(Exception e){
                                                    e.printStackTrace();
                                                }
                                            }
                                        }

                                    }).show();
                            radioButton1= (RadioButton) dialog1.findViewById(R.id.radiobt1);
                            radioButton2= (RadioButton) dialog1.findViewById(R.id.radiobt2);
                            radioButton3= (RadioButton) dialog1.findViewById(R.id.radiobt3);
                            radioButton4= (RadioButton) dialog1.findViewById(R.id.radiobt4);
                            Button btnpositive=dialog1.getButton(AlertDialog.BUTTON_POSITIVE);
                            Button btnnegative=dialog1.getButton(AlertDialog.BUTTON_NEGATIVE);
                            btnpositive.setTextColor(getResources().getColor(R.color.color29));
                            btnnegative.setTextColor(getResources().getColor(R.color.color29));
                        }
                    }
                })
                .show();
        editText= (EditText) dialog.findViewById(R.id.filename);
        bt1= (RadioButton) dialog.findViewById(R.id.gpx);
        bt2= (RadioButton) dialog.findViewById(R.id.kml);
        bt3= (RadioButton) dialog.findViewById(R.id.cass);
        bt4= (RadioButton) dialog.findViewById(R.id.excel);
        exportpath = (TextView) dialog.findViewById(R.id.export_path);
        path = projects.get(Integer.parseInt(pposition)).getPath();
        exportpath.setText("导出目录:"+path);

        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        btnpositive.setTextColor(getResources().getColor(R.color.color29));
        btnnegative.setTextColor(getResources().getColor(R.color.color29));

        exportpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.mFileFileterBySuffixs.acceptSuffixs("");
                Intent f=new Intent(DataManagerExportActivity.this,FileChooserActivity.class);
                startActivityForResult(f, REQUEST_CHOOSER);
            }
        });
    }
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.export_all:
                    ExportAllData();
                    break;
            }
        }
    }
    /**
     *更新项目路径
     */
    private void UpdatePath(String str){
        ContentValues values = new ContentValues();
        values.put("exportpath",str);
        db.update("Newproject",values,"position = ?",new String[]{String.valueOf(pposition)});
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    String path = FileUtils.getPath(this, uri);
                    if (path != null && FileUtils.isLocal(path)) {
                        File file = new File(path);
                        String str = file.toString();
                        UpdatePath(str);
                        exportpath.setText("导出目录:"+str);
                    }
                }
                break;
        }
    }
}
