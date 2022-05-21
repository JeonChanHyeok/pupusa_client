package com.example.capston;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class menu_main_viewadapter extends RecyclerView.Adapter<menu_main_viewadapter.ViewHolder> {
    private ArrayList<menu_main_item> mData = null;

    public menu_main_viewadapter(ArrayList<menu_main_item> data) {
        mData = data;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_main, parent, false);
        menu_main_viewadapter.ViewHolder vh = new menu_main_viewadapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        menu_main_item item = mData.get(position);
        holder.imageView.setBackground(item.getImage());
        holder.mainText.setText(item.getMenu_name());
        holder.subText.setText(String.valueOf(item.getPrice()));
    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView mainText;
        TextView subText;
        ViewHolder(View itemView) {
            super(itemView);
        // 뷰 객체에 대한 참조
            imageView = itemView.findViewById(R.id.image);
            mainText = itemView.findViewById(R.id.menu_name);
            subText = itemView.findViewById(R.id.price);
        }
    }
}
