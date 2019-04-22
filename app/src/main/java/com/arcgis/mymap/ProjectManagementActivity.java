package com.arcgis.mymap;

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
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.arcgis.mymap.contacts.LitepalPoints;
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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/3/4.
 */

public class ProjectManagementActivity extends AppCompatActivity {
    private ConvenientBanner convenientBanner;
    private List<Integer> imgs=new ArrayList<>();
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private List<String> listLa3=new ArrayList<>();
    private List<String> listLa=new ArrayList<>();
    private List<String> listLa2=new ArrayList<>();
    private List<String> listXLa=new ArrayList<>();
    private List<String> listXLa2=new ArrayList<>();
    private SimpleAdapter adapter;
    private EditText dt;
    private EditText name;
    private TextView tv;
    public String f;
    public String  pposition;
    public int amount=0;
    private static final int REQUEST_CHOOSER = 1234;
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public ContentValues values;
    public Myreceiver myreceiver;
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
        Cursor cursor=db.query("Newproject",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历cursor对象，取出数据
                String name=cursor.getString(cursor.getColumnIndex("pname"));
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
                    new AlertDialog.Builder(ProjectManagementActivity.this)
                            .setTitle("删除项目")
                            .setMessage("是否删除项目：")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Map<String,Object> map=dataList.get(position);
                                    dataList.remove(dataList.get(position));
                                    String pname= (String) map.get("text");
                                    LogUtils.i("TAG","name="+pname);
                                   //先添加后删除
                                    Cursor cursor = db.query("Newproject", null, "pname = ?", new String[]{pname}, null, null, null);
                                    if (cursor.moveToFirst()){
                                        pposition=cursor.getString(cursor.getColumnIndex("position"));
                                    }
                                    cursor.close();
                                    ContentValues values=new ContentValues();
                                    values.put("position",pposition);
                                    db.insert("Newposition",null,values);
                                    values.clear();
                                    //删除point表
                                    Cursor cursor1=db.query("Newpoints"+pposition,null,null,null,null,null,null);
                                    if (cursor1.moveToFirst()){
                                        do {
                                            String la=cursor1.getString(cursor1.getColumnIndex("la"));
                                            listLa.add(la);
                                        }while(cursor1.moveToNext());
                                    }
                                    cursor1.close();
                                    for (int i=0;i<listLa.size();i++){
                                        db.delete("Newpoints"+pposition, "la=?", new String[]{listLa.get(i)});
                                    }
                                    //删除photo表
                                    Cursor cursor5=db.query("Photopoints"+pposition,null,null,null,null,null,null);
                                    if (cursor5.moveToFirst()){
                                        do {
                                            String la=cursor5.getString(cursor5.getColumnIndex("la"));
                                            listLa3.add(la);
                                        }while(cursor5.moveToNext());
                                    }
                                    cursor5.close();
                                    for (int i=0;i<listLa3.size();i++){
                                        db.delete("Photopoints"+pposition, "la=?", new String[]{listLa3.get(i)});
                                    }
                                    //删除lines表
                                    Cursor cursor2=db.query("Newlines"+pposition,null,null,null,null,null,null);
                                    if (cursor2.moveToFirst()){
                                        do {
                                            String la=cursor2.getString(cursor2.getColumnIndex("xla"));
                                            listXLa.add(la);
                                        }while(cursor2.moveToNext());
                                    }
                                    cursor2.close();
                                    for (int i=0;i<listXLa.size();i++){
                                        db.delete("Newlines"+pposition, "xla=?", new String[]{listXLa.get(i)});
                                    }
                                    //删除PointExcel表
                                    Cursor cursor3=db.query("Pointexcel"+pposition,null,null,null,null,null,null);
                                    if (cursor3.moveToFirst()){
                                        do {
                                            String la=cursor3.getString(cursor3.getColumnIndex("la"));
                                            listLa2.add(la);
                                        }while(cursor3.moveToNext());
                                    }
                                    cursor3.close();
                                    for (int i=0;i<listLa2.size();i++){
                                        db.delete("Pointexcel"+pposition, "la=?", new String[]{listLa2.get(i)});
                                    }
                                    //删除LineExcel表
                                    Cursor cursor4=db.query("Lineexcel"+pposition,null,null,null,null,null,null);
                                    if (cursor4.moveToFirst()){
                                        do {
                                            String la=cursor4.getString(cursor4.getColumnIndex("xla"));
                                            listXLa2.add(la);
                                        }while(cursor4.moveToNext());
                                    }
                                    cursor4.close();
                                    for (int i=0;i<listXLa2.size();i++){
                                        db.delete("Lineexcel"+pposition, "xla=?", new String[]{listXLa2.get(i)});
                                    }
                                    db.delete("Newproject", "pname=?", new String[]{pname});
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
                    LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.alert_newproject,null);
                    AlertDialog dialog=new AlertDialog.Builder(ProjectManagementActivity.this)
                            .setTitle("新建项目：")
                            .setView(linearLayout)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String pname=dt.getText().toString();
                                    String personname=name.getText().toString();
                                    String mtv=tv.getText().toString();
                                    Map<String,Object> map=new HashMap<>();
                                    map.put("img",R.mipmap.dakai);
                                    map.put("text",pname);
                                    if (dataList.size()<20){
                                        if (!dataList.contains(map)){
                                            if (!TextUtils.isEmpty(pname)&&!TextUtils.isEmpty(mtv)&&!TextUtils.isEmpty(personname)){
                                                Map<String,Object> map1=new HashMap<>();
                                                map1.put("img",R.mipmap.dakai);
                                                map1.put("text",pname);
                                                dataList.add(dataList.size()-1,map1);
                                                values=new ContentValues();
                                                values.put("pname",pname);
                                                values.put("sname",mtv);
                                                values.put("name",personname);
                                                if (amount==0){
                                                    values.put("position",position);
                                                    db.insert("Newproject",null,values);
                                                }else {
                                                    //先添加后删除
                                                    List<String> list=new ArrayList<>();
                                                    Cursor cursor = db.query("Newposition", null, null, null, null, null, null);
                                                    if (cursor.moveToFirst()){
                                                        do {
                                                            String p=cursor.getString(cursor.getColumnIndex("position"));
                                                            list.add(p);
                                                        }while (cursor.moveToNext());
                                                    }
                                                    values.put("position",list.get(list.size()-1));
                                                    db.insert("Newproject",null,values);
                                                    values.clear();
                                                    db.delete("Newposition", "position=?", new String[]{list.get(list.size()-1)});
                                                }
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                },300);
                                            }else {
                                                ToastNotRepeat.show(ProjectManagementActivity.this,"请添加文件或项目名和测量人员！");
                                            }
                                        }else {
                                            ToastNotRepeat.show(ProjectManagementActivity.this,"已存在相同的项目名！");
                                        }
                                    }else {
                                        ToastNotRepeat.show(ProjectManagementActivity.this,"项目数量已超出，请删除不重要的项目！");
                                    }
                                }
                            }).show();
                    Button btnpositive=dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative=dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    Cursor c=db.rawQuery("select*from Newposition",null);
                    amount=c.getCount();//如果amount=0则表Newstr为空
                    dt= (EditText) dialog.findViewById(R.id.na);
                    name= (EditText) dialog.findViewById(R.id.name);
                    tv= (TextView) dialog.findViewById(R.id.str);
                    ImageButton iv= (ImageButton) dialog.findViewById(R.id.folder);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FileUtils.mFileFileterBySuffixs.acceptSuffixs("tpk");
                            Intent intent=new Intent(ProjectManagementActivity.this,FileChooserActivity.class);
                            startActivityForResult(intent, REQUEST_CHOOSER);
                        }
                    });
                }else {
                    int a=0;
                    Cursor c=db.rawQuery("select*from Newppposition",null);
                    a=c.getCount();//如果amount=0则表Newstr为空
                    if (a==0){
                        ContentValues values1 = new ContentValues();
                        values1.put("position",position);
                        db.insert("Newppposition",null,values1);
                        values1.clear();
                    }else {
                        ContentValues values2 = new ContentValues();
                        values2.put("position",position);
                        db.update("Newppposition", values2, "id=?", new String[]{"1"});
                        values2.clear();
                    }
                    Intent i=new Intent(ProjectManagementActivity.this,MainMenuActivity.class);
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
                return new LocalImageHolderView();
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
        imgs.add(R.mipmap.city5);
        imgs.add(R.mipmap.city6);
        imgs.add(R.mipmap.city7);
        imgs.add(R.mipmap.city8);
        gridView= (GridView) findViewById(R.id.gridView);
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.example.gengxin.action.MyReceiver");
        myreceiver=new Myreceiver();
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
                        tv.setText(f);
                    }
                }
            break;
        }
    }
    //广播接收器接收之后数据处理
    public  class Myreceiver extends BroadcastReceiver{
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
