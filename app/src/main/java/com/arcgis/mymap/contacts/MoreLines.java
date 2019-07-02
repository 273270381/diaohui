package com.arcgis.mymap.contacts;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class MoreLines implements Serializable {
    private int id;
    private String name;
    private String classification;
    private List<String> listla;
    private List<String> listln;
    private List<String> linetime;
    private String datatime;
    private String description;
    private String code;

    public MoreLines() {
    }

    public MoreLines(List<String> listla, List<String> listln) {
        this.listla = listla;
        this.listln = listln;
    }

    public MoreLines(int id, String classification, List<String> listla, List<String> listln, String name, String datatime, String description, String code) {
        this.id = id;
        this.name = name;
        this.classification = classification;
        this.listla = listla;
        this.listln = listln;
        this.datatime = datatime;
        this.description = description;
        this.code = code;
    }

    public List<String> getLinetime() {
        return linetime;
    }

    public void setLinetime(List<String> linetime) {
        this.linetime = linetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getClassification() {
        return classification;
    }

    public List<String> getListla() {
        return listla;
    }

    public List<String> getListln() {
        return listln;
    }

    public String getDatatime() {
        return datatime;
    }

    public String getDescription() {
        return description;
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

    public void setListla(List<String> listla) {
        this.listla = listla;
    }

    public void setListln(List<String> listln) {
        this.listln = listln;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
