package com.arcgis.mymap.Geological;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class GeoGetTable {
    public SQLiteDatabase db;
    public MyDatabaseHelper dbHelper;
    public String getTableposition(Context context, SQLiteDatabase db, MyDatabaseHelper dbHelper, List<NewProject> list){
        //获取表位置
        dbHelper=new MyDatabaseHelper(context, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Geonewproject",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历cursor对象，取出数据
                NewProject newProject=new NewProject();
                String name=cursor.getString(cursor.getColumnIndex("gpname"));
                String url=cursor.getString(cursor.getColumnIndex("gsname"));
                String time=cursor.getString(cursor.getColumnIndex("timestamp"));
                String position=cursor.getString(cursor.getColumnIndex("gposition"));
                newProject.setProjectname(name);
                newProject.setStrname(url);
                newProject.setDatetime(time);
                newProject.setPosition(position);
                list.add(newProject);
            }while (cursor.moveToNext());
        }
        cursor.close();
        int sposition=Integer.parseInt(getPpposition(context,db,dbHelper));
        String pposition=list.get(sposition).getPosition();
        return pposition;
    }
    public String getPpposition(Context context,SQLiteDatabase db,MyDatabaseHelper dbHelper){
        dbHelper=new MyDatabaseHelper(context, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        String position = null;
        Cursor cursor=db.query("Geonewppposition",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            position=cursor.getString(cursor.getColumnIndex("gposition"));
        }
        cursor.close();
        return position;
    }
}
