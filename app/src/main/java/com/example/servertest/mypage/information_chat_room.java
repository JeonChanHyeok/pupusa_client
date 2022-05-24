package com.example.capston;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class information_chat_room extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_chat_room);

        TextView title = (TextView) findViewById(R.id.title);
        TextView content = (TextView) findViewById(R.id.content);
        TextView shop_name = (TextView) findViewById(R.id.shop_name);
        TextView address = (TextView) findViewById(R.id.address);
        TextView name = (TextView) findViewById(R.id.name);

        title.setText("한남동에서 무친치킨 같이 시켜요");
        content.setText("이거 먹고 저거먹고 요거목고 다같이 맛있게 멁어요 야미~");
        shop_name.setText("한남동 무친치킨");
        address.setText("부산 남구 한남동");
        name.setText("전찬혁");
    }
}
