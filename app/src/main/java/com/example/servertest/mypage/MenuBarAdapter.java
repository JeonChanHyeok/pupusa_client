package com.example.servertest.mypage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.example.servertest.R;

public class MenuBarAdapter extends RecyclerView.Adapter<MenuBarAdapter.ViewHolder> {

    private ArrayList<String> itemList;
    private Context context;
    private View.OnClickListener onClickItem;

    public MenuBarAdapter(Context context, ArrayList<String> itemList, View.OnClickListener onClickItem) {
        this.context = context;
        this.itemList = itemList;
        this.onClickItem = onClickItem;
    }


    //껍데기 생성 1번(뷰홀더 객체 생성하여 리턴)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.menu_bar_item, parent, false);

        return new ViewHolder(view);
    }

    //껍데기에 데이터 바인딩 2번(뷰홀더 안의 내용을 position에 해당되는 데이터로 교체)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = itemList.get(position);

        holder.menuBarItem.setText(item);
        holder.menuBarItem.setTag(item);
        holder.menuBarItem.setOnClickListener(onClickItem);

    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return itemList.size();
    }


    //뷰들의 책꽂이
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView menuBarItem;

        public ViewHolder(View itemView) {
            super(itemView);

            menuBarItem = itemView.findViewById(R.id.tv_menu_bar_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        view.setBackgroundColor(Color.parseColor("#FFFF00"));
                    }
                }
            });
        }
    }
}
