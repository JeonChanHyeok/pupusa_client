package com.example.servertest.menu;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MainMenuDeco extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1){
            outRect.right = 30;
        }
    }
}
