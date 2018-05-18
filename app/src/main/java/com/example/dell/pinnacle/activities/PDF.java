package com.example.dell.pinnacle.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dell.pinnacle.R;

public class PDF extends AppCompatActivity {
    WebView wv;
    ProgressDialog pDialog;

    String pdfload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        Bundle bn = getIntent().getExtras();
        pdfload = (String) bn.getCharSequence("pdfUrl");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        //pdfload="http://xinatechnologies.com/new_coachingapp/subjects/unique_questions/unique_questions_pdf/Unique%20Questions_1.pdf";

        pDialog = new ProgressDialog(this,R.style.MyTheme);
        /*pDialog.setTitle("PDF Tutorial");
        pDialog.setMessage("Loading...");*/
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.setMax(100);


        wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setFocusable(true);
        wv.setFocusableInTouchMode(true);
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setDatabaseEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                View r = findViewById(android.R.id.content);
                Snackbar.make(r,"Error!!",Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                pDialog.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {

                pDialog.dismiss();
                super.onPageFinished(view, url);
                wv.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");


            }
        });
        wv.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdfload);
        // wv.setWebViewClient(new WebViewClient());

    }
}
