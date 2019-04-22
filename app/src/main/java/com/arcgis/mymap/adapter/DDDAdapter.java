package com.arcgis.mymap.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.LitepalPoints;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19.
 */

public class DDDAdapter extends BaseAdapter {
    private List<LitepalPoints> list;
    private Context context;
    private int resourse;
    public static int  selectItem=-1;

    public DDDAdapter(List<LitepalPoints> list, Context context, int resourse) {
        this.list = list;
        this.context = context;
        this.resourse = resourse;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LitepalPoints points=list.get(position);
        if (convertView == null){
            convertView=View.inflate(context,resourse,null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.text1);
            holder.des = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(points.getName());
        holder.des.setText(points.getDescription());
        if (position==selectItem){
            convertView.setBackgroundColor(Color.YELLOW);
        }else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }
    public static class ViewHolder{
        TextView name;
        TextView des;

        public ViewHolder() {
        }
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
}
