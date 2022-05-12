package com.example.project_ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Wish_MyAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public Wish_MyAdapter(Context context, ArrayList<SampleData> data) {
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
    public SampleData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.wish_listview_custom, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.poster);
        TextView wishtitle = (TextView) view.findViewById(R.id.wish_title);
        TextView wishcontext = (TextView) view.findViewById(R.id.wish_context);

        imageView.setImageResource(sample.get(position).getPoster());
        wishtitle.setText(sample.get(position).getMovieName());
        wishcontext.setText(sample.get(position).getGrade());

        return view;
    }
}