package com.arcgis.mymap.contacts;

import java.math.BigDecimal;

public class SevenPrams {
    // x方向偏移量，单位：米
    public double x_off = 0.0;
    // y方向偏移量，单位：米
    public double y_off = 0.0;
    // z方向偏移量，单位：米
    public double z_off = 0.0;
    // 绕x轴旋转的角度，单位：弧度
    public double x_angle = 0.0;
    // 绕y轴旋转的角度，单位：弧度
    public double y_angle = 0.0;
    // 绕z轴旋转的角度，单位：弧度
    public double z_angle = 0.0;
    // 尺度因子，单位，无
    public double m = 1.0;

    public SevenPrams(double x_off, double y_off, double z_off, double x_angle, double y_angle, double z_angle, double m) {
        setX_off(x_off);
        setY_off(y_off);
        setZ_off(z_off);

        setX_angle(x_angle);
        setY_angle(y_angle);
        setZ_angle(z_angle);

        setM(m);
    }

    public double getX_off() {
        return x_off;
    }

    public double getY_off() {
        return y_off;
    }

    public double getZ_off() {
        return z_off;
    }

    public double getX_angle() {
        return x_angle;
    }

    public double getY_angle() {
        return y_angle;
    }

    public double getZ_angle() {
        return z_angle;
    }

    public double getM() {
        return m;
    }

    public void setX_off(double x_off) {
        this.x_off = x_off;
    }

    public void setY_off(double y_off) {
        this.y_off = y_off;
    }

    public void setZ_off(double z_off) {
        this.z_off = z_off;
    }

    public void setX_angle(double x_angle) {
        this.x_angle = x_angle;
    }

    public void setY_angle(double y_angle) {
        this.y_angle = y_angle;
    }

    public void setZ_angle(double z_angle) {
        this.z_angle = z_angle;
    }

    public void setM(double m) {
        this.m = m;
    }
    public String toString() {
        String str = "七参数："+"\n△X  "+new BigDecimal(x_off).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue() +"\n△Y  "
                +new BigDecimal(y_off).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()+"\n△Z  "
                +new BigDecimal(z_off).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()+"\n△α  "
                +new BigDecimal(x_angle).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()+"\n△β  "
                +new BigDecimal(y_angle).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()+"\n△γ  "
                +new BigDecimal(z_angle).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()+"\n比例尺  "+m;
        return str;
    }
}
