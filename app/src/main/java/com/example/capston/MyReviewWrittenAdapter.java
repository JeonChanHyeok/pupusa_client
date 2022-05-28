package com.example.capston;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;

public class MyReviewWrittenAdapter extends BaseAdapter {
    private ArrayList<MyReviewWrittenItem> MyReviewWrittenItemList = new ArrayList<>();
    Button deleteButton;

    public MyReviewWrittenAdapter(){}

    @Override
    public int getCount() {
        return MyReviewWrittenItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return MyReviewWrittenItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_review_written_item, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.ig_my_review_written_item_star_rate);
        TextView storeTextView = (TextView) convertView.findViewById(R.id.tv_my_review_written_item_store_name);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.tv_my_review_written_item_date);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.tv_my_review_written_item_content);
        TextView menu = (TextView) convertView.findViewById(R.id.tv_my_review_written_item_menu);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_my_review_written_item_menu_image);

        MyReviewWrittenItem my_review_item = MyReviewWrittenItemList.get(position);

        iconImageView.setImageDrawable(my_review_item.getReviewRate());
        storeTextView.setText(my_review_item.getStoreName());
        dateTextView.setText(my_review_item.getDate());
        contentTextView.setText(my_review_item.getContent());
        menu.setText(my_review_item.getMenu());
        imageView.setImageDrawable(my_review_item.getImage());

        //리뷰 삭제 버튼 클릭 이벤트
        deleteButton =(Button) convertView.findViewById(R.id.btn_my_review_written_item_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), position+"", Toast.LENGTH_SHORT).show();
                MyReviewWrittenItemList.remove(position);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    public void addItem(Drawable icon, String store, String date, String content, String menu, Drawable image){
        MyReviewWrittenItem item = new MyReviewWrittenItem();
        item.setDate(date);
        item.setReviewRate(icon);
        item.setMenu(menu);
        item.setContent(content);
        item.setStoreName(store);
        item.setImage(image);
        MyReviewWrittenItemList.add(item);
    }

}
