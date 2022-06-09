package com.example.servertest.alret;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertActivity extends AppCompatActivity {
    ArrayList<AlertSampleData> alertDataList;
    private Gson gson = new Gson();
    private ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    AlertResponseList alertResponseList;
    AdapterAlert myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);


        String objJson = getIntent().getStringExtra("loginedId");
        Call<AlertResponseList> call = service.loadAlert(objJson);
        ListView listView = findViewById(R.id.lv_alert);



        call.enqueue(new Callback<AlertResponseList>() {
            @Override
            public void onResponse(Call<AlertResponseList> call, Response<AlertResponseList> response) {
                String str = gson.toJson(response.body());
                alertResponseList = gson.fromJson(str, AlertResponseList.class);
                alertDataList = new ArrayList<AlertSampleData>();
                for(AlertResponse a: alertResponseList.getAlertResponseList()){
                    alertDataList.add(new AlertSampleData(a.title,a.msg, a.time));
                }
                myAdapter = new AdapterAlert(getApplicationContext(), alertDataList);
                listView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<AlertResponseList> call, Throwable t) {

            }
        });



        listView.setOnItemClickListener((parent, v, position, id) -> Toast.makeText(getApplicationContext(),
                myAdapter.getItem(position).getTitle(),
                Toast.LENGTH_LONG).show());
    }

}
