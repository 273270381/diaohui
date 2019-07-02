package com.arcgis.mymap.contacts;

/**
 * 平面直角坐标系
 */
public class PointXY {
    private double x = 0.0;
    private double y = 0.0;

    public PointXY() {
        setX(x);
        setY(y);
    }

    public PointXY(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
