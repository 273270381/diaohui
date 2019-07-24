package com.arcgis.mymap.contacts;

import java.io.Serializable;

public class GouzhuwuPoint implements Serializable {
    private int id;
    private String name;
    private String time;
    private String la;
    private String ln;
    private String high;
    private String description;
    private String classification;
    private String code;

    public GouzhuwuPoint() {
    }

    public GouzhuwuPoint(int id, String name, String la, String ln, String high, String description, String classification, String code) {
        this.id = id;
        this.name = name;
        this.la = la;
        this.ln = ln;
        this.high = high;
        this.description = description;
        this.classification = classification;
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLa() {
        return la;
    }

    public String getLn() {
        return ln;
    }

    public String getHigh() {
        return high;
    }

    public String getDescription() {
        return description;
    }

    public String getClassification() {
        return classification;
    }

    public String getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLa(String la) {
        this.la = la;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
