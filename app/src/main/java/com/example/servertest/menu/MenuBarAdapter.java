package com.example.servertest.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.servertest.R;

import java.util.ArrayList;

public class MenuBarAdapter extends RecyclerView.Adapter<MenuBarAdapter.ViewHolder> {
    private OnItemClickListener mListener = null;
    private ArrayList<MenuBarItem> mData = null;

    public MenuBarAdapter(ArrayList<MenuBarItem> data) {
        mData = data;
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    //껍데기 생성 1번(뷰홀더 객체 생성하여 리턴)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        // context 와 parent.getContext() 는 같다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_bar_item, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    //껍데기에 데이터 바인딩 2번(뷰홀더 안의 내용을 position에 해당되는 데이터로 교체)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuBarItem item = mData.get(position);

        holder.menuBarItem.setText(item.getMenuBarItem());
    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }


    //뷰들의 책꽂이
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView menuBarItem;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();  //현재 자신의 위치 확인
                    if(position != RecyclerView.NO_POSITION){
                        if (mListener != null) {
                            mListener.onItemClick(view, position) ;
                        }
                    }
                }
            });
            menuBarItem = itemView.findViewById(R.id.tv_menu_bar_item2);
        }
    }


}
