package com.example.servertest.instore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoreOrderHistoryMain extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();

    ListView listView;
    TextView orderHistoryMyAmountPrice;
    StoreOrderHistoryAdapter adapter;
    Button orderCheckButton, orderCancelButton;
    Long storeId;
    Long payOrderId;
    StoreOrderContentResponse storeOrderContentResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_content);

        orderCheckButton = (Button) findViewById(R.id.btn_confirm_order_store);
        orderCancelButton = (Button) findViewById(R.id.btn_confirm_order_store_cancel);

        payOrderId = getIntent().getLongExtra("payOrderId", 0L);

            String objJson = gson.toJson(payOrderId);

        Call<StoreOrderContentResponse> call = service.payOrderLoadInStore(objJson);
        call.enqueue(new Callback<StoreOrderContentResponse>() {
            @Override
            public void onResponse(Call<StoreOrderContentResponse> call, Response<StoreOrderContentResponse> response) {
                String str = gson.toJson(response.body());
                storeOrderContentResponse = gson.fromJson(str, StoreOrderContentResponse.class);
                orderMenu();
            }

            @Override
            public void onFailure(Call<StoreOrderContentResponse> call, Throwable t) {

            }
        });



        orderCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(storeOrderContentResponse.getState() == 3){
                    Toast.makeText(StoreOrderHistoryMain.this, "이미 배달이 완료된 주문입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    String objJson = gson.toJson(payOrderId);
                    Call call1 = service.payOrderConfirmInStore(objJson);
                    call1.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Intent intent = new Intent();
                            String text = "";
                            switch (storeOrderContentResponse.getState()){
                                case 0:
                                    text = "주문 확인";
                                    break;
                                case 1:
                                    text = "배달 시작";
                                    break;
                                case 2:
                                    text = "주문 완료";
                                    break;
                            }
                            intent.putExtra("text", text);
                            setResult(RESULT_OK, intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                }
            }
        });



        //buttonClick();
    }

    public void orderMenu(){
        listView = (ListView) findViewById(R.id.ll_store_order_content_menu);
        adapter = new StoreOrderHistoryAdapter();
        listView.setAdapter(adapter);

        for(int i = 0; i < storeOrderContentResponse.getMenus().size() ; i ++){
            adapter.addItem(storeOrderContentResponse.getMenus().get(i), storeOrderContentResponse.getMenucount().get(i), storeOrderContentResponse.getPrices().get(i));
        }
        adapter.addItem("배달료 : ", 999, storeOrderContentResponse.getDelPee());
        TextView tv_store_order_content_time = (TextView) findViewById(R.id.tv_store_order_content_time);
        tv_store_order_content_time.setText(storeOrderContentResponse.getOrderTime());
        TextView tv_store_order_content_address = (TextView) findViewById(R.id.tv_store_order_content_address);
        tv_store_order_content_address.setText(storeOrderContentResponse.getAddress());
        TextView tv_store_order_content_request_content = (TextView) findViewById(R.id.tv_store_order_content_request_content);
        tv_store_order_content_request_content.setText(storeOrderContentResponse.getOrderRequest());
        TextView tv_store_order_content_my_amount_price = (TextView) findViewById(R.id.tv_store_order_content_my_amount_price);
        String str = "" + storeOrderContentResponse.getAllPrice();
        tv_store_order_content_my_amount_price.setText(str);

    }
/*
    public void buttonClick(){
        orderHistoryButton = findViewById(R.id.btn_confirm_payment_pay);
        orderHistoryCancelButton = findViewById(R.id.btn_confirm_payment_pay_cancel);
        orderCheckButton = findViewById(R.id.btn_order_check_unconfirm);

        //확인 눌렀을때
        orderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreOrderHistoryMain.this, StoreOrderCheck.class);
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
                Intent intent = new Intent(StoreOrderHistoryMain.this, StoreOrderCheck.class);
                int num = getIntent().getIntExtra("num", 1);

                startActivity(intent);
            }
        });
    }*/

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