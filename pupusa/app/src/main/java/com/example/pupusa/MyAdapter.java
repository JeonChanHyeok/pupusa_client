package com.example.pupusa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public MyAdapter(Context context, ArrayList<SampleData> data) {
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
        View ChatView = mLayoutInflater.inflate(R.layout.listview_chattingroom, null);


        ImageView imageView = (ImageView)ChatView.findViewById(R.id.shop_icon);
        TextView title = (TextView)ChatView.findViewById(R.id.chatting_title);
        TextView address = (TextView)ChatView.findViewById(R.id.chatting_address);
        TextView contents = (TextView)ChatView.findViewById(R.id.chatting_context);

        imageView.setImageResource(sample.get(position).getPoster());
        title.setText(sample.get(position).getTitle());
        address.setText(sample.get(position).getAddress());
        contents.setText(sample.get(position).getMyContext());

        return ChatView;
    }
}
