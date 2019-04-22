package com.arcgis.mymap.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.LitepalPoints;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */
public class RightlistAdapter extends BaseAdapter{
    private List<LitepalPoints> pointsList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;

    public RightlistAdapter(List<LitepalPoints> pointsList, int resourse, Context context) {
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
        ViewHolder holder;
        LitepalPoints points=pointsList.get(position);
        if (convertView==null){
            convertView=View.inflate(context,resourse,null);
            holder=new ViewHolder();
            holder.tvname= (TextView) convertView.findViewById(R.id.tv_table_content_right_item0);
            holder.tvla= (TextView) convertView.findViewById(R.id.tv_table_content_right_item1);
            holder.tvln= (TextView) convertView.findViewById(R.id.tv_table_content_right_item2);
            holder.tvhigh= (TextView) convertView.findViewById(R.id.tv_table_content_right_item3);
            holder.classification= (TextView) convertView.findViewById(R.id.tv_table_content_right_item4);
            holder.datetime= (TextView) convertView.findViewById(R.id.tv_table_content_right_item5);
            holder.description= (TextView) convertView.findViewById(R.id.tv_table_content_right_item6);
            holder.code= (TextView) convertView.findViewById(R.id.tv_table_content_right_item7);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(points.getName());
        holder.tvla.setText(points.getLa());
        holder.tvln.setText(points.getLn());
        holder.tvhigh.setText(points.getHigh());
        holder.classification.setText(points.getClassification());
        holder.datetime.setText(points.getDatetime());
        holder.description.setText(points.getDescription());
        holder.code.setText(points.getCode());
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
        TextView tvla;
        TextView tvln;
        TextView tvhigh;
        TextView description;
        TextView classification;
        TextView datetime;
        TextView code;
    }
    public static  class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectItem=-1;
        }
    }
}
