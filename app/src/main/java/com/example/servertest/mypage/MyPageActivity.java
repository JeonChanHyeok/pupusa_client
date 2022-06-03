package com.example.servertest.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.servertest.MainActivity;
import com.example.servertest.R;
import com.example.servertest.login.User;
import com.example.servertest.mypage.myreview.MyReview;
import com.example.servertest.mypage.orderhistory.OrderHistory;
import com.example.servertest.mypage.wishlist.WishListActivity;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {
    private String loginedId;
    private String loginedName;
    private int isLogin;
    private User user;
    private Intent myPageIntent;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        myPageIntent = getIntent();
        loginedId = myPageIntent.getStringExtra("loginedId");
        loginedName = myPageIntent.getStringExtra("loginedName");
        isLogin = myPageIntent.getIntExtra("isLogin", 1);

        Button btn_order = findViewById(R.id.btn_my_page_order_history);
        Button btn_review = findViewById(R.id.btn_my_page_review_management);
        Button btn_wish_list = findViewById(R.id.btn_my_page_wish_list);
        Button btn_locker = findViewById(R.id.btn_my_page_coupon_management);
        Button btn_chat =findViewById(R.id.btn_my_page_chattingroom_history);
        Button btn_change_info = findViewById(R.id.btn_my_page_changing_information);
        Button btn_logout = findViewById(R.id.btn_my_page_logout);
        TextView tv_username = findViewById(R.id.tv_my_page_my_name);
        TextView tv_vip = findViewById(R.id.tv_my_page_vip_level);
        TextView tv_address = findViewById(R.id.tv_my_page_show_address);



        System.out.println(loginedId);

        Call<User> loadUser = service.userInfoLoad(loginedId);
        loadUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                tv_username.setText(user.getUserName());
                int vip = user.getUserGrade();
                switch(vip){
                    case 0 :
                        tv_vip.setText("노비");
                        break;
                    case 1 :
                        tv_vip.setText("VIP");
                        break;
                    case 2 :
                        tv_vip.setText("VVIP");
                        break;
                }
                tv_address.setText(user.getUserAddress());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });



        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                Intent intent = new Intent(MyPageActivity.this, OrderHistory.class);
                startActivity(intent);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MyReview.class);
                startActivity(intent);
            }
        });

        btn_wish_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, WishListActivity.class);
                startActivity(intent);
            }
        });

        btn_locker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, CreateChatRoom.class);
                startActivity(intent);
            }
        });

        btn_chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, InformationChatRoom.class);
                startActivity(intent);
            }
        });

        btn_change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MenuList.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, MainActivity.class);
                intent.putExtra("islogin", 0);
                intent.putExtra("loginedId", "");
                intent.putExtra("loginedName", "");
                startActivity(intent);
                finish();
            }
        });

    }



}