package com.example.servertest;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;

    ViewHolder(Context context, View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.tv_rv_item);
        imageView = itemView.findViewById(R.id.iv_rv_item);
    }
}
