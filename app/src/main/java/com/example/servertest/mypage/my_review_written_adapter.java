package com.example.servertest.mypage;

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

public class my_review_written_adapter extends BaseAdapter {
    private ArrayList<my_review_written_item> my_review_written_item = new ArrayList<>();

    public my_review_written_adapter(){}

    @Override
    public int getCount() {
        return my_review_written_item.size();
    }

    @Override
    public Object getItem(int position) {
        return my_review_written_item.get(position);
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
            convertView = inflater.inflate(R.layout.my_review_written_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.icon);
        TextView storeTextView = (TextView) convertView.findViewById(R.id.store_name);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.date);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.content);
        TextView menu = (TextView) convertView.findViewById(R.id.menu);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

        my_review_written_item my_review_item = my_review_written_item.get(position);

        iconImageView.setImageDrawable(my_review_item.getIcon());
        storeTextView.setText(my_review_item.getStore_name());
        dateTextView.setText(my_review_item.getDate());
        contentTextView.setText(my_review_item.getContent());
        menu.setText(my_review_item.getMenu());
        imageView.setImageDrawable(my_review_item.getImage());

        return convertView;
    }

    public void addItem(Drawable icon, String store, String date, String content, String menu, Drawable image){
        my_review_written_item item = new my_review_written_item();
        item.setDate(date);
        item.setIcon(icon);
        item.setMenu(menu);
        item.setContent(content);
        item.setStore_name(store);
        item.setImage(image);
        my_review_written_item.add(item);
    }

    public void delItem(int position) {
        my_review_written_item.remove(position);
    }
}
