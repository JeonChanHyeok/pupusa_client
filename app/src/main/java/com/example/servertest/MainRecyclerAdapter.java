package com.example.servertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {
    private List<MainRecyclerItem> mItemList;

    public MainRecyclerAdapter(List<MainRecyclerItem> a_list) {
        mItemList = a_list;
    }

    public List<MainRecyclerItem> getmItemList() {
        return mItemList;
    }

    interface OnItemClickListener{
        void onItemClick(View v, int position); //뷰와 포지션값
    }
    //리스너 객체 참조 변수
    private OnItemClickListener mListener = null;
    //리스너 객체 참조를 어댑터에 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    public class MainViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_rv_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition ();
                    if (position!=RecyclerView.NO_POSITION){
                        if (mListener!=null){
                            mListener.onItemClick (view,position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_item_list, parent, false);

        MainViewHolder viewholder = new MainViewHolder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final MainRecyclerItem item = mItemList.get(position);
        holder.imageView.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    // 데이터를 입력
    public void setArrayData(String strData) {

    }
}
