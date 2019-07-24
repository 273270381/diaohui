package com.arcgis.mymap.contacts;

import java.io.Serializable;

public class ShuiwendizhiPoint implements Serializable{
    private int id;
    private String name;
    private String time;
    private String la;
    private String ln;
    private String high;
    private String sllx;
    private String smkd;
    private String ss;
    private String ls;
    private String ll;
    private String sz;
    private String des;
    private String code;

    public ShuiwendizhiPoint() {
    }

    public ShuiwendizhiPoint(int id, String name, String la, String ln, String high, String sllx, String smkd, String ss, String ls, String ll, String sz, String des, String code) {
        this.id = id;
        this.name = name;
        this.la = la;
        this.ln = ln;
        this.high = high;
        this.sllx = sllx;
        this.smkd = smkd;
        this.ss = ss;
        this.ls = ls;
        this.ll = ll;
        this.sz = sz;
        this.des = des;
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

    public String getSllx() {
        return sllx;
    }

    public String getSmkd() {
        return smkd;
    }

    public String getSs() {
        return ss;
    }

    public String getLs() {
        return ls;
    }

    public String getLl() {
        return ll;
    }

    public String getSz() {
        return sz;
    }

    public String getDes() {
        return des;
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

    public void setSllx(String sllx) {
        this.sllx = sllx;
    }

    public void setSmkd(String smkd) {
        this.smkd = smkd;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public void setLl(String ll) {
        this.ll = ll;
    }

    public void setSz(String sz) {
        this.sz = sz;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
