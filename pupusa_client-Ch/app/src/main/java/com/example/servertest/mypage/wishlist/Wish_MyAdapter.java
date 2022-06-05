package com.example.servertest.mypage.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.servertest.R;

import java.util.ArrayList;

public class Wish_MyAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Wish_Data> sample;

    public Wish_MyAdapter(Context context, ArrayList<Wish_Data> data) {
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
    public Wish_Data getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.wish_listview_custom, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.heart);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.poster);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.star);


        TextView wishtitle = (TextView) view.findViewById(R.id.wish_title);
        TextView wishcontext = (TextView) view.findViewById(R.id.star_score);

        imageView.setImageResource(sample.get(position).getPoster2());
        imageView2.setImageResource(sample.get(position).getPoster());
        imageView3.setImageResource(sample.get(position).getPoster3());

        wishtitle.setText(sample.get(position).getStore());
        wishcontext.setText(sample.get(position).getstarscore());

        return view;
    }
}