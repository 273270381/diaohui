package com.arcgis.mymap.Geological;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.arcgis.mymap.Export.GeoExportLineUtils;
import com.arcgis.mymap.Export.GeoWirteLineGpx;
import com.arcgis.mymap.Export.GeoWriteLineCass;
import com.arcgis.mymap.Export.GeoWriteLineKml;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.ComBoxLineAdapter;
import com.arcgis.mymap.adapter.GeoLeftListLineAdapter;
import com.arcgis.mymap.adapter.GeoRightLineAdapter;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.SyncHorizontalScrollView;
import com.arcgis.mymap.utils.ToastNotRepeat;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/5/2.
 */

public class GeoInportLineActivity extends Activity {
    TextView left_title;
    private LinearLayout right_title_container;
    private ListView leftlistView;
    private ListView rightlistView,comboxlistview;
    private SyncHorizontalScrollView titleHorScv;
    private SyncHorizontalScrollView contentHorScv;
    private MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public String pposition;
    private CheckBox checkBox;
    public List<NewProject> projects=new ArrayList<>();
    public List<MoreLines> linesList,lineExport;
    public MoreLines line;
    public int resource,resource2,resource3;
    public ImageButton back,search;
    public EditText text,eText2,editText;
    private GeoLeftListLineAdapter leftlineAdapter;
    private GeoRightLineAdapter rightLineAdapter;
    private ComBoxLineAdapter comBoxLineAdapter;
    public Button delate,close,edit,detailed,findall,bt_export;
    public String des,classification;
    public RadioButton bt1,bt2,bt3,bt4;
    public RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    public int id,p;
    public boolean b = false;
    public InputMethodManager imm;
    private List<String> listLa=new ArrayList<>();
    private List<String> gla = new ArrayList<>();
    private List<String> gln = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataline);
        try {
            intview();
            listener();
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        comBoxLineAdapter.setCheck(true,true);
                        comBoxLineAdapter.notifyDataSetChanged();
                        lineExport.clear();
                        lineExport.addAll(linesList);
                        Log.i("tag",String.valueOf(lineExport.size()));
                    }else{
                        comBoxLineAdapter.setCheck(true,false);
                        comBoxLineAdapter.notifyDataSetChanged();
                        lineExport.clear();
                        Log.i("tag",String.valueOf(lineExport.size()));
                    }
                    Intent i=new Intent("com.checkbox_line.broadcaster");
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
        pposition=getTable.getTableposition(GeoInportLineActivity.this,db,dbHelper,projects);
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
        linesList = new ArrayList<>();
        lineExport = new ArrayList<>();
        Cursor cursor = db.query("Geomorelineexcel"+pposition, null, null, null, null, null, null);
        linesList=getData(linesList, cursor);
        resource=R.layout.table_left_item;
        resource2=R.layout.geotable_right_itemline;
        resource3 = R.layout.table_combox_item;
        leftlineAdapter=new GeoLeftListLineAdapter(linesList,resource,GeoInportLineActivity.this);
        rightLineAdapter=new GeoRightLineAdapter(linesList,resource2,GeoInportLineActivity.this);
        comBoxLineAdapter = new ComBoxLineAdapter(linesList,resource3,GeoInportLineActivity.this);
        leftlistView.setAdapter(leftlineAdapter);
        rightlistView.setAdapter(rightLineAdapter);
        leftlineAdapter.notifyDataSetInvalidated();
        comboxlistview.setAdapter(comBoxLineAdapter);
        comBoxLineAdapter.notifyDataSetChanged();
        comboxlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComBoxLineAdapter.ViewHolder holder = (ComBoxLineAdapter.ViewHolder) view.getTag();
                rightLineAdapter.setSelectItem(position);
                rightLineAdapter.notifyDataSetChanged();
                leftlineAdapter.setSelectItem(position);
                leftlineAdapter.notifyDataSetChanged();
                comBoxLineAdapter.setSelectItem(position);
                comBoxLineAdapter.notifyDataSetChanged();
                line = linesList.get(position);
                if(holder.getCheckBox().isChecked()!=true){
                    lineExport.add(line);
                    Log.i("tag",String.valueOf(lineExport.size()));
                }else{
                    if (lineExport.contains(line)){
                        lineExport.remove(line);
                        Log.i("tag",String.valueOf(lineExport.size()));
                    }
                }
            }
        });
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
                comBoxLineAdapter.setSelectItem(position);
                comBoxLineAdapter.notifyDataSetChanged();
                p=position;
                MoreLines line=linesList.get(position);
                id=line.getId();
                classification=line.getClassification();
                des=line.getDescription();
                gla=line.getListla();
                gln=line.getListln();
                b = true;
                if (lineExport.contains(line)){
                    lineExport.remove(line);
                    Log.i("tag",String.valueOf(lineExport.size()));
                }else{
                    lineExport.add(line);
                    Log.i("tag",String.valueOf(lineExport.size()));
                }
            }
        });
    }
    //查询数据
    public List<MoreLines> getData(List<MoreLines> list, Cursor cursor) throws ParseException {
        if (cursor.moveToFirst()) {
            do {
                //遍历cursor对象，取出数据
                MoreLines lines=new MoreLines();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String classification=cursor.getString(cursor.getColumnIndex("gclassification"));
                String gla = cursor.getString(cursor.getColumnIndex("gla"));
                String gln = cursor.getString(cursor.getColumnIndex("gln"));
                String datetime=cursor.getString(cursor.getColumnIndex("time"));
                String code=cursor.getString(cursor.getColumnIndex("gcode"));
                //String formatType="yyyy-MM-dd HH:mm:ss";
                String description = cursor.getString(cursor.getColumnIndex("gdescription"));
                //long time=Stringtolong(date,formatType);
                //String datetime=getTime(time);
                lines.setId(id);
                lines.setClassification(classification);
                lines.setListla(Arrays.asList(gla.split(",")));
                lines.setListln(Arrays.asList(gln.split(",")));
                lines.setDatatime(datetime);
                lines.setDescription(description);
                lines.setCode(code);
                list.add(lines);
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
            AlertDialog dialog = new AlertDialog.Builder(GeoInportLineActivity.this).setTitle("警告：")
                    .setMessage("是否删除全部数据？")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
                            //清空lines表
                            Cursor cursor1=db.query("Geomorelineexcel"+pposition,null,null,null,null,null,null);
                            if (cursor1.moveToFirst()){
                                do {
                                    String xla=cursor1.getString(cursor1.getColumnIndex("gla"));
                                    listLa.add(xla);
                                }while(cursor1.moveToNext());
                            }
                            cursor1.close();
                            for (int i=0;i<listLa.size();i++){
                                db.delete("Geomorelineexcel"+pposition, "gla=?", new String[]{listLa.get(i)});
                            }

                            Cursor cursor = db.query("Geomorelineexcel"+pposition, null, null, null, null, null, null);
                            List<MoreLines> lineslist2=new ArrayList<>();
                            try {
                                lineslist2=getData(lineslist2,cursor);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            final List<MoreLines> finallinelist=lineslist2;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    leftlineAdapter=new GeoLeftListLineAdapter(finallinelist,resource,GeoInportLineActivity.this);
                                    rightLineAdapter=new GeoRightLineAdapter(finallinelist,resource2,GeoInportLineActivity.this);
                                    leftlistView.setAdapter(leftlineAdapter);
                                    rightlistView.setAdapter(rightLineAdapter);
                                    ToastNotRepeat.show(GeoInportLineActivity.this,"删除成功");
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
                    lineExport.clear();
                    finish();
                    break;
                case R.id.bt5:
                    Intent i=new Intent(GeoInportLineActivity.this,GeologicalMapActivity.class);
                    i.putExtra("list2", (Serializable) linesList);
                    startActivity(i);
                    Intent li=new Intent("com.showall.broadcasttest");
                    sendBroadcast(li);
                    lineExport.clear();
                    finish();
                    break;
                case R.id.bt1:
                    if (b){
                        LinearLayout linearLayout2 = (LinearLayout) getLayoutInflater().inflate(R.layout.geodetaildata_line, null);
                        AlertDialog dialog1 = new AlertDialog.Builder(GeoInportLineActivity.this)
                                .setTitle("详细：")
                                .setView(linearLayout2)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("查看", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(GeoInportLineActivity.this,GeologicalMapActivity.class);
                                        List<String> mgla = new ArrayList<>(gla);
                                        List<String> mgln = new ArrayList<>(gln);
                                        i.putStringArrayListExtra("gla", (ArrayList<String>) mgla);
                                        i.putStringArrayListExtra("gln", (ArrayList<String>) mgln);
                                        i.putExtra("clas",classification);
                                        startActivity(i);
                                        Intent li=new Intent("com.showline.broadcasttest");
                                        sendBroadcast(li);
                                        lineExport.clear();
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
                        AlertDialog dialog = new AlertDialog.Builder(GeoInportLineActivity.this)
                                .setTitle("数据编辑：")
                                .setView(linearLayout)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String finaltext=editText.getText().toString();
                                        ContentValues values = new ContentValues();
                                        values.put("gdescription", finaltext);
                                        db.update("Geomorelineexcel"+pposition, values, "id=?", new String[]{String.valueOf(id)});
                                        linesList.get(p).setDescription(finaltext);
                                        if (imm!=null){
                                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rightLineAdapter.notifyDataSetChanged();
                                                ToastNotRepeat.show(GeoInportLineActivity.this,"保存成功");
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
                case R.id.export:
                    LinearLayout linearLayout1= (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogexport,null);
                    AlertDialog dialog3=new AlertDialog.Builder(GeoInportLineActivity.this)
                            .setTitle("导出数据:")
                            .setView(linearLayout1)
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
                                            //GeoExportLineUtils.writelineExcel(GeoInportLineActivity.this,lineExport,filename);
                                            ToastNotRepeat.show(GeoInportLineActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt1.isChecked()){
                                        GeoWirteLineGpx wirteLineGpx = new GeoWirteLineGpx();
                                        try{
                                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date curDate =  new Date(System.currentTimeMillis());
                                            String  str  =  formatter.format(curDate);
                                            //wirteLineGpx.createlineGpx(filename,lineExport,str);
                                            ToastNotRepeat.show(GeoInportLineActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt2.isChecked()){
                                        GeoWriteLineKml writeLineKml = new GeoWriteLineKml();
                                        try{
                                            //writeLineKml.createKml(filename,lineExport);
                                            ToastNotRepeat.show(GeoInportLineActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else {
                                        LinearLayout linearLayout1= (LinearLayout) getLayoutInflater().inflate(R.layout.choosesystem,null);
                                        final String finalFilename = filename;
                                        AlertDialog dialog1=new AlertDialog.Builder(GeoInportLineActivity.this)
                                                .setTitle("选择坐标系统：")
                                                .setView(linearLayout1)
                                                .setNegativeButton("取消",null)
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (radioButton1.isChecked()){
                                                            GeoWriteLineCass writeLineCass = new GeoWriteLineCass();
                                                            try{
                                                                //writeLineCass.creatWgs84(finalFilename,lineExport);
                                                                ToastNotRepeat.show(GeoInportLineActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else if (radioButton2.isChecked()){
                                                            GeoWriteLineCass writeLineCass = new GeoWriteLineCass();
                                                            try{
                                                                //writeLineCass.createbeijing54(finalFilename,lineExport);
                                                                ToastNotRepeat.show(GeoInportLineActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else if (radioButton3.isChecked()){
                                                            GeoWriteLineCass writeLineCass = new GeoWriteLineCass();
                                                            try{
                                                                //writeLineCass.createxian80(finalFilename,lineExport);
                                                                ToastNotRepeat.show(GeoInportLineActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }else {
                                                            GeoWriteLineCass writeLineCass = new GeoWriteLineCass();
                                                            try{
                                                                //writeLineCass.createguojia2000(finalFilename,lineExport);
                                                                ToastNotRepeat.show(GeoInportLineActivity.this,"导出成功！");
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
                    editText= (EditText) dialog3.findViewById(R.id.filename);
                    bt1= (RadioButton) dialog3.findViewById(R.id.gpx);
                    bt2= (RadioButton) dialog3.findViewById(R.id.kml);
                    bt3= (RadioButton) dialog3.findViewById(R.id.cass);
                    bt4= (RadioButton) dialog3.findViewById(R.id.excel);
                    Button btnpositive1=dialog3.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative1=dialog3.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive1.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative1.setTextColor(getResources().getColor(R.color.color29));
                    break;
                case R.id.bt3:
                    if (lineExport.size()!=0){
                        AlertDialog dialog = new AlertDialog.Builder(GeoInportLineActivity.this)
                                .setTitle("删除数据")
                                .setMessage("是否删除数据：")
                                .setNegativeButton("取消",null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        b = false;
                                        for(int i =0;i<lineExport.size();i++){
                                            db.delete("Geomorelineexcel"+pposition, "id=?", new String[]{String.valueOf(lineExport.get(i).getId())});
                                            linesList.remove(lineExport.get(i));
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                leftlineAdapter.notifyDataSetChanged();
                                                rightLineAdapter.notifyDataSetChanged();
                                                comBoxLineAdapter.notifyDataSetChanged();
                                            }
                                        },400);
                                        Intent i=new Intent("com.exampleline.broadcast");
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
                    lineExport.clear();
                    finish();
                    break;
                case R.id.search:
                    String tsname = text.getText().toString();
                    if (TextUtils.isEmpty(text.getText())) {
                        linesList.clear();
                        Cursor cursor = db.query("Geomorelineexcel"+pposition, null, null, null, null, null, null);
                        try {
                            getData(linesList, cursor);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftlineAdapter.notifyDataSetChanged();
                                rightLineAdapter.notifyDataSetChanged();
                                comBoxLineAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    } else {
                        linesList.subList(0, linesList.size()).clear();
                        Cursor cursor2 = db.query("Geomorelineexcel"+pposition, null, "gclassification like ?", new String[]{ "%" + tsname + "%" }, null, null, null);
                        try {
                            getData(linesList, cursor2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (linesList.size() == 0) {
                            ToastNotRepeat.show(GeoInportLineActivity.this, "抱歉，没有找到你要的数据！");
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftlineAdapter.notifyDataSetChanged();
                                rightLineAdapter.notifyDataSetChanged();
                                comBoxLineAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    }
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
}
