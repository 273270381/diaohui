package com.arcgis.mymap.Geological;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arcgis.mymap.Export.GeoExportSurfaceUtils;
import com.arcgis.mymap.Export.GeoExportUtils;
import com.arcgis.mymap.Export.GeoWriteCass;
import com.arcgis.mymap.Export.GeoWriteGpx;
import com.arcgis.mymap.Export.GeoWriteSurfaceCass;
import com.arcgis.mymap.Export.GeoWriteSurfaceGpx;
import com.arcgis.mymap.Export.GeoWriteSurfaceKml;
import com.arcgis.mymap.Export.GeoWritekml;
import com.arcgis.mymap.MainActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.ComBoxListAdapter;
import com.arcgis.mymap.adapter.ComBoxSurfaceAdapter;
import com.arcgis.mymap.adapter.GeoLeftListLineAdapter;
import com.arcgis.mymap.adapter.GeoLeftSurfaceAdapter;
import com.arcgis.mymap.adapter.GeoRightLineAdapter;
import com.arcgis.mymap.adapter.GeoRightSurfaceAdapter;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.contacts.SurFace;
import com.arcgis.mymap.utils.SyncHorizontalScrollView;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/5/1.
 */

public class GeoDataSurfaceActivity extends Activity{
    TextView left_title;
    private LinearLayout right_title_container;
    private ListView leftlistView;
    private ListView rightlistView,comboxlistview;
    private SyncHorizontalScrollView titleHorScv;
    private SyncHorizontalScrollView contentHorScv;
    private MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public String pposition;
    private TextView exportpath;
    private String path;
    private static final int REQUEST_CHOOSER = 1;
    public List<NewProject> projects=new ArrayList<>();
    public List<SurFace> surFaces,surFaceExport;
    public SurFace surFace;
    public int resource,resource2,resource3;
    public ImageButton back,search;
    public EditText text,eText2,editText;
    private GeoLeftSurfaceAdapter leftlineAdapter;
    private GeoRightSurfaceAdapter rightLineAdapter;
    private ComBoxSurfaceAdapter comBoxAdapter;
    private CheckBox checkBox;
    public RadioButton bt1,bt2,bt3,bt4;
    public RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    public Button delate,close,edit,detailed,findall,bt_export;
    public String des,classification;
    public int id,p;
    public boolean b = false;
    public InputMethodManager imm;
    private List<String> listLa=new ArrayList<>();
    private List<String> gla = new ArrayList<>();
    private List<String> gln = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datasurface);
        try {
            intview();
            listener();
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        comBoxAdapter.setCheck(true,true);
                        comBoxAdapter.notifyDataSetChanged();
                        surFaceExport.clear();
                        surFaceExport.addAll(surFaces);
                        Log.i("tag",String.valueOf(surFaceExport.size()));
                    }else{
                        comBoxAdapter.setCheck(true,false);
                        comBoxAdapter.notifyDataSetChanged();
                        surFaceExport.clear();
                        Log.i("tag",String.valueOf(surFaceExport.size()));
                    }
                    Intent i=new Intent("com.checkbox_surface.broadcaster");
                    sendBroadcast(i);
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //添加监听
    private void listener() {
        ClickListener listener=new ClickListener();
        LongClicklistener longClicklistener=new LongClicklistener();
        back.setOnClickListener(listener);
        detailed.setOnClickListener(listener);
        edit.setOnClickListener(listener);
        close.setOnClickListener(listener);
        delate.setOnClickListener(listener);
        delate.setOnLongClickListener(longClicklistener);
        search.setOnClickListener(listener);
        findall.setOnClickListener(listener);
        bt_export.setOnClickListener(listener);
    }

    private void intview() throws ParseException {
        findbyid();
    }
    //初始化控件
    private void findbyid() throws ParseException {
        checkBox = (CheckBox) findViewById(R.id.item_cb);
        bt_export= (Button) findViewById(R.id.export);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        left_title= (TextView) findViewById(R.id.tv_table_title_left);
        left_title.setText("序号");
        right_title_container = (LinearLayout) findViewById(R.id.right_title_container);
        getLayoutInflater().inflate(R.layout.geo_table_right_titleline, right_title_container);
        leftlistView= (ListView) findViewById(R.id.left_container_listview);
        rightlistView= (ListView) findViewById(R.id.right_container_listview);
        comboxlistview = (ListView) findViewById(R.id.right_container_comboxlistview);
        titleHorScv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorScv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        // 设置两个水平控件的联动
        titleHorScv.setScrollView(contentHorScv);
        contentHorScv.setScrollView(titleHorScv);
        dbHelper = new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        //获取表位置
        GeoGetTable getTable=new GeoGetTable();
        pposition=getTable.getTableposition(GeoDataSurfaceActivity.this,db,dbHelper,projects);
        back = (ImageButton) findViewById(R.id.back);
        search = (ImageButton) findViewById(R.id.search);
        text = (EditText) findViewById(R.id.editText);
        delate = (Button) findViewById(R.id.bt3);
        close = (Button) findViewById(R.id.bt4);
        edit = (Button) findViewById(R.id.bt2);
        eText2 = (EditText) findViewById(R.id.etext2);
        detailed= (Button) findViewById(R.id.bt1);
        findall= (Button) findViewById(R.id.bt5);
        initTableView();
    }
    private void initTableView() throws ParseException {
        //查询Newlines表中的所有数据
        surFaces = new ArrayList<>();
        surFaceExport = new ArrayList<>();
        Cursor cursor = db.query("Geosurface"+pposition, null, null, null, null, null, null);
        surFaces=getData(surFaces, cursor);
        resource=R.layout.table_left_item;
        resource2=R.layout.geotable_right_itemline;
        resource3 = R.layout.table_combox_item;
        leftlineAdapter=new GeoLeftSurfaceAdapter(surFaces,resource,GeoDataSurfaceActivity.this);
        rightLineAdapter=new GeoRightSurfaceAdapter(surFaces,resource2,GeoDataSurfaceActivity.this);
        comBoxAdapter = new ComBoxSurfaceAdapter(surFaces,resource3,GeoDataSurfaceActivity.this);
        leftlistView.setAdapter(leftlineAdapter);
        rightlistView.setAdapter(rightLineAdapter);
        comboxlistview.setAdapter(comBoxAdapter);
        comBoxAdapter.notifyDataSetChanged();
        comboxlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComBoxSurfaceAdapter.ViewHolder holder = (ComBoxSurfaceAdapter.ViewHolder) view.getTag();
                //holder.getCheckBox().toggle();
                rightLineAdapter.setSelectItem(position);
                rightLineAdapter.notifyDataSetChanged();
                leftlineAdapter.setSelectItem(position);
                leftlineAdapter.notifyDataSetChanged();
                comBoxAdapter.notifyDataSetChanged();
                comBoxAdapter.setSelectItem(position);
                surFace =surFaces.get(position);
                if(holder.getCheckBox().isChecked()!=true){
                    surFaceExport.add(surFace);
                    Log.i("tag",String.valueOf(surFaceExport.size()));
                }else{
                    if (surFaceExport.contains(surFace)){
                        surFaceExport.remove(surFace);
                        Log.i("tag",String.valueOf(surFaceExport.size()));
                    }
                }
            }
        });
        leftlineAdapter.notifyDataSetInvalidated();
        leftlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        rightlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                rightLineAdapter.setSelectItem(position);
                rightLineAdapter.notifyDataSetChanged();
                leftlineAdapter.setSelectItem(position);
                leftlineAdapter.notifyDataSetChanged();
                comBoxAdapter.notifyDataSetChanged();
                comBoxAdapter.setSelectItem(position);
                p=position;
                SurFace surFace = surFaces.get(position);
                id=surFace.getId();
                classification=surFace.getClassification();
                des=surFace.getDescription();
                gla=surFace.getListla();
                gln=surFace.getListln();
                b = true;
                if (surFaceExport.contains(surFace)){
                    surFaceExport.remove(surFace);
                    Log.i("tag",String.valueOf(surFaceExport.size()));
                }else{
                    surFaceExport.add(surFace);
                    Log.i("tag",String.valueOf(surFaceExport.size()));
                }
            }
        });
    }
    //查询数据
    public List<SurFace> getData(List<SurFace> list, Cursor cursor) throws ParseException {
        if (cursor.moveToFirst()) {
            do {
                //遍历cursor对象，取出数据
                SurFace surFace = new SurFace();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String classification=cursor.getString(cursor.getColumnIndex("gclassification"));
                String gla = cursor.getString(cursor.getColumnIndex("gla"));
                String gln = cursor.getString(cursor.getColumnIndex("gln"));
                String datetime=cursor.getString(cursor.getColumnIndex("time"));
                String code=cursor.getString(cursor.getColumnIndex("gcode"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                //String formatType="yyyy-MM-dd HH:mm:ss";
                String description = cursor.getString(cursor.getColumnIndex("gdescription"));
                //long time=Stringtolong(date,formatType);
                //String datetime=getTime(time);
                surFace.setId(id);
                surFace.setClassification(classification);
                surFace.setListla(Arrays.asList(gla.split(",")));
                surFace.setListln(Arrays.asList(gln.split(",")));
                surFace.setDatatime(datetime);
                surFace.setDescription(description);
                surFace.setCode(code);
                surFace.setName(name);
                list.add(surFace);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    /** 由时间戳转化为文本 */
    private String getTime(long time) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = sdf.format(time+28800000);
        return date;
    }
    //长按监听
    private class LongClicklistener implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            AlertDialog dialog = new AlertDialog.Builder(GeoDataSurfaceActivity.this).setTitle("警告：")
                    .setMessage("是否删除全部数据？")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
                            //清空lines表
                            Cursor cursor1=db.query("Geosurface"+pposition,null,null,null,null,null,null);
                            if (cursor1.moveToFirst()){
                                do {
                                    String xla=cursor1.getString(cursor1.getColumnIndex("gla"));
                                    listLa.add(xla);
                                }while(cursor1.moveToNext());
                            }
                            cursor1.close();
                            for (int i=0;i<listLa.size();i++){
                                db.delete("Geosurface"+pposition, "gla=?", new String[]{listLa.get(i)});
                            }

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    leftlineAdapter.notifyDataSetChanged();
                                    rightLineAdapter.notifyDataSetChanged();
                                    comBoxAdapter.notifyDataSetChanged();
                                    ToastNotRepeat.show(GeoDataSurfaceActivity.this,"删除成功");
                                }
                            },400);
                            Intent a=new Intent();
                            a.setAction("com.delatedatas.broadcast");
                            sendBroadcast(a);
                        }
                    })
                    .show();
            Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            btnpositive.setTextColor(getResources().getColor(R.color.color29));
            btnnegative.setTextColor(getResources().getColor(R.color.color29));
            return true;
        }
    }
    //点击监听
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    surFaceExport.clear();
                    finish();
                    break;
                case R.id.bt5:
                    Intent i=new Intent(GeoDataSurfaceActivity.this,GeologicalMapActivity.class);
                    i.putExtra("list3", (Serializable) surFaces);
                    startActivity(i);
                    Intent li=new Intent("com.showall.broadcasttest");
                    sendBroadcast(li);
                    surFaceExport.clear();
                    finish();
                    break;
                case R.id.bt1:
                    if (b){
                        LinearLayout linearLayout2 = (LinearLayout) getLayoutInflater().inflate(R.layout.geodetaildata_line, null);
                        AlertDialog dialog1 = new AlertDialog.Builder(GeoDataSurfaceActivity.this)
                                .setTitle("详细：")
                                .setView(linearLayout2)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("查看", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(GeoDataSurfaceActivity.this,GeologicalMapActivity.class);
                                        List<String> mgla = new ArrayList<>(gla);
                                        List<String> mgln = new ArrayList<>(gln);
                                        i.putStringArrayListExtra("mgla", (ArrayList<String>) mgla);
                                        i.putStringArrayListExtra("mgln", (ArrayList<String>) mgln);
                                        i.putExtra("mclas",classification);
                                        startActivity(i);
                                        Intent li=new Intent("com.showsurface.broadcasttest");
                                        sendBroadcast(li);
                                        surFaceExport.clear();
                                        finish();
                                    }
                                })
                                .show();
                        TextView tv1= (TextView) dialog1.findViewById(R.id.xuhao);
                        TextView tv2= (TextView) dialog1.findViewById(R.id.leibie);
                        TextView tv3= (TextView) dialog1.findViewById(R.id.xjingdu);
                        TextView tv4= (TextView) dialog1.findViewById(R.id.xweidu);
                        TextView tv7= (TextView) dialog1.findViewById(R.id.miaoshu);
                        tv1.setText(String.valueOf(id));
                        tv2.setText(classification);
                        tv3.setText(StringUtils.join(gla,","));
                        tv4.setText(StringUtils.join(gln,","));
                        tv7.setText(des);
                        Button btnpositive=dialog1.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog1.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt2:
                    if (b){
                        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogline, null);
                        AlertDialog dialog = new AlertDialog.Builder(GeoDataSurfaceActivity.this)
                                .setTitle("数据编辑：")
                                .setView(linearLayout)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String finaltext=editText.getText().toString();
                                        ContentValues values = new ContentValues();
                                        values.put("gdescription", finaltext);
                                        db.update("Geosurface"+pposition, values, "id=?", new String[]{String.valueOf(id)});
                                        surFaces.get(p).setDescription(finaltext);
                                        if (imm!=null){
                                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rightLineAdapter.notifyDataSetChanged();
                                                ToastNotRepeat.show(GeoDataSurfaceActivity.this,"保存成功");
                                            }
                                        }, 400);
                                        Intent a=new Intent();
                                        a.setAction("com.delatedatas.broadcast");
                                        sendBroadcast(a);
                                    }
                                }).show();
                        TextView textView= (TextView) dialog.findViewById(R.id.etext1);
                        textView.setText(classification);
                        editText= (EditText) dialog.findViewById(R.id.etext2);
                        editText.setText(des);
                        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt3:
                    if (surFaceExport.size()!=0){
                        AlertDialog dialog = new AlertDialog.Builder(GeoDataSurfaceActivity.this)
                                .setTitle("删除数据")
                                .setMessage("是否删除数据：")
                                .setNegativeButton("取消",null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        b = false;
                                        for(int i =0;i < surFaceExport.size();i++){
                                            db.delete("Geosurface"+pposition, "id=?", new String[]{String.valueOf(surFaceExport.get(i).getId())});
                                            surFaces.remove(surFaceExport.get(i));
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                leftlineAdapter.notifyDataSetChanged();
                                                rightLineAdapter.notifyDataSetChanged();
                                                comBoxAdapter.notifyDataSetChanged();
                                            }
                                        },400);
                                        Intent i=new Intent("com.examplesurface.broadcast");
                                        sendBroadcast(i);
                                        Intent a=new Intent();
                                        a.setAction("com.delatedatas.broadcast");
                                        sendBroadcast(a);
                                    }
                                })
                                .show();
                        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt4:
                    surFaceExport.clear();
                    finish();
                    break;
                case R.id.search:
                    String tsname = text.getText().toString();
                    if (TextUtils.isEmpty(text.getText())) {
                        surFaces.clear();
                        Cursor cursor = db.query("Geosurface"+pposition, null, null, null, null, null, null);
                        try {
                            getData(surFaces, cursor);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftlineAdapter.notifyDataSetChanged();
                                rightLineAdapter.notifyDataSetChanged();
                                comBoxAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    } else {
                        surFaces.subList(0, surFaces.size()).clear();
                        Cursor cursor2 = db.query("Geosurface"+pposition, null, "gclassification like ?", new String[]{ "%" + tsname + "%" }, null, null, null);
                        try {
                            getData(surFaces, cursor2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (surFaces.size() == 0) {
                            ToastNotRepeat.show(GeoDataSurfaceActivity.this, "抱歉，没有找到你要的数据！");
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftlineAdapter.notifyDataSetChanged();
                                rightLineAdapter.notifyDataSetChanged();
                                comBoxAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    }
                    break;
                case R.id.export:
                    LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogexport,null);
                    AlertDialog dialog=new AlertDialog.Builder(GeoDataSurfaceActivity.this)
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
                                            GeoExportSurfaceUtils.writesurfaceExcel(GeoDataSurfaceActivity.this,surFaceExport,filename,path);
                                            ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt1.isChecked()){
                                        GeoWriteSurfaceGpx writeSurfaceGpx = new GeoWriteSurfaceGpx();
                                        try{
                                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date curDate =  new Date(System.currentTimeMillis());
                                            String  str  =  formatter.format(curDate);
                                            writeSurfaceGpx.createsurfaceGpx(filename,surFaceExport,str,path);
                                            ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt2.isChecked()){
                                        GeoWriteSurfaceKml writeSurfaceKml = new GeoWriteSurfaceKml();
                                        try{
                                            writeSurfaceKml.createKml(filename,surFaceExport,path);
                                            ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else {
                                        LinearLayout linearLayout1= (LinearLayout) getLayoutInflater().inflate(R.layout.choosesystem,null);
                                        final String finalFilename = filename;
                                        AlertDialog dialog1=new AlertDialog.Builder(GeoDataSurfaceActivity.this)
                                                .setTitle(" 选择坐标系统：")
                                                .setView(linearLayout1)
                                                .setNegativeButton("取消",null)
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (radioButton1.isChecked()){
                                                            GeoWriteSurfaceCass writeSurfaceCass = new GeoWriteSurfaceCass();
                                                            try{
                                                                writeSurfaceCass.creatWgs84(finalFilename,surFaceExport,path);
                                                                ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else if (radioButton2.isChecked()){
                                                            GeoWriteSurfaceCass writeSurfaceCass = new GeoWriteSurfaceCass();
                                                            try{
                                                                writeSurfaceCass.createbeijing54(finalFilename,surFaceExport,path);
                                                                ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else if (radioButton3.isChecked()){
                                                            GeoWriteSurfaceCass writeSurfaceCass = new GeoWriteSurfaceCass();
                                                            try{
                                                                writeSurfaceCass.createxian80(finalFilename,surFaceExport,path);
                                                                ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else if(radioButton4.isChecked()){
                                                            GeoWriteSurfaceCass writeSurfaceCass = new GeoWriteSurfaceCass();
                                                            try{
                                                                writeSurfaceCass.createguojia2000(finalFilename,surFaceExport,path);
                                                                ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else{
                                                            GeoWriteSurfaceCass writeSurfaceCass = new GeoWriteSurfaceCass();
                                                            try{
                                                                writeSurfaceCass.create(finalFilename,surFaceExport,path);
                                                                ToastNotRepeat.show(GeoDataSurfaceActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                })
                                                .show();
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
                            Intent f=new Intent(GeoDataSurfaceActivity.this,FileChooserActivity.class);
                            startActivityForResult(f, REQUEST_CHOOSER);
                        }
                    });

                    break;

            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    /**
     *更新项目路径
     */
    private void UpdatePath(String str){
        ContentValues values = new ContentValues();
        values.put("exportpath",str);
        db.update("Geonewproject",values,"gposition = ?",new String[]{String.valueOf(pposition)});
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
