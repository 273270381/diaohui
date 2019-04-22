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

public class LeftDcyxAdapter extends BaseAdapter {
    private List<DicengyanxingPoint> pointsList;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;

    public LeftDcyxAdapter(List<DicengyanxingPoint> pointsList, int resourse, Context context) {
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
        final ViewHolder holder;
        DicengyanxingPoint points = pointsList.get(position);
        if (convertView==null){
            convertView = View.inflate(context,resourse,null);
            holder=new ViewHolder();
            holder.tvnum= (TextView) convertView.findViewById(R.id.tv_table_content_item_left);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvnum.setText(String.valueOf(points.getId()));
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
