package com.arcgis.mymap.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.SurFace;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/5/1.
 */

public class GeoRightSurfaceAdapter extends BaseAdapter {
    private List<SurFace> linesList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;

    public GeoRightSurfaceAdapter(List<SurFace> linesList, int resourse, Context context) {
        this.linesList = linesList;
        this.resourse = resourse;
        this.context = context;
    }

    @Override
    public int getCount() {
        return linesList.size();
    }

    @Override
    public Object getItem(int position) {
        return linesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        SurFace surFace = linesList.get(position);
        if (convertView == null){
            convertView=View.inflate(context,resourse,null);
            holder = new GeoRightSurfaceAdapter.ViewHolder();
            holder.name= (TextView) convertView.findViewById(R.id.tv_table_content_right_item1);
            holder.classification= (TextView) convertView.findViewById(R.id.tv_table_content_right_item2);
            //holder.datetime= (TextView) convertView.findViewById(R.id.tv_table_content_right_item6);
            //holder.gla = (TextView) convertView.findViewById(R.id.tv_table_content_right_item2);
            //holder.gln = (TextView) convertView.findViewById(R.id.tv_table_content_right_item3);
            holder.description= (TextView) convertView.findViewById(R.id.tv_table_content_right_item3);
            //holder.code= (TextView) convertView.findViewById(R.id.tv_table_content_right_item8);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(surFace.getName());
        holder.classification.setText(surFace.getClassification());
        //holder.datetime.setText(surFace.getDatatime());
        holder.description.setText(surFace.getDescription());
        //holder.code.setText(surFace.getCode());
        //holder.gla.setText(StringUtils.join(surFace.getListla(),","));
        //holder.gln.setText(StringUtils.join(surFace.getListln(),","));
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
        TextView name;
        TextView classification;
        //TextView gla;
        //TextView gln;
        //TextView datetime;
        TextView description;
        //TextView code;
    }
    public static  class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectItem=-1;
        }
    }
}
