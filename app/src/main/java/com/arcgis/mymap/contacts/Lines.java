package com.arcgis.mymap.contacts;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/9.
 */

public class Lines implements Serializable{
    private int id;
    private String classification;
    private String Xla;
    private String Xln;
    private String Yla;
    private String Yln;
    private String datatime;
    private String description;
    private String code;

    public Lines() {
    }

    public Lines(int id, String classification, String xla, String xln, String yla, String yln, String datatime, String description, String code) {
        this.id = id;
        this.classification = classification;
        Xla = xla;
        Xln = xln;
        Yla = yla;
        Yln = yln;
        this.datatime = datatime;
        this.description = description;
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setXla(String xla) {
        Xla = xla;
    }

    public void setXln(String xln) {
        Xln = xln;
    }

    public void setYla(String yla) {
        Yla = yla;
    }

    public void setYln(String yln) {
        Yln = yln;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getClassification() {
        return classification;
    }

    public String getXla() {
        return Xla;
    }

    public String getXln() {
        return Xln;
    }

    public String getYla() {
        return Yla;
    }

    public String getYln() {
        return Yln;
    }

    public String getDatatime() {
        return datatime;
    }

    public String getDescription() {
        return description;
    }
}
