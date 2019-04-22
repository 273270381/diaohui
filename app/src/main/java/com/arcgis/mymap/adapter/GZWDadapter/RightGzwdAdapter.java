package com.arcgis.mymap.adapter.GZWDadapter;

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
import com.arcgis.mymap.contacts.GouzhuwuPoint;

import java.util.List;

public class RightGzwdAdapter extends BaseAdapter{
    private List<GouzhuwuPoint> pointsList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;

    public RightGzwdAdapter(List<GouzhuwuPoint> pointsList, int resourse, Context context) {
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
        GouzhuwuPoint points = pointsList.get(position);
        if (convertView==null){
            convertView=View.inflate(context,resourse,null);
            holder=new ViewHolder();
            holder.tvname= (TextView) convertView.findViewById(R.id.tv_table_content_right_item0);
            holder.classification= (TextView) convertView.findViewById(R.id.tv_table_content_right_item1);
            holder.description= (TextView) convertView.findViewById(R.id.tv_table_content_right_item2);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(points.getName());
        holder.classification.setText(points.getClassification());
        holder.description.setText(points.getDescription());
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
        TextView classification;
        TextView description;
    }
    public static  class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectItem=-1;
        }
    }
}
