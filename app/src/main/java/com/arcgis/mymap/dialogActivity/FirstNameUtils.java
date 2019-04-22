package com.arcgis.mymap.dialogActivity;

import android.database.Cursor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstNameUtils {
    public static String getName(Cursor utc,String t){
        String ut ="";
        if (utc.moveToLast()){
            ut=utc.getString(utc.getColumnIndex("name"));
        }
        utc.close();
        if (ut==null){
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
