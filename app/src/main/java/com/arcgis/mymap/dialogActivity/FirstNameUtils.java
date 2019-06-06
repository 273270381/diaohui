package com.arcgis.mymap.dialogActivity;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstNameUtils {
    public static String getClassification(Cursor cursor,String str){
        String string = "";
        if (cursor.moveToLast()){
            string = cursor.getString(cursor.getColumnIndex("lx"));
        }
        cursor.close();
        if (string.equals("")){
            string = str;
        }
        return string;
    }
    public static String getClassification2(Cursor cursor,String str){
        String string = "";
        if (cursor.moveToLast()){
            string = cursor.getString(cursor.getColumnIndex("gclassification"));
        }
        cursor.close();
        if (string.equals("")){
            string = str;
        }
        return string;
    }
    public static String getClassification3(Cursor cursor,String str){
        String string = "";
        if (cursor.moveToLast()){
            string = cursor.getString(cursor.getColumnIndex("sllx"));
        }
        cursor.close();
        if (string.equals("")){
            string = str;
        }
        return string;
    }
    public static String getClassification4(Cursor cursor,String str,List<String> list){
        String string = "";
        if (cursor.moveToLast()){
            do {
                string = cursor.getString(cursor.getColumnIndex("gclassification"));
                if (list.contains(string)){
                    break;
                }else{
                    string = "";
                }
            }while (cursor.moveToPrevious());
        }
        if (string.equals("")){
            string = list.get(0);
        }
        return string;
    }
    public static List<String> getList(Cursor cursor,List<String> stringList){
        List<String> list = new ArrayList<>();
        if (cursor.moveToLast()){
            String str1 = cursor.getString(cursor.getColumnIndex("dznd"));
            String str2 = cursor.getString(cursor.getColumnIndex("ytmc"));
            list.add(str1);
            list.add(str2);
        }
        cursor.close();
        if (list.size() == 0){
            list.addAll(stringList);
        }
        return list;
    }
    public static String getName(Cursor utc,String t){
        String ut ="";
        if (utc.moveToLast()){
            do {
                ut=utc.getString(utc.getColumnIndex("name"));
                if (ut.contains(t)){
                    break;
                }else{
                    ut = "";
                }
            }while (utc.moveToPrevious());
        }
        utc.close();
        if (ut.equals("")){
            ut=t+"0";
        }
        if (isContainNumber(ut)){
            int x=getInt(ut);
            ut=ut.replace(String.valueOf(x),String.valueOf(x+1));
            //et.setText(ut);
        }else {
            ut=ut+"1";
            //et.setText(ut);
        }
        return ut;
    }
    /**
     * 判断字符串中是否包含数字
     */
    public static boolean isContainNumber(String company) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(company);
        if (m.find()) {
            return true;
        }
        return false;
    }
    /**
     * 截取字符串
     * @param str
     * @return
     */
    public static int getInt(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        int num=Integer.parseInt(m.replaceAll("").trim());
        return num;
    }
}
