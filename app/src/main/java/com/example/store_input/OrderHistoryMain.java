package com.example.store_input;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderHistoryMain extends AppCompatActivity {
    ListView listView;
    TextView orderHistoryMyAmountPrice;
    OrderHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        orderHistoryMyAmountPrice = findViewById(R.id.tv_order_history_my_amount_price_won);

        orderMenu();
    }

    public void orderMenu(){
        listView = findViewById(R.id.ll_order_history_order_menu);

        adapter = new OrderHistoryAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("맛있는 치킨", 1, 15000);
        adapter.addItem("맛없는 치킨", 2, 45000);
        adapter.addItem("맛있을까 치킨", 5, 35000);

    }
}