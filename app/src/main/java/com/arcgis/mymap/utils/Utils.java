package com.arcgis.mymap.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/5/3.
 */
public class Utils {
    private static long lastClickTime;
    private static SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private boolean flag = false;
    private static double correction = 0.0 ;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 500) {
            return true;
        }

        lastClickTime = time;
        return false;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**获取屏幕分辨率宽*/
    public static int getScreenWidth(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        //((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        return dm.widthPixels;
    }

    /**获取屏幕分辨率高*/
    public static int getScreenHeight(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        //((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);

        return dm.heightPixels;
    }

    public static String bytesToHexString(byte[] src, int length){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0 || length<=0) {
            return null;
        }

        for (int i = 0; i < length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            stringBuilder.append(" ");  //间隔一个空格.//<-----间隔了一个空格.
        }
        return stringBuilder.toString();
    }

    public static byte StringToByte(String str) throws IllegalArgumentException {
        str = str.toUpperCase();
        char[] hexChars = str.toCharArray();
        char char1 = hexChars[0];
        char char2 = hexChars[1];
        checkIsHexChar(char1);
        checkIsHexChar(char2);
        return (byte) (charToByte(char1) << 4 | charToByte(char2));
    }

    public static String checkIsHexChar(char ch) {
        if ('a' <= ch && ch <= 'f') {
            return null;
        }
        if ('A' <= ch && ch <= 'F') {
            return null;
        }
        if ('0' <= ch && ch <= '9') {
            return null;
        }
        throw new IllegalArgumentException(String.valueOf(ch));
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] HextoByte(String str)
    {
        String[]  strArray=str.split(" ");
        byte[] d = new byte[strArray.length];
        for(int i=0;i<strArray.length;i++)
        {
            byte  bAray=StringToByte(strArray[i]);
            d[i]=bAray;
        }
        return d;
    }

    //激光测距数据解析
    public static double DistAnalysisNew(byte[] buffer, int num,Context context) {
        //判断长度是否为3，不为3则数据有误，将dist赋值-1，数据错误，重新读
        if (sp==null) {
            sp = context.getSharedPreferences("config_xml", context.MODE_PRIVATE);
        }
        correction = Double.parseDouble(sp.getFloat("mechine_edit", 0)+"");
        double dist = -1.0;
        if (num == 6) {
            int a1, b1, c1 ,d1,e1;
            a1 = (int) buffer[1]&0xff;
            b1 = (int) buffer[2]&0xff;
            c1 = (int) buffer[3]&0xff;
            d1 = (int) buffer[4]&0xff;
            e1 = (int) buffer[5]&0xff;
            if(buffer[2] != 0x0A && buffer[3] !=0x0A&& buffer[4] !=0x0A) {
                dist = a1*10 + b1+ c1*0.1+d1*0.01+e1*0.001;
            }
            //激光测距修正
            dist = dist - 0.0191+correction;
        }
        if((dist<0)||(dist==0))
        {
            dist = -1.0;
        }
        dist  =  new BigDecimal(dist).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();//四舍五入保留两位
        return dist;
    }

    //激光测距数据解析
    public static double DistAnalysis(byte[] buffer, int num) {
        //判断长度是否为3，不为3则数据有误，将dist赋值-1，数据错误，重新读
        double dist = -1.0;
        if (num == 3) {
            int a1, b1;
            a1 = (int) buffer[0]&0xff;
            b1 = (int) buffer[1]&0xff;
            dist = a1 + b1 * 0.01;
            //激光测距修正
            dist = dist - 0.0212;
        }
        if((dist<0)||(dist==0))
        {
            dist = -1.0;
        }
        dist  =  new BigDecimal(dist).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();//四舍五入保留两位
        return dist;
    }

    /** * 判断字符串是否是整数 */
    public static boolean isInteger(String value)
    {
        try {
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e)
        { return false;
        }
    }


    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /** * 判断字符串是否是数字 */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }


    //是否是日期
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }
    public static String SetDeclinationData(double str2, Context context) {
        int decint = 0;
        // 68 06 00 06 02 08 16  20.8
        String fistorder = "68 06 00 06 ";
        double ff = str2;
        if (ff>15||ff<-10){
            ToastNotRepeat.show(context,"输入磁偏角有误");
            return null;
        }
        if (ff>=0){
            fistorder = fistorder+"0";
        }else {
            fistorder = fistorder+"1";
        }
        if (Math.abs(ff)>=10){
            fistorder = fistorder+"1 ";
        }else {
            fistorder = fistorder+"0 ";
        }

        if (Math.abs(ff)<1){
            fistorder = fistorder+"0";
        }else {
            ff = Math.abs(ff);
            ff = ff%10;
            fistorder = fistorder+((int)ff+"");
        }
        ff = Math.abs(ff);
        fistorder = fistorder + ((int)((ff*10)%10)+"");
        //68 06 00 06 02 08 16
        String[] strs = fistorder.split(" ");
        for(int i=1;i<strs.length;i++){
            decint = decint+toInt(strs[i]);
        }
        if (decint<16){
            fistorder = fistorder+" 0"+ Integer.toHexString(decint);
        }else {
            fistorder = fistorder+" "+ Integer.toHexString(decint);
        }
        return fistorder;
    }
    public static int toInt(String ss){
        int result = 0;
        int s = Integer.parseInt(ss.charAt(0)+"");
        if (s>0){
            result = s*16;
        }
        int q = Integer.parseInt(ss.charAt(1)+"");
        result = result+q;
        return result;
    }
}
