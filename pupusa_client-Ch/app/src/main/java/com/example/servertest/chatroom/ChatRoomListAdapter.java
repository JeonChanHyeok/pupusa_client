package com.example.servertest.chatroom;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.servertest.R;
import com.example.servertest.chatroom.ChatRoomResponse;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomListAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    List<ChatRoomResponse> sample = new ArrayList<>();

    public ChatRoomListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setSample(List<ChatRoomResponse> sample) {
        this.sample = sample;
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
    public ChatRoomResponse getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_main_chat_room_list, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.ifv_main_chat_list_shop_icon);
        TextView title = (TextView)view.findViewById(R.id.tv_main_chat_list_chatting_title);
        TextView address = (TextView)view.findViewById(R.id.tv_main_chat_list_chatting_address);
        TextView contents = (TextView)view.findViewById(R.id.tv_main_chat_list_store_Name);

        imageView.setImageResource(R.drawable.kyochon);
        title.setText(sample.get(position).getChatRoomName());
        address.setText(sample.get(position).getChatRoomAddress());
        contents.setText(sample.get(position).getChatRoomStoreName());
        return view;
    }
}