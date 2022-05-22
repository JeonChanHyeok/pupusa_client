package com.example.capston;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class OrderHistory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ListView listView = findViewById(R.id.ll_order_history);

        OrderHistoryAdapter adapter = new OrderHistoryAdapter();
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.kyochon),"교촌치킨", "2022-04-10", "순살 닭강정 치킨", 10000);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.thunder),"썬더치킨", "2022-03-10", "뼈 치킨", 19000);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.zicoba),"지코바", "2022-02-10", "닭 날개", 10000);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                order_history_item item = (order_history_item) parent.getItemAtPosition(position);

                String title = item.getStoreName();
                String str = title;

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }
        });
    }
}
