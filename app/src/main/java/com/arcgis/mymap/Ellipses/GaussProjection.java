package com.arcgis.mymap.Ellipses;


import com.arcgis.mymap.contacts.PointGPS;
import com.arcgis.mymap.contacts.PointXY;
import com.arcgis.mymap.contacts.PointXYZ;

/**
 * Created by Administrator on 2016/3/26.
 */

public class GaussProjection {

    public static double PI=3.14159265353846;

    public double L0;        // 中央子午线经度
    public double B, L;        // 大地坐标
    public double x, y;        // 高斯投影平面坐标

    protected double a, f, e2, e12;        // 基本椭球参数 a 长半轴 f 偏率倒数
    protected double A1, A2, A3, A4, A5;        // 用于计算X的椭球参数
    protected double C;
    public double m_dYDif;

    //正算
    public void BL2xy(){
        double X0,N,t,ng2,xx,yy;					//X:
        double sinB,cosB,l,lcosB;
        sinB = Math.sin(B);								//B:成员变量
        cosB = Math.cos(B);
        t = Math.tan(B);
        X0 = A1*B + A2* Math.sin(2 * B) + A3* Math.sin(4 * B) + A4* Math.sin(6 * B) + A5* Math.sin(8 * B);
        l = L - L0;
        lcosB = l*cosB;
        ng2 = cosB*cosB*e2/(1-e2);
        N = C/ Math.sqrt(1+ng2);
        xx = X0+t*N*lcosB*lcosB/2.0+t*N*lcosB*lcosB*lcosB*lcosB*(5-t*t+9*ng2+4*ng2*ng2)/24.0+
                t*N*lcosB*lcosB*lcosB*lcosB*lcosB*lcosB*(61.0-58.0*t*t+t*t*t*t+270.0*ng2-330.0*t*t*ng2)/720.0;
        yy = N*lcosB+N*lcosB*lcosB*lcosB*(1-t*t+ng2)/6.0+N*lcosB*lcosB*lcosB*lcosB*lcosB*(5-18*t*t+t*t*t*t+14*ng2-58*ng2*t*t)/120.0;
        yy += m_dYDif;	// yy+=500000;
//        Setxy(xx,yy);

        x = xx;
        y = yy;
    }

    //反算
    public void xy2BL(){
        double sinB,cosB,t,t2,N,ng2,V,yN;
        double preB0,B0;
        double eta;
        y -= m_dYDif;		// y-=500000;
        B0 = x/A1;
        do
        {
            preB0=B0;
            B0 = (x-(A2* Math.sin(2*B0)+A3* Math.sin(4*B0)+A4* Math.sin(6*B0) + A5* Math.sin(8*B0)))/A1;
            eta = Math.abs(B0 - preB0);
        }while(eta > 0.000000001);
        sinB = Math.sin(B0);
        cosB = Math.cos(B0);
        t = Math.tan(B0);
        t2 = t*t;
        N = a/ Math.sqrt(1-e2*sinB*sinB);
        ng2 = cosB*cosB*e2/(1-e2);
        V = Math.sqrt(1+ng2);
        yN = y/N;
        B = B0-(yN*yN-(5+3*t2+ng2-9*ng2*t2)*yN*yN*yN*yN/12.0+(61+90*t2+45*t2*t2)*yN*yN*yN*yN*yN*yN/360.0)*V*V*t/2;
        L = L0+(yN-(1+2*t2+ng2)*yN*yN*yN/6.0+(5+28*t2+24*t2*t2+6*ng2+8*ng2*t2)*yN*yN*yN*yN*yN/120.0)/cosB;

        B = Rad2Dms(B);
        L = Rad2Dms(L);
//        SetBL(B,L);

        B = Dms2Rad(B);
        L = Dms2Rad(L);
    }

//    //设置中央子午线经度
//    public boolean SetL0(double dL0){
//        L0 = Dms2Rad(dL0);
//        return true;
//    }
//
//    //dB, dL: ddd.mmssss
//    public boolean SetBL(double dB, double dL){
//        B = Dms2Rad(dB);
//        L = Dms2Rad(dL);
//        return true;
//    }

    public boolean GetBL(double dB, double dL){
        dB = Rad2Dms(B);
        dL = Rad2Dms(L);
        return true;
    }

//    public boolean Setxy(double dx, double dy){
//        x = dx;
//        y = dy;
//        return true;
//    }

    public boolean Getxy(double dx, double dy){
        dx = x;
        dy = y;
        return true;
    }

    /**
     * 大地坐标转平面坐标
     */


    /**
     *  大地坐标系（BLH）转 空间直角坐标系（XYZ）
     * @param ptBLH
     * @return PointXYZ
     */
    public PointXYZ BLH2XYZ(PointGPS ptBLH){
        double b = a-a/f;
        PointXYZ ptXYZ = new PointXYZ(0.0, 0.0, 0.0);

        ptBLH.setWgLat(Dms2Rad(ptBLH.getWgLat()));
        ptBLH.setWgLon(Dms2Rad(ptBLH.getWgLon()));

        double Nf = a/(Math.sqrt(1 - (a * a - b * b) * Math.sin(ptBLH.getWgLat()) * Math.sin(ptBLH.getWgLat()) / (a * a)));
        double xx=0.0,yy=0.0,zz=0.0;
        xx = (Nf+ptBLH.getWgHeight())* Math.cos(ptBLH.getWgLat())* Math.cos(ptBLH.getWgLon());
        yy = (Nf + ptBLH.getWgHeight())* Math.cos(ptBLH.getWgLat())* Math.sin(ptBLH.getWgLon());
        zz = (b*b*Nf/(a*a)+ptBLH.getWgHeight())* Math.sin(ptBLH.getWgLat());

        ptXYZ.setX(xx);
        ptXYZ.setY(yy);
        ptXYZ.setZ(zz);

        return ptXYZ;
    }

    public PointGPS XYZ2BLH(PointXYZ ptXYZ){
        double b = a-a/f;
        PointGPS ptBLH = new PointGPS(0.0, 0.0, 0.0);
        double X,Y,Z;

        X = ptXYZ.getX();
        Y = ptXYZ.getY();
        Z = ptXYZ.getZ();
        double sita = Math.atan(Z*a/(b* Math.sqrt(X*X+Y*Y)));

        double tmp = Math.atan(Y / X);
        ptBLH.setWgLon(tmp);

        if(ptBLH.getWgLon() < 0)
            ptBLH.setWgLon(ptBLH.getWgLon() + PI);

        ptBLH.setWgLon(Rad2Dms(ptBLH.getWgLon()));
        ptBLH.setWgLat(Math.atan((Z + e12 * b * Math.sin(sita) * Math.sin(sita) * Math.sin(sita)) / (Math.sqrt(X * X + Y * Y) - e2 * a * Math.cos(sita) * Math.cos(sita) * Math.cos(sita))));
        double N = a/ Math.sqrt(1-e2* Math.sin(ptBLH.getWgLat())* Math.sin(ptBLH.getWgLat()));
        ptBLH.setWgHeight(Math.sqrt(X*X+Y*Y)/ Math.cos(ptBLH.getWgLat())-N);
        ptBLH.setWgLat(Rad2Dms(ptBLH.getWgLat()));
        return ptBLH;
    }


    public double Dms2Rad(double Dms)
    {
        double Degree, Miniute;
        double Second;
        int Sign;
        double Rad;
        if(Dms >= 0)
            Sign = 1;
        else
            Sign = -1;
        Dms = Math.abs(Dms);
        Degree = Math.floor(Dms);
        Miniute = Math.floor((Dms*100.0) % 100.0);
        Second = (Dms * 10000.0) % 100.0;
        Rad = Sign * (Degree+Miniute/60.0+Second/3600.0)*PI/180.0;

        return Rad;
    }

    public double Rad2Dms(double Rad)
    {
        double Degree, Miniute;
        double Second;
        int Sign;
        double Dms;
        if(Rad >= 0)
            Sign = 1;
        else
            Sign = -1;
        Rad = Math.abs(Rad * 180.0 / PI);
        Degree = Math.floor(Rad);
        Miniute = Math.floor(Rad*60.0 % 60.0);
        Second = (Rad*3600.0) % 60.0;
        Dms = Sign*(Degree+Miniute/100.0+Second/10000.0);

        return Dms;
    }
    //----------------------------------------------------
    // 将角度转换为弧度
    // 输入       degree  : 角度，以度为单位
    // 输出                 弧度，以度为单位
    //----------------------------------------------------
    public static double degree_rad(double degree)
    {
        return degree * Math.PI/180.0;
    }


    //----------------------------------------------------
    // 将弧度转换为角度
    // 输入       rad  : 弧度，以度为单位
    // 输出              角度，以度为单位
    //----------------------------------------------------
    public static double rad_degree(double rad)
    {
        return rad*180.0/ Math.PI;
    }

}