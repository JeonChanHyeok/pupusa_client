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
    Button reviewDeleteBtn;
    MyReviewWrittenAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        listView = findViewById(R.id.ll_my_review);

//        // 빈 데이터 리스트 생성.
//        final ArrayList<String> items = new ArrayList<String>() ;
//        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
//        final ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items) ;

        adapter = new MyReviewWrittenAdapter();
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
                MyReviewWrittenItem item = (MyReviewWrittenItem) adapter.getItem(position); //parent.getItemAtPosition(position);

                String title = item.getStoreName();
                String str = title;

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteItem(){
        reviewDeleteBtn = findViewById(R.id.btn_my_review_written_item_delete);
        reviewDeleteBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = listView.getCheckedItemPosition();
                if (pos != ListView.INVALID_POSITION) {
                   // listView.remove(pos);
                    listView.clearChoices();
                    adapter.notifyDataSetChanged();
                }
            }
        }) ;
    }
}
