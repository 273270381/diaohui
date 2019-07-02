package com.arcgis.mymap.utils;

import android.graphics.Rect;


import com.arcgis.mymap.Ellipses.PrjPoint_CGCS2000;
import com.arcgis.mymap.Ellipses.PrjPoint_IUGG1975;
import com.arcgis.mymap.Ellipses.PrjPoint_Krasovsky;
import com.arcgis.mymap.Ellipses.PrjPoint_USERDefined;
import com.arcgis.mymap.Ellipses.PrjPoint_WGS84;
import com.arcgis.mymap.JamaMatrix.Matrix;
import com.arcgis.mymap.contacts.FourPrams;
import com.arcgis.mymap.contacts.PointGPS;
import com.arcgis.mymap.contacts.PointXY;
import com.arcgis.mymap.contacts.PointXYZ;
import com.arcgis.mymap.contacts.SevenPrams;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;


/**********************************************************************
 * 注意：以下函数仅限内部转
 * DatumTransBL2XY
 * DatumTransBLH2XYZ
 * DatumTransXYZ2BLH
 * ：例如WGS的XYZ转WGS的BLH,不能WGS的XYZ转BJ的BLH
 ************************************************************************/


/**
 * GPS 坐标系转换
 * BLH : 大地坐标系
 * XYZ ：空间直角坐标系
 */
public class CoordTransUtil {

    public static double PI = 3.14159265353846;

    final public static String WGS84 = "WGS84";
    final public static String CGCS2000 = "China CGCS 2000";
    final public static String XIAN = "China Xian 80";
    final public static String BEIJING54 = "China Beijing 54";
    final public static String USERDEFINED = "自定义";

    /**
     * WGS（BLH）转CGCS2000（XYZ）
     * 传入参数：3D坐标  其它参数：中央子午线(double)
     *
     * @param ptIn  GPS点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYDif 东加常数  （高斯投影）
     * @return
     */
    public static PointXYZ DatumTransWGS2CGCS(PointGPS ptIn, double dCM, SevenPrams st, double dYDif) {
        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
        PointXYZ ptCar = objWGS84.BLH2XYZ(ptIn);    // 得到空间直角坐标

        PointXYZ ptCarCGCS = Trans7Param(st, ptCar);
        PrjPoint_CGCS2000 objPrjCGCS2000 = new PrjPoint_CGCS2000();
        PointGPS ptCGCSBLH = objPrjCGCS2000.XYZ2BLH(ptCarCGCS);

        objPrjCGCS2000.L0 = objPrjCGCS2000.Dms2Rad(dCM);
        objPrjCGCS2000.B = objPrjCGCS2000.Dms2Rad(ptCGCSBLH.getWgLat());
        objPrjCGCS2000.L = objPrjCGCS2000.Dms2Rad(ptCGCSBLH.getWgLon());

        objPrjCGCS2000.m_dYDif = dYDif;
        objPrjCGCS2000.BL2xy();

        double xx = objPrjCGCS2000.x;
        double yy = objPrjCGCS2000.y;

        PointXYZ ptOut = new PointXYZ(0.0, 0.0, 0.0);
        ptOut.setX(xx);
        ptOut.setY(yy);
        ptOut.setZ(ptIn.getWgHeight());
        return ptOut;
    }


    /**
     * WGS（BLH）转北京（XYZ）
     * 传入参数：3D坐标  其它参数：中央子午线(double)
     *
     * @param ptIn  GPS点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYDif 东加常数  （高斯投影）
     * @return
     */
    public static PointXYZ DatumTransWGS2BJ(PointGPS ptIn, double dCM, SevenPrams st, double dYDif) {
        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
        PointXYZ ptCar = objWGS84.BLH2XYZ(ptIn);    // 得到空间直角坐标

        PointXYZ ptCarBJ = Trans7Param(st, ptCar);
        PrjPoint_Krasovsky objPrjBJ54 = new PrjPoint_Krasovsky();
        PointGPS ptBJBLH = objPrjBJ54.XYZ2BLH(ptCarBJ);

        objPrjBJ54.L0 = objPrjBJ54.Dms2Rad(dCM);
        objPrjBJ54.B = objPrjBJ54.Dms2Rad(ptBJBLH.getWgLat());
        objPrjBJ54.L = objPrjBJ54.Dms2Rad(ptBJBLH.getWgLon());

        objPrjBJ54.m_dYDif = dYDif;
        objPrjBJ54.BL2xy();

        double xx = objPrjBJ54.x;
        double yy = objPrjBJ54.y;

        PointXYZ ptOut = new PointXYZ(0.0, 0.0, 0.0);
        ptOut.setX(xx);
        ptOut.setY(yy);
        ptOut.setZ(ptIn.getWgHeight());
        return ptOut;
    }

    /**
     * WGS（BLH）转 WGS（XYZ）
     * 传入参数：3D坐标  其它参数：中央子午线(double)
     *
     * @param ptIn  GPS点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数   自己转自己不需要七参数
     * @param dYDif 东加常数  （高斯投影）
     * @return x, y直接用高斯投影下的xy
     */
    public static PointXYZ DatumTransWGSBLH2XYZ(PointGPS ptIn, double dCM, SevenPrams st, double dYDif) {
        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
//        PointXYZ ptCar = objWGS84.BLH2XYZ(ptIn);    // 得到空间直角坐标

        //  PointXYZ ptCarWGS = Trans7Param(st, ptCar);
        // PrjPoint_WGS84 objPrjWGS = new PrjPoint_WGS84();
        //  PointGPS ptBJBLH = objPrjWGS.XYZ2BLH(ptCarWGS);

        objWGS84.L0 = objWGS84.Dms2Rad(dCM);
        objWGS84.B = objWGS84.Dms2Rad(ptIn.getWgLat());
        objWGS84.L = objWGS84.Dms2Rad(ptIn.getWgLon());

        objWGS84.m_dYDif = dYDif;
        objWGS84.BL2xy();

        double xx = objWGS84.x;
        double yy = objWGS84.y;

        PointXYZ ptOut = new PointXYZ(0.0, 0.0, 0.0);
        ptOut.setX(xx);
        ptOut.setY(yy);
        ptOut.setZ(ptIn.getWgHeight());
        return ptOut;
    }


    /**
     * WGS（BLH）转西安（XYZ）
     *
     * @param ptIn  GPS点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYDif 东加常数  （高斯投影）
     */
    public static PointXYZ DatumTransWGS2XiAn(PointGPS ptIn, double dCM, SevenPrams st, double dYDif) {
        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();

        PointXYZ ptCar = objWGS84.BLH2XYZ(ptIn);    // 得到空间直角坐标

        PointXYZ ptCarXiAn = Trans7Param(st, ptCar);

        PrjPoint_IUGG1975 objXiAn = new PrjPoint_IUGG1975();
        PointGPS ptXiAnBLH = objXiAn.XYZ2BLH(ptCarXiAn);

        objXiAn.L0 = objXiAn.Dms2Rad(dCM);
        objXiAn.B = objXiAn.Dms2Rad(ptXiAnBLH.getWgLat());
        objXiAn.L = objXiAn.Dms2Rad(ptXiAnBLH.getWgLon());

        objXiAn.m_dYDif = dYDif;
        objXiAn.BL2xy();

        double xx = objXiAn.x;
        double yy = objXiAn.y;

        PointXYZ ptOut = new PointXYZ(0.0, 0.0, 0.0);
        ptOut.setX(xx);
        ptOut.setY(yy);
        ptOut.setZ(ptIn.getWgHeight());
        return ptOut;
    }

    /**
     * WGS（BLH）转自定义（XYZ）
     *
     * @param ptIn  GPS点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYDif 东加常数  （高斯投影）
     */
    public static PointXYZ DatumTransWGS2USER(PointGPS ptIn, double dCM, SevenPrams st, double dYDif, double a, double f) {
        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
        PointXYZ ptCar = objWGS84.BLH2XYZ(ptIn);    // 得到空间直角坐标

        PointXYZ ptCarUSER = Trans7Param(st, ptCar);

        PrjPoint_USERDefined objUSERDefined = new PrjPoint_USERDefined(a, f);
        PointGPS ptXiAnBLH = objUSERDefined.XYZ2BLH(ptCarUSER);

        objUSERDefined.L0 = objUSERDefined.Dms2Rad(dCM);
        objUSERDefined.B = objUSERDefined.Dms2Rad(ptXiAnBLH.getWgLat());
        objUSERDefined.L = objUSERDefined.Dms2Rad(ptXiAnBLH.getWgLon());

        objUSERDefined.m_dYDif = dYDif;
        objUSERDefined.BL2xy();

        double xx = objUSERDefined.x;
        double yy = objUSERDefined.y;

        PointXYZ ptOut = new PointXYZ(0.0, 0.0, 0.0);
        ptOut.setX(xx);
        ptOut.setY(yy);
        ptOut.setZ(ptIn.getWgHeight());
        return ptOut;
    }

    /**
     * WGS（XYZ高斯投影）转 WGS（BLH）
     * 传入参数：3D坐标  其它参数：中央子午线(double)
     *
     * @param ptIn  GPS点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYDif 东加常数  （高斯投影）
     * @return
     */
    public static PointGPS DatumTransWGSXYZ2BLH(PointXYZ ptIn, double dCM, SevenPrams st, double dYDif) {
        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
        objWGS84.m_dYDif = dYDif;

        objWGS84.L0 = objWGS84.Dms2Rad(dCM);
        objWGS84.x = ptIn.getX();
        objWGS84.y = ptIn.getY();
        objWGS84.xy2BL();

        double BB = objWGS84.Rad2Dms(objWGS84.B);
        double LL = objWGS84.Rad2Dms(objWGS84.L);

        PointGPS ptWGS84_BLH = new PointGPS(BB, LL, ptIn.getZ());

//        PointXYZ ptCarWGS84_XYZ = objWGS84.BLH2XYZ(ptWGS84_BLH);
//        PointXYZ ptWGS84_XYZ = Trans7Param(st, ptCarWGS84_XYZ);
//
//        PrjPoint_WGS84 objCarWGS84 = new PrjPoint_WGS84();

        return ptWGS84_BLH;
    }

    /**
     * 2000（XYZ）转WGS（BLH）
     *
     * @param ptIn  2000点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYdif 东加常数  （高斯投影）
     * @return
     */
    public static PointGPS DatumTransCGCS2WGS(PointXYZ ptIn, double dCM, SevenPrams st, double dYdif) {
        PrjPoint_CGCS2000 objCGCS = new PrjPoint_CGCS2000();
        objCGCS.m_dYDif = dYdif;

        objCGCS.L0 = objCGCS.Dms2Rad(dCM);
        objCGCS.x = ptIn.getX();
        objCGCS.y = ptIn.getY();
        objCGCS.xy2BL();

        double BB = objCGCS.Rad2Dms(objCGCS.B);
        double LL = objCGCS.Rad2Dms(objCGCS.L);

        PointGPS ptCGCS_BLH = new PointGPS(BB, LL, ptIn.getZ());

        PointXYZ ptCGCS_XYZ = objCGCS.BLH2XYZ(ptCGCS_BLH);
        PointXYZ ptWGS84_XYZ = Trans7Param(st, ptCGCS_XYZ);

        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();

        return objWGS84.XYZ2BLH(ptWGS84_XYZ);
    }

    /**
     * 北京（XYZ）转WGS（BLH）
     *
     * @param ptIn  BJ点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYdif 东加常数  （高斯投影）
     * @return
     */
    public static PointGPS DatumTransBJ2WGS(PointXYZ ptIn, double dCM, SevenPrams st, double dYdif) {
        PrjPoint_Krasovsky objBJ = new PrjPoint_Krasovsky();
        objBJ.m_dYDif = dYdif;

        objBJ.L0 = objBJ.Dms2Rad(dCM);
        objBJ.x = ptIn.getX();
        objBJ.y = ptIn.getY();
        objBJ.xy2BL();

        double BB = objBJ.Rad2Dms(objBJ.B);
        double LL = objBJ.Rad2Dms(objBJ.L);

        PointGPS ptBJ_BLH = new PointGPS(BB, LL, ptIn.getZ());

        PointXYZ ptBJ_XYZ = objBJ.BLH2XYZ(ptBJ_BLH);
        PointXYZ ptWGS84_XYZ = Trans7Param(st, ptBJ_XYZ);

        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();

        return objWGS84.XYZ2BLH(ptWGS84_XYZ);
    }

    /**
     * 西安（XYZ）转WGS（BLH）
     *
     * @param ptIn  xian点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYDif 东加常数  （高斯投影）
     * @return 度分秒
     */
    public static PointGPS DatumTransXiAn2WGS(PointXYZ ptIn, double dCM, SevenPrams st, double dYDif) {
        PrjPoint_IUGG1975 objXiAn = new PrjPoint_IUGG1975();
        objXiAn.m_dYDif = dYDif;

        objXiAn.L0 = objXiAn.Dms2Rad(dCM);
        objXiAn.x = ptIn.getX();
        objXiAn.y = ptIn.getY();
        objXiAn.xy2BL();

        double BB = objXiAn.Rad2Dms(objXiAn.B);
        double LL = objXiAn.Rad2Dms(objXiAn.L);

        PointGPS ptBJ_BLH = new PointGPS(BB, LL, ptIn.getZ());

        PointXYZ ptBJ_XYZ = objXiAn.BLH2XYZ(ptBJ_BLH);
        PointXYZ ptWGS84_XYZ = Trans7Param(st, ptBJ_XYZ);

        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();

        return objWGS84.XYZ2BLH(ptWGS84_XYZ);
    }

    /**
     * 自定义（XYZ）转WGS（BLH）
     *
     * @param ptIn  字定义点坐标
     * @param dCM   中央子午线(double)（高斯投影）
     * @param st    转换参数  三参数/七参数
     * @param dYDif 东加常数  （高斯投影）
     * @return
     */
    public static PointGPS DatumTransUSER2WGS(PointXYZ ptIn, double dCM, SevenPrams st, double dYDif, double a, double f) {
        PrjPoint_USERDefined objUSERDefined = new PrjPoint_USERDefined(a, f);
        objUSERDefined.m_dYDif = dYDif;

        objUSERDefined.L0 = objUSERDefined.Dms2Rad(dCM);
        objUSERDefined.x = ptIn.getX();
        objUSERDefined.y = ptIn.getY();
        objUSERDefined.xy2BL();

        double BB = objUSERDefined.Rad2Dms(objUSERDefined.B);
        double LL = objUSERDefined.Rad2Dms(objUSERDefined.L);

        PointGPS ptBJ_BLH = new PointGPS(BB, LL, ptIn.getZ());

        PointXYZ ptBJ_XYZ = objUSERDefined.BLH2XYZ(ptBJ_BLH);
        PointXYZ ptWGS84_XYZ = Trans7Param(st, ptBJ_XYZ);

        PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();

        return objWGS84.XYZ2BLH(ptWGS84_XYZ);
    }


    /**
     * 计算七参数
     *
     * @param vecCP  公共点对
     * @param dSigma
     * @return
     */
    public static SevenPrams CalSevenParam(Vector<stCP> vecCP, double dSigma) {
        int M = vecCP.size();

        Matrix B = new Matrix(3 * M, 7);
        Matrix L = new Matrix(3 * M, 1);

        for (int k = 0; k < M; k++) {
            // v=BX-L
            // 构造系数矩阵B
            B.set(0 + k * 3, 0, 1.0);
            B.set(0 + k * 3, 1, 0.0);
            B.set(0 + k * 3, 2, 0.0);
            B.set(0 + k * 3, 3, 0.0);
            B.set(0 + k * 3, 4, -vecCP.get(k).getdOZ());
            B.set(0 + k * 3, 5, vecCP.get(k).getdOY());
            B.set(0 + k * 3, 6, vecCP.get(k).getdOX());
            B.set(1 + k * 3, 0, 0.0);
            B.set(1 + k * 3, 1, 1.0);
            B.set(1 + k * 3, 2, 0.0);
            B.set(1 + k * 3, 3, vecCP.get(k).getdOZ());
            B.set(1 + k * 3, 4, 0.0);
            B.set(1 + k * 3, 5, -vecCP.get(k).getdOX());
            B.set(1 + k * 3, 6, vecCP.get(k).getdOY());
            B.set(2 + k * 3, 0, 0.0);
            B.set(2 + k * 3, 1, 0.0);
            B.set(2 + k * 3, 2, 1.0);
            B.set(2 + k * 3, 3, -vecCP.get(k).getdOY());
            B.set(2 + k * 3, 4, vecCP.get(k).getdOX());
            B.set(2 + k * 3, 5, 0.0);
            B.set(2 + k * 3, 6, vecCP.get(k).getdOZ());
            // 构造常数矩阵L
            L.set(0 + k * 3, 0, vecCP.get(k).getdDX() - vecCP.get(k).getdOX());
            L.set(1 + k * 3, 0, vecCP.get(k).getdDY() - vecCP.get(k).getdOY());
            L.set(2 + k * 3, 0, vecCP.get(k).getdDZ() - vecCP.get(k).getdOZ());
        }
        //Matrix Xhat=((B.MatTran()*B).MatInv_All())*(B.MatTran()*L);
        Matrix Xhat = ((B.transpose().times(B)).inverse()).times((B.transpose().times(L)));

        SevenPrams par = new SevenPrams(Xhat.get(0, 0), Xhat.get(1, 0), Xhat.get(2, 0), Xhat.get(3, 0), Xhat.get(4, 0), Xhat.get(5, 0), Xhat.get(6, 0));

        // 精度评定
        int nN = 0, nT = 0;        // 分别为观测值个数和必要观测值个数
        nN = B.getRowDimension();
        nT = 7;
        Matrix V = B.times(Xhat).minus(L);
        dSigma = 0.0;
        double dSum = 0.0;
        for (int k = 0; k < nN; k++) {
            dSum += V.get(k, 0) * V.get(k, 0);
        }
        dSigma = Math.sqrt(dSum / (nN - nT));
        return par;
    }


    //********************************************************************
    // 测试高程拟合程序，6个点，二次多项式拟合（曲面拟合），大地坐标，前提条件为至少6个公共点
    //   V  =  Q * B   -  e;
    //  m*1   m*6 6*1    m*1

    // e = B0 + B1*x + B2*y + B3*x*x + B4*x*y + B5*y*y
    // 中心化处理

    /**
     * @param vec_xyz
     * @return
     */
    public static PointXYZ Centralization(Vector<PointXYZ> vec_xyz) {
        int M = vec_xyz.size();

        double dAveX = 0, dSumX = 0, dAveY = 0, dSumY = 0;    //中心化处理
        for (int i = 0; i < M; i++) {
            dSumX += vec_xyz.get(i).getX();
            dSumY += vec_xyz.get(i).getY();
        }
        dAveX = dSumX / M;
        dAveY = dSumY / M;

        return new PointXYZ(dAveX, dAveY, 0);
    }

    //vec_xyz为原始数据(x,y,e)

    /**
     * 计算高程拟合参数
     *
     * @param vec_xyz
     * @return
     */
//    public static FittingParams CalFittingParams(Vector<PointXYZ> vec_xyz) {
//
//        int M = vec_xyz.size();
//
//        Matrix Q = new Matrix(M, 6);
//
//
//        Vector<PointXYZ> vector_pt = new Vector<>(vec_xyz);
//
//        PointXYZ centrlXY = Centralization(vector_pt);
//
//        for (int i = 0; i < M; i++) {
//            vector_pt.get(i).setX(vec_xyz.get(i).getX() - centrlXY.getX());
//            vector_pt.get(i).setY(vec_xyz.get(i).getY() - centrlXY.getY());
//            vector_pt.get(i).setZ(vec_xyz.get(i).getZ());
//
//        }
//
//        for (int k = 0; k < M; k++) {
//            Q.set(k, 0, 1.0);
//            Q.set(k, 1, vector_pt.get(k).getX());
//            Q.set(k, 2, vector_pt.get(k).getY());
//            Q.set(k, 3, vector_pt.get(k).getX() * vector_pt.get(k).getX());
//            Q.set(k, 4, vector_pt.get(k).getX() * vector_pt.get(k).getY());
//            Q.set(k, 5, vector_pt.get(k).getY() * vector_pt.get(k).getY());
//        }
//
//        Matrix E = new Matrix(M, 1);
//
//        for (int i = 0; i < M; i++) {
//            E.set(i, 0, vector_pt.get(i).getZ());
//        }
////
////        E.set(0,0, vector_pt.get(0).getZ());
////        E.set(1,0, vector_pt.get(1).getZ());
////        E.set(2,0, vector_pt.get(2).getZ());
////        E.set(3,0, vector_pt.get(3).getZ());
////        E.set(4,0, vector_pt.get(4).getZ());
////        E.set(5,0, vector_pt.get(5).getZ());
//
//        Matrix B = Q.transpose().times(Q).inverse().times(Q.transpose()).times(E);
//
//        FittingParams fp = new FittingParams();
//        fp.setB0(B.get(0, 0));
//        fp.setB1(B.get(1, 0));
//        fp.setB2(B.get(2, 0));
//        fp.setB3(B.get(3, 0));
//        fp.setB4(B.get(4, 0));
//        fp.setB5(B.get(5, 0));
//
//        return fp;
//    }

    /**
     * 空间直角坐标系转换（七参数）
     *
     * @param par
     * @param ptXYZOrig
     * @return
     */
    public static PointXYZ Trans7Param(SevenPrams par, PointXYZ ptXYZOrig) {
        double Tx, Ty, Tz, Wx, Wy, Wz, k;
        Tx = par.getX_off();
        Ty = par.getY_off();
        Tz = par.getZ_off();
        Wx = par.getX_angle();
        Wy = par.getY_angle();
        Wz = par.getZ_angle();
        k = par.getM();
        PointXYZ ptXYZDest = new PointXYZ(0.0, 0.0, 0.0);

        double xx = ptXYZOrig.getX() + Tx - ptXYZOrig.getZ() * Wy + ptXYZOrig.getY() * Wz + ptXYZOrig.getX() * k;
        double yy = ptXYZOrig.getY() + Ty + ptXYZOrig.getZ() * Wx - ptXYZOrig.getX() * Wz + ptXYZOrig.getY() * k;
        double zz = ptXYZOrig.getZ() + Tz - ptXYZOrig.getY() * Wx + ptXYZOrig.getX() * Wy + ptXYZOrig.getZ() * k;
        ptXYZDest.setX(xx);
        ptXYZDest.setY(yy);
        ptXYZDest.setZ(zz);

        return ptXYZDest;
    }
//    /**
//     * 空间直角坐标系转换（四参数）
//     *
//     * @param par
//     * @param ptXYZOrig
//     * @return
//     */
//    public static PointXYZ Trans4Param(FourPrams par, PointXYZ ptXYZOrig) {
//        double Tx, Ty, R, k;
//        Tx = par.getX_off();
//        Ty = par.getY_off();
//        R = par.getAngle();
//        k = par.getM();
//        PointXY ptXYDest = new PointXY(0.0,0.0);
//        double x =
//    }

    /**
     * 注意：本函数仅限内部转：例如WGS的BLH转WGS的XYZ,不能WGS的BLH转BJ的XYZ
     * 大地坐标系（BLH）转 空间直角坐标系（XYZ）
     *
     * @param ptBLH
     * @param projectionType 椭球类型
     * @param a              基本椭球参数 长半轴
     * @param f              基本椭球参数 偏率倒数
     * @return
     */
    public static PointXYZ DatumTransBLH2XYZ(PointGPS ptBLH, String projectionType, double a, double f) {
        if (projectionType.equals(WGS84)) {
            PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
            return objWGS84.BLH2XYZ(ptBLH);
        } else if (projectionType.equals(CGCS2000)) {
            PrjPoint_CGCS2000 objCGCS = new PrjPoint_CGCS2000();
            return objCGCS.BLH2XYZ(ptBLH);
        } else if (projectionType.equals(XIAN)) {
            PrjPoint_IUGG1975 objXiAn = new PrjPoint_IUGG1975();
            return objXiAn.BLH2XYZ(ptBLH);
        } else if (projectionType.equals(BEIJING54)) {
            PrjPoint_Krasovsky objBJ = new PrjPoint_Krasovsky();
            return objBJ.BLH2XYZ(ptBLH);
        } else {
            PrjPoint_USERDefined objUSERDefined = new PrjPoint_USERDefined(a, f);
            return objUSERDefined.BLH2XYZ(ptBLH);
        }
    }


    /**
     * 注意：本函数仅限内部转：例如WGS的BL转WGS的XY,不能WGS的BL转BJ的XY
     * 大地坐标系（BL）转 空间直角坐标系（XY）
     *
     * @param ptBLH
     * @param projectionType 椭球类型
     * @param a              基本椭球参数 长半轴
     * @param f              基本椭球参数 偏率倒数
     * @param dCM            中央子午线(double)（高斯投影）
     * @param dYdif          东加常数  （高斯投影）
     * @return
     */
//    public static PointXY DatumTransBL2XY(PointGPS ptBLH, String projectionType, double a, double f, double dCM, double dYdif) {
//        PointXY pointXY = new PointXY();
//        if (projectionType.equals(WGS84)) {
//
//            PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
//            objWGS84.L0 = objWGS84.Dms2Rad(dCM);
//            objWGS84.B = objWGS84.Dms2Rad(ptBLH.getWgLat());
//            objWGS84.L = objWGS84.Dms2Rad(ptBLH.getWgLon());
//            objWGS84.m_dYDif = dYdif;
//            objWGS84.BL2xy();
//            pointXY.setX(objWGS84.x);
//            pointXY.setX(objWGS84.y);
//            return pointXY;
//        } else if (projectionType.equals(CGCS2000)) {
//            PrjPoint_CGCS2000 objCGCS = new PrjPoint_CGCS2000();
//            objCGCS.L0 = objCGCS.Dms2Rad(dCM);
//            objCGCS.B = objCGCS.Dms2Rad(ptBLH.getWgLat());
//            objCGCS.L = objCGCS.Dms2Rad(ptBLH.getWgLon());
//            objCGCS.m_dYDif = dYdif;
//            objCGCS.BL2xy();
//            pointXY.setX(objCGCS.x);
//            pointXY.setX(objCGCS.y);
//            return pointXY;
//        } else if (projectionType.equals(XIAN)) {
//            PrjPoint_IUGG1975 objXiAn = new PrjPoint_IUGG1975();
//            objXiAn.L0 = objXiAn.Dms2Rad(dCM);
//            objXiAn.B = objXiAn.Dms2Rad(ptBLH.getWgLat());
//            objXiAn.L = objXiAn.Dms2Rad(ptBLH.getWgLon());
//            objXiAn.m_dYDif = dYdif;
//            objXiAn.BL2xy();
//            pointXY.setX(objXiAn.x);
//            pointXY.setX(objXiAn.y);
//            return pointXY;
//        } else if (projectionType.equals(BEIJING54)) {
//            PrjPoint_Krasovsky objBJ = new PrjPoint_Krasovsky();
//            objBJ.L0 = objBJ.Dms2Rad(dCM);
//            objBJ.B = objBJ.Dms2Rad(ptBLH.getWgLat());
//            objBJ.L = objBJ.Dms2Rad(ptBLH.getWgLon());
//            objBJ.m_dYDif = dYdif;
//            objBJ.BL2xy();
//            pointXY.setX(objBJ.x);
//            pointXY.setX(objBJ.y);
//            return pointXY;
//        } else {
//            PrjPoint_USERDefined objUSERDefined = new PrjPoint_USERDefined(a, f);
//            objUSERDefined.L0 = objUSERDefined.Dms2Rad(dCM);
//            objUSERDefined.B = objUSERDefined.Dms2Rad(ptBLH.getWgLat());
//            objUSERDefined.L = objUSERDefined.Dms2Rad(ptBLH.getWgLon());
//            objUSERDefined.m_dYDif = dYdif;
//            objUSERDefined.BL2xy();
//            pointXY.setX(objUSERDefined.x);
//            pointXY.setX(objUSERDefined.y);
//            return pointXY;
//        }
//    }


    /**
     * 注意：本函数仅限内部转：例如WGS的XYZ转WGS的BLH,不能WGS的XYZ转BJ的BLH
     * 空间直角坐标系（XYZ）转 大地坐标系（BLH）
     *
     * @param ptXYZ
     * @param projectionType 椭球类型
     * @param a              基本椭球参数 长半轴
     * @param f              基本椭球参数 偏率倒数
     * @return
     */
    public static PointGPS DatumTransXYZ2BLH(PointXYZ ptXYZ, String projectionType, double a, double f) {
        if (projectionType.equals(WGS84)) {
            PrjPoint_WGS84 objWGS84 = new PrjPoint_WGS84();
            return objWGS84.XYZ2BLH(ptXYZ);
        } else if (projectionType.equals(CGCS2000)) {
            PrjPoint_CGCS2000 objCGCS = new PrjPoint_CGCS2000();
            return objCGCS.XYZ2BLH(ptXYZ);
        } else if (projectionType.equals(XIAN)) {
            PrjPoint_IUGG1975 objXiAn = new PrjPoint_IUGG1975();
            return objXiAn.XYZ2BLH(ptXYZ);
        } else if (projectionType.equals(BEIJING54)) {
            PrjPoint_Krasovsky objBJ = new PrjPoint_Krasovsky();
            return objBJ.XYZ2BLH(ptXYZ);
        } else {
            PrjPoint_USERDefined objUSERDefined = new PrjPoint_USERDefined(a, f);
            return objUSERDefined.XYZ2BLH(ptXYZ);
        }
    }


    public static double Dms2Rad(double Dms) {
        double Degree, Miniute;
        double Second;
        int Sign;
        double Rad;
        if (Dms >= 0)
            Sign = 1;
        else
            Sign = -1;
        Dms = Math.abs(Dms);
        Degree = Math.floor(Dms);
        Miniute = Math.floor((Dms * 100.0) % 100.0);
        Second = (Dms * 10000.0) % 100.0;
        Rad = Sign * (Degree + Miniute / 60.0 + Second / 3600.0) * PI / 180.0;

        return Rad;
    }

    public static double Rad2Dms(double Rad) {
        double Degree, Miniute;
        double Second;
        int Sign;
        double Dms;
        if (Rad >= 0)
            Sign = 1;
        else
            Sign = -1;
        Rad = Math.abs(Rad * 180.0 / PI);
        Degree = Math.floor(Rad);
        Miniute = Math.floor(Rad * 60.0 % 60.0);
        Second = (Rad * 3600.0) % 60.0;
        Dms = Sign * (Degree + Miniute / 100.0 + Second / 10000.0);

        return Dms;
    }

    public static double Dms2Degree(double Dms) {
        double Degree, Miniute;
        double Second;
        int Sign;
        double degree;
        if (Dms >= 0)
            Sign = 1;
        else
            Sign = -1;
        Dms = Math.abs(Dms);
        Degree = Math.floor(Dms);
        Miniute = Math.floor((Dms * 100.0) % 100.0);
        Second = (Dms * 10000.0) % 100.0;
        degree = Sign * (Degree + Miniute / 60.0 + Second / 3600.0);
        return degree;
    }

    public static double Degree2Dms(double degree) {
        double Degree, Miniute;
        double Second;
        int Sign;
        double Dms;
        if (degree >= 0)
            Sign = 1;
        else
            Sign = -1;
        degree = Math.abs(degree);
        Degree = Math.floor(degree);
        Miniute = Math.floor(degree * 60.0 % 60.0);
        Second = (degree * 3600.0) % 60.0;
        Dms = Sign * (Degree + Miniute / 100.0 + Second / 10000.0);
        return Dms;
    }

    public static double Degree2Second(double degree) {
        double Second;
        int Sign;
        if (degree >= 0)
            Sign = 1;
        else
            Sign = -1;
        degree = Math.abs(degree);
        Second = Sign * (degree * 3600.0);
        return Second;
    }

    public static double Second2Degree(double Second) {
        double degree;
        int Sign;
        if (Second >= 0)
            Sign = 1;
        else
            Sign = -1;
        Second = Math.abs(Second);
        degree = Sign * (Second / 3600.0);
        return degree;
    }

    public static String D2DMS(double degree) {
        String str;
        if (degree == 0) {
            str = "00°00′00.00000″";
        } else {
            int d = (int) degree;
            int m = (int) ((degree - d) * 60);
            double s = (((degree - d) * 60) - m) * 60;
            s = new BigDecimal(s).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
            str = d + "°" + m + "′" + s + "″";
        }
        return str;
    }

    public static String Dms2DMS(double Dms) {
        int Sign = 0;
        if (Dms >= 0)
            Sign = 1;
        else
            Sign = -1;
        Dms = Math.abs(Dms);
        String str;
        if (Dms == 0) {
            str = "00°00′00.00000″";
        } else {
            int d = (int) Dms;
            int m = (int) ((Dms - d) * 100);
            double s = (((Dms - d) * 100) - m) * 100;
            if (s >= 60) {
                m = m + 1;
                s = s - 60;
            }
            if (m >= 60) {
                d = d + 1;
                m = m - 60;
            }
            DecimalFormat df = new DecimalFormat("0.00000");
            // df.format(s);
            // s = new BigDecimal(s).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();

            // int lenth

            if (Sign < 0)
                str = "-" + d + "°" + m + "′" + df.format(s) + "″";
            else
                str = d + "°" + m + "′" + df.format(s) + "″";
        }
        return str;
    }

    /**
     * 倾斜修正
     *
     * @param pointGPSIn     输入点
     * @param attitudeDataIn 姿态数据
     * @param l              相位中心距离地面的高度
     * @return 修正后的点
     */
//    public static PointGPS TiltMeasurement(PointGPS pointGPSIn, AttitudeData attitudeDataIn, double l) {
//
//        double costhetaH = Math.cos(ScaleTransition.degree_rad(attitudeDataIn.getHeading()));
//        double sinthetaH = Math.sin(ScaleTransition.degree_rad(attitudeDataIn.getHeading()));
//
//        double costhetaP = Math.cos(ScaleTransition.degree_rad(attitudeDataIn.getPitch()));
//        double sinthetaP = Math.sin(ScaleTransition.degree_rad(attitudeDataIn.getPitch()));
//
//        double costhetaR = Math.cos(ScaleTransition.degree_rad(attitudeDataIn.getRoll()));
//        double sinthetaR = Math.sin(ScaleTransition.degree_rad(attitudeDataIn.getRoll()));
//
//        double lr2 = l * l + l * l - 2 * l * l * costhetaR;
//        double lp2 = l * l + l * l - 2 * l * l * costhetaP;
//
//        double lt2 = lr2 + lp2;
//
//        double costhetaT = costhetaR + costhetaP - 1;
//
//        double dis = l * Math.sin(Math.acos(costhetaT));
//
//        PointGPS gps = ScaleTransition.GetLUH(pointGPSIn, dis, attitudeDataIn.getHeading(), 0.0);
//
//        gps.setWgHeight(gps.getWgHeight() - l * Math.cos(Math.acos(costhetaT)));
//
//        return gps;
//    }

    /**
     * 计算倾斜误差
     *
     * @param ptIn_first
     * @param ptIn_second
     * @return
     */
    public static double calcGaussDis(PointGPS ptIn_first, PointGPS ptIn_second) {

        PrjPoint_WGS84 objWGS84_second = new PrjPoint_WGS84();
        PrjPoint_WGS84 objWGS84_first = new PrjPoint_WGS84();

        int N_first = (int) Math.round(ptIn_first.getWgLon() / 3);//取经度整数
        int L0_first = 3 * N_first;

        objWGS84_first.L0 = objWGS84_first.Dms2Rad(L0_first);
        objWGS84_first.B = objWGS84_first.Dms2Rad(ptIn_first.getWgLat());
        objWGS84_first.L = objWGS84_first.Dms2Rad(ptIn_first.getWgLon());

        objWGS84_first.m_dYDif = 500000;
        objWGS84_first.BL2xy();

        int N_second = (int) Math.round(ptIn_second.getWgLon() / 3);//取经度整数
        int L0_second = 3 * N_second;

        objWGS84_second.L0 = objWGS84_second.Dms2Rad(L0_second);
        objWGS84_second.B = objWGS84_second.Dms2Rad(ptIn_second.getWgLat());
        objWGS84_second.L = objWGS84_second.Dms2Rad(ptIn_second.getWgLon());

        objWGS84_second.m_dYDif = 500000;
        objWGS84_second.BL2xy();

        double x_second = objWGS84_second.x;
        double y_second = objWGS84_second.y;
        double x_first = objWGS84_first.x;
        double y_first = objWGS84_first.y;
        //计算平面直角坐标系下两点间距离
        double dis = Math.sqrt((x_second - x_first) * (x_second - x_first) + (y_second - y_first) * (y_second - y_first));

        return dis;
    }

    /**
     * 计算一条直线按距离分割后每个分割点的坐标
     * 方程组 l²=(x-x1)²+(y-y1)²  y-y1=(x-x1)*D
     *
     * @param point1 开始点
     * @param point2 结束点
     * @param l      分割长度
     * @return
     */
//    public static List<PointXY> calcSplitPoint(PointXY point1, PointXY point2, double l) {
//        List<PointXY> pointXYList = new ArrayList<>();
//        if (l > 0) {
//            double dist = Math.sqrt(Math.pow(Math.abs(point1.getX() - point2.getX()), 2) + Math.pow(Math.abs(point1.getY() - point2.getY()), 2));
//            if (l < dist) {
//                int i = 1;
//                do {
//                    double D = (point2.getY() - point1.getY()) / ((point2.getX() - point1.getX()) == 0 ? 1 : (point2.getX() - point1.getX()));
//                    double x = l * i / Math.sqrt((1 + Math.pow(D, 2))) + point1.getX();
//                    double y = l * i * D / Math.sqrt((1 + Math.pow(D, 2))) + point1.getY();
//                    pointXYList.add(new PointXY(x, y));
//                    i++;
//                } while (i * l < dist);
//            }
//        }
//
//        return pointXYList;
//    }

    /**
     * 根据中间线的坐标与半边长计算矩形
     *
     * @param point1 中点1
     * @param point2 中点2
     * @param a      半边长
     * @return
     */
//    public static Rect calcRectFromCenterLine(PointXY point1, PointXY point2, double a) {
//        //长度
//        double b = Math.sqrt(Math.pow(Math.abs(point1.getX() - point2.getX()), 2) + Math.pow(Math.abs(point1.getY() - point2.getY()), 2));
//        if (a > 0 && b > 0) {
//            double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
//
//        }
//        return new Rect(0, 0, 0, 0);
//    }
}
