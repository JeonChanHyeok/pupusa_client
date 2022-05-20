package com.example.servertest.mypage.mychatlist;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.Display;

        import com.example.servertest.R;

public class ChatInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_attend);

        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

    }
}
