package com.arcgis.mymap.contacts;

/**
 * 空间直角坐标系XYZ三坐标点类结构,有时也兼用于NEU坐标系点,XYZ分别对应于NEU
 */
public class PointXYZ {

    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    public PointXYZ(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public PointXYZ() {
        setX(x);
        setY(y);
        setZ(z);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }
}
