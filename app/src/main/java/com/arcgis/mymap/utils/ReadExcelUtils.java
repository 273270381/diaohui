package com.arcgis.mymap.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.arcgis.mymap.contacts.MyDatabaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ReadExcelUtils {
    public void writePointExcel(Context context,String position,String s){
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(context,"pointsStore.db",null,5);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try{
            Workbook book=Workbook.getWorkbook(new File(s));
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();//总行数
            int Cols = sheet.getColumns();//总列数
            ContentValues values=new ContentValues();
            for (int i=1;i<Rows;i++){
                values.put("name",(sheet.getCell(0,i)).getContents());
                values.put("classification",(sheet.getCell(1,i)).getContents());
                values.put("code",(sheet.getCell(2,i)).getContents());
                values.put("la",(sheet.getCell(3,i)).getContents());
                values.put("ln",(sheet.getCell(4,i)).getContents());
                values.put("high",(sheet.getCell(5,i)).getContents());
                values.put("time",(sheet.getCell(6,i)).getContents());
                values.put("description",(sheet.getCell(7,i)).getContents());
                db.insert("Pointexcel"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void writeLineExcel(Context context,String position,String s){
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(context,"pointsStore.db",null,5);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try{
            Workbook book=Workbook.getWorkbook(new File(s));
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();//总行数
            int Cols = sheet.getColumns();//总列数
            ContentValues values=new ContentValues();
            for (int i=1;i<Rows;i++){
                values.put("classification",(sheet.getCell(0,i)).getContents());
                values.put("code",(sheet.getCell(1,i)).getContents());
                values.put("xla",(sheet.getCell(2,i)).getContents());
                values.put("xln",(sheet.getCell(3,i)).getContents());
                values.put("time",(sheet.getCell(4,i)).getContents());
                values.put("description",(sheet.getCell(5,i)).getContents());
                db.insert("Lineexcel"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
