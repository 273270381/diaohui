package com.arcgis.mymap.contacts;

/**
 * Created by Administrator on 2018/3/4.
 */

public class NewProject {
    private int id;
    private String color;
    private String projectname;
    private String personname;
    private String strname;
    private String proNum;
    private String datetime;
    private String position;
    private String des;
    private String path;
    private String width;
    //坐标系
    private String c1code;
    //七参数
    private double c3xzpy;
    private double c3yzpy;
    private double c3zzpy;
    private double c3xzxz;
    private double c3yzxz;
    private double c3zzxz;
    private double c3bl;
    //中央子午线(double)（高斯投影）
    private double c2zyzwx;
    //东加常数  （高斯投影）
    private double c2djcs;
    //长半轴
    private Double c1cbz;
    //扁率倒数
    private Double c1plds;


    public NewProject() {
    }

    public NewProject(int id, String color, String projectname, String personname, String strname, String proNum, String datetime, String position, String des, String path, String width, String c1code, double c3xzpy, double c3yzpy, double c3zzpy, double c3xzxz, double c3yzxz, double c3zzxz, double c3bl) {
        this.id = id;
        this.color = color;
        this.projectname = projectname;
        this.personname = personname;
        this.strname = strname;
        this.proNum = proNum;
        this.datetime = datetime;
        this.position = position;
        this.des = des;
        this.path = path;
        this.width = width;
        this.c1code = c1code;
        this.c3xzpy = c3xzpy;
        this.c3yzpy = c3yzpy;
        this.c3zzpy = c3zzpy;
        this.c3xzxz = c3xzxz;
        this.c3yzxz = c3yzxz;
        this.c3zzxz = c3zzxz;
        this.c3bl = c3bl;
    }

    public String getC1code() {
        return c1code;
    }

    public void setC1code(String c1code) {
        this.c1code = c1code;
    }

    public void setC3xzpy(double c3xzpy) {
        this.c3xzpy = c3xzpy;
    }

    public void setC3yzpy(double c3yzpy) {
        this.c3yzpy = c3yzpy;
    }

    public void setC3zzpy(double c3zzpy) {
        this.c3zzpy = c3zzpy;
    }

    public void setC3xzxz(double c3xzxz) {
        this.c3xzxz = c3xzxz;
    }

    public void setC3yzxz(double c3yzxz) {
        this.c3yzxz = c3yzxz;
    }

    public Double getC1cbz() {
        return c1cbz;
    }

    public Double getC1plds() {
        return c1plds;
    }

    public void setC1cbz(Double c1cbz) {
        this.c1cbz = c1cbz;
    }

    public void setC1plds(Double c1plds) {
        this.c1plds = c1plds;
    }

    public void setC3zzxz(double c3zzxz) {
        this.c3zzxz = c3zzxz;
    }

    public void setC3bl(double c3bl) {
        this.c3bl = c3bl;
    }

    public double getC3xzpy() {
        return c3xzpy;
    }

    public double getC3yzpy() {
        return c3yzpy;
    }

    public double getC3zzpy() {
        return c3zzpy;
    }

    public double getC2zyzwx() {
        return c2zyzwx;
    }

    public double getC2djcs() {
        return c2djcs;
    }

    public double getC3xzxz() {
        return c3xzxz;
    }

    public double getC3yzxz() {
        return c3yzxz;
    }

    public double getC3zzxz() {
        return c3zzxz;
    }

    public double getC3bl() {
        return c3bl;
    }

    public void setC2zyzwx(double c2zyzwx) {
        this.c2zyzwx = c2zyzwx;
    }

    public void setC2djcs(double c2djcs) {
        this.c2djcs = c2djcs;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setProNum(String proNum){
        this.proNum = proNum;
    }
    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getPersonname() {
        return personname;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public void setStrname(String strname) {
        this.strname = strname;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public String getProjectname() {
        return projectname;
    }

    public String getStrname() {
        return strname;
    }

    public String getDatetime() {
        return datetime;
    }
    public String getProNum(){
        return proNum;
    }

    public String getPath() {
        return path;
    }

    public String getColor() {
        return color;
    }

    public String getWidth() {
        return width;
    }
}
