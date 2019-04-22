package com.arcgis.mymap.Geological;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arcgis.mymap.ProjectAttributes;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.GetTable;
import com.arcgis.mymap.utils.ToastNotRepeat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/4/18.
 */

public class GeoProjectAttributes extends AppCompatActivity {
    EditText tv1;
    TextView tv2;
    TextView tv3;
    TextView tv5;
    TextView proNum;
    EditText et1;
    ImageButton back;
    Button bt;
    public int sposition;
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    private List<NewProject> projects=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geoactivity_projectattributes);
        intView();
        try {
            getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setData();
        addlistener();
    }
    private void addlistener() {
        ClickListener listener=new ClickListener();
        bt.setOnClickListener(listener);
        back.setOnClickListener(listener);
    }
    private void intView() {
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        tv1= (EditText) findViewById(R.id.etext1);
        tv2= (TextView) findViewById(R.id.etext2);
        tv3= (TextView) findViewById(R.id.etext3);
        tv5= (TextView) findViewById(R.id.etext5);
        proNum = (TextView)findViewById(R.id.pronum);
        et1= (EditText) findViewById(R.id.etext4);
        bt= (Button) findViewById(R.id.bt);
        back= (ImageButton) findViewById(R.id.back);
    }
    // 查询数据
    public List<NewProject> getData() throws ParseException {
        Cursor cursor=db.query("Geonewproject",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历cursor对象，取出数据
                NewProject newProject=new NewProject();
                //String id=cursor.getString(cursor.getColumnIndex("id"));
                String name=cursor.getString(cursor.getColumnIndex("gpname"));
                String url=cursor.getString(cursor.getColumnIndex("gsname"));
                String des=cursor.getString(cursor.getColumnIndex("gdescription"));
                String date=cursor.getString(cursor.getColumnIndex("timestamp"));
                String position=cursor.getString(cursor.getColumnIndex("gposition"));
                String pname=cursor.getString(cursor.getColumnIndex("name"));
                String pronum = cursor.getString(cursor.getColumnIndex("pnum"));
                String formatType="yyyy-MM-dd HH:mm:ss";
                long time=Stringtolong(date,formatType);
                String datetime=getTime(time);
                newProject.setProjectname(name);
                newProject.setStrname(url);
                newProject.setDatetime(datetime);
                newProject.setDes(des);
                newProject.setPersonname(pname);
                newProject.setPosition(position);
                newProject.setProNum(pronum);
                projects.add(newProject);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return projects;
    }
    private void setData() {
        GeoGetTable getTable=new GeoGetTable();
        sposition=Integer.parseInt(getTable.getPpposition(GeoProjectAttributes.this,db,dbHelper));
        tv1.setText(projects.get(sposition).getProjectname());
        tv2.setText(projects.get(sposition).getStrname());
        tv3.setText(projects.get(sposition).getDatetime());
        tv5.setText(projects.get(sposition).getPersonname());
        et1.setText(projects.get(sposition).getDes());
        proNum.setText(projects.get(sposition).getProNum());
    }
    public class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.bt:
                    if (!TextUtils.isEmpty(tv1.getText().toString())){
                        String t1=tv1.getText().toString();
                        String t4=et1.getText().toString();
                        String pnum = proNum.getText().toString();
                        ContentValues values = new ContentValues();
                        values.put("gpname", t1);
                        values.put("gdescription",t4);
                        values.put("pnum",pnum);
                        db.update("Geonewproject", values, "gposition=?", new String[]{projects.get(sposition).getPosition()});
                        ToastNotRepeat.show(GeoProjectAttributes.this,"保存成功");
                        Intent intent=new Intent();
                        intent.setAction("com.example.gengxin2.action.MyReceiver");
                        intent.putExtra("msg",t1);
                        intent.putExtra("msg2",String.valueOf(sposition));
                        sendBroadcast(intent);
                    }else {
                        ToastNotRepeat.show(GeoProjectAttributes.this,"项目名不能为空");
                    }
                    break;
                case R.id.back:
                    finish();
                    break;
            }
        }
    }
    /** 由时间戳转化为文本 */
    private String getTime(long time) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = sdf.format(time+28800000);
        return date;
    }
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long Stringtolong(String strTime,String formatType)throws ParseException {
        Date date =stringToDate(strTime, formatType);// String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }
}
