package com.example.capston;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ServiceCenter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center);

        ListView listView = findViewById(R.id.ll_service_center);

        ServiceCenterAdapter adapter = new ServiceCenterAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.question), "자주 묻는 질문");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.kakaotalk), "채팅 상담");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.email), "이메일 문의");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.store), "입점 문의하기");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceCenterItem item = (ServiceCenterItem) parent.getItemAtPosition(position);

                String title = item.getTitle();
                Drawable icon = item.getIcon();

                String str = title;
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }
        });

    }
}
