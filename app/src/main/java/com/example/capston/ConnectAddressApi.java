package com.example.capston;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectAddressApi extends AppCompatActivity {
    private WebView webView;

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {
            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_api);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });
        //webView.loadUrl("C:/Users/YOU/AndroidStudioProjects/capston/app/src/main/assets/daum.html");
        webView.loadUrl("file:///android_asset/daum.html");
        //webView.loadUrl("https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js");
        //webView.loadUrl("https://t1.daumcdn.net/postcode/cssjs/service/1650867043088/service.v2.min.js");
        //webView.loadUrl("http://서버주소/daum.html");
    }


}
