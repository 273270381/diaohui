package com.arcgis.mymap.utils;

// 计算坐标转换七参数的公共点
// structCommonPoint --> stCP
public class stCP {

    String strPtName;	// 点名
    double dOX = 0.0;			// 源X坐标
    double dOY = 0.0;			// 源Y坐标
    double dOZ = 0.0;			// 源Z坐标
    double dDX = 0.0;			// 目标X坐标
    double dDY = 0.0;			// 目标Y坐标
    double dDZ = 0.0;			// 目标Z坐标

    public stCP(double ox, double oy, double oz, double dx, double dy, double dz) {
        setdOX(ox);
        setdOY(oy);
        setdOZ(oz);

        setdDX(dx);
        setdDY(dy);
        setdDZ(dz);
    }


    public double getdOX() {
        return dOX;
    }
    public void setdOX(double x) {
        this.dOX = x;
    }

    public double getdOY() {
        return dOY;
    }
    public void setdOY(double y) {
        this.dOY = y;
    }

    public double getdOZ() {
        return dOZ;
    }
    public void setdOZ(double z) {
        this.dOZ = z;
    }

    public double getdDX() {
        return dDX;
    }
    public void setdDX(double x) {
        this.dDX = x;
    }

    public double getdDY() {
        return dDY;
    }
    public void setdDY(double y) {
        this.dDY = y;
    }

    public double getdDZ() {
        return dDZ;
    }
    public void setdDZ(double z) {
        this.dDZ = z;
    }


}
