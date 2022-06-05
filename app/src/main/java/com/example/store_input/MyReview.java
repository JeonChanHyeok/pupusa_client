package com.example.store_input;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MyReview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        ListView listView = findViewById(R.id.ll_my_review);

        MyReviewWrittenAdapter adapter = new MyReviewWrittenAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pasta));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "기기괴괴",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pasta));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "기기괴괴",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyReviewWrittenItem item = (MyReviewWrittenItem) parent.getItemAtPosition(position);

                String title = item.getStoreName();
                String str = title;

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
