package com.example.capston;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MyReview extends AppCompatActivity {
    MyReviewWrittenAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        listView = findViewById(R.id.ll_my_review);

        adapter = new MyReviewWrittenAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.pasta), 4f);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.pizza),3f);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "기기괴괴",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.pizza), 4.5f);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "피자핫",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.ramen), 4.5f);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.pasta), 4.5f);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.pizza), 4.5f);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "기기괴괴",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.pizza), 4.5f);
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "피자핫",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리",
                ContextCompat.getDrawable(this, R.drawable.ramen), 4.5f);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyReviewWrittenItem item = (MyReviewWrittenItem) adapter.getItem(position); //parent.getItemAtPosition(position);

                String title = item.getStoreName();
                String str = title;

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
