package com.arcgis.mymap.Ellipses;

public class PrjPoint_Krasovsky extends GaussProjection    //Krasovsky椭球--1954年北京坐标系
{
    public PrjPoint_Krasovsky() {
        super();

        a = 6378245.000;

        C = 6399698.90178;	//Matlab:6399698.901782711
        f = 298.3;
        e2 = 0.00669342162297;
        e12 = 0.00673852541468;

        A1 = 6367558.49687498;
        A2 = -16036.4802694138;
        A3 = 16.8280668848672;
        A4 = -0.0219753092;
        A5 = 0.0000311242720727;	// 增加A5
    }
}
