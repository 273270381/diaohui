package com.arcgis.mymap.utils;

/**
 * Created by Administrator on 2018/1/5.
 */
public class Degree {
    public static double getDegree(double X1,double Y1,double X2,double Y2){
        double ew1;
        double deree;
        if (X1>=X2&&Y1>Y2){
            ew1=Math.atan2(X1-X2,Y1-Y2);
            deree=ew1*180/Math.PI;
        }else if (X1>X2&&Y1<=Y2){
            ew1=Math.atan2(Y2-Y1,X1-X2);
            deree=(ew1*180/Math.PI)+90.00;
        }else if (X1<X2&&Y1>=Y2){
            ew1=Math.atan2(Y1-Y2,X2-X1);
            deree=(ew1*180/Math.PI)+270.00;
        }else {
            ew1=Math.atan2(X2-X1,Y2-Y1);
            deree=(ew1*180/Math.PI)+180.00;
        }
        return deree;
    }
    public static double getAngle1(double lat_a, double lng_a, double lat_b, double lng_b){
        double y = Math.sin(lng_b - lng_a) * Math.cos(lat_b);
        double x = Math.cos(lat_a) * Math.sin(lat_b) - Math.sin(lat_a) * Math.cos(lat_b) * Math.cos(lng_b - lng_a);
        double brng = Math.atan2(y, x);
        brng = Math.toDegrees(brng);
        if (brng < 0)
            brng = brng + 360;
        return brng;
    }
}
