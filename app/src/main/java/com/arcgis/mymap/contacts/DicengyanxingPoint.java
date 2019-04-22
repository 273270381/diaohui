package com.arcgis.mymap.contacts;

import java.io.Serializable;

public class DicengyanxingPoint implements Serializable{
    private int id;
    private String name;
    private String la;
    private String ln;
    private String high;
    private String dznd;
    private String ytmc;
    private String description;
    private String fhcd;
    private String cz;
    private String jl;
    private String classification;
    private String code;

    public DicengyanxingPoint() {
    }

    public DicengyanxingPoint(int id, String name, String la, String ln, String high, String dznd, String ytmc, String description, String fhcd, String cz, String jl, String classification, String code) {
        this.id = id;
        this.name = name;
        this.la = la;
        this.ln = ln;
        this.high = high;
        this.dznd = dznd;
        this.ytmc = ytmc;
        this.description = description;
        this.fhcd = fhcd;
        this.cz = cz;
        this.jl = jl;
        this.classification = classification;
        this.code = code;
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

    public String getDznd() {
        return dznd;
    }

    public String getYtmc() {
        return ytmc;
    }

    public String getDescription() {
        return description;
    }

    public String getFhcd() {
        return fhcd;
    }

    public String getCz() {
        return cz;
    }

    public String getJl() {
        return jl;
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

    public void setDznd(String dznd) {
        this.dznd = dznd;
    }

    public void setYtmc(String ytmc) {
        this.ytmc = ytmc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFhcd(String fhcd) {
        this.fhcd = fhcd;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public void setJl(String jl) {
        this.jl = jl;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
