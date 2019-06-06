package com.arcgis.mymap.contacts;

/**
 * GPS点类结构
 */
public class PointGPS {

    private double wgLat = 30.452525;//纬度
    private double wgLon = 114.39330833333334;//经度
    private double wgHeight = 0.0;//高程

    public PointGPS(double wgLat, double wgLon, double wgHeight) {
        setWgLat(wgLat);
        setWgLon(wgLon);
        setWgHeight(wgHeight);
    }

    public PointGPS(double wgLat, double wgLon) {
        setWgLat(wgLat);
        setWgLon(wgLon);
    }

    public PointGPS() {
        setWgLat(wgLat);
        setWgLon(wgLon);
        setWgHeight(wgHeight);
    }

    public double getWgLat() {
        return wgLat;
    }

    public void setWgLat(double wgLat) {
        this.wgLat = wgLat;
    }

    public double getWgLon() {
        return wgLon;
    }

    public void setWgLon(double wgLon) {
        this.wgLon = wgLon;
    }

    public double getWgHeight() {
        return wgHeight;
    }

    public void setWgHeight(double wgHeight) {
        this.wgHeight = wgHeight;
    }

    @Override
    public String toString() {
        return wgLat + "," + wgLon + "," + wgHeight;
    }
}