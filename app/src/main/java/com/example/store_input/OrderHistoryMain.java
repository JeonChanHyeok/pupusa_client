package com.example.store_input;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class OrderHistoryMain extends AppCompatActivity {
    ListView listView;
    TextView orderHistoryMyAmountPrice;
    OrderHistoryAdapter adapter;
    Button orderHistoryButton, orderCheckButton, orderHistoryCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        orderHistoryMyAmountPrice = findViewById(R.id.tv_order_history_my_amount_price_won);

        orderMenu();
        buttonClick();
    }

    public void orderMenu(){
        listView = findViewById(R.id.ll_order_history_order_menu);

        adapter = new OrderHistoryAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("맛있는 치킨", 1, 15000);
        adapter.addItem("맛없는 치킨", 2, 45000);
        adapter.addItem("맛있을까 치킨", 5, 35000);
    }

    public void buttonClick(){
        orderHistoryButton = findViewById(R.id.btn_confirm_payment_pay);
        orderHistoryCancelButton = findViewById(R.id.btn_confirm_payment_pay_cancel);
        orderCheckButton = findViewById(R.id.btn_order_check_unconfirm);

        //확인 눌렀을때
        orderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderHistoryMain.this, OrderCheck.class);
                int num = getIntent().getIntExtra("num", 1);

                Log.d("num2", num+"");
                //orderCheckButton.setBackgroundResource(R.drawable.btn_round_yellow);
                startActivity(intent);
            }
        });

        //취소 눌렀을때
        orderHistoryCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderHistoryMain.this, OrderCheck.class);
                int num = getIntent().getIntExtra("num", 1);

                startActivity(intent);
            }
        });
    }

//    public void btnColorChange(int position){
//        orderCheckButton = findViewById(R.id.btn_order_check_confirm);
//        adapter = new OrderHistoryAdapter();
//        OrderHistoryAdapter ohAdapter = new OrderHistoryAdapter();
//
//        switch (position){
//            case 0:
//                //ohAdapter.orderHistoryItemList
//                orderCheckButton.setBackgroundResource(R.drawable.btn_round_yellow);
//        }
//    }
}