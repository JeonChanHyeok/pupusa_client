package com.example.store_input;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OrderCheck extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_check);

        orderList();
    }

    public void orderList(){
        listView = findViewById(R.id.ll_order_check);
        OrderCheckAdapter adapter = new OrderCheckAdapter();
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                OrderCheckItem item = (OrderCheckItem) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
