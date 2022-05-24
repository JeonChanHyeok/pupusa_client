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

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {
    private ArrayList<MainMenuItem> mData = null;

    public MainMenuAdapter(ArrayList<MainMenuItem> data) {
        mData = data;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_menu_item, parent, false);
        MainMenuAdapter.ViewHolder vh = new MainMenuAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainMenuItem item = mData.get(position);
        holder.mainMenuImage.setBackground(item.getImage());
        holder.menuName.setText(item.getMenuName());
        holder.price.setText(String.valueOf(item.getPrice()));
    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainMenuImage;
        TextView menuName;
        TextView price;
        ViewHolder(View itemView) {
            super(itemView);
        // 뷰 객체에 대한 참조
            mainMenuImage = itemView.findViewById(R.id.iv_main_menu_item_image);
            menuName = itemView.findViewById(R.id.tv_main_menu_item_menu_name);
            price = itemView.findViewById(R.id.tv_main_menu_item_price);
        }
    }
}
