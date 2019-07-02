package com.arcgis.mymap.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/15.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_GEOPOINTS0="create table Geodxdmpoints0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS1="create table Geodxdmpoints1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS2="create table Geodxdmpoints2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS3="create table Geodxdmpoints3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS4="create table Geodxdmpoints4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS5="create table Geodxdmpoints5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS6="create table Geodxdmpoints6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS7="create table Geodxdmpoints7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS8="create table Geodxdmpoints8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS9="create table Geodxdmpoints9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS10="create table Geodxdmpoints10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS11="create table Geodxdmpoints11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS12="create table Geodxdmpoints12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS13="create table Geodxdmpoints13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS14="create table Geodxdmpoints14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS15="create table Geodxdmpoints15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS16="create table Geodxdmpoints16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS17="create table Geodxdmpoints17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS18="create table Geodxdmpoints18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS19="create table Geodxdmpoints19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"zhibeifayu text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTS20="create table Geodxdmpoints20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";


    public static final String CREATE_GEODCYXPOINTS0="create table Geodcyxpoints0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS1="create table Geodcyxpoints1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS2="create table Geodcyxpoints2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS3="create table Geodcyxpoints3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS4="create table Geodcyxpoints4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS5="create table Geodcyxpoints5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS6="create table Geodcyxpoints6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS7="create table Geodcyxpoints7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS8="create table Geodcyxpoints8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS9="create table Geodcyxpoints9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS10="create table Geodcyxpoints10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS11="create table Geodcyxpoints11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS12="create table Geodcyxpoints12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS13="create table Geodcyxpoints13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS14="create table Geodcyxpoints14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS15="create table Geodcyxpoints15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS16="create table Geodcyxpoints16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS17="create table Geodcyxpoints17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS18="create table Geodcyxpoints18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS19="create table Geodcyxpoints19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";
    public static final String CREATE_GEODCYXPOINTS20="create table Geodcyxpoints20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"dznd text,"+"ytmc text,"+"gclassification text,"+"gcode text,"+"fhcd text,"+"cz text,"+"jl text,"+"gdescription text)";



    public static final String CREATE_GEOSWDZPOINTS0="create table Geoswdzpoints0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS1="create table Geoswdzpoints1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS2="create table Geoswdzpoints2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS3="create table Geoswdzpoints3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS4="create table Geoswdzpoints4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS5="create table Geoswdzpoints5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS6="create table Geoswdzpoints6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS7="create table Geoswdzpoints7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS8="create table Geoswdzpoints8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS9="create table Geoswdzpoints9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS10="create table Geoswdzpoints10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS11="create table Geoswdzpoints11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS12="create table Geoswdzpoints12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS13="create table Geoswdzpoints13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS14="create table Geoswdzpoints14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS15="create table Geoswdzpoints15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS16="create table Geoswdzpoints16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS17="create table Geoswdzpoints17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS18="create table Geoswdzpoints18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS19="create table Geoswdzpoints19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOSWDZPOINTS20="create table Geoswdzpoints20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"sllx text,"+"smkd text,"+"ss text,"+"ls text,"+"ll text,"+"sz text,"+"code text,"+"gdescription text)";



    public static final String CREATE_GEOGZWDPOINTS0="create table Geogzwdpoints0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS1="create table Geogzwdpoints1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS2="create table Geogzwdpoints2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS3="create table Geogzwdpoints3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS4="create table Geogzwdpoints4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS5="create table Geogzwdpoints5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS6="create table Geogzwdpoints6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS7="create table Geogzwdpoints7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS8="create table Geogzwdpoints8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS9="create table Geogzwdpoints9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS10="create table Geogzwdpoints10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS11="create table Geogzwdpoints11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS12="create table Geogzwdpoints12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS13="create table Geogzwdpoints13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS14="create table Geogzwdpoints14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS15="create table Geogzwdpoints15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS16="create table Geogzwdpoints16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS17="create table Geogzwdpoints17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS18="create table Geogzwdpoints18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS19="create table Geogzwdpoints19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";
    public static final String CREATE_GEOGZWDPOINTS20="create table Geogzwdpoints20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"lx text,"+"code text,"+"gdescription text)";



    public static final String CREATE_GEOPHOTOPOINTS0="create table Geophotopoints0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS1="create table Geophotopoints1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS2="create table Geophotopoints2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS3="create table Geophotopoints3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS4="create table Geophotopoints4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS5="create table Geophotopoints5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS6="create table Geophotopoints6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS7="create table Geophotopoints7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS8="create table Geophotopoints8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS9="create table Geophotopoints9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS10="create table Geophotopoints10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS11="create table Geophotopoints11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS12="create table Geophotopoints12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS13="create table Geophotopoints13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS14="create table Geophotopoints14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS15="create table Geophotopoints15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS16="create table Geophotopoints16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS17="create table Geophotopoints17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS18="create table Geophotopoints18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS19="create table Geophotopoints19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPHOTOPOINTS20="create table Geophotopoints20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";




    public static final String CREATE_GEOPOINTEXCEL0="create table Geopointexcel0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL1="create table Geopointexcel1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL2="create table Geopointexcel2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL3="create table Geopointexcel3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL4="create table Geopointexcel4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL5="create table Geopointexcel5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL6="create table Geopointexcel6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL7="create table Geopointexcel7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL8="create table Geopointexcel8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL9="create table Geopointexcel9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL10="create table Geopointexcel10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL11="create table Geopointexcel11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL12="create table Geopointexcel12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL13="create table Geopointexcel13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL14="create table Geopointexcel14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL15="create table Geopointexcel15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL16="create table Geopointexcel16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL17="create table Geopointexcel17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL18="create table Geopointexcel18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL19="create table Geopointexcel19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOPOINTEXCEL20="create table Geopointexcel20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"gclassification text,"+"gcode text,"+"time text,"+"gdescription text)";






    public static final String CREATE_GEOMORELINES0="create table Geomorelines0("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES1="create table Geomorelines1("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES2="create table Geomorelines2("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES3="create table Geomorelines3("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES4="create table Geomorelines4("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES5="create table Geomorelines5("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES6="create table Geomorelines6("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES7="create table Geomorelines7("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES8="create table Geomorelines8("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES9="create table Geomorelines9("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES10="create table Geomorelines10("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES11="create table Geomorelines11("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES12="create table Geomorelines12("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES13="create table Geomorelines13("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES14="create table Geomorelines14("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES15="create table Geomorelines15("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES16="create table Geomorelines16("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES17="create table Geomorelines17("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES18="create table Geomorelines18("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES19="create table Geomorelines19("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINES20="create table Geomorelines20("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";




    public static final String CREATE_GEOMORELINEEXCEL0="create table Geomorelineexcel0("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL1="create table Geomorelineexcel1("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL2="create table Geomorelineexcel2("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL3="create table Geomorelineexcel3("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL4="create table Geomorelineexcel4("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL5="create table Geomorelineexcel5("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL6="create table Geomorelineexcel6("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL7="create table Geomorelineexcel7("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL8="create table Geomorelineexcel8("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL9="create table Geomorelineexcel9("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL10="create table Geomorelineexcel10("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL11="create table Geomorelineexcel11("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL12="create table Geomorelineexcel12("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL13="create table Geomorelineexcel13("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL14="create table Geomorelineexcel14("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL15="create table Geomorelineexcel15("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL16="create table Geomorelineexcel16("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL17="create table Geomorelineexcel17("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL18="create table Geomorelineexcel18("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL19="create table Geomorelineexcel19("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOMORELINEEXCEL20="create table Geomorelineexcel20("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";




    public static final String CREATE_GEOSURFACE0="create table Geosurface0("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE1="create table Geosurface1("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE2="create table Geosurface2("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE3="create table Geosurface3("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE4="create table Geosurface4("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE5="create table Geosurface5("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE6="create table Geosurface6("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE7="create table Geosurface7("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE8="create table Geosurface8("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE9="create table Geosurface9("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE10="create table Geosurface10("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE11="create table Geosurface11("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE12="create table Geosurface12("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE13="create table Geosurface13("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE14="create table Geosurface14("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE15="create table Geosurface15("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE16="create table Geosurface16("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE17="create table Geosurface17("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE18="create table Geosurface18("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE19="create table Geosurface19("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACE20="create table Geosurface20("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";



    public static final String CREATE_GEOSURFACEEXCEL0="create table Geosurfaceexcel0("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL1="create table Geosurfaceexcel1("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL2="create table Geosurfaceexcel2("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL3="create table Geosurfaceexcel3("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL4="create table Geosurfaceexcel4("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL5="create table Geosurfaceexcel5("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL6="create table Geosurfaceexcel6("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL7="create table Geosurfaceexcel7("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL8="create table Geosurfaceexcel8("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL9="create table Geosurfaceexcel9("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL10="create table Geosurfaceexcel10("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL11="create table Geosurfaceexcel11("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL12="create table Geosurfaceexcel12("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL13="create table Geosurfaceexcel13("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL14="create table Geosurfaceexcel14("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL15="create table Geosurfaceexcel15("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL16="create table Geosurfaceexcel16("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL17="create table Geosurfaceexcel17("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL18="create table Geosurfaceexcel18("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL19="create table Geosurfaceexcel19("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";
    public static final String CREATE_GEOSURFACEEXCEL20="create table Geosurfaceexcel20("+"id integer primary key autoincrement,"+"name text,"+"gclassification text,"+"gcode text,"
            +"gla text,"+"gln text,"+"time text,"+"gdescription text)";




    public static final String CREATE_NEWPOINTS0="create table Newpoints0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS1="create table Newpoints1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS2="create table Newpoints2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS3="create table Newpoints3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS4="create table Newpoints4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS5="create table Newpoints5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS6="create table Newpoints6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS7="create table Newpoints7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS8="create table Newpoints8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS9="create table Newpoints9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS10="create table Newpoints10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS11="create table Newpoints11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS12="create table Newpoints12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS13="create table Newpoints13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS14="create table Newpoints14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS15="create table Newpoints15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS16="create table Newpoints16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS17="create table Newpoints17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS18="create table Newpoints18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS19="create table Newpoints19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_NEWPOINTS20="create table Newpoints20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";



    public static final String CREATE_PHOTOPOINTS0="create table Photopoints0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS1="create table Photopoints1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS2="create table Photopoints2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS3="create table Photopoints3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS4="create table Photopoints4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS5="create table Photopoints5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS6="create table Photopoints6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS7="create table Photopoints7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS8="create table Photopoints8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS9="create table Photopoints9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS10="create table Photopoints10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS11="create table Photopoints11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS12="create table Photopoints12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS13="create table Photopoints13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS14="create table Photopoints14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS15="create table Photopoints15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS16="create table Photopoints16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS17="create table Photopoints17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS18="create table Photopoints18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS19="create table Photopoints19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_PHOTOPOINTS20="create table Photopoints20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";




    public static final String CREATE_POINTEXCEL0="create table Pointexcel0("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL1="create table Pointexcel1("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL2="create table Pointexcel2("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL3="create table Pointexcel3("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL4="create table Pointexcel4("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL5="create table Pointexcel5("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL6="create table Pointexcel6("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL7="create table Pointexcel7("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL8="create table Pointexcel8("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL9="create table Pointexcel9("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL10="create table Pointexcel10("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL11="create table Pointexcel11("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL12="create table Pointexcel12("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL13="create table Pointexcel13("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL14="create table Pointexcel14("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL15="create table Pointexcel15("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL16="create table Pointexcel16("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL17="create table Pointexcel17("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL18="create table Pointexcel18("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL19="create table Pointexcel19("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";
    public static final String CREATE_POINTEXCEL20="create table Pointexcel20("+"id integer primary key autoincrement,"+"name text,"
            +"la text,"+"ln text,"+"high text,"+"classification text,"+"code text,"+"time text,"+"description text)";











    public static final String CREATE_NEWLINES0="create table Newlines0("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES1="create table Newlines1("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES2="create table Newlines2("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES3="create table Newlines3("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES4="create table Newlines4("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES5="create table Newlines5("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES6="create table Newlines6("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES7="create table Newlines7("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES8="create table Newlines8("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES9="create table Newlines9("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES10="create table Newlines10("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES11="create table Newlines11("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES12="create table Newlines12("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES13="create table Newlines13("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES14="create table Newlines14("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES15="create table Newlines15("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES16="create table Newlines16("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES17="create table Newlines17("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES18="create table Newlines18("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES19="create table Newlines19("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";
    public static final String CREATE_NEWLINES20="create table Newlines20("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"linetime text,"+"time text,"+"description text)";



    public static final String CREATE_LINEEXCEL0="create table Lineexcel0("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL1="create table Lineexcel1("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL2="create table Lineexcel2("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL3="create table Lineexcel3("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL4="create table Lineexcel4("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL5="create table Lineexcel5("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL6="create table Lineexcel6("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL7="create table Lineexcel7("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL8="create table Lineexcel8("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL9="create table Lineexcel9("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL10="create table Lineexcel10("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL11="create table Lineexcel11("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL12="create table Lineexcel12("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL13="create table Lineexcel13("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL14="create table Lineexcel14("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL15="create table Lineexcel15("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL16="create table Lineexcel16("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL17="create table Lineexcel17("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL18="create table Lineexcel18("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL19="create table Lineexcel19("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";
    public static final String CREATE_LINEEXCEL20="create table Lineexcel20("+"id integer primary key autoincrement,"+"classification text,"+"code text,"
            +"xla text,"+"xln text,"+"time text,"+"description text)";




    public static final String CREATE_NEWTIMES="create table Newtimes("+"id integer primary key autoincrement,"+"time text)";
    public static final String CREATE_NEWPOSITION="create table Newposition("+"id integer primary key autoincrement,"+"position text)";
    public static final String CREATE_NEWPPPOSITION="create table Newppposition("+"id integer primary key autoincrement,"+"position text)";
    public static final String CREATE_NEWPROJECT="create table Newproject("+"id integer primary key autoincrement,"+"exportpath text,"+"pname text,"+"name text,"+"sname text,"
            +"timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,"+"position text,"+"description text,"+"layercolor text,"+"width text,"+"c3xzpy text,"+"c3yzpy text,"+"c3zzpy text,"
            +"c3xzxz text,"+"c3yzxz text,"+"c3zzxz text,"+"c3bl text,"+"c2zyzwx text,"+"c2djcs text,"+"c1cbz text,"+"c1plds text,"+"c1code text)";

    public static final String CREATE_GEONEWPOSITION="create table Geonewposition("+"id integer primary key autoincrement,"+"gposition text)";
    public static final String CREATE_GEONEWPPPOSITION="create table Geonewppposition("+"id integer primary key autoincrement,"+"gposition text)";
    public static final String CREATE_GEONEWPROJECT="create table Geonewproject("+"id integer primary key autoincrement,"+"gpname text,"+"layercolor text,"+"width text,"+"exportpath text,"+"name text,"+"gsname text,"
            +"pnum text,"+"c3xzpy text,"+"c3yzpy text,"+"c3zzpy text,"+"c3xzxz text,"+"c3yzxz text,"+"c3zzxz text,"+"c3bl text,"+"c2zyzwx text,"+"c2djcs text,"+"c1cbz text,"+"c1plds text,"+"c1code text,"+"timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,"+"gposition text,"+"gdescription text)";

    private Context mcontext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GEOPOINTS0);
        db.execSQL(CREATE_GEOPOINTS1);
        db.execSQL(CREATE_GEOPOINTS2);
        db.execSQL(CREATE_GEOPOINTS3);
        db.execSQL(CREATE_GEOPOINTS4);
        db.execSQL(CREATE_GEOPOINTS5);
        db.execSQL(CREATE_GEOPOINTS6);
        db.execSQL(CREATE_GEOPOINTS7);
        db.execSQL(CREATE_GEOPOINTS8);
        db.execSQL(CREATE_GEOPOINTS9);
        db.execSQL(CREATE_GEOPOINTS10);
        db.execSQL(CREATE_GEOPOINTS11);
        db.execSQL(CREATE_GEOPOINTS12);
        db.execSQL(CREATE_GEOPOINTS13);
        db.execSQL(CREATE_GEOPOINTS14);
        db.execSQL(CREATE_GEOPOINTS15);
        db.execSQL(CREATE_GEOPOINTS16);
        db.execSQL(CREATE_GEOPOINTS17);
        db.execSQL(CREATE_GEOPOINTS18);
        db.execSQL(CREATE_GEOPOINTS19);
        db.execSQL(CREATE_GEOPOINTS20);

        db.execSQL(CREATE_GEODCYXPOINTS0);
        db.execSQL(CREATE_GEODCYXPOINTS1);
        db.execSQL(CREATE_GEODCYXPOINTS2);
        db.execSQL(CREATE_GEODCYXPOINTS3);
        db.execSQL(CREATE_GEODCYXPOINTS4);
        db.execSQL(CREATE_GEODCYXPOINTS5);
        db.execSQL(CREATE_GEODCYXPOINTS6);
        db.execSQL(CREATE_GEODCYXPOINTS7);
        db.execSQL(CREATE_GEODCYXPOINTS8);
        db.execSQL(CREATE_GEODCYXPOINTS9);
        db.execSQL(CREATE_GEODCYXPOINTS10);
        db.execSQL(CREATE_GEODCYXPOINTS11);
        db.execSQL(CREATE_GEODCYXPOINTS12);
        db.execSQL(CREATE_GEODCYXPOINTS13);
        db.execSQL(CREATE_GEODCYXPOINTS14);
        db.execSQL(CREATE_GEODCYXPOINTS15);
        db.execSQL(CREATE_GEODCYXPOINTS16);
        db.execSQL(CREATE_GEODCYXPOINTS17);
        db.execSQL(CREATE_GEODCYXPOINTS18);
        db.execSQL(CREATE_GEODCYXPOINTS19);
        db.execSQL(CREATE_GEODCYXPOINTS20);


        db.execSQL(CREATE_GEOSWDZPOINTS0);
        db.execSQL(CREATE_GEOSWDZPOINTS1);
        db.execSQL(CREATE_GEOSWDZPOINTS2);
        db.execSQL(CREATE_GEOSWDZPOINTS3);
        db.execSQL(CREATE_GEOSWDZPOINTS4);
        db.execSQL(CREATE_GEOSWDZPOINTS5);
        db.execSQL(CREATE_GEOSWDZPOINTS6);
        db.execSQL(CREATE_GEOSWDZPOINTS7);
        db.execSQL(CREATE_GEOSWDZPOINTS8);
        db.execSQL(CREATE_GEOSWDZPOINTS9);
        db.execSQL(CREATE_GEOSWDZPOINTS10);
        db.execSQL(CREATE_GEOSWDZPOINTS11);
        db.execSQL(CREATE_GEOSWDZPOINTS12);
        db.execSQL(CREATE_GEOSWDZPOINTS13);
        db.execSQL(CREATE_GEOSWDZPOINTS14);
        db.execSQL(CREATE_GEOSWDZPOINTS15);
        db.execSQL(CREATE_GEOSWDZPOINTS16);
        db.execSQL(CREATE_GEOSWDZPOINTS17);
        db.execSQL(CREATE_GEOSWDZPOINTS18);
        db.execSQL(CREATE_GEOSWDZPOINTS19);
        db.execSQL(CREATE_GEOSWDZPOINTS20);


        db.execSQL(CREATE_GEOGZWDPOINTS0);
        db.execSQL(CREATE_GEOGZWDPOINTS1);
        db.execSQL(CREATE_GEOGZWDPOINTS2);
        db.execSQL(CREATE_GEOGZWDPOINTS3);
        db.execSQL(CREATE_GEOGZWDPOINTS4);
        db.execSQL(CREATE_GEOGZWDPOINTS5);
        db.execSQL(CREATE_GEOGZWDPOINTS6);
        db.execSQL(CREATE_GEOGZWDPOINTS7);
        db.execSQL(CREATE_GEOGZWDPOINTS8);
        db.execSQL(CREATE_GEOGZWDPOINTS9);
        db.execSQL(CREATE_GEOGZWDPOINTS10);
        db.execSQL(CREATE_GEOGZWDPOINTS11);
        db.execSQL(CREATE_GEOGZWDPOINTS12);
        db.execSQL(CREATE_GEOGZWDPOINTS13);
        db.execSQL(CREATE_GEOGZWDPOINTS14);
        db.execSQL(CREATE_GEOGZWDPOINTS15);
        db.execSQL(CREATE_GEOGZWDPOINTS16);
        db.execSQL(CREATE_GEOGZWDPOINTS17);
        db.execSQL(CREATE_GEOGZWDPOINTS18);
        db.execSQL(CREATE_GEOGZWDPOINTS19);
        db.execSQL(CREATE_GEOGZWDPOINTS20);


        db.execSQL(CREATE_GEOPHOTOPOINTS0);
        db.execSQL(CREATE_GEOPHOTOPOINTS1);
        db.execSQL(CREATE_GEOPHOTOPOINTS2);
        db.execSQL(CREATE_GEOPHOTOPOINTS3);
        db.execSQL(CREATE_GEOPHOTOPOINTS4);
        db.execSQL(CREATE_GEOPHOTOPOINTS5);
        db.execSQL(CREATE_GEOPHOTOPOINTS6);
        db.execSQL(CREATE_GEOPHOTOPOINTS7);
        db.execSQL(CREATE_GEOPHOTOPOINTS8);
        db.execSQL(CREATE_GEOPHOTOPOINTS9);
        db.execSQL(CREATE_GEOPHOTOPOINTS10);
        db.execSQL(CREATE_GEOPHOTOPOINTS11);
        db.execSQL(CREATE_GEOPHOTOPOINTS12);
        db.execSQL(CREATE_GEOPHOTOPOINTS13);
        db.execSQL(CREATE_GEOPHOTOPOINTS14);
        db.execSQL(CREATE_GEOPHOTOPOINTS15);
        db.execSQL(CREATE_GEOPHOTOPOINTS16);
        db.execSQL(CREATE_GEOPHOTOPOINTS17);
        db.execSQL(CREATE_GEOPHOTOPOINTS18);
        db.execSQL(CREATE_GEOPHOTOPOINTS19);
        db.execSQL(CREATE_GEOPHOTOPOINTS20);



        db.execSQL(CREATE_GEOPOINTEXCEL0);
        db.execSQL(CREATE_GEOPOINTEXCEL1);
        db.execSQL(CREATE_GEOPOINTEXCEL2);
        db.execSQL(CREATE_GEOPOINTEXCEL3);
        db.execSQL(CREATE_GEOPOINTEXCEL4);
        db.execSQL(CREATE_GEOPOINTEXCEL5);
        db.execSQL(CREATE_GEOPOINTEXCEL6);
        db.execSQL(CREATE_GEOPOINTEXCEL7);
        db.execSQL(CREATE_GEOPOINTEXCEL8);
        db.execSQL(CREATE_GEOPOINTEXCEL9);
        db.execSQL(CREATE_GEOPOINTEXCEL10);
        db.execSQL(CREATE_GEOPOINTEXCEL11);
        db.execSQL(CREATE_GEOPOINTEXCEL12);
        db.execSQL(CREATE_GEOPOINTEXCEL13);
        db.execSQL(CREATE_GEOPOINTEXCEL14);
        db.execSQL(CREATE_GEOPOINTEXCEL15);
        db.execSQL(CREATE_GEOPOINTEXCEL16);
        db.execSQL(CREATE_GEOPOINTEXCEL17);
        db.execSQL(CREATE_GEOPOINTEXCEL18);
        db.execSQL(CREATE_GEOPOINTEXCEL19);
        db.execSQL(CREATE_GEOPOINTEXCEL20);



        db.execSQL(CREATE_GEOMORELINES0);
        db.execSQL(CREATE_GEOMORELINES1);
        db.execSQL(CREATE_GEOMORELINES2);
        db.execSQL(CREATE_GEOMORELINES3);
        db.execSQL(CREATE_GEOMORELINES4);
        db.execSQL(CREATE_GEOMORELINES5);
        db.execSQL(CREATE_GEOMORELINES6);
        db.execSQL(CREATE_GEOMORELINES7);
        db.execSQL(CREATE_GEOMORELINES8);
        db.execSQL(CREATE_GEOMORELINES9);
        db.execSQL(CREATE_GEOMORELINES10);
        db.execSQL(CREATE_GEOMORELINES11);
        db.execSQL(CREATE_GEOMORELINES12);
        db.execSQL(CREATE_GEOMORELINES13);
        db.execSQL(CREATE_GEOMORELINES14);
        db.execSQL(CREATE_GEOMORELINES15);
        db.execSQL(CREATE_GEOMORELINES16);
        db.execSQL(CREATE_GEOMORELINES17);
        db.execSQL(CREATE_GEOMORELINES18);
        db.execSQL(CREATE_GEOMORELINES19);
        db.execSQL(CREATE_GEOMORELINES20);



        db.execSQL(CREATE_GEOMORELINEEXCEL0);
        db.execSQL(CREATE_GEOMORELINEEXCEL1);
        db.execSQL(CREATE_GEOMORELINEEXCEL2);
        db.execSQL(CREATE_GEOMORELINEEXCEL3);
        db.execSQL(CREATE_GEOMORELINEEXCEL4);
        db.execSQL(CREATE_GEOMORELINEEXCEL5);
        db.execSQL(CREATE_GEOMORELINEEXCEL6);
        db.execSQL(CREATE_GEOMORELINEEXCEL7);
        db.execSQL(CREATE_GEOMORELINEEXCEL8);
        db.execSQL(CREATE_GEOMORELINEEXCEL9);
        db.execSQL(CREATE_GEOMORELINEEXCEL10);
        db.execSQL(CREATE_GEOMORELINEEXCEL11);
        db.execSQL(CREATE_GEOMORELINEEXCEL12);
        db.execSQL(CREATE_GEOMORELINEEXCEL13);
        db.execSQL(CREATE_GEOMORELINEEXCEL14);
        db.execSQL(CREATE_GEOMORELINEEXCEL15);
        db.execSQL(CREATE_GEOMORELINEEXCEL16);
        db.execSQL(CREATE_GEOMORELINEEXCEL17);
        db.execSQL(CREATE_GEOMORELINEEXCEL18);
        db.execSQL(CREATE_GEOMORELINEEXCEL19);
        db.execSQL(CREATE_GEOMORELINEEXCEL20);



        db.execSQL(CREATE_GEOSURFACE0);
        db.execSQL(CREATE_GEOSURFACE1);
        db.execSQL(CREATE_GEOSURFACE2);
        db.execSQL(CREATE_GEOSURFACE3);
        db.execSQL(CREATE_GEOSURFACE4);
        db.execSQL(CREATE_GEOSURFACE5);
        db.execSQL(CREATE_GEOSURFACE6);
        db.execSQL(CREATE_GEOSURFACE7);
        db.execSQL(CREATE_GEOSURFACE8);
        db.execSQL(CREATE_GEOSURFACE9);
        db.execSQL(CREATE_GEOSURFACE10);
        db.execSQL(CREATE_GEOSURFACE11);
        db.execSQL(CREATE_GEOSURFACE12);
        db.execSQL(CREATE_GEOSURFACE13);
        db.execSQL(CREATE_GEOSURFACE14);
        db.execSQL(CREATE_GEOSURFACE15);
        db.execSQL(CREATE_GEOSURFACE16);
        db.execSQL(CREATE_GEOSURFACE17);
        db.execSQL(CREATE_GEOSURFACE18);
        db.execSQL(CREATE_GEOSURFACE19);
        db.execSQL(CREATE_GEOSURFACE20);



        db.execSQL(CREATE_GEOSURFACEEXCEL0);
        db.execSQL(CREATE_GEOSURFACEEXCEL1);
        db.execSQL(CREATE_GEOSURFACEEXCEL2);
        db.execSQL(CREATE_GEOSURFACEEXCEL3);
        db.execSQL(CREATE_GEOSURFACEEXCEL4);
        db.execSQL(CREATE_GEOSURFACEEXCEL5);
        db.execSQL(CREATE_GEOSURFACEEXCEL6);
        db.execSQL(CREATE_GEOSURFACEEXCEL7);
        db.execSQL(CREATE_GEOSURFACEEXCEL8);
        db.execSQL(CREATE_GEOSURFACEEXCEL9);
        db.execSQL(CREATE_GEOSURFACEEXCEL10);
        db.execSQL(CREATE_GEOSURFACEEXCEL11);
        db.execSQL(CREATE_GEOSURFACEEXCEL12);
        db.execSQL(CREATE_GEOSURFACEEXCEL13);
        db.execSQL(CREATE_GEOSURFACEEXCEL14);
        db.execSQL(CREATE_GEOSURFACEEXCEL15);
        db.execSQL(CREATE_GEOSURFACEEXCEL16);
        db.execSQL(CREATE_GEOSURFACEEXCEL17);
        db.execSQL(CREATE_GEOSURFACEEXCEL18);
        db.execSQL(CREATE_GEOSURFACEEXCEL19);
        db.execSQL(CREATE_GEOSURFACEEXCEL20);




        db.execSQL(CREATE_NEWPOINTS0);
        db.execSQL(CREATE_NEWPOINTS1);
        db.execSQL(CREATE_NEWPOINTS2);
        db.execSQL(CREATE_NEWPOINTS3);
        db.execSQL(CREATE_NEWPOINTS4);
        db.execSQL(CREATE_NEWPOINTS5);
        db.execSQL(CREATE_NEWPOINTS6);
        db.execSQL(CREATE_NEWPOINTS7);
        db.execSQL(CREATE_NEWPOINTS8);
        db.execSQL(CREATE_NEWPOINTS9);
        db.execSQL(CREATE_NEWPOINTS10);
        db.execSQL(CREATE_NEWPOINTS11);
        db.execSQL(CREATE_NEWPOINTS12);
        db.execSQL(CREATE_NEWPOINTS13);
        db.execSQL(CREATE_NEWPOINTS14);
        db.execSQL(CREATE_NEWPOINTS15);
        db.execSQL(CREATE_NEWPOINTS16);
        db.execSQL(CREATE_NEWPOINTS17);
        db.execSQL(CREATE_NEWPOINTS18);
        db.execSQL(CREATE_NEWPOINTS19);
        db.execSQL(CREATE_NEWPOINTS20);

        db.execSQL(CREATE_PHOTOPOINTS0);
        db.execSQL(CREATE_PHOTOPOINTS1);
        db.execSQL(CREATE_PHOTOPOINTS2);
        db.execSQL(CREATE_PHOTOPOINTS3);
        db.execSQL(CREATE_PHOTOPOINTS4);
        db.execSQL(CREATE_PHOTOPOINTS5);
        db.execSQL(CREATE_PHOTOPOINTS6);
        db.execSQL(CREATE_PHOTOPOINTS7);
        db.execSQL(CREATE_PHOTOPOINTS8);
        db.execSQL(CREATE_PHOTOPOINTS9);
        db.execSQL(CREATE_PHOTOPOINTS10);
        db.execSQL(CREATE_PHOTOPOINTS11);
        db.execSQL(CREATE_PHOTOPOINTS12);
        db.execSQL(CREATE_PHOTOPOINTS13);
        db.execSQL(CREATE_PHOTOPOINTS14);
        db.execSQL(CREATE_PHOTOPOINTS15);
        db.execSQL(CREATE_PHOTOPOINTS16);
        db.execSQL(CREATE_PHOTOPOINTS17);
        db.execSQL(CREATE_PHOTOPOINTS18);
        db.execSQL(CREATE_PHOTOPOINTS19);
        db.execSQL(CREATE_PHOTOPOINTS20);

        db.execSQL(CREATE_POINTEXCEL0);
        db.execSQL(CREATE_POINTEXCEL1);
        db.execSQL(CREATE_POINTEXCEL2);
        db.execSQL(CREATE_POINTEXCEL3);
        db.execSQL(CREATE_POINTEXCEL4);
        db.execSQL(CREATE_POINTEXCEL5);
        db.execSQL(CREATE_POINTEXCEL6);
        db.execSQL(CREATE_POINTEXCEL7);
        db.execSQL(CREATE_POINTEXCEL8);
        db.execSQL(CREATE_POINTEXCEL9);
        db.execSQL(CREATE_POINTEXCEL10);
        db.execSQL(CREATE_POINTEXCEL11);
        db.execSQL(CREATE_POINTEXCEL12);
        db.execSQL(CREATE_POINTEXCEL13);
        db.execSQL(CREATE_POINTEXCEL14);
        db.execSQL(CREATE_POINTEXCEL15);
        db.execSQL(CREATE_POINTEXCEL16);
        db.execSQL(CREATE_POINTEXCEL17);
        db.execSQL(CREATE_POINTEXCEL18);
        db.execSQL(CREATE_POINTEXCEL19);
        db.execSQL(CREATE_POINTEXCEL20);

        db.execSQL(CREATE_NEWLINES0);
        db.execSQL(CREATE_NEWLINES1);
        db.execSQL(CREATE_NEWLINES2);
        db.execSQL(CREATE_NEWLINES3);
        db.execSQL(CREATE_NEWLINES4);
        db.execSQL(CREATE_NEWLINES5);
        db.execSQL(CREATE_NEWLINES6);
        db.execSQL(CREATE_NEWLINES7);
        db.execSQL(CREATE_NEWLINES8);
        db.execSQL(CREATE_NEWLINES9);
        db.execSQL(CREATE_NEWLINES10);
        db.execSQL(CREATE_NEWLINES11);
        db.execSQL(CREATE_NEWLINES12);
        db.execSQL(CREATE_NEWLINES13);
        db.execSQL(CREATE_NEWLINES14);
        db.execSQL(CREATE_NEWLINES15);
        db.execSQL(CREATE_NEWLINES16);
        db.execSQL(CREATE_NEWLINES17);
        db.execSQL(CREATE_NEWLINES18);
        db.execSQL(CREATE_NEWLINES19);
        db.execSQL(CREATE_NEWLINES20);


        db.execSQL(CREATE_LINEEXCEL0);
        db.execSQL(CREATE_LINEEXCEL1);
        db.execSQL(CREATE_LINEEXCEL2);
        db.execSQL(CREATE_LINEEXCEL3);
        db.execSQL(CREATE_LINEEXCEL4);
        db.execSQL(CREATE_LINEEXCEL5);
        db.execSQL(CREATE_LINEEXCEL6);
        db.execSQL(CREATE_LINEEXCEL7);
        db.execSQL(CREATE_LINEEXCEL8);
        db.execSQL(CREATE_LINEEXCEL9);
        db.execSQL(CREATE_LINEEXCEL10);
        db.execSQL(CREATE_LINEEXCEL11);
        db.execSQL(CREATE_LINEEXCEL12);
        db.execSQL(CREATE_LINEEXCEL13);
        db.execSQL(CREATE_LINEEXCEL14);
        db.execSQL(CREATE_LINEEXCEL15);
        db.execSQL(CREATE_LINEEXCEL16);
        db.execSQL(CREATE_LINEEXCEL17);
        db.execSQL(CREATE_LINEEXCEL18);
        db.execSQL(CREATE_LINEEXCEL19);
        db.execSQL(CREATE_LINEEXCEL20);


        db.execSQL(CREATE_NEWTIMES);
        db.execSQL(CREATE_NEWPOSITION);
        db.execSQL(CREATE_NEWPPPOSITION);
        db.execSQL(CREATE_NEWPROJECT);


        db.execSQL(CREATE_GEONEWPOSITION);
        db.execSQL(CREATE_GEONEWPPPOSITION);
        db.execSQL(CREATE_GEONEWPROJECT);

        //Toast.makeText(mcontext,"Create succeede",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Geodxdmpoints0");
        db.execSQL("drop table if exists Geodxdmpoints1");
        db.execSQL("drop table if exists Geodxdmpoints2");
        db.execSQL("drop table if exists Geodxdmpoints3");
        db.execSQL("drop table if exists Geodxdmpoints4");
        db.execSQL("drop table if exists Geodxdmpoints5");
        db.execSQL("drop table if exists Geodxdmpoints6");
        db.execSQL("drop table if exists Geodxdmpoints7");
        db.execSQL("drop table if exists Geodxdmpoints8");
        db.execSQL("drop table if exists Geodxdmpoints9");
        db.execSQL("drop table if exists Geodxdmpoints10");
        db.execSQL("drop table if exists Geodxdmpoints11");
        db.execSQL("drop table if exists Geodxdmpoints12");
        db.execSQL("drop table if exists Geodxdmpoints13");
        db.execSQL("drop table if exists Geodxdmpoints14");
        db.execSQL("drop table if exists Geodxdmpoints15");
        db.execSQL("drop table if exists Geodxdmpoints16");
        db.execSQL("drop table if exists Geodxdmpoints17");
        db.execSQL("drop table if exists Geodxdmpoints18");
        db.execSQL("drop table if exists Geodxdmpoints19");
        db.execSQL("drop table if exists Geodxdmpoints20");

        db.execSQL("drop table if exists Geodcyxpoints0");
        db.execSQL("drop table if exists Geodcyxpoints1");
        db.execSQL("drop table if exists Geodcyxpoints2");
        db.execSQL("drop table if exists Geodcyxpoints3");
        db.execSQL("drop table if exists Geodcyxpoints4");
        db.execSQL("drop table if exists Geodcyxpoints5");
        db.execSQL("drop table if exists Geodcyxpoints6");
        db.execSQL("drop table if exists Geodcyxpoints7");
        db.execSQL("drop table if exists Geodcyxpoints8");
        db.execSQL("drop table if exists Geodcyxpoints9");
        db.execSQL("drop table if exists Geodcyxpoints10");
        db.execSQL("drop table if exists Geodcyxpoints11");
        db.execSQL("drop table if exists Geodcyxpoints12");
        db.execSQL("drop table if exists Geodcyxpoints13");
        db.execSQL("drop table if exists Geodcyxpoints14");
        db.execSQL("drop table if exists Geodcyxpoints15");
        db.execSQL("drop table if exists Geodcyxpoints16");
        db.execSQL("drop table if exists Geodcyxpoints17");
        db.execSQL("drop table if exists Geodcyxpoints18");
        db.execSQL("drop table if exists Geodcyxpoints19");
        db.execSQL("drop table if exists Geodcyxpoints20");


        db.execSQL("drop table if exists Geoswdzpoints0");
        db.execSQL("drop table if exists Geoswdzpoints1");
        db.execSQL("drop table if exists Geoswdzpoints2");
        db.execSQL("drop table if exists Geoswdzpoints3");
        db.execSQL("drop table if exists Geoswdzpoints4");
        db.execSQL("drop table if exists Geoswdzpoints5");
        db.execSQL("drop table if exists Geoswdzpoints6");
        db.execSQL("drop table if exists Geoswdzpoints7");
        db.execSQL("drop table if exists Geoswdzpoints8");
        db.execSQL("drop table if exists Geoswdzpoints9");
        db.execSQL("drop table if exists Geoswdzpoints10");
        db.execSQL("drop table if exists Geoswdzpoints11");
        db.execSQL("drop table if exists Geoswdzpoints12");
        db.execSQL("drop table if exists Geoswdzpoints13");
        db.execSQL("drop table if exists Geoswdzpoints14");
        db.execSQL("drop table if exists Geoswdzpoints15");
        db.execSQL("drop table if exists Geoswdzpoints16");
        db.execSQL("drop table if exists Geoswdzpoints17");
        db.execSQL("drop table if exists Geoswdzpoints18");
        db.execSQL("drop table if exists Geoswdzpoints19");
        db.execSQL("drop table if exists Geoswdzpoints20");


        db.execSQL("drop table if exists Photopoints0");
        db.execSQL("drop table if exists Photopoints1");
        db.execSQL("drop table if exists Photopoints2");
        db.execSQL("drop table if exists Photopoints3");
        db.execSQL("drop table if exists Photopoints4");
        db.execSQL("drop table if exists Photopoints5");
        db.execSQL("drop table if exists Photopoints6");
        db.execSQL("drop table if exists Photopoints7");
        db.execSQL("drop table if exists Photopoints8");
        db.execSQL("drop table if exists Photopoints9");
        db.execSQL("drop table if exists Photopoints10");
        db.execSQL("drop table if exists Photopoints11");
        db.execSQL("drop table if exists Photopoints12");
        db.execSQL("drop table if exists Photopoints13");
        db.execSQL("drop table if exists Photopoints14");
        db.execSQL("drop table if exists Photopoints15");
        db.execSQL("drop table if exists Photopoints16");
        db.execSQL("drop table if exists Photopoints17");
        db.execSQL("drop table if exists Photopoints18");
        db.execSQL("drop table if exists Photopoints19");
        db.execSQL("drop table if exists Photopoints20");

        db.execSQL("drop table if exists Pointexcel0");
        db.execSQL("drop table if exists Pointexcel1");
        db.execSQL("drop table if exists Pointexcel2");
        db.execSQL("drop table if exists Pointexcel3");
        db.execSQL("drop table if exists Pointexcel4");
        db.execSQL("drop table if exists Pointexcel5");
        db.execSQL("drop table if exists Pointexcel6");
        db.execSQL("drop table if exists Pointexcel7");
        db.execSQL("drop table if exists Pointexcel8");
        db.execSQL("drop table if exists Pointexcel9");
        db.execSQL("drop table if exists Pointexcel10");
        db.execSQL("drop table if exists Pointexcel11");
        db.execSQL("drop table if exists Pointexcel12");
        db.execSQL("drop table if exists Pointexcel13");
        db.execSQL("drop table if exists Pointexcel14");
        db.execSQL("drop table if exists Pointexcel15");
        db.execSQL("drop table if exists Pointexcel16");
        db.execSQL("drop table if exists Pointexcel17");
        db.execSQL("drop table if exists Pointexcel18");
        db.execSQL("drop table if exists Pointexcel19");
        db.execSQL("drop table if exists Pointexcel20");

        db.execSQL("drop table if exists Newlines0");
        db.execSQL("drop table if exists Newlines1");
        db.execSQL("drop table if exists Newlines2");
        db.execSQL("drop table if exists Newlines3");
        db.execSQL("drop table if exists Newlines4");
        db.execSQL("drop table if exists Newlines5");
        db.execSQL("drop table if exists Newlines6");
        db.execSQL("drop table if exists Newlines7");
        db.execSQL("drop table if exists Newlines8");
        db.execSQL("drop table if exists Newlines9");
        db.execSQL("drop table if exists Newlines10");
        db.execSQL("drop table if exists Newlines11");
        db.execSQL("drop table if exists Newlines12");
        db.execSQL("drop table if exists Newlines13");
        db.execSQL("drop table if exists Newlines14");
        db.execSQL("drop table if exists Newlines15");
        db.execSQL("drop table if exists Newlines16");
        db.execSQL("drop table if exists Newlines17");
        db.execSQL("drop table if exists Newlines18");
        db.execSQL("drop table if exists Newlines19");
        db.execSQL("drop table if exists Newlines20");


        db.execSQL("drop table if exists Lineexcel0");
        db.execSQL("drop table if exists Lineexcel1");
        db.execSQL("drop table if exists Lineexcel2");
        db.execSQL("drop table if exists Lineexcel3");
        db.execSQL("drop table if exists Lineexcel4");
        db.execSQL("drop table if exists Lineexcel5");
        db.execSQL("drop table if exists Lineexcel6");
        db.execSQL("drop table if exists Lineexcel7");
        db.execSQL("drop table if exists Lineexcel8");
        db.execSQL("drop table if exists Lineexcel9");
        db.execSQL("drop table if exists Lineexcel10");
        db.execSQL("drop table if exists Lineexcel11");
        db.execSQL("drop table if exists Lineexcel12");
        db.execSQL("drop table if exists Lineexcel13");
        db.execSQL("drop table if exists Lineexcel14");
        db.execSQL("drop table if exists Lineexcel15");
        db.execSQL("drop table if exists Lineexcel16");
        db.execSQL("drop table if exists Lineexcel17");
        db.execSQL("drop table if exists Lineexcel18");
        db.execSQL("drop table if exists Lineexcel19");
        db.execSQL("drop table if exists Lineexcel20");


        db.execSQL("drop table if exists Newtimes");
        db.execSQL("drop table if exists Newposition");
        db.execSQL("drop table if exists Newppposition");
        db.execSQL("drop table if exists Newproject");
        onCreate(db);
    }
}