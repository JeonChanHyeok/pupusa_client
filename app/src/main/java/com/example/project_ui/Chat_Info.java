package com.example.project_ui;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.Display;

public class Chat_Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_attend);

        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

    }
}
