package com.arcgis.mymap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/2.
 */

public class UpdateActivity extends AppCompatActivity {
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public String time;
    public TextView textView;
    public TextView copyright;
    public ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        textView= (TextView) findViewById(R.id.time);
        copyright= (TextView) findViewById(R.id.copyright);
        back= (ImageButton) findViewById(R.id.back);
        Cursor cursor=db.query("Newtimes",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            time=cursor.getString(cursor.getColumnIndex("time"));
        }
        cursor.close();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        try {
            Date dt1=simpleDateFormat.parse(time);
            long t1=dt1.getTime()+1728000000;
            Date date1=new Date(t1);
            String time1=simpleDateFormat.format(date1);
            String time2=time1.substring(0,time1.indexOf("日"));
            textView.setText("使用期限\t"+time2+"日");
            String time3=time1.substring(0,4);
            copyright.setText("Copyright\t©\t2017-"+time3+"\tTianjiang.All\tRights\tReserved");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
