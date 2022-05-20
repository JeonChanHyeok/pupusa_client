package com.example.servertest.mypage.mychatlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.servertest.R;

import java.util.ArrayList;

public class Chat_MyAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ChatData> sample;

    public Chat_MyAdapter(Context context, ArrayList<ChatData> data) {
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
    public ChatData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.chat_listview_custom, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_poster);
        TextView chat_title = (TextView) view.findViewById(R.id.tv_chatting_title);
        TextView  chat_location= (TextView) view.findViewById(R.id.tv_chatting_location);
        TextView chat_content = (TextView) view.findViewById(R.id.tv_chatting_context);

        imageView.setImageResource(sample.get(position).getPoster());
        chat_title.setText(sample.get(position).getStore());
        chat_location.setText(sample.get(position).store_location());
        chat_content.setText(sample.get(position).store_content());
        return view;
    }
}