package com.example.servertest.mypage.wishlist;

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
public class WishListActivity extends AppCompatActivity {

    ArrayList<Wish_Data> movieDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_listview_parent);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.lv_wish_list_listview);
        final Wish_MyAdapter wishMyAdapter = new Wish_MyAdapter(this,movieDataList);

        listView.setAdapter(wishMyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        wishMyAdapter.getItem(position).getStore(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<Wish_Data>();

        movieDataList.add(new Wish_Data(R.drawable.ic_60gye,R.drawable.heart,R.drawable.star, "60계치킨","4.9(1020)"));
        movieDataList.add(new Wish_Data(R.drawable.ic_kyochon2,R.drawable.heart,R.drawable.star, "교촌치킨","4.7(626)"));
        movieDataList.add(new Wish_Data(R.drawable.ic_thunder2,R.drawable.heart,R.drawable.star, "썬더치킨","4.0(398)"));
    }
}