package com.arcgis.mymap.contacts;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/14.
 */
public class LitepalPoints implements Serializable{
    private int id;
    private String name;
    private String la;
    private String ln;
    private String high;
    private String description;
    private String classification;
    private String code;
    private String litepalPoints;
    private String datetime;

    public LitepalPoints() {
    }

    public LitepalPoints(String litepalPoints) {
        this.litepalPoints=litepalPoints;
    }

    public LitepalPoints(int id,String code, String name, String la, String ln, String high,String classification, String description,String datetime) {
        this.id = id;
        this.code=code;
        this.name = name;
        this.la = la;
        this.ln = ln;
        this.high = high;
        this.classification=classification;
        this.description = description;
        this.datetime=datetime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
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
}
