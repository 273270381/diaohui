package com.arcgis.mymap.adapter.DCYXadapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.DicengyanxingPoint;


import java.util.List;

public class RightDcyxAdapter extends BaseAdapter{
    private List<DicengyanxingPoint> pointsList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;

    public RightDcyxAdapter(List<DicengyanxingPoint> pointsList, int resourse, Context context) {
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
        DicengyanxingPoint points = pointsList.get(position);
        if (convertView==null){
            convertView=View.inflate(context,resourse,null);
            holder=new ViewHolder();
            holder.tvname= (TextView) convertView.findViewById(R.id.tv_table_content_right_item0);
            holder.dizhiniandai = (TextView) convertView.findViewById(R.id.tv_table_content_right_item1);
            holder.yantumingcheng = (TextView) convertView.findViewById(R.id.tv_table_content_right_item2);
            holder.chengyinleixing = (TextView) convertView.findViewById(R.id.tv_table_content_right_item3);
            holder.fenghuachengdu = (TextView) convertView.findViewById(R.id.tv_table_content_right_item4);
            holder.chanzhuang = (TextView) convertView.findViewById(R.id.tv_table_content_right_item5);
            holder.jieliliexi = (TextView) convertView.findViewById(R.id.tv_table_content_right_item6);
            holder.miaoshu = (TextView) convertView.findViewById(R.id.tv_table_content_right_item7);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(points.getName());
        holder.dizhiniandai.setText(points.getDznd());
        holder.yantumingcheng.setText(points.getYtmc());
        holder.chengyinleixing.setText(points.getClassification());
        holder.fenghuachengdu.setText(points.getFhcd());
        holder.chanzhuang.setText(points.getCz());
        holder.jieliliexi.setText(points.getJl());
        holder.miaoshu.setText(points.getDescription());
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
        TextView dizhiniandai;
        TextView yantumingcheng;
        TextView chengyinleixing;
        TextView fenghuachengdu;
        TextView chanzhuang;
        TextView jieliliexi;
        TextView miaoshu;
    }
    public static  class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectItem=-1;
        }
    }
}
