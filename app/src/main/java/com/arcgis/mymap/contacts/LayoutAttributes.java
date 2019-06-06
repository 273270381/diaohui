package com.arcgis.mymap.contacts;

import java.io.Serializable;

public class LayoutAttributes implements Serializable{
    private String name;
    private int color;

    public LayoutAttributes() {
    }

    public LayoutAttributes(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
