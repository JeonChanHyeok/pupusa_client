package com.example.pupusa.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pupusa.AdapterAlert;
import com.example.pupusa.AlertSampleData;
import com.example.pupusa.R;

import java.util.ArrayList;

public class AlertActivity extends AppCompatActivity {
    ArrayList<AlertSampleData> alertDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        this.InitializeAlertData();

        ListView listView = findViewById(R.id.lv_alert);
        final AdapterAlert myAdapter = new AdapterAlert(this, alertDataList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener((parent, v, position, id) -> Toast.makeText(getApplicationContext(),
                myAdapter.getItem(position).getTitle(),
                Toast.LENGTH_LONG).show());
    }

    public void InitializeAlertData()
    {
        alertDataList = new ArrayList<AlertSampleData>();
        alertDataList.add(new AlertSampleData("배달이 완료되었습니다.","배달되었습니다. 안동하회찜닭n갈비찜을(를) 이용해주셔서 감사합니다. 맛있게 드세요.", "05월 12일 21:13"));
        alertDataList.add(new AlertSampleData("리뷰를 남겨보세요.","60계치킨 연산점의 음식이 맛있으셨다면 다른 분들을 위해 리뷰를 남겨주세요. (리뷰 쓰기는 주문 이후 3일 동안만 가능합니다.", "05월 12일 19:43"));
        alertDataList.add(new AlertSampleData("조리가 완료되었습니다.","투가이즈 피자&치킨 연산점을(를) 이용해주셔서 감사합니다. 맛있게 드세요.", "05월 10일 00:13"));
        alertDataList.add(new AlertSampleData("리뷰를 남겨보세요.","맥도날드 토곡점의 음식이 맛있으셨다면 다른 분들을 위해 리뷰를 남겨주세요. (리뷰 쓰기는 주문 이후 3일 동안만 가능합니다.", "05월 09일 22:42"));
        alertDataList.add(new AlertSampleData("리뷰를 남겨보세요.","빨봉분식 미니연산점의 음식이 맛있으셨다면 다른 분들을 위해 리뷰를 남겨주세요. (리뷰 쓰기는 주문 이후 3일 동안만 가능합니다.", "05월 08일 22:52"));
    }
}
