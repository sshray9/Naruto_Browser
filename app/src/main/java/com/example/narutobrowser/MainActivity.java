package com.example.narutobrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    Button go,forward,back,clear,reload;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brow = findViewById(R.id.wv_brow);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        brow= (WebView)findViewById(R.id.wv_brow);
        urledit = (EditText)findViewById(R.id.et_url);
        go = (Button)findViewById(R.id.btn_go);
        forward = (Button)findViewById(R.id.btn_fwd);
        back = (Button)findViewById(R.id.btn_bck);
        clear = (Button)findViewById(R.id.btn_clear);
        reload = (Button)findViewById(R.id.btn_reload);

        brow.setWebViewClient(new ourViewClient());   // opens link in same browser

        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);

                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                }

            }
        });

        WebSettings webSettings = brow.getSettings();
        webSettings.setJavaScriptEnabled(true);

        brow.loadUrl("http://www.google.com");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String EditTextValue = urledit.getText().toString();

                if(!EditTextValue.startsWith("http://")){
                    EditTextValue = "http://" + EditTextValue;
                }

                String url = EditTextValue;
                brow.loadUrl(url);

                //hide keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                inputMethodManager.hideSoftInputFromWindow(urledit.getWindowToken(), 0);

            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brow.canGoForward())
                    brow.goForward();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brow.canGoBack())
                    brow.goBack();
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brow.reload();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brow.clearHistory();
            }
        });


    }
}