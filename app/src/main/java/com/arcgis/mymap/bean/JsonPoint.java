package com.arcgis.mymap.bean;

public class JsonPoint {
    private String x;
    private String y;
    private Object spatialReference;

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public Object getSpatialReference() {
        return spatialReference;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setSpatialReference(Object spatialReference) {
        this.spatialReference = spatialReference;
    }
}
