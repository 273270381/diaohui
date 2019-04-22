package com.arcgis.mymap.adapter.GZWDadapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DXDMadapter.ComBoxDxdmAdapter;
import com.arcgis.mymap.contacts.DixingdimaoPoint;
import com.arcgis.mymap.contacts.GouzhuwuPoint;

import java.util.List;

public class ComBoxGzwdAdapter extends BaseAdapter{
    private List<GouzhuwuPoint> pointsList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;
    public static boolean flag = false;
    public static boolean flage_click = false;

    public ComBoxGzwdAdapter(List<GouzhuwuPoint> pointsList, int resourse, Context context) {
        this.pointsList = pointsList;
        this.resourse = resourse;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pointsList.size();
    }

    @Override
    public Object getItem(int position) {
        return pointsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context,resourse,null);
            holder=new  ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.item_cb);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        if (position==selectItem){
            convertView.setBackgroundColor(Color.YELLOW);
            holder.checkBox.toggle();
        }else{
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        if (flag){
            holder.checkBox.setChecked(flage_click);
        }
        return convertView;
    }
    public static class ViewHolder{
        CheckBox checkBox;

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    public void setCheck(boolean f1,boolean f2){
        this.flag = f1;
        this.flage_click = f2;
    }
    public static  class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectItem=-1;
            flag = false;
        }
    }
}
