package com.arcgis.mymap.service;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by Administrator on 2017/12/29.
 */
public class LocationService {
    private LocationClient client=null;
    private LocationClientOption mOption,DIYoption;
    private Object objLock=new Object();

    public LocationService(Context  locationContext){
        synchronized (objLock){
            if(client == null) {
                client = new LocationClient(locationContext);
                client.setLocOption(getDefaultLocationClientOption());
            }
        }
    }


    public boolean registerListener(BDLocationListener listener){
    boolean isSuccess=false;
    if (listener!=null){
        client.registerLocationListener(listener);
        isSuccess=true;
    }
    return isSuccess;
    }

    public void unregisterListener(BDLocationListener listener){
        if (listener!=null){
            client.unRegisterLocationListener(listener);
        }
    }

    public boolean setLocationOption(LocationClientOption option){
        boolean isSuccess = false;
        if (option!=null){
            if (client.isStarted())
                client.stop();
            DIYoption=option;
            client.setLocOption(option);
        }
        return isSuccess;
    }

    public LocationClientOption getOption(){
        return DIYoption;
    }

    public LocationClientOption getDefaultLocationClientOption(){
        if (mOption==null){
            mOption=new LocationClientOption();
            mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            mOption.setOpenGps(true);
            mOption.setCoorType("bd09ll");
            mOption.setNeedDeviceDirect(true);
            mOption.setScanSpan(3000);
            mOption.setIsNeedAddress(true);
        }
        return mOption;
    }

    public void start(){
        synchronized (objLock){
            if (client!=null&&!client.isStarted()){
                client.start();
            }
        }
    }
    public void stop(){
        synchronized (objLock){
            if (client!=null&&client.isStarted()){
                client.stop();
            }
        }
    }
    public boolean requestHotSpotState(){
        return client.requestHotSpotState();
    }
}
