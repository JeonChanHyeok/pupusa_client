package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DataItem> myDataList = null;


    public MyAdapter(ArrayList<DataItem> dataList){
        myDataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == Code.ViewType.CENTER_CONTENT){
            view = inflater.inflate(R.layout.room_center_item_list,parent,false);
            return new CenterViewHolder(view);
        }else if(viewType == Code.ViewType.LEFT_CONTENT){
            view = inflater.inflate(R.layout.room_left_item_list,parent,false);
            return new LeftViewHolder(view);
        }else{
            view = inflater.inflate(R.layout.room_right_item_list,parent,false);
            return new RightViewHolder(view);
        }

    }



    // 실제 각 뷰 홀더에 데이터를 연결해주는 함수
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof CenterViewHolder){
            ((CenterViewHolder)viewHolder).textv.setText(myDataList.get(position).getContent());
        }else if(viewHolder instanceof LeftViewHolder){
            ((LeftViewHolder)viewHolder).textv_nicname.setText(myDataList.get(position).getName());
            ((LeftViewHolder)viewHolder).textv_msg.setText(myDataList.get(position).getContent());
        }else{
            ((RightViewHolder)viewHolder).textv_msg.setText(myDataList.get(position).getContent());
        }

    }

    // 리사이클러뷰안에서 들어갈 뷰 홀더의 개수
    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    // ★★★
    // 위에 3개만 오버라이드가 기본 셋팅임,
    // 이 메소드는 ViewType때문에 오버라이딩 했음(구별할려고)
    @Override
    public int getItemViewType(int position) {
        return myDataList.get(position).getViewType();
    }

    // "리사이클러뷰에 들어갈 뷰 홀더", 그리고 "그 뷰 홀더에 들어갈 아이템들을 셋팅"
    public class CenterViewHolder extends RecyclerView.ViewHolder{
        TextView textv;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            textv = (TextView)itemView.findViewById(R.id.tv_room_center_item_list);
        }
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgv;
        TextView textv_nicname;
        TextView textv_msg;
        TextView textv_time;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv = (CircleImageView)itemView.findViewById(R.id.iv_room_left_item_list_profile);
            textv_nicname = (TextView)itemView.findViewById(R.id.tv_room_left_item_list_name);
            textv_msg = (TextView)itemView.findViewById(R.id.tv_room_left_item_list_message);
            textv_time = (TextView)itemView.findViewById(R.id.tv_room_left_item_list_time);

            textv_time.setText(getTime());
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{
        TextView textv_msg;
        TextView textv_time;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            textv_msg = (TextView)itemView.findViewById(R.id.tv_room_right_item_list_message);
            textv_time = (TextView)itemView.findViewById(R.id.tv_room_right_item_list_time);

            textv_time.setText(getTime());
        }
    }

    //현재 시간 불러오는 함수
    private String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("aa hh:mm");
        String getTime = sdf.format(date);

        return getTime;
    }


}