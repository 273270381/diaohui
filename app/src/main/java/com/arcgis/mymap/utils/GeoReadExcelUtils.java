package com.arcgis.mymap.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.arcgis.mymap.contacts.MyDatabaseHelper;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by Administrator on 2018/5/3.
 */

public class GeoReadExcelUtils {
    public void writePointExcel(Context context, String position, String s){
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
                values.put("gclassification",(sheet.getCell(1,i)).getContents());
                values.put("gcode",(sheet.getCell(2,i)).getContents());
                values.put("la",(sheet.getCell(3,i)).getContents());
                values.put("ln",(sheet.getCell(4,i)).getContents());
                values.put("high",(sheet.getCell(5,i)).getContents());
                values.put("time",(sheet.getCell(6,i)).getContents());
                values.put("gdescription",(sheet.getCell(7,i)).getContents());
                db.insert("Geopointexcel"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void writeDxdmPointExcel(Context context, String position, String s){
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
                values.put("gcode",(sheet.getCell(1,i)).getContents());
                values.put("la",(sheet.getCell(2,i)).getContents());
                values.put("ln",(sheet.getCell(3,i)).getContents());
                values.put("gclassification",(sheet.getCell(4,i)).getContents());
                values.put("zhibeifayu",(sheet.getCell(5,i)).getContents());
                values.put("gdescription",(sheet.getCell(6,i)).getContents());
                db.insert("Geodxdmpoints"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void writeDcyxPointExcel(Context context, String position, String s){
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
                values.put("gcode",(sheet.getCell(1,i)).getContents());
                values.put("la",(sheet.getCell(2,i)).getContents());
                values.put("ln",(sheet.getCell(3,i)).getContents());
                values.put("dznd",(sheet.getCell(4,i)).getContents());
                values.put("ytmc",(sheet.getCell(5,i)).getContents());
                values.put("gclassification",(sheet.getCell(6,i)).getContents());
                values.put("fhcd",(sheet.getCell(7,i)).getContents());
                values.put("cz",(sheet.getCell(8,i)).getContents());
                values.put("jl",(sheet.getCell(9,i)).getContents());
                values.put("gdescription",(sheet.getCell(10,i)).getContents());
                db.insert("Geodcyxpoints"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void writeSwdzPointExcel(Context context, String position, String s){
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
                values.put("code",(sheet.getCell(1,i)).getContents());
                values.put("la",(sheet.getCell(2,i)).getContents());
                values.put("ln",(sheet.getCell(3,i)).getContents());
                values.put("sllx",(sheet.getCell(4,i)).getContents());
                values.put("smkd",(sheet.getCell(5,i)).getContents());
                values.put("ss",(sheet.getCell(6,i)).getContents());
                values.put("ls",(sheet.getCell(7,i)).getContents());
                values.put("ll",(sheet.getCell(8,i)).getContents());
                values.put("sz",(sheet.getCell(9,i)).getContents());
                values.put("gdescription",(sheet.getCell(10,i)).getContents());
                db.insert("Geoswdzpoints"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void writeGzwdPointExcel(Context context, String position, String s){
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
                values.put("code",(sheet.getCell(1,i)).getContents());
                values.put("la",(sheet.getCell(2,i)).getContents());
                values.put("ln",(sheet.getCell(3,i)).getContents());
                values.put("lx",(sheet.getCell(9,i)).getContents());
                values.put("gdescription",(sheet.getCell(10,i)).getContents());
                db.insert("Geogzwdpoints"+position,null,values);
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
                values.put("name",(sheet.getCell(0,i)).getContents());
                values.put("gcode",(sheet.getCell(1,i)).getContents());
                values.put("gla",(sheet.getCell(2,i)).getContents());
                values.put("gln",(sheet.getCell(3,i)).getContents());
                values.put("gclassification",(sheet.getCell(4,i)).getContents());
                values.put("gdescription",(sheet.getCell(5,i)).getContents());
                db.insert("Geomorelines"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void writeSurfaceExcel(Context context,String position,String s){
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
                values.put("gcode",(sheet.getCell(1,i)).getContents());
                values.put("gla",(sheet.getCell(2,i)).getContents());
                values.put("gln",(sheet.getCell(3,i)).getContents());
                values.put("gclassification",(sheet.getCell(4,i)).getContents());
                values.put("gdescription",(sheet.getCell(5,i)).getContents());
                db.insert("Geosurface"+position,null,values);
                values.clear();
            }
            book.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
