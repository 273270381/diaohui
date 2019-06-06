package com.arcgis.mymap.Ellipses;


//WGS84 1984世界大地坐标系
public class PrjPoint_WGS84 extends GaussProjection {
    public PrjPoint_WGS84() {
        super();

        a = 6378137.000;

        C = 6399593.625758493;
        f = 298.2572235634;
        e2 = 0.00669437999013245;
        e12 = 0.00673949674228;

        A1 = 6367449.14582342;
        A2 = -16038.5086629761;
        A3 = 16.832613263236;

        A4 = -0.0219844040626737;
        A5 = 0.0000311416246802;	// 增加A5
    }
}
