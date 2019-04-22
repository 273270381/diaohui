package com.arcgis.mymap.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.LitepalPoints;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/2/3.
 */
public class ExportAdatper extends BaseAdapter {
    // 填充数据的list
    private List<LitepalPoints> list;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer,Boolean> isSelected;
    // 上下文
    private Context context;
    // 用来导入布局
    private int resourse;

    // 构造器
    public ExportAdatper(List<LitepalPoints> list,int resourse, Context context) {
        this.context = context;
        this.list = list;
        this.resourse = resourse;
        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate(){
        for(int i=0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }
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
        Viewholder holder;
        LitepalPoints points=list.get(position);
        if (convertView==null){
            convertView=View.inflate(context,resourse,null);
            holder=new Viewholder();
            holder.dianming= (TextView) convertView.findViewById(R.id.dian);
            holder.leibie= (TextView) convertView.findViewById(R.id.lei);
            holder.datetime= (TextView) convertView.findViewById(R.id.date);
            holder.miaoshu= (TextView) convertView.findViewById(R.id.miao);
            holder.checkBox= (CheckBox) convertView.findViewById(R.id.item_cb);
            convertView.setTag(holder);
        }else {
            holder= (Viewholder) convertView.getTag();
        }
            holder.dianming.setText(points.getName());
            holder.leibie.setText(points.getClassification());
            holder.datetime.setText(points.getDatetime());
            holder.miaoshu.setText(points.getDescription());
            holder.checkBox.setChecked(getIsSelected().get(position));
        return convertView;
    }
    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {
        ExportAdatper.isSelected = isSelected;
    }
    public static class Viewholder {
        TextView dianming;
        TextView leibie;
        TextView datetime;
        TextView miaoshu;
        CheckBox checkBox;
        public Viewholder() {
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
    }
}
