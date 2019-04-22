package com.arcgis.mymap.utils;

import com.esri.arcgisruntime.geometry.Point;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */
public class WithIn {
    /**
     * 判断坐标点是否落在指定的多边形区域内
     * @param point  指定的坐标点
     * @param list   多变形区域的节点集合
     * @return   True 落在范围内 False 不在范围内
     */
    public boolean IsWithIn(Point point, List<Point> list) {
        double x = point.getX();
        double y = point.getY();

        int isum, icount, index;
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;

        if (list.size() < 3) {
            return false;
        }

        isum = 0;
        icount = list.size();

        for (index = 0; index < icount - 1; index++) {
            if (index == icount - 1) {
                dLon1 = list.get(index).getX();
                dLat1 = list.get(index).getY();
                dLon2 = list.get(0).getX();
                dLat2 = list.get(0).getY();
            } else {
                dLon1 = list.get(index).getX();
                dLat1 = list.get(index).getY();
                dLon2 = list.get(index + 1).getX();
                dLat2 = list.get(index + 1).getY();
            }

            // 判断指定点的 纬度是否在 相邻两个点(不为同一点)的纬度之间
            if (((y >= dLat1) && (y < dLat2)) || ((y >= dLat2) && (y < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - y)) / (dLat1 - dLat2);
                    if (dLon < x){
                        isum++;
                    }
                }
            }
        }

        if ((isum % 2) != 0) {
            return true;
        } else {
            return false;
        }
    }

}
