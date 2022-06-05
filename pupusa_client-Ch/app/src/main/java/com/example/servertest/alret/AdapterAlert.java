package com.example.servertest.alret;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.servertest.R;

import java.util.ArrayList;

public class AdapterAlert extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<AlertSampleData> sample;

    public AdapterAlert(Context context, ArrayList<AlertSampleData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public AlertSampleData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View AlertView = mLayoutInflater.inflate(R.layout.listview_alert, null);


        TextView title = AlertView.findViewById(R.id.alert_title);
        TextView contents = AlertView.findViewById(R.id.alert_contents);
        TextView date = AlertView.findViewById(R.id.alert_date);

        title.setText(sample.get(position).getTitle());
        contents.setText(sample.get(position).getMyContents());
        date.setText(sample.get(position).getDate());

        return AlertView;
    }
}
