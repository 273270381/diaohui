package com.arcgis.mymap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arcgis.mymap.Export.ExprotUtils;
import com.arcgis.mymap.Export.WriteCASS;
import com.arcgis.mymap.Export.WriteGPX;
import com.arcgis.mymap.Export.WriteKml;
import com.arcgis.mymap.adapter.ExportAdatper;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.GetTable;
import com.arcgis.mymap.utils.LogUtils;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */
public class DataExportActivity extends Activity {
    private ListView lv;
    private String path;
    private ExportAdatper mAdapter;
    private TextView exportpath;
    public List<LitepalPoints> list,list2,listExport;
    public LitepalPoints point;
    private Button bt_selectall,bt_cancel,bt_close,bt_export,bt_delate;
    private ImageButton back;
    private NewDataActivity dataActivity;
    private SQLiteDatabase db;
    private MyDatabaseHelper dbHelper;
    public String pposition;
    public List<NewProject> projects=new ArrayList<>();
    public EditText editText;
    public RadioButton bt1,bt2,bt3,bt4;
    public RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    private static final int REQUEST_CHOOSER = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataexport);
        intview();
        setlistener();
        Cursor cursor = db.query("Newpoints"+pposition, null, null, null, null, null, null);
        try {
            list=dataActivity.getData(list,cursor);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        list2.addAll(list);
        mAdapter=new ExportAdatper(list,R.layout.exportitem,DataExportActivity.this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                ExportAdatper.Viewholder holder= (ExportAdatper.Viewholder) view.getTag();
                // 改变CheckBox的状态
                holder.getCheckBox().toggle();
                // 将CheckBox的选中状况记录下来
                mAdapter.getIsSelected().put(position,holder.getCheckBox().isChecked());
                 point=list.get(position);
                if (holder.getCheckBox().isChecked()==true){
                         listExport.add(point);
                }else {
                    if (listExport.contains(point)){
                        listExport.remove(point);
                    }
                }
            }
        });
    }
    private void intview() {
        dataActivity=new NewDataActivity();
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db=dbHelper.getWritableDatabase();
        lv= (ListView) findViewById(R.id.listview);
        bt_selectall= (Button) findViewById(R.id.selectall);
        bt_cancel= (Button) findViewById(R.id.cancel);
        bt_close= (Button) findViewById(R.id.closeall);
        bt_export= (Button) findViewById(R.id.export);
        bt_delate= (Button) findViewById(R.id.delate);
        back= (ImageButton) findViewById(R.id.back);
        list=new ArrayList<>();
        list2=new ArrayList<>();
        listExport=new ArrayList<>();
        point=new LitepalPoints();

        //获取表位置
        GetTable getTable=new GetTable();
        pposition=getTable.getTableposition(DataExportActivity.this,db,dbHelper,projects);
    }
    //为按钮添加监听
    private void setlistener() {
        ClickListener listener=new ClickListener();
        bt_selectall.setOnClickListener(listener);
        bt_cancel.setOnClickListener(listener);
        bt_close.setOnClickListener(listener);
        bt_export.setOnClickListener(listener);
        bt_delate.setOnClickListener(listener);
        back.setOnClickListener(listener);
    }
    //实现点击按钮的功能
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.delate:
                    for (int x=0;x<listExport.size();x++){
                        db.delete("Newpoints"+pposition, "id=?", new String[]{String.valueOf(listExport.get(x).getId())});
                        list.remove(listExport.get(x));
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           mAdapter.notifyDataSetChanged();
                        }
                    },400);
                    for (int i=0;i<list.size();i++){
                        mAdapter.getIsSelected().put(i, false);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.back:
                    listExport.clear();
                    finish();
                    break;
                case R.id.selectall:
                    for (int i=0;i<list.size();i++){
                        mAdapter.getIsSelected().put(i, true);
                    }
                    listExport.clear();
                    listExport.addAll(list2);
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.cancel:
                    for (int i=0;i<list.size();i++){
                        if (mAdapter.getIsSelected().get(i)){
                            mAdapter.getIsSelected().put(i,false);
                        }
                    }
                    listExport.clear();
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.export:
                    LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogexport,null);
                    AlertDialog dialog=new AlertDialog.Builder(DataExportActivity.this)
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
                                            ExprotUtils.writeExcel(DataExportActivity.this,listExport,filename,path);
                                            ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt1.isChecked()) {
                                        WriteGPX writeGPX=new WriteGPX();
                                        try{
                                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date curDate =  new Date(System.currentTimeMillis());
                                            String  str  =  formatter.format(curDate);
                                            writeGPX.createGpx(filename,listExport,str,path);
                                            ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt2.isChecked()){
                                        WriteKml writeKml=new WriteKml();
                                        try {
                                            writeKml.createKml(filename,listExport,path);
                                            ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else {
                                        LinearLayout linearLayout1= (LinearLayout) getLayoutInflater().inflate(R.layout.choosesystem,null);
                                        final String finalFilename = filename;
                                        AlertDialog dialog1=new AlertDialog.Builder(DataExportActivity.this)
                                                .setTitle(" 选择坐标系统：")
                                                .setView(linearLayout1)
                                                .setNegativeButton("取消",null)
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (radioButton1.isChecked()){
                                                              WriteCASS writeCASS=new WriteCASS();
                                                        try{
                                                            writeCASS.creatWgs84(finalFilename,listExport,path);
                                                            ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                                        }catch(Exception e){
                                                            e.printStackTrace();
                                                        }
                                                        }
                                                        else if (radioButton2.isChecked()){
                                                            WriteCASS writeCASS=new WriteCASS();
                                                            try{
                                                                writeCASS.createbeijing54(finalFilename,listExport,path);
                                                                ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        else if (radioButton3.isChecked()){
                                                            WriteCASS writeCASS=new WriteCASS();
                                                            try{
                                                                writeCASS.createxian80(finalFilename,listExport,path);
                                                                ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        else if(radioButton4.isChecked()){
                                                            WriteCASS writeCASS=new WriteCASS();
                                                            try{
                                                                writeCASS.createguojia2000(finalFilename,listExport,path);
                                                                ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else {
                                                            WriteCASS writeCASS=new WriteCASS();
                                                            try {
                                                                writeCASS.creat(finalFilename,listExport,path);
                                                                ToastNotRepeat.show(DataExportActivity.this,"导出成功！");
                                                            }catch (Exception e){
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
                            Intent f=new Intent(DataExportActivity.this,FileChooserActivity.class);
                            startActivityForResult(f, REQUEST_CHOOSER);
                        }
                    });
                    break;
                case R.id.closeall:
                    listExport.clear();
                    finish();
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
