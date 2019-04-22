package com.arcgis.mymap.contacts;

/**
 * Created by Administrator on 2018/3/4.
 */

public class NewProject {
    private int id;
    private String projectname;
    private String personname;
    private String strname;
    private String proNum;
    private String datetime;
    private String position;
    private String des;

    public NewProject() {
    }

    public NewProject(int id, String projectname, String personname, String strname, String datetime, String position, String des,String proNum) {
        this.id = id;
        this.projectname = projectname;
        this.personname = personname;
        this.strname = strname;
        this.datetime = datetime;
        this.position = position;
        this.des = des;
        this.proNum = proNum;
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
}
