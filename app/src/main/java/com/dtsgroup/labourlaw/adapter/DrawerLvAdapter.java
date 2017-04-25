package com.dtsgroup.labourlaw.adapter;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.model.ItemLvDrawer;

import java.util.List;

public class DrawerLvAdapter extends BaseAdapter {

    private List<ItemLvDrawer> itemLvDrawerList;
    private LayoutInflater layoutInflater;
    private Context context;
    private int itemSelected = 0;

    public DrawerLvAdapter(Context context, List<ItemLvDrawer> lvDrawers){
        this.context = context;
        this.itemLvDrawerList = lvDrawers;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemLvDrawerList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemLvDrawerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item_drawer,null);
            holder = new ViewHolder();
            holder.desc = (TextView) convertView.findViewById(R.id.tv_descreption_item_drawer);
            holder.imgDes = (ImageView) convertView.findViewById(R.id.img_list_item_drawer);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == itemSelected){
            holder.desc.setTextColor(context.getResources().getColor(R.color.selected));
            holder.imgDes.setImageResource(itemLvDrawerList.get(position).getImgSelected());
        }else {
            holder.desc.setTextColor(context.getResources().getColor(R.color.un_selected));
            holder.imgDes.setImageResource(itemLvDrawerList.get(position).getImg());
        }

        holder.desc.setText(itemLvDrawerList.get(position).getDescription());
        holder.desc.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.text_height_drawer));

        return convertView;
    }
    public void setItemSelected(int position){
        this.itemSelected = position;
    }

    static class ViewHolder {
        ImageView imgDes;
        TextView desc;
    }
}
