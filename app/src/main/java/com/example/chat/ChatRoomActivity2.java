package com.example.chat;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRoomActivity2 extends Activity {
    private ArrayList<DataItem> dataList;
    ImageButton btn;
    EditText editText;
    MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);   //나중에 바꾸기

        initData();



        RecyclerView recyvlerv = findViewById(R.id.rc_activity_chat_room);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyvlerv.setLayoutManager(manager);
        //recyvlerv.setAdapter(new MyAdapter(dataList));

        adapter = new MyAdapter(dataList);
        recyvlerv.setAdapter(adapter);

        btn = findViewById(R.id.btn_chat_room_send);
        editText = findViewById(R.id.et_chat_room_input_text);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(ChatRoomActivity2.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                dataList.add(new DataItem(editText.getText().toString(), "사용자2", Code.ViewType.RIGHT_CONTENT));  //editText.getText().toString(), "사용자2", Code.ViewType.RIGHT_CONTENT
                adapter.notifyDataSetChanged(); // 변경되었음을 어답터에 알려준다.
                editText.setText("");
            }
        });

    }

    private void initData(){
        dataList = new ArrayList<>();
        dataList.add(new DataItem("시용자1님 입장했음",null,Code.ViewType.CENTER_CONTENT));
        dataList.add(new DataItem("사용자2님 입장했음",null,Code.ViewType.CENTER_CONTENT));
        dataList.add(new DataItem("안녕하세요11","사용자1",Code.ViewType.LEFT_CONTENT));
        dataList.add(new DataItem("안녕하세요22","사용자2",Code.ViewType.RIGHT_CONTENT));

    }

    //EditText이외에 다른 부분 클릭 시 키보드 내려가기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

}