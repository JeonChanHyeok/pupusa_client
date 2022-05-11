package com.example.capston;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class my_review extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_review);

        ListView listView = findViewById(R.id.ll2);

        my_review_written_adapter adapter = new my_review_written_adapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pasta));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "기기괴괴",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "피자핫",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.ramen));


        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "닭다리치킨집",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pasta));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "오봉이네",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "기기괴괴",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.pizza));
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.rating), "피자핫",
                "2021-02-10", "너무맛있엉ㅆ어용 눈물이 날정도예요", "닭다리", ContextCompat.getDrawable(this, R.drawable.ramen));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                my_review_written_item item = (my_review_written_item) parent.getItemAtPosition(position);

                String title = item.getStore_name();
                String str = title;

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}