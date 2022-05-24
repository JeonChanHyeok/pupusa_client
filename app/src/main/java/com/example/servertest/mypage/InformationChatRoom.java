package com.example.capston;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationChatRoom extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_chat_room);

        TextView title = (TextView) findViewById(R.id.tv_information_chat_room_title);
        TextView content = (TextView) findViewById(R.id.tv_information_chat_room_content);
        TextView shopName = (TextView) findViewById(R.id.tv_information_chat_room_shop_name);
        TextView address = (TextView) findViewById(R.id.tv_information_chat_room_address);
        TextView name = (TextView) findViewById(R.id.tv_information_chat_room_my_name);

        title.setText("한남동에서 무친치킨 같이 시켜요");
        content.setText("이거 먹고 저거먹고 요거목고 다같이 맛있게 멁어요 야미~");
        shopName.setText("한남동 무친치킨");
        address.setText("부산 남구 한남동");
        name.setText("전찬혁");
    }
}
