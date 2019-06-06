package com.arcgis.mymap.Geological;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.arcgis.mymap.MainMenuActivity;
import com.arcgis.mymap.ProjectManagementActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.utils.LogUtils;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/18.
 */

public class GeoprojectActivity  extends AppCompatActivity {
    private ConvenientBanner convenientBanner;
    private List<Integer> imgs=new ArrayList<>();
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private List<String> listLa=new ArrayList<>();
    private List<String> listLa2=new ArrayList<>();
    private List<String> listLa3=new ArrayList<>();
    private List<String> listLa4=new ArrayList<>();
    private List<String> listLa5=new ArrayList<>();
    private List<String> listLa6=new ArrayList<>();
    private List<String> listLa7=new ArrayList<>();
    private List<String> listLa8=new ArrayList<>();
    private List<String> listLa9=new ArrayList<>();
    private List<String> listLa10=new ArrayList<>();
    private EditText x,y,z,rx,rz,ry,ppm,zyzw;
    private String String_x,String_y,String_z,String_rx,String_ry,String_rz,String_ppm,String_zyzw;
    private SimpleAdapter adapter;
    private EditText dt;
    private EditText name;
    private EditText projectNum;
    //private TextView tv;
    public String f;
    public String  pposition;
    public int amount=0;
    private static final int REQUEST_CHOOSER = 1234;
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public ContentValues values;
    public GeoMyreceiver myreceiver;
    List<Integer> list1=new ArrayList<>();
    List<String> list2=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        setViews();
        initData();
        setConvenientBanner();
        showGridView();
    }
    //初始化数据
    private void initData() {
        list1.add(R.mipmap.xinjian);
        list2.add("新建项目");
        dataList=new ArrayList<>();
        Cursor cursor=db.query("Geonewproject",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历cursor对象，取出数据
                String name=cursor.getString(cursor.getColumnIndex("gpname"));
                list2.add(list2.size()-1,name);
            }while (cursor.moveToNext());
        }
        cursor.close();
        for (int i=0;i<list2.size()-1;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",R.mipmap.dakai);
            map.put("text",list2.get(i));
            dataList.add(map);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("img",R.mipmap.xinjian);
        map.put("text",list2.get(list2.size()-1));
        dataList.add(map);
    }
    //gridView添加数据后显示
    private void showGridView(){
        String[] from={"img","text"};
        int[] to={R.id.img,R.id.text};
        adapter=new SimpleAdapter(this, dataList, R.layout.gridview_item, from, to);
        gridView.setAdapter(adapter);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position!=dataList.size()-1){
                    new AlertDialog.Builder(GeoprojectActivity.this)
                            .setTitle("删除项目")
                            .setMessage("是否删除项目：")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Map<String,Object> map=dataList.get(position);
                                    dataList.remove(dataList.get(position));
                                    String pname= (String) map.get("text");
                                    //先添加后删除
                                    Cursor cursor = db.query("Geonewproject", null, "gpname = ?", new String[]{pname}, null, null, null);
                                    if (cursor.moveToFirst()){
                                        pposition=cursor.getString(cursor.getColumnIndex("gposition"));
                                    }
                                    cursor.close();
                                    ContentValues values=new ContentValues();
                                    values.put("gposition",pposition);
                                    db.insert("Geonewposition",null,values);
                                    values.clear();
                                    //删除Geodxdmpoints表
                                    Cursor cursor1=db.query("Geodxdmpoints"+pposition,null,null,null,null,null,null);
                                    if (cursor1.moveToFirst()){
                                        do {
                                            String la=cursor1.getString(cursor1.getColumnIndex("la"));
                                            listLa.add(la);
                                        }while(cursor1.moveToNext());
                                    }
                                    cursor1.close();
                                    for (int i=0;i<listLa.size();i++){
                                        db.delete("Geodxdmpoints"+pposition, "la=?", new String[]{listLa.get(i)});
                                    }
                                    //删除Geodcyxpoints表
                                    Cursor cursor2=db.query("Geodcyxpoints"+pposition,null,null,null,null,null,null);
                                    if (cursor2.moveToFirst()){
                                        do {
                                            String la=cursor2.getString(cursor2.getColumnIndex("la"));
                                            listLa2.add(la);
                                        }while(cursor2.moveToNext());
                                    }
                                    cursor2.close();
                                    for (int i=0;i<listLa2.size();i++){
                                        db.delete("Geodcyxpoints"+pposition, "la=?", new String[]{listLa2.get(i)});
                                    }
                                    //删除Geoswdzpoints表
                                    Cursor cursor3=db.query("Geoswdzpoints"+pposition,null,null,null,null,null,null);
                                    if (cursor3.moveToFirst()){
                                        do {
                                            String la=cursor3.getString(cursor3.getColumnIndex("la"));
                                            listLa3.add(la);
                                        }while(cursor3.moveToNext());
                                    }
                                    cursor3.close();
                                    for (int i=0;i<listLa3.size();i++){
                                        db.delete("Geoswdzpoints"+pposition, "la=?", new String[]{listLa3.get(i)});
                                    }
                                    //删除Geogzwdpoints表
                                    Cursor cursor4=db.query("Geogzwdpoints"+pposition,null,null,null,null,null,null);
                                    if (cursor4.moveToFirst()){
                                        do {
                                            String la=cursor4.getString(cursor4.getColumnIndex("la"));
                                            listLa4.add(la);
                                        }while(cursor4.moveToNext());
                                    }
                                    cursor4.close();
                                    for (int i=0;i<listLa4.size();i++){
                                        db.delete("Geogzwdpoints"+pposition, "la=?", new String[]{listLa4.get(i)});
                                    }
                                    //删除geophoto表
                                    Cursor cursor5=db.query("Geophotopoints"+pposition,null,null,null,null,null,null);
                                    if (cursor5.moveToFirst()){
                                        do {
                                            String la=cursor5.getString(cursor5.getColumnIndex("la"));
                                            listLa5.add(la);
                                        }while(cursor5.moveToNext());
                                    }
                                    cursor5.close();
                                    for (int i=0;i<listLa5.size();i++){
                                        db.delete("Geophotopoints"+pposition, "la=?", new String[]{listLa5.get(i)});
                                    }
                                    /*//删除Geonewlines表
                                    Cursor cursor2=db.query("Geonewlines"+pposition,null,null,null,null,null,null);
                                    if (cursor2.moveToFirst()){
                                        do {
                                            String la=cursor2.getString(cursor2.getColumnIndex("xla"));
                                            listXLa.add(la);
                                        }while(cursor2.moveToNext());
                                    }
                                    cursor2.close();
                                    for (int i=0;i<listXLa.size();i++){
                                        db.delete("Geonewlines"+pposition, "xla=?", new String[]{listXLa.get(i)});
                                    }*/
                                    //删除Geopointexcel表
                                    Cursor cursor6=db.query("Geopointexcel"+pposition,null,null,null,null,null,null);
                                    if (cursor6.moveToFirst()){
                                        do {
                                            String la=cursor6.getString(cursor6.getColumnIndex("la"));
                                            listLa6.add(la);
                                        }while(cursor6.moveToNext());
                                    }
                                    cursor6.close();
                                    for (int i=0;i<listLa6.size();i++){
                                        db.delete("Geopointexcel"+pposition, "la=?", new String[]{listLa6.get(i)});
                                    }
                                   /* //删除Geolineexcel表
                                    Cursor cursor4=db.query("Geolineexcel"+pposition,null,null,null,null,null,null);
                                    if (cursor4.moveToFirst()){
                                        do {
                                            String la=cursor4.getString(cursor4.getColumnIndex("xla"));
                                            listXLa2.add(la);
                                        }while(cursor4.moveToNext());
                                    }
                                    cursor4.close();
                                    for (int i=0;i<listXLa2.size();i++){
                                        db.delete("Geolineexcel"+pposition, "xla=?", new String[]{listXLa2.get(i)});
                                    }*/
                                    //删除Geomorelines表
                                    Cursor cursor7=db.query("Geomorelines"+pposition,null,null,null,null,null,null);
                                    if (cursor7.moveToFirst()){
                                        do {
                                            String la=cursor7.getString(cursor7.getColumnIndex("gla"));
                                            listLa7.add(la);
                                        }while(cursor7.moveToNext());
                                    }
                                    cursor7.close();
                                    for (int i=0;i<listLa7.size();i++){
                                        db.delete("Geomorelines"+pposition, "gla=?", new String[]{listLa7.get(i)});
                                    }
                                    //删除Geomorelineexcel表
                                    Cursor cursor8=db.query("Geomorelineexcel"+pposition,null,null,null,null,null,null);
                                    if (cursor8.moveToFirst()){
                                        do {
                                            String la=cursor8.getString(cursor8.getColumnIndex("gla"));
                                            listLa8.add(la);
                                        }while(cursor8.moveToNext());
                                    }
                                    cursor8.close();
                                    for (int i=0;i<listLa8.size();i++){
                                        db.delete("Geomorelineexcel"+pposition, "gla=?", new String[]{listLa8.get(i)});
                                    }
                                    //删除Geosurface表
                                    Cursor cursor9=db.query("Geosurface"+pposition,null,null,null,null,null,null);
                                    if (cursor9.moveToFirst()){
                                        do {
                                            String la=cursor9.getString(cursor9.getColumnIndex("gla"));
                                            listLa9.add(la);
                                        }while(cursor9.moveToNext());
                                    }
                                    cursor9.close();
                                    for (int i=0;i<listLa9.size();i++){
                                        db.delete("Geosurface"+pposition, "gla=?", new String[]{listLa9.get(i)});
                                    }
                                    //删除Geosurfaceexcel表
                                    Cursor cursor10=db.query("Geosurfaceexcel"+pposition,null,null,null,null,null,null);
                                    if (cursor10.moveToFirst()){
                                        do {
                                            String la=cursor10.getString(cursor10.getColumnIndex("gla"));
                                            listLa10.add(la);
                                        }while(cursor10.moveToNext());
                                    }
                                    cursor10.close();
                                    for (int i=0;i<listLa10.size();i++){
                                        db.delete("Geosurfaceexcel"+pposition, "gla=?", new String[]{listLa10.get(i)});
                                    }

                                    db.delete("Geonewproject", "gpname=?", new String[]{pname});
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    },400);

                                }
                            }).show();
                }
                return true;
            }
        });
        //gridView的Item单击监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position==dataList.size()-1){
                    ScrollView scrollView= (ScrollView) getLayoutInflater().inflate(R.layout.geoalert_newproject,null);
                    AlertDialog dialog=new AlertDialog.Builder(GeoprojectActivity.this)
                            .setTitle("新建项目：")
                            .setView(scrollView)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String pname=dt.getText().toString();
                                    String personname=name.getText().toString();
                                    String pNum = projectNum.getText().toString();
                                    //String mtv=tv.getText().toString();
                                    Map<String,Object> map=new HashMap<>();
                                    map.put("img",R.mipmap.dakai);
                                    map.put("text",pname);
                                    if (dataList.size()<20){
                                        if (!dataList.contains(map)){
                                            if (!TextUtils.isEmpty(pname)&&!TextUtils.isEmpty(personname)&&!TextUtils.isEmpty(pNum)){
                                                Map<String,Object> map1=new HashMap<>();
                                                map1.put("img",R.mipmap.dakai);
                                                map1.put("text",pname);
                                                dataList.add(dataList.size()-1,map1);
                                                values=new ContentValues();
                                                values.put("gpname",pname);
                                                //values.put("gsname",mtv);
                                                values.put("exportpath", Environment.getExternalStorageDirectory()+"/MyMap");//导出路径
                                                values.put("name",personname);
                                                values.put("pnum",pNum);
                                                values.put("c3xzpy",String_x);
                                                values.put("c3yzpy",String_y);
                                                values.put("c3zzpy",String_z);
                                                values.put("c3xzxz",String_rx);
                                                values.put("c3yzxz",String_ry);
                                                values.put("c3zzxz",String_rz);
                                                values.put("c3bl",String_ppm);
                                                values.put("c2zyzwx",String_zyzw);
                                                values.put("c2djcs","500000.0");
                                                values.put("c1code","unknown");
                                                values.put("c1cbz","6378137.0");
                                                values.put("c1plds","298.257223563");
                                                if (amount==0){
                                                    values.put("gposition",position);
                                                    db.insert("Geonewproject",null,values);
                                                }else {
                                                    //先添加后删除
                                                    List<String> list=new ArrayList<>();
                                                    Cursor cursor = db.query("Geonewposition", null, null, null, null, null, null);
                                                    if (cursor.moveToFirst()){
                                                        do {
                                                            String p=cursor.getString(cursor.getColumnIndex("gposition"));
                                                            list.add(p);
                                                        }while (cursor.moveToNext());
                                                    }
                                                    values.put("gposition",list.get(list.size()-1));
                                                    db.insert("Geonewproject",null,values);
                                                    values.clear();
                                                    db.delete("Geonewposition", "gposition=?", new String[]{list.get(list.size()-1)});
                                                }
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                },300);
                                            }else {
                                                ToastNotRepeat.show(GeoprojectActivity.this,"属性不能为空！！！");
                                            }
                                        }else {
                                            ToastNotRepeat.show(GeoprojectActivity.this,"已存在相同的项目名！");
                                        }
                                    }else {
                                        ToastNotRepeat.show(GeoprojectActivity.this,"项目数量已超出，请删除不重要的项目！");
                                    }
                                }
                            }).show();
                    Button btnpositive=dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative=dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    ImageButton imageButton_down = (ImageButton) dialog.findViewById(R.id.ci_down);
                    ImageButton imageButton_up = (ImageButton) dialog.findViewById(R.id.ci_up);
                    LinearLayout setting = (LinearLayout) dialog.findViewById(R.id.setting);
                    x = (EditText) dialog.findViewById(R.id.x);
                    y = (EditText) dialog.findViewById(R.id.y);
                    z = (EditText) dialog.findViewById(R.id.z);
                    rx = (EditText) dialog.findViewById(R.id.rx);
                    ry = (EditText) dialog.findViewById(R.id.ry);
                    rz = (EditText) dialog.findViewById(R.id.rz);
                    ppm = (EditText) dialog.findViewById(R.id.ppm);
                    zyzw = (EditText) dialog.findViewById(R.id.zyzw);
                    String_x = x.getText().toString();
                    String_y = y.getText().toString();
                    String_z = z.getText().toString();
                    String_rx = rx.getText().toString();
                    String_ry = ry.getText().toString();
                    String_rz = rz.getText().toString();
                    String_ppm = ppm.getText().toString();
                    String_zyzw = zyzw.getText().toString();
                    if (String_x.equals("")){
                        String_x = "0.0";
                    }
                    if (String_y.equals("")){
                        String_y = "0.0";
                    }
                    if (String_z.equals("")){
                        String_z = "0.0";
                    }
                    if (String_rx.equals("")){
                        String_rx = "0.0";
                    }
                    if (String_ry.equals("")){
                        String_ry = "0.0";
                    }
                    if (String_rz.equals("")){
                        String_rz = "0.0";
                    }
                    if (String_ppm.equals("")){
                        String_ppm = "0.0";
                    }
                    if (String_zyzw.equals("")){
                        String_zyzw = "0.0";
                    }
                    imageButton_down.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageButton_down.setVisibility(View.GONE);
                            imageButton_up.setVisibility(View.VISIBLE);
                            setting.setVisibility(View.VISIBLE);
                        }
                    });
                    imageButton_up.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageButton_down.setVisibility(View.VISIBLE);
                            imageButton_up.setVisibility(View.GONE);
                            setting.setVisibility(View.GONE);
                        }
                    });
                    Cursor c=db.rawQuery("select*from Geonewposition",null);
                    amount=c.getCount();//如果amount=0则表Newstr为空
                    dt= (EditText) dialog.findViewById(R.id.na);
                    name= (EditText) dialog.findViewById(R.id.name);
                    projectNum = (EditText) dialog.findViewById(R.id.projectNum);
                }else {
                    int a=0;
                    Cursor c=db.rawQuery("select*from Geonewppposition",null);
                    a=c.getCount();//如果amount=0则表Newstr为空
                    if (a==0){
                        ContentValues values1 = new ContentValues();
                        values1.put("gposition",position);
                        db.insert("Geonewppposition",null,values1);
                        values1.clear();
                    }else {
                        ContentValues values2 = new ContentValues();
                        values2.put("gposition",position);
                        db.update("Geonewppposition", values2, "id=?", new String[]{"1"});
                        values2.clear();
                    }
                    Intent i=new Intent(GeoprojectActivity.this,GeoMenuActivity.class);
                    i.putExtra("pposition",String.valueOf(position));
                    startActivity(i);
                }
            }
        });
    }
    //广告栏实现
    private void setConvenientBanner() {
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new GeoprojectActivity.LocalImageHolderView();
            }
        },imgs).setPointViewVisible(true)//设置指示器是否可见
                .setPageIndicator(new int[]{R.mipmap.yuandianbantou,R.mipmap.yuandian});//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        convenientBanner.setManualPageable(true);//设置手动影响（设置了该项无法手动切换）
        convenientBanner.startTurning(2000);     //设置自动切换（同时设置了切换时间间隔）
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);//设置指示器位置（左、中、右）
    }
    //广告栏设置布局
    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
    // 广告栏添加数据
    private void setViews() {
        convenientBanner= (ConvenientBanner) findViewById(R.id.convenientBanner);
        imgs.add(R.mipmap.city10);
        imgs.add(R.mipmap.city9);
        imgs.add(R.mipmap.city11);
        imgs.add(R.mipmap.city12);
        gridView= (GridView) findViewById(R.id.gridView);
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.example.gengxin2.action.MyReceiver");
        myreceiver= new GeoMyreceiver();
        registerReceiver(myreceiver,filter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myreceiver);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    String path = FileUtils.getPath(this, uri);
                    if (path != null && FileUtils.isLocal(path)) {
                        File file = new File(path);
                        f=file.toString();
                        //tv.setText(f);
                    }
                }
                break;
        }
    }
    //广播接收器接收之后数据处理
    public  class GeoMyreceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String t1=intent.getStringExtra("msg");
            String t2=intent.getStringExtra("msg2");
            Map<String,Object> map=new HashMap<>();
            map.put("img",R.mipmap.dakai);
            map.put("text",t1);
            dataList.set(Integer.parseInt(t2),map);
            adapter.notifyDataSetChanged();
        }
    }
}
