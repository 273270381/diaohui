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
                String path = cursor.getString(cursor.getColumnIndex("exportpath"));
                String time=cursor.getString(cursor.getColumnIndex("timestamp"));
                String position=cursor.getString(cursor.getColumnIndex("position"));
                String color = cursor.getString(cursor.getColumnIndex("layercolor"));
                String width = cursor.getString(cursor.getColumnIndex("width"));
                String c1code = cursor.getString(cursor.getColumnIndex("c1code"));
                Double c3xzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3xzpy")));
                Double c3yzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3yzpy")));
                Double c3zzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3zzpy")));
                Double c3xzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3xzxz")));
                Double c3yzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3yzxz")));
                Double c3zzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3zzxz")));
                Double c3bl = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3bl")));
                Double c2zyzwx = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c2zyzwx")));
                Double c2djcs = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c2djcs")));
                Double c1plds = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c1plds")));
                Double c1cbz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c1cbz")));
                newProject.setProjectname(name);
                newProject.setStrname(url);
                newProject.setDatetime(time);
                newProject.setPosition(position);
                newProject.setPath(path);
                newProject.setColor(color);
                newProject.setWidth(width);
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
                newProject.setC2djcs(c2djcs);
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
