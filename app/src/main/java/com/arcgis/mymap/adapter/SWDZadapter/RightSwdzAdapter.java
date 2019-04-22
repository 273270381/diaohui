package com.arcgis.mymap.adapter.SWDZadapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DXDMadapter.RightDxdmAdapter;
import com.arcgis.mymap.contacts.DixingdimaoPoint;
import com.arcgis.mymap.contacts.ShuiwendizhiPoint;

import java.util.List;

public class RightSwdzAdapter extends BaseAdapter{
    private List<ShuiwendizhiPoint> pointsList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;

    public RightSwdzAdapter(List<ShuiwendizhiPoint> pointsList, int resourse, Context context) {
        this.pointsList = pointsList;
        this.resourse = resourse;
        this.context = context;
    }


    public int getCount() {
        return pointsList.size();
    }


    public Object getItem(int position) {
        return pointsList.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ShuiwendizhiPoint points = pointsList.get(position);
        if (convertView==null){
            convertView=View.inflate(context,resourse,null);
            holder=new ViewHolder();
            holder.tvname= (TextView) convertView.findViewById(R.id.tv_table_content_right_item0);
            holder.sllx = (TextView) convertView.findViewById(R.id.tv_table_content_right_item1);
            holder.smkd = (TextView) convertView.findViewById(R.id.tv_table_content_right_item2);
            holder.ss = (TextView) convertView.findViewById(R.id.tv_table_content_right_item3);
            holder.ls = (TextView) convertView.findViewById(R.id.tv_table_content_right_item4);
            holder.ll = (TextView) convertView.findViewById(R.id.tv_table_content_right_item5);
            holder.sz = (TextView) convertView.findViewById(R.id.tv_table_content_right_item6);
            holder.ms = (TextView) convertView.findViewById(R.id.tv_table_content_right_item7);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(points.getName());
        holder.sllx.setText(points.getSllx());
        holder.smkd.setText(points.getSmkd());
        holder.ss.setText(points.getSs());
        holder.ls.setText(points.getLs());
        holder.ll.setText(points.getLl());
        holder.sz.setText(points.getSz());
        holder.ms.setText(points.getDes());
        if (position==selectItem){
            convertView.setBackgroundColor(Color.YELLOW);
        }else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    static class ViewHolder{
        TextView tvname;
        TextView sllx;
        TextView smkd;
        TextView ss;
        TextView ls;
        TextView ll;
        TextView sz;
        TextView ms;
    }
    public static  class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectItem=-1;
        }
    }
}
