package com.arcgis.mymap.utils;

import android.view.PixelCopy;

import java.security.PublicKey;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/3/18.
 */

public class ConverUtils {
    /**
     * 面积换算
     * @param x
     * @return
     */
    public static String PfmToPfgl(double x){
        double y;
        y=x*0.000001;
        return dataformatX(y);
    }
    public static String PfmToGq(double x){
        double y;
        y=x*0.0001;
        return dataformatX(y);
    }
    public static String PfmToM(double x){
        double y;
        y=x*0.0015;
        return dataformatX(y);
    }
    public static String PfglToPfm(double x){
        double y;
        y=x*1000000;
        return dataformatX(y);
    }
    public static String PfglToGq(double x){
        double y;
        y=x*100;
        return dataformatX(y);
    }
    public static String PfglToM(double x){
        double y;
        y=x*1500;
        return dataformatX(y);
    }
    public static String GqToPfm(double x){
        double y;
        y=x*10000;
        return dataformatX(y);
    }
    public static String GqToPfgl(double x){
        double y;
        y=x*0.01;
        return dataformatX(y);
    }
    public static String GqToM(double x){
        double y;
        y=x*15;
        return dataformatX(y);
    }
    public static String MToPfm(double x){
        double y;
        y=x*666.66666667;
        return dataformatX(y);
    }
    public static String MToPfgl(double x){
        double y;
        y=x*0.0006667;
        return dataformatX(y);
    }
    public static String MToGq(double x){
        double y;
        y=x*0.06666667;
        return dataformatX(y);
    }

    /**
     * c长度换算
     * @param x
     * @return
     */
    public static String MtoC(double x){
        double y;
        y=x*3;
        return dataformatX(y);
    }
    public static String MtoYc(double x){
        double y;
        y=x*3.2808399;
        return dataformatX(y);
    }
    public static String MtoL(double x){
        double y;
        y=x*0.002;
        return dataformatX(y);
    }
    public static String MtoYl(double x){
        double y;
        y=x*0.0006214;
        return dataformatX(y);
    }
    public static String MtoGl(double x){
        double y;
        y=x*0.001;
        return dataformatX(y);
    }
    public static String CtoM(double x){
        double y;
        y=x*0.3333333;
        return dataformatX(y);
    }
    public static String CtoYc(double x){
        double y;
        y=x*1.0936133;
        return dataformatX(y);
    }
    public static String CtoL(double x){
        double y;
        y=x*0.0006667;
        return dataformatX(y);
    }
    public static String CtoYl(double x){
        double y;
        y=x*0.0002071;
        return dataformatX(y);
    }
    public static String CtoGl(double x){
        double y;
        y=x*0.0003333;
        return dataformatX(y);
    }
    public static String YctoM(double x){
        double y;
        y=x*0.3048;
        return dataformatX(y);
    }
    public static String YctoC(double x){
        double y;
        y=x*0.9144;
        return dataformatX(y);
    }
    public static String YctoL(double x){
        double y;
        y=x*0.0006096;
        return dataformatX(y);
    }
    public static String YctoYl(double x){
        double y;
        y=x*0.0001894;
        return dataformatX(y);
    }
    public static String YctoGl(double x){
        double y;
        y=x*0.0003048;
        return dataformatX(y);
    }
    public static String LtoM(double x){
        double y;
        y=x*500;
        return dataformatX(y);
    }
    public static String LtoC(double x){
        double y;
        y=x*1500;
        return dataformatX(y);
    }
    public static String LtoYc(double x){
        double y;
        y=x*1640.4199475;
        return dataformatX(y);
    }
    public static String LtoYl(double x){
        double y;
        y=x*0.3106856;
        return dataformatX(y);
    }
    public static String LtoGl(double x){
        double y;
        y=x*0.5;
        return dataformatX(y);
    }
    public static String YltoM(double x){
        double y;
        y=x*1609.344;
        return dataformatX(y);
    }
    public static String YltoC(double x){
        double y;
        y=x*4828.032;
        return dataformatX(y);
    }
    public static String YltoYc(double x){
        double y;
        y=x*5280;
        return dataformatX(y);
    }
    public static String YltoL(double x){
        double y;
        y=x*3.218688;
        return dataformatX(y);
    }
    public static String YltoGl(double x){
        double y;
        y=x*1.609344;
        return dataformatX(y);
    }
    public static String GltoM(double x){
        double y;
        y=x*1000;
        return dataformatX(y);
    }
    public static String GltoC(double x){
        double y;
        y=x*3000;
        return dataformatX(y);
    }
    public static String GltoYc(double x){
        double y;
        y=x*3280.839895;
        return dataformatX(y);
    }
    public static String GltoL(double x){
        double y;
        y=x*2;
        return dataformatX(y);
    }
    public static String GltoYl(double x){
        double y;
        y=x*0.6213712;
        return dataformatX(y);
    }

    /**
     * 重量
     * @param x
     * @return
     */
    public static String KtoQk(double x){
        double y;
        y=x*0.001;
        return dataformatX(y);
    }
    public static String KtoD(double x){
        double y;
        y=x*0.000001;
        return dataformatX(y);
    }
    public static String QktoK(double x){
        double y;
        y=x*1000;
        return dataformatX(y);
    }
    public static String QktoD(double x){
        double y;
        y=x*0.001;
        return dataformatX(y);
    }
    public static String DtoK(double x){
        double y;
        y=x*1000000;
        return dataformatX(y);
    }
    public static String DtoQk(double x){
        double y;
        y=x*1000;
        return dataformatX(y);
    }
    public static String dataformatX(double x){
        DecimalFormat df=new DecimalFormat("#0.000000");
        return  df.format(x);
    }
}
