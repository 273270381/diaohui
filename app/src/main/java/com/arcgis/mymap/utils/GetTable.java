package com.arcgis.mymap.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */

public class GetTable {
    public SQLiteDatabase db;
    public MyDatabaseHelper dbHelper;
    public String getTableposition(Context context,SQLiteDatabase db,MyDatabaseHelper dbHelper,List<NewProject> list){
        //获取表位置
        dbHelper=new MyDatabaseHelper(context, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Newproject",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历cursor对象，取出数据
                NewProject newProject=new NewProject();
                String name=cursor.getString(cursor.getColumnIndex("pname"));
                String url=cursor.getString(cursor.getColumnIndex("sname"));
                String time=cursor.getString(cursor.getColumnIndex("timestamp"));
                String position=cursor.getString(cursor.getColumnIndex("position"));
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
        Cursor cursor=db.query("Newppposition",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            position=cursor.getString(cursor.getColumnIndex("position"));
        }
        cursor.close();
        return position;
    }
}
