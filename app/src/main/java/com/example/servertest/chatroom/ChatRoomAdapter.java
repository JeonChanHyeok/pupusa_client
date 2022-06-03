package com.example.servertest.chatroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.servertest.R;
import com.example.servertest.chat.ChatMessage;

import java.util.ArrayList;

public class ChatRoomAdapter extends BaseAdapter {
    Context mContext = null;
    ArrayList<ChatMessage> sample;
    LayoutInflater mLayoutInflater = null;

    public ChatRoomAdapter(Context context, ArrayList<ChatMessage> sample) {
        mContext = context;
        this.sample = sample;
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
    public ChatMessage getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.chatting_item, null);

        TextView content = (TextView)view.findViewById(R.id.chat_context);
        TextView id = (TextView)view.findViewById(R.id.chat_id);

        content.setText(sample.get(position).getMessage());
        id.setText(sample.get(position).getUserId());

        return view;
    }
}
