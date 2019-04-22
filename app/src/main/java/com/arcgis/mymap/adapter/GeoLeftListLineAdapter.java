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
import com.arcgis.mymap.contacts.Lines;
import com.arcgis.mymap.contacts.MoreLines;

import java.util.List;

/**
 * Created by Administrator on 2018/5/1.
 */

public class GeoLeftListLineAdapter extends BaseAdapter{
    private List<MoreLines> linesList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;


    public GeoLeftListLineAdapter(List<MoreLines> linesList, int resourse, Context context) {
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
        final ViewHolder holder;
        MoreLines lines = linesList.get(position);
        if (convertView==null){
            convertView = View.inflate(context,resourse,null);
            holder = new ViewHolder();
            holder.tvnum = (TextView) convertView.findViewById(R.id.tv_table_content_item_left);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvnum.setText(String.valueOf(lines.getId()));
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
        TextView tvnum;
    }
    public static  class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            selectItem=-1;
        }
    }
}
