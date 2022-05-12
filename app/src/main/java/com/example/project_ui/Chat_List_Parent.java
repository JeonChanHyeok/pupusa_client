package com.example.project_ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// 리스트 뷰
public class Chat_List_Parent extends AppCompatActivity {

    ArrayList<SampleData> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_listview_parent);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.listView);
        final Chat_MyAdapter chatMyAdapter = new Chat_MyAdapter(this,movieDataList);

        listView.setAdapter(chatMyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        chatMyAdapter.getItem(position).getMovieName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<SampleData>();

        movieDataList.add(new SampleData(R.drawable.ic_60gye, "60계치킨","10시에 60계 드실분"));
        movieDataList.add(new SampleData(R.drawable.ic_kyochon2, "교촌치킨","10시에 교촌 드실분"));
        movieDataList.add(new SampleData(R.drawable.ic_thunder2, "썬더치킨","10시에 썬더 드실분"));
    }
}