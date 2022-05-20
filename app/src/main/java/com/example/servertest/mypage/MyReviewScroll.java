package com.example.servertest.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.example.servertest.R;

public class MyReviewScroll extends Fragment {
    private ListView ll;
    private ListView ll2;
    private MyReviewWrittenAdapter adapter;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = (ListAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_review, container, false);

        //report list view
        adapter = new MyReviewWrittenAdapter();
        ll = (ListView) v.findViewById(R.id.ll);
        ll.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this.getContext(), R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this.getContext(), R.drawable.pasta));
        adapter.addItem(ContextCompat.getDrawable(this.getContext(), R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this.getContext(), R.drawable.pizza));

        ll.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //report list view
        adapter = new MyReviewWrittenAdapter();
        ll2 = (ListView) v.findViewById(R.id.ll2);
        ll2.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this.getContext(), R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this.getContext(), R.drawable.pasta));
        adapter.addItem(ContextCompat.getDrawable(this.getContext(), R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this.getContext(), R.drawable.pizza));

        ll2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(ll);
        setListViewHeightBasedOnChildren(ll2);

        return v;
    }
}
