package com.arcgis.mymap.Ellipses;

/**
 * Created by King on 2016/5/9.
 */
public class PrjPoint_USERDefined extends GaussProjection{
    public PrjPoint_USERDefined(double a, double f) {
        super();

        this.a = a;//长半轴
        this.f = f;//扁率倒数

        double b = a - a/f;
        C=a*a/b;
        e2=1-((f-1)/f)*((f-1)/f);
        e12=(f/(f-1))*(f/(f-1))-1;

        A1 = a * (1-e2) * (1 + 3/4.0*e2 + 45/64.0*e2*e2 + 175/256.0*e2*e2*e2 + 11025/16384.0*e2*e2*e2*e2 + 43659/65536.0*e2*e2*e2*e2*e2);
        A2 = -1/2.0 * a * (1-e2) * (3/4.0*e2 + 15/16.0*e2*e2 + 525/512.0*e2*e2*e2 + 2205/2048.0*e2*e2*e2*e2 + 72765/65536.0*e2*e2*e2*e2*e2);
        A3 = 1/4.0 * a * (1-e2) * (15/64.0*e2*e2 + 105/256.0*e2*e2*e2 + 2205/4096.0*e2*e2*e2*e2 + 10395/16384.0*e2*e2*e2*e2*e2);
        A4 = -1/6.0 * a * (1-e2) * (35/512.0*e2*e2*e2 + 315/2048.0*e2*e2*e2*e2 + 31185/131072.0*e2*e2*e2*e2*e2);
        A5 = 1/8.0 * a *(1-e2) * (315/16384.0*e2*e2*e2*e2 + 3465/65536.0*e2*e2*e2*e2*e2);
    }

}
