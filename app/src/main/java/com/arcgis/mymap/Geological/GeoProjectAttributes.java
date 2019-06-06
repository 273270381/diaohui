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
import android.widget.LinearLayout;
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
    LinearLayout linearLayout;
    ImageButton imageButton_down;
    ImageButton imageButton_up;
    private EditText x,y,z,rx,ry,rz,ppm,zyzw,cbz,ds;
    private LinearLayout l1,l2;
    public int sposition;
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    private List<NewProject> projects=new ArrayList<>();
    private String str;
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
        imageButton_up.setOnClickListener(listener);
        imageButton_down.setOnClickListener(listener);
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
        imageButton_down = (ImageButton) findViewById(R.id.ci_down);
        imageButton_up = (ImageButton) findViewById(R.id.ci_up);
        linearLayout = (LinearLayout) findViewById(R.id.setting);
        x = (EditText) findViewById(R.id.x);
        y = (EditText) findViewById(R.id.y);
        z = (EditText) findViewById(R.id.z);
        rx = (EditText) findViewById(R.id.rx);
        ry = (EditText) findViewById(R.id.ry);
        rz = (EditText) findViewById(R.id.rz);
        ppm = (EditText) findViewById(R.id.ppm);
        zyzw = (EditText) findViewById(R.id.zyzw);
        cbz = (EditText) findViewById(R.id.cbz);
        ds = (EditText) findViewById(R.id.ds);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
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
                String c1code = cursor.getString(cursor.getColumnIndex("c1code"));
                Double c3xzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3xzpy")));
                Double c3yzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3yzpy")));
                Double c3zzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3zzpy")));
                Double c3xzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3xzxz")));
                Double c3yzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3yzxz")));
                Double c3zzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3zzxz")));
                Double c3bl = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3bl")));
                Double c2zyzwx = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c2zyzwx")));
                Double c1plds = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c1plds")));
                Double c1cbz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c1cbz")));
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
                newProject.setC1code(c1code);
                newProject.setC3xzpy(c3xzpy);
                newProject.setC3yzpy(c3yzpy);
                newProject.setC3zzpy(c3zzpy);
                newProject.setC3xzxz(c3xzxz);
                newProject.setC3yzxz(c3yzxz);
                newProject.setC3zzxz(c3zzxz);
                newProject.setC3bl(c3bl);
                newProject.setC2zyzwx(c2zyzwx);
                newProject.setC1cbz(c1cbz);
                newProject.setC1plds(c1plds);
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
        x.setText(String.valueOf(projects.get(sposition).getC3xzpy()));
        y.setText(String.valueOf(projects.get(sposition).getC3yzpy()));
        z.setText(String.valueOf(projects.get(sposition).getC3zzpy()));
        rx.setText(String.valueOf(projects.get(sposition).getC3xzxz()));
        ry.setText(String.valueOf(projects.get(sposition).getC3yzxz()));
        rz.setText(String.valueOf(projects.get(sposition).getC3zzxz()));
        ppm.setText(String.valueOf(projects.get(sposition).getC3bl()));
        zyzw.setText(String.valueOf(projects.get(sposition).getC2zyzwx()));
        cbz.setText(String.valueOf(projects.get(sposition).getC1cbz()));
        ds.setText(String.valueOf(projects.get(sposition).getC1plds()));
        str = projects.get(sposition).getC1code();
        if (str.equals("unknown")){
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.VISIBLE);
        }else{
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
        }
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
                        if (str.equals("unknown")){
                            values.put("c1cbz",cbz.getText().toString());
                            values.put("c1plds",ds.getText().toString());
                        }
                        values.put("gpname", t1);
                        values.put("gdescription",t4);
                        values.put("pnum",pnum);
                        values.put("c3xzpy",x.getText().toString());
                        values.put("c3yzpy",y.getText().toString());
                        values.put("c3zzpy",z.getText().toString());
                        values.put("c3xzxz",rx.getText().toString());
                        values.put("c3yzxz",ry.getText().toString());
                        values.put("c3zzxz",rz.getText().toString());
                        values.put("c3bl",ppm.getText().toString());
                        values.put("c2zyzwx",zyzw.getText().toString());
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
                case R.id.ci_down:
                    imageButton_down.setVisibility(View.GONE);
                    imageButton_up.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    break;
                case R.id.ci_up:
                    imageButton_down.setVisibility(View.VISIBLE);
                    imageButton_up.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
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
