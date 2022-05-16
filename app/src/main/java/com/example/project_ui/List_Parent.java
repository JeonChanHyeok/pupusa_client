package com.example.project_ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
// 리스트 뷰
public class List_Parent extends AppCompatActivity {

    ArrayList<Wish_Data> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_parent);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.listView);
        final Wish_Adapter myAdapter = new Wish_Adapter(this,movieDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getStore(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<Wish_Data>();

        movieDataList.add(new Wish_Data(R.drawable.ic_60gye,R.drawable.heart, R.drawable.star,"60계치킨","후라이드치킨"));
        movieDataList.add(new Wish_Data(R.drawable.ic_kyochon2,R.drawable.heart,R.drawable.star, "교촌치킨","허니콤보"));
        movieDataList.add(new Wish_Data(R.drawable.ic_thunder2, R.drawable.heart,R.drawable.star,"썬더치킨","양념치킨"));
    }
}