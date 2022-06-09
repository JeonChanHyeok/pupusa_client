package com.example.servertest.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.servertest.R;

import java.util.ArrayList;

public class SelectMenuItemAdapter extends BaseAdapter {
    private ArrayList<SelectMenuItem> SelectMenuItemList = new ArrayList<>();

    public SelectMenuItemAdapter(){}

    @Override
    public int getCount() {
        return SelectMenuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return SelectMenuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.select_menu_item, parent, false);
        }

        ImageView menuImageView = (ImageView) convertView.findViewById(R.id.iv_select_menu_item_menu_img);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.tv_select_menu_item_price);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.tv_select_menu_item_content);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.tv_select_menu_item_menu_name);

        SelectMenuItem service_item = SelectMenuItemList.get(position);

        menuImageView.setImageDrawable(service_item.getMenuImg());
        priceTextView.setText(String.valueOf(service_item.getMenuPrice()));
        contentTextView.setText(service_item.getMenuContent());
        nameTextView.setText(service_item.getMenuName());

        return convertView;
    }

    //item에 데이터 추가
    public void addItem(Drawable menuImg, String content, String menu, int price, Long menuId){
        SelectMenuItem item = new SelectMenuItem();

        item.setMenuImg(menuImg);
        item.setMenuContent(content);
        item.setMenuPrice(price);
        item.setMenuName(menu);
        item.setMenuId(menuId);
        SelectMenuItemList.add(item);
    }
}
