package com.example.servertest.servicecenter;

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

public class service_center_adapter extends BaseAdapter{
    private ArrayList<service_center_item> service_center_item = new ArrayList<service_center_item>();

    public service_center_adapter(){}

    @Override
    public int getCount() {
        return service_center_item.size();
    }

    @Override
    public Object getItem(int position) {
        return service_center_item.get(position);
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
            convertView = inflater.inflate(R.layout.service_center_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.iv_my_page_profile_image);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.tv_user_name);

        service_center_item service_item = service_center_item.get(position);

        iconImageView.setImageDrawable(service_item.getIcon());
        titleTextView.setText(service_item.getTitle());

        return convertView;
    }

    public void addItem(Drawable icon, String title){
        service_center_item item = new service_center_item();
        item.setIcon(icon);
        item.setTitle(title);
        service_center_item.add(item);
    }
}
