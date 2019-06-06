package com.arcgis.mymap.Ellipses;

//IAG&IUGG 1975推荐椭球--1980年西安大地坐标系
public class PrjPoint_IUGG1975 extends GaussProjection {
    public PrjPoint_IUGG1975() {
        a = 6378140.000;
        f = 298.257;

        C = 6399596.65198801;			//1/f=(a-b)/a;	C=a*a/b;
        e2 = 0.00669438499959;		//e2=1-((f-1)/f)*((f-1)/f);
        e12 = 0.00673950181947;		//e12=(f/(f-1))*(f/(f-1))-1;

        A1 = 6367452.13278844;
        A2 = -16038.528228699;
        A3 = 16.8326464360351;
        A4 = -0.0219844638947795;
        A5 = 0.0000311417328063;	// 增加A5
    }
}
