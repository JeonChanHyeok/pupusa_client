package com.example.servertest.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servertest.R;
import com.example.servertest.map.Map_Main;
import com.example.servertest.payment.ConfirmPaymentActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();
    private ExpandableListView lv_order_list;
    DetailOrderAdapter detailOrderAdapter;
    Long roomId;
    ArrayList<DetailOrderGroup> DataList;
    TextView tv_order_pickup_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String userId = getIntent().getStringExtra("loginedId");
        roomId = getIntent().getLongExtra("roomId", 0L);
        TextView tv_store_name = (TextView)findViewById(R.id.tv_order_store_name);
        Button btn_choice_menu = (Button) findViewById(R.id.btn_order_choice_menu);
        tv_order_pickup_location = (TextView) findViewById(R.id.tv_order_pickup_location);
        Button btn_order_location = (Button) findViewById(R.id.btn_order_location);
        Button btn_go_order = (Button) findViewById(R.id.btn_go_order);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        DataList = new ArrayList<DetailOrderGroup>();
        lv_order_list = (ExpandableListView)findViewById(R.id.lv_order_list);

        detailOrderAdapter = new DetailOrderAdapter(getApplicationContext(),R.layout.detailorder_parent_list_view,R.layout.detailorder_child_list_view,DataList);
        lv_order_list.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        lv_order_list.setAdapter(detailOrderAdapter);

        initOrderList();

        String objJson = gson.toJson(roomId);

        Call<OrderRoomInfoResponse> orderRoomInfoResponseCall = service.loadOrderRoomInfo(objJson);
        orderRoomInfoResponseCall.enqueue(new Callback<OrderRoomInfoResponse>() {
            @Override
            public void onResponse(Call<OrderRoomInfoResponse> call, Response<OrderRoomInfoResponse> response) {
                tv_store_name.setText(response.body().getStoreName());
                tv_order_pickup_location.setText(response.body().getPickupLocation());
                if(!userId.equals(response.body().getMasterId())){
                    btn_go_order.setClickable(false);
                }
            }

            @Override
            public void onFailure(Call<OrderRoomInfoResponse> call, Throwable t) {

            }
        });

        btn_order_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(getApplicationContext(), Map_Main.class);
                startActivityForResult(map, 0);
            }
        });


        btn_choice_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOrder sendOrder = new SendOrder(userId, roomId, 7L);
                String objJson = gson.toJson(sendOrder);
                Call send = service.sendOrder(objJson);
                send.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        String str = gson.toJson(response.body());
                        OrderResponseList orderResponses = gson.fromJson(str, OrderResponseList.class);
                        if(orderResponses.getCode() == 0){
                            Toast.makeText(OrderActivity.this, "결제가 진행중입니다.", Toast.LENGTH_SHORT).show();
                        }else{
                            DataList.clear();
                            for(OrderResponse o : orderResponses.getOrderResponseList()){
                                DetailOrderGroup temp = new DetailOrderGroup(o.getUserName());
                                temp.child.add(o.getMenuName() + " " + o.getPrice() + "원");
                                DataList.add(temp);
                            }
                            detailOrderAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        });

        btn_go_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objJson = gson.toJson(roomId);
                Call go_pay = service.goPay(objJson);
                go_pay.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                Intent go_order = new Intent(getApplicationContext(), ConfirmPaymentActivity.class);
                go_order.putExtra("roomId", roomId);
                go_order.putExtra("loginedId", userId);
                startActivity(go_order);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String address = data.getStringExtra("address");
        Location location = new Location(roomId, address);
        String objJson = gson.toJson(location);
        Call calll = service.changeLocation(objJson);
        calll.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(OrderActivity.this, "주소가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                finish();//인텐트 종료
                overridePendingTransition(0, 0);//인텐트 효과 없애기
                Intent intent = getIntent(); //인텐트
                startActivity(intent); //액티비티 열기
                overridePendingTransition(0, 0);//인텐트 효과 없애기
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }
    private class Location{
        Long roomId;
        String address;

        public Location(Long roomId, String location) {
            this.roomId = roomId;
            this.address = location;
        }
    }

    public void initOrderList(){
        String objJson = gson.toJson(roomId);
        Call initOrder = service.loadOrderList(objJson);
        initOrder.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                String str = gson.toJson(response.body());
                OrderResponseList orderResponses = gson.fromJson(str, OrderResponseList.class);
                for(OrderResponse o : orderResponses.getOrderResponseList()){
                    DetailOrderGroup temp = new DetailOrderGroup(o.getUserName());
                    temp.child.add(o.getMenuName() + " " + o.getPrice() + "원");
                    DataList.add(temp);
                }
                detailOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    private class SendOrder{
        String userId;
        Long roomId;
        Long menuId;

        public SendOrder(String userId, Long roomId, Long menuId) {
            this.userId = userId;
            this.roomId = roomId;
            this.menuId = menuId;
        }
    }
}