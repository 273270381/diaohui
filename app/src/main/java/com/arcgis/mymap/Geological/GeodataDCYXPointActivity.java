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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arcgis.mymap.Alert_dialogActivity;
import com.arcgis.mymap.Export.GeoWriteGpx;
import com.arcgis.mymap.Export.GeoWritekml;
import com.arcgis.mymap.NewDataActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DCYXadapter.GeoBoxDcyxAdapter;
import com.arcgis.mymap.adapter.DCYXadapter.GeoDcyxCass;
import com.arcgis.mymap.adapter.DCYXadapter.GeoDcyxUtils;
import com.arcgis.mymap.adapter.DCYXadapter.LeftDcyxAdapter;
import com.arcgis.mymap.adapter.DCYXadapter.RightDcyxAdapter;
import com.arcgis.mymap.adapter.DXDMadapter.ComBoxDxdmAdapter;
import com.arcgis.mymap.adapter.DXDMadapter.GeoDxdmCass;
import com.arcgis.mymap.adapter.DXDMadapter.GeoDxdmUtils;
import com.arcgis.mymap.contacts.DicengyanxingPoint;
import com.arcgis.mymap.contacts.DixingdimaoPoint;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.SyncHorizontalScrollView;
import com.arcgis.mymap.utils.ToastNotRepeat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeodataDCYXPointActivity extends Activity{
    private TextView tv_table_title_left;
    private LinearLayout right_title_container;
    private ListView leftlistView,rightlistView,comboxlistview;
    private SyncHorizontalScrollView titleHorScv,contentHorScv;
    private MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public String pposition;
    public List<NewProject> projects=new ArrayList<>();
    public List<DicengyanxingPoint> pointsList,listExport;
    public DicengyanxingPoint point;
    private LeftDcyxAdapter leftAdapter;
    private RightDcyxAdapter rightAdapter;
    private GeoBoxDcyxAdapter comBoxAdapter;
    private CheckBox checkBox;
    public ImageButton back,search;
    public GridView gridView;
    public int resource,resource2,resource3;
    public EditText text,eText1,eText2,eText3;
    public Button delate,findall,close,edit,detailed,bt_export;
    public String name,dznd,ytmc,cylx,fhcd,cz,jl,des,la,ln,high;
    public int id,p;
    public boolean b = false;
    public EditText editText;
    public RadioButton bt1,bt2,bt3,bt4;
    public RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    public InputMethodManager imm;
    public String[] titles;
    public Integer[] pictrues;
    public Alert_dialogActivity alert_dialogActivity;
    public NewDataActivity.PictureAdapter adapter;
    private List<String> listLa=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newdata_manager);
        try {
            init();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void init() throws ParseException {
        findByid();
        listener();
        alert_dialogActivity=new Alert_dialogActivity();
        titles=alert_dialogActivity.getStrings();
        pictrues=alert_dialogActivity.getInteger();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    comBoxAdapter.setCheck(true,true);
                    comBoxAdapter.notifyDataSetChanged();
                    listExport.clear();
                    listExport.addAll(pointsList);
                    Log.i("tag",String.valueOf(listExport.size()));
                }else{
                    comBoxAdapter.setCheck(true,false);
                    comBoxAdapter.notifyDataSetChanged();
                    listExport.clear();
                    Log.i("tag",String.valueOf(listExport.size()));
                }
                Intent i=new Intent("com.checkbox.broadcaster");
                sendBroadcast(i);
            }
        });
    }
    //为控件添加监听
    private void listener() {
        ClickListener listener = new ClickListener();
        LongClickLister longClickLister=new LongClickLister();
        back.setOnClickListener(listener);
        detailed.setOnClickListener(listener);
        edit.setOnClickListener(listener);
        close.setOnClickListener(listener);
        delate.setOnClickListener(listener);
        bt_export.setOnClickListener(listener);
        delate.setOnLongClickListener(longClickLister);
        search.setOnClickListener(listener);
        findall.setOnClickListener(listener);
    }
    /**
     * 初始化控件
     * @throws ParseException
     */
    public void findByid() throws ParseException {
        checkBox = (CheckBox) findViewById(R.id.item_cb);
        bt_export= (Button) findViewById(R.id.export);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        tv_table_title_left = (TextView) findViewById(R.id.tv_table_title_left);
        tv_table_title_left.setText("序号");
        right_title_container = (LinearLayout) findViewById(R.id.right_title_container);
        getLayoutInflater().inflate(R.layout.geo_table_right_title_dcyx, right_title_container);
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
        pposition=getTable.getTableposition(GeodataDCYXPointActivity.this,db,dbHelper,projects);
        back = (ImageButton) findViewById(R.id.back);
        search = (ImageButton) findViewById(R.id.search);
        text = (EditText) findViewById(R.id.editText);
        delate = (Button) findViewById(R.id.bt3);
        findall= (Button) findViewById(R.id.bt5);
        close = (Button) findViewById(R.id.bt4);
        edit = (Button) findViewById(R.id.bt2);
        eText2 = (EditText) findViewById(R.id.etext2);
        detailed= (Button) findViewById(R.id.bt1);
        initTableView();
    }
    private void initTableView() throws ParseException {
        //查询Newpoints表中的所有数据
        pointsList = new ArrayList<>();
        listExport = new ArrayList<>();
        Cursor cursor = db.query("Geodcyxpoints"+pposition, null, null, null, null, null, null);
        pointsList=getData(pointsList, cursor);
        resource=R.layout.table_left_item;
        resource2=R.layout.geo_table_right_item_dcyx;
        resource3 = R.layout.table_combox_item;
        leftAdapter=new LeftDcyxAdapter(pointsList,resource,GeodataDCYXPointActivity.this);
        rightAdapter=new RightDcyxAdapter(pointsList,resource2,GeodataDCYXPointActivity.this);
        comBoxAdapter = new GeoBoxDcyxAdapter(pointsList,resource3,GeodataDCYXPointActivity.this);
        leftlistView.setAdapter(leftAdapter);
        rightlistView.setAdapter(rightAdapter);
        comboxlistview.setAdapter(comBoxAdapter);
        comBoxAdapter.notifyDataSetChanged();
        comboxlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                GeoBoxDcyxAdapter.ViewHolder holder = (GeoBoxDcyxAdapter.ViewHolder) view.getTag();
                //holder.getCheckBox().toggle();
                rightAdapter.setSelectItem(position);
                rightAdapter.notifyDataSetChanged();
                leftAdapter.setSelectItem(position);
                leftAdapter.notifyDataSetChanged();
                comBoxAdapter.notifyDataSetChanged();
                comBoxAdapter.setSelectItem(position);
                point =pointsList.get(position);
                id=point.getId();
                name = point.getName();
                dznd = point.getDznd();
                ytmc = point.getYtmc();
                cylx = point.getClassification();
                fhcd = point.getFhcd();
                cz = point.getCz();
                jl = point.getJl();
                des = point.getDescription();
                la=point.getLa();
                ln=point.getLn();
                high=point.getHigh();
                b = true;
                if(holder.getCheckBox().isChecked()!=true){
                    listExport.add(point);
                }else{
                    if (listExport.contains(point)){
                        listExport.remove(point);
                    }
                }
            }
        });
        leftAdapter.notifyDataSetInvalidated();
        leftlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
            }
        });
        rightlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                rightAdapter.setSelectItem(position);
                rightAdapter.notifyDataSetChanged();
                leftAdapter.setSelectItem(position);
                leftAdapter.notifyDataSetChanged();
                comBoxAdapter.notifyDataSetChanged();
                comBoxAdapter.setSelectItem(position);
                p=position;
                DicengyanxingPoint point = pointsList.get(position);
                id=point.getId();
                name = point.getName();
                des = point.getDescription();
                la=point.getLa();
                ln=point.getLn();
                high=point.getHigh();
                b = true;
                if (listExport.contains(point)){
                    listExport.remove(point);
                }else{
                    listExport.add(point);
                }
            }
        });
    }
    //根据查询条件，查询数据库
    public List<DicengyanxingPoint> getData(List<DicengyanxingPoint> list, Cursor cursor) throws ParseException {
        if (cursor.moveToFirst()) {
            do {
                //遍历cursor对象，取出数据
                DicengyanxingPoint dicengyanxingPoint = new DicengyanxingPoint();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String la = cursor.getString(cursor.getColumnIndex("la"));
                String ln = cursor.getString(cursor.getColumnIndex("ln"));
                String high = cursor.getString(cursor.getColumnIndex("high"));
                String dznd=cursor.getString(cursor.getColumnIndex("dznd"));
                String ytmc=cursor.getString(cursor.getColumnIndex("ytmc"));
                String cla=cursor.getString(cursor.getColumnIndex("gclassification"));
                String code=cursor.getString(cursor.getColumnIndex("gcode"));
                String fhcd=cursor.getString(cursor.getColumnIndex("fhcd"));
                String cz=cursor.getString(cursor.getColumnIndex("cz"));
                String jl=cursor.getString(cursor.getColumnIndex("jl"));
                String miaoshu=cursor.getString(cursor.getColumnIndex("gdescription"));

                dicengyanxingPoint.setId(id);
                dicengyanxingPoint.setName(name);
                dicengyanxingPoint.setLa(la);
                dicengyanxingPoint.setLn(ln);
                dicengyanxingPoint.setHigh(high);
                dicengyanxingPoint.setDznd(dznd);
                dicengyanxingPoint.setYtmc(ytmc);
                dicengyanxingPoint.setClassification(cla);
                dicengyanxingPoint.setCode(code);
                dicengyanxingPoint.setFhcd(fhcd);
                dicengyanxingPoint.setCz(cz);
                dicengyanxingPoint.setJl(jl);
                dicengyanxingPoint.setDescription(miaoshu);

                list.add(dicengyanxingPoint);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    private class LongClickLister implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            AlertDialog dialog=new AlertDialog.Builder(GeodataDCYXPointActivity.this).setTitle("警告：")
                    .setMessage("是否删除全部数据？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
                            //删除point表
                            Cursor cursor1=db.query("Geodcyxpoints"+pposition,null,null,null,null,null,null);
                            if (cursor1.moveToFirst()){
                                do {
                                    String la=cursor1.getString(cursor1.getColumnIndex("la"));
                                    listLa.add(la);
                                }while(cursor1.moveToNext());
                            }
                            cursor1.close();
                            for (int i=0;i<listLa.size();i++){
                                db.delete("Geodcyxpoints"+pposition, "la=?", new String[]{listLa.get(i)});
                            }
                            Cursor cursor = db.query("Geodcyxpoints"+pposition, null, null, null, null, null, null);
                            List<DicengyanxingPoint> pointsList2 = new ArrayList<>();
                            try {
                                pointsList2=getData(pointsList2, cursor);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            final List<DicengyanxingPoint> finalPointsList = pointsList2;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    leftAdapter=new LeftDcyxAdapter(finalPointsList,resource,GeodataDCYXPointActivity.this);
                                    rightAdapter=new RightDcyxAdapter(finalPointsList,resource2,GeodataDCYXPointActivity.this);
                                    leftlistView.setAdapter(leftAdapter);
                                    rightlistView.setAdapter(rightAdapter);
                                }
                            }, 400);
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
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    listExport.clear();
                    finish();
                    break;
                case R.id.bt5:
                    Intent i=new Intent(GeodataDCYXPointActivity.this,GeologicalMapActivity.class);
                    i.putExtra("list1", (Serializable) pointsList);
                    startActivity(i);
                    Intent li=new Intent("com.showall.broadcasttest");
                    sendBroadcast(li);
                    listExport.clear();
                    finish();
                    break;
                case R.id.bt1:
                    if (b){
                        LinearLayout linearLayout2 = (LinearLayout) getLayoutInflater().inflate(R.layout.detaildata_data_dcyx, null);
                        AlertDialog dialog1 = new AlertDialog.Builder(GeodataDCYXPointActivity.this)
                                .setTitle("详细：")
                                .setView(linearLayout2)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("查看", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(GeodataDCYXPointActivity.this,GeologicalMapActivity.class);
                                        i.putExtra("name",name);
                                        i.putExtra("dznd",dznd);
                                        i.putExtra("ytmc",ytmc);
                                        i.putExtra("cylx",cylx);
                                        i.putExtra("fhcd",fhcd);
                                        i.putExtra("cz",cz);
                                        i.putExtra("jl",jl);
                                        i.putExtra("des",des);
                                        startActivity(i);
                                        Intent li=new Intent("com.showpoint.broadcasttest");
                                        sendBroadcast(li);
                                        listExport.clear();
                                        finish();
                                    }
                                })
                                .show();
                        TextView tv1 = (TextView) dialog1.findViewById(R.id.xuhao);
                        TextView tv2 = (TextView) dialog1.findViewById(R.id.dianming);
                        TextView tv3 = (TextView) dialog1.findViewById(R.id.dznd);
                        TextView tv4 = (TextView) dialog1.findViewById(R.id.ytmc);
                        TextView tv5 = (TextView) dialog1.findViewById(R.id.cylx);
                        TextView tv6 = (TextView) dialog1.findViewById(R.id.fhcd);
                        TextView tv7 = (TextView) dialog1.findViewById(R.id.cz);
                        TextView tv8 = (TextView) dialog1.findViewById(R.id.jl);
                        TextView tv9 = (TextView) dialog1.findViewById(R.id.miaoshu);
                        tv1.setText(String.valueOf(id));
                        tv2.setText(name);
                        tv3.setText(dznd);
                        tv4.setText(ytmc);
                        tv5.setText(cylx);
                        tv6.setText(fhcd);
                        tv7.setText(cz);
                        tv8.setText(jl);
                        tv9.setText(des);
                        Button btnpositive=dialog1.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog1.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt2:
                    if (b){
                        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogedit_dxdm, null);
                        AlertDialog dialog = new AlertDialog.Builder(GeodataDCYXPointActivity.this)
                                .setTitle("数据编辑：")
                                .setView(linearLayout)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String finalText1 = eText1.getText().toString();
                                        String finalText2 = eText2.getText().toString();
                                        ContentValues values = new ContentValues();
                                        values.put("name", finalText1);
                                        values.put("gdescription", finalText2);
                                        db.update("Geodcyxpoints"+pposition, values, "id=?", new String[]{String.valueOf(id)});
                                        pointsList.get(p).setName(finalText1);
                                        pointsList.get(p).setDescription(finalText2);
                                        if (imm!=null){
                                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rightAdapter.notifyDataSetChanged();
                                                ToastNotRepeat.show(GeodataDCYXPointActivity.this,"保存成功");
                                            }
                                        }, 400);
                                        Intent a=new Intent();
                                        a.setAction("com.delatedatas.broadcast");
                                        sendBroadcast(a);
                                    }
                                })
                                .show();
                        eText1 = (EditText) dialog.findViewById(R.id.etext1);
                        eText1.setText(name);
                        eText2 = (EditText) dialog.findViewById(R.id.etext2);
                        eText2.setText(des);
                        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt3:
                    if (listExport.size()!=0){
                        AlertDialog dialog=new AlertDialog.Builder(GeodataDCYXPointActivity.this)
                                .setTitle("删除数据")
                                .setMessage("是否删除数据：")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        b = false;
                                        for (int i = 0 ; i < listExport.size();i++){
                                            db.delete("Geodcyxpoints"+pposition, "id=?", new String[]{String.valueOf(listExport.get(i).getId())});
                                            pointsList.remove(listExport.get(i));
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                leftAdapter.notifyDataSetChanged();
                                                rightAdapter.notifyDataSetChanged();
                                                comBoxAdapter.notifyDataSetChanged();
                                            }
                                        }, 400);
                                        Intent i=new Intent("com.example.broadcast");
                                        sendBroadcast(i);
                                        Intent a=new Intent();
                                        a.setAction("com.checkbox.broadcaster");
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
                    listExport.clear();
                    finish();
                    break;
                case R.id.export:
                    LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogexport,null);
                    AlertDialog dialog=new AlertDialog.Builder(GeodataDCYXPointActivity.this)
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
                                            GeoDcyxUtils.writeExcel(GeodataDCYXPointActivity.this,listExport,filename);
                                            ToastNotRepeat.show(GeodataDCYXPointActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt1.isChecked()) {
                                        GeoWriteGpx writeGpx = new GeoWriteGpx();
                                        try{
                                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date curDate =  new Date(System.currentTimeMillis());
                                            String  str  =  formatter.format(curDate);
                                            writeGpx.createDcyxGpx(filename,listExport,str);
                                            ToastNotRepeat.show(GeodataDCYXPointActivity.this,"导出成功！");
                                        }catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }else if (bt2.isChecked()){
                                        GeoWritekml writekml = new GeoWritekml();
                                        try {
                                            writekml.createDcyxKml(filename,listExport);
                                            ToastNotRepeat.show(GeodataDCYXPointActivity.this,"导出成功！");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }else {
                                        LinearLayout linearLayout1= (LinearLayout) getLayoutInflater().inflate(R.layout.choosesystem,null);
                                        final String finalFilename = filename;
                                        AlertDialog dialog1=new AlertDialog.Builder(GeodataDCYXPointActivity.this)
                                                .setTitle(" 选择坐标系统：")
                                                .setView(linearLayout1)
                                                .setNegativeButton("取消",null)
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        if (radioButton1.isChecked()){
                                                            GeoDcyxCass writeCASS = new GeoDcyxCass();
                                                            try{
                                                                writeCASS.creatWgs84(finalFilename,listExport);
                                                                ToastNotRepeat.show(GeodataDCYXPointActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        else if (radioButton2.isChecked()){
                                                            GeoDcyxCass writeCASS = new GeoDcyxCass();
                                                            try{
                                                                writeCASS.createbeijing54(finalFilename,listExport);
                                                                ToastNotRepeat.show(GeodataDCYXPointActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        else if (radioButton3.isChecked()){
                                                            GeoDcyxCass writeCASS = new GeoDcyxCass();
                                                            try{
                                                                writeCASS.createxian80(finalFilename,listExport);
                                                                ToastNotRepeat.show(GeodataDCYXPointActivity.this,"导出成功！");
                                                            }catch(Exception e){
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                        else {
                                                            GeoDcyxCass writeCASS = new GeoDcyxCass();
                                                            try{
                                                                writeCASS.createguojia2000(finalFilename,listExport);
                                                                ToastNotRepeat.show(GeodataDCYXPointActivity.this,"导出成功！");
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
                    Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    break;
                case R.id.search:
                    String tsname = text.getText().toString();
                    if (TextUtils.isEmpty(text.getText())) {
                        pointsList.clear();
                        Cursor cursor = db.query("Geodcyxpoints"+pposition, null, null, null, null, null, null);
                        try {
                            getData(pointsList, cursor);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftAdapter.notifyDataSetChanged();
                                rightAdapter.notifyDataSetChanged();
                                comBoxAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    } else {
                        pointsList.subList(0, pointsList.size()).clear();
                        Cursor cursor1 = db.query("Geodcyxpoints"+pposition, null, "name like ?", new String[]{"%" + tsname + "%"}, null, null, null);
                        try {
                            getData(pointsList, cursor1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Cursor cursor2 = db.query("Geodcyxpoints"+pposition, null, "gclassification like ?", new String[]{"%" + tsname + "%"}, null, null, null);
                        try {
                            getData(pointsList, cursor2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (pointsList.size() == 0) {
                            ToastNotRepeat.show(GeodataDCYXPointActivity.this, "抱歉，没有找到你要的数据！");
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftAdapter.notifyDataSetChanged();
                                rightAdapter.notifyDataSetChanged();
                                comBoxAdapter.notifyDataSetChanged();
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
    //遍历数组
}
