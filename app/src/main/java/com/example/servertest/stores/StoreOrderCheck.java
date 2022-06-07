package com.example.servertest.stores;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;

public class StoreOrderCheck extends AppCompatActivity {
    ListView listView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_check);

        orderList();
    }

    public void orderList(){
        listView = findViewById(R.id.ll_order_check);
        button = findViewById(R.id.btn_order_check_unconfirm);

        StoreOrderCheckAdapter adapter = new StoreOrderCheckAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("부산 가야로 11길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨, ssssssss,ssssss", "2022.06.05", 10000);
        adapter.addItem("부산 가야로 22길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.04", 20000);
        adapter.addItem("부산 가야로 33길 33로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.03", 20000);
        adapter.addItem("부산 가야로 44길 44로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.02", 20000);
        adapter.addItem("부산 가야로 55길 55로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.01", 20000);
        adapter.addItem("부산 가야로 11길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.05", 230000);
        adapter.addItem("부산 가야로 22길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.04", 20000);
        adapter.addItem("부산 가야로 33길 33로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.03", 20000);
        adapter.addItem("부산 가야로 44길 44로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.02", 10000);
        adapter.addItem("부산 가야로 55길 55로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.01", 20000);
        adapter.addItem("부산 가야로 11길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.05", 20000);
        adapter.addItem("부산 가야로 22길 22로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.04", 20000);
        adapter.addItem("부산 가야로 33길 33로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.03", 40000);
        adapter.addItem("부산 가야로 44길 44로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.02", 20000);
        adapter.addItem("부산 가야로 55길 55로", "맛있는 치킨, 맛없는치킨, 꿀꿀치킨", "2022.06.01", 20000);

        listView.setFocusable(false);
        //button.setFocusable(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                StoreOrderCheckItem item = (StoreOrderCheckItem) parent.getItemAtPosition(position);

                String title = item.getAddress();
                String str = title;
                Log.d("리스트뷰 포인터", "point"+ position);
                /*Intent intent = new Intent(StoreOrderCheck.this, StoreOrderHistoryMain.class);
                intent.putExtra("num", position);
                Log.d("num1", ""+position);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}
