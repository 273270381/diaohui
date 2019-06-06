package com.arcgis.mymap.utils;

import java.util.ArrayList;
import java.util.List;

public class CoordinateTransform {
    //第一步转换，大地坐标系换换成空间直角坐标系
    public static List<Double>  BLHtoXYZ(double B, double L, double H, double aAxis, double bAxis) {
        double dblD2R = Math.PI / 180;
        double e1 = Math.sqrt(Math.pow(aAxis, 2) - Math.pow(bAxis, 2)) / aAxis;
        double targetX,targetY,targetZ;
        List<Double> list = new ArrayList<>();

        double N = aAxis / Math.sqrt(1.0 - Math.pow(e1, 2) * Math.pow(Math.sin(B * dblD2R), 2));
        targetX = (N + H) * Math.cos(B * dblD2R) * Math.cos(L * dblD2R);
        targetY = (N + H) * Math.cos(B * dblD2R) * Math.sin(L * dblD2R);
        targetZ = (N * (1.0 - Math.pow(e1, 2)) + H) * Math.sin(B * dblD2R);
        list.add(targetX);
        list.add(targetY);
        list.add(targetZ);
        return list;
    }
//    public static List<Double> ParameterTransform(){
//
////第二步转换，空间直角坐标系里七参数转换
//        Ex = transParaSeven.m_dbXRotate / 180 * Math.PI;
//        Ey = transParaSeven.m_dbYRotate / 180 * Math.PI;
//        Ez = transParaSeven.m_dbZRotate / 180 * Math.PI;
//
//        double newX = transParaSeven.m_dbXOffset + transParaSeven.m_dbScale * targetX + targetY * Ez - targetZ * Ey + targetX;
//        double newY = transParaSeven.m_dbYOffset + transParaSeven.m_dbScale * targetY - targetX * Ez + targetZ * Ex + targetY;
//        double newZ = transParaSeven.m_dbZOffset + transParaSeven.m_dbScale * targetZ + targetX * Ey - targetY * Ex + targetZ;
//
//    }
}
