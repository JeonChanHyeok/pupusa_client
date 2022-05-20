package com.example.servertest.mypage.mychatlist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;

import java.util.ArrayList;

// 리스트 뷰
public class MyChatHistoryListActivity extends AppCompatActivity {

    ArrayList<ChatData> chatDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_listview_parent);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.lv_chat_history_listview);
        final Chat_MyAdapter chatMyAdapter = new Chat_MyAdapter(this,chatDataArrayList);

        listView.setAdapter(chatMyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        chatMyAdapter.getItem(position).getStore(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InitializeMovieData()
    {
        chatDataArrayList = new ArrayList<ChatData>();

        chatDataArrayList.add(new ChatData(R.drawable.ic_60gye, "60계치킨","연제구 길벗동 동흠로13","10시에 60계 드실분"));
        chatDataArrayList.add(new ChatData(R.drawable.ic_kyochon2, "교촌치킨","동래구 사직동 연분로45","1시에 교촌 드실분"));
        chatDataArrayList.add(new ChatData(R.drawable.ic_thunder2,"썬더치킨","금정구 고동동 지금로2","5시에 썬더 드실분"));
    }
}