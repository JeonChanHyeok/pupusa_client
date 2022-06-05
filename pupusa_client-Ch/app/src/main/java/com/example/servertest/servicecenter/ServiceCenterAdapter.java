package com.example.servertest.servicecenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.servertest.R;

public class ServiceCenterAdapter extends BaseAdapter{
    private ArrayList<ServiceCenterItem> serviceCenterItemList = new ArrayList<ServiceCenterItem>();

    public ServiceCenterAdapter(){}

    @Override
    public int getCount() {
        return serviceCenterItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceCenterItemList.get(position);
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

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.iv_service_center_item_image);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.tv_service_center_item_content);

        ServiceCenterItem service_item = serviceCenterItemList.get(position);

        iconImageView.setImageDrawable(service_item.getIcon());
        titleTextView.setText(service_item.getTitle());

        return convertView;
    }

    public void addItem(Drawable icon, String title){
        ServiceCenterItem item = new ServiceCenterItem();
        item.setIcon(icon);
        item.setTitle(title);
        serviceCenterItemList.add(item);
    }
}
