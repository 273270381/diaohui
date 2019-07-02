package com.arcgis.mymap.contacts;

public class FourPrams {
    //东方向偏移量，单位：米
    public double x_off = 0.0;
    // 北方向偏移量，单位：米
    public double y_off = 0.0;
    // 旋转的角度，单位：弧度
    public double angle = 0.0;
    // 尺度因子，单位，无
    public double m = 1.0;

    public FourPrams(double x_off, double y_off, double angle, double m) {
        setX_off(x_off);
        setY_off(y_off);
        setAngle(angle);
        setM(m);
    }

    public double getX_off() {
        return x_off;
    }

    public double getY_off() {
        return y_off;
    }

    public double getAngle() {
        return angle;
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

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setM(double m) {
        this.m = m;
    }
}
