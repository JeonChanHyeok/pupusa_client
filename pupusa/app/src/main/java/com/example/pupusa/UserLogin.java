package com.example.pupusa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void signUp(View v) {
        int id = v.getId();
        TextView textView = v.findViewById(id);
        String tag = (String) textView.getTag();

        Intent it = new Intent(this, UserJoin.class);
        it.putExtra("it_tag", tag);
        startActivity(it);
    }
}
