package com.arcgis.mymap.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.arcgis.mymap.MainActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.LayoutAttributes;

import java.util.List;

public class LayoutlistAdapter extends BaseAdapter {
    private List<LayoutAttributes> list;
    private int sourse;
    private Context context;
    public static int  selectItem=-1;
    private AddlayerListener addlayerListener;
    private DellayerListener dellayerListener;

    public LayoutlistAdapter(List<LayoutAttributes> list, int sourse, Context context) {
        this.list = list;
        this.sourse = sourse;
        this.context = context;
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
        MyViewHoler holer;
        LayoutAttributes layoutAttributes = list.get(position);
        if (convertView == null){
            convertView = View.inflate(context,sourse,null);
            holer = new MyViewHoler();
            holer.textView = (TextView) convertView.findViewById(R.id.tv_layout_item);
            holer.button = (ImageButton) convertView.findViewById(R.id.addlayer);
            holer.button2 = (ImageButton) convertView.findViewById(R.id.dellayer);
            convertView.setTag(holer);
        }else{
            holer = (MyViewHoler) convertView.getTag();
        }
        holer.textView.setText(layoutAttributes.getName());
        holer.button.setVisibility(View.GONE);
        holer.button2.setVisibility(View.GONE);
        holer.button2.setTag(position);
        if (position == list.size()-1){
            holer.button.setVisibility(View.VISIBLE);
            holer.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addlayerListener.addLayer();
                }
            });
        }else {
            holer.button2.setVisibility(View.VISIBLE);
            holer.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dellayerListener.dellayer(v);
                }
            });
        }
        if (position == selectItem){
            if (holer.textView.getCurrentTextColor() == context.getResources().getColor(R.color.color18)){
                holer.textView.setBackgroundColor(context.getResources().getColor(R.color.color4));
                holer.textView.setTextColor(context.getResources().getColor(R.color.color22));
            }else {
                holer.textView.setBackgroundColor(context.getResources().getColor(R.color.color3));
                holer.textView.setTextColor(context.getResources().getColor(R.color.color18));
            }
        }
        return convertView;
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public interface AddlayerListener{
        void addLayer();
    }
    public interface DellayerListener{
        void dellayer(View v);
    }
    public void setAddlayerListener(AddlayerListener addlayerListener){
        this.addlayerListener = addlayerListener;
    }
    public void setDellayerListener(DellayerListener dellayerListener){
        this.dellayerListener = dellayerListener;
    }
    public static class MyViewHoler {
        TextView textView;
        ImageButton button;
        ImageButton button2;

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
