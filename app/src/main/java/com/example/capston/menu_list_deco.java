package com.example.capston;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class menu_list_deco extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1){
            outRect.right = 30;
        }
    }
}
