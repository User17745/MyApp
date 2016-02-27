package com.technotronics.priceconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by HP Lap on 27-Feb-16.
 */
public class WeeklyReport extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_report);

        WebView mWebView = (WebView) findViewById(R.id.mWebView);

        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://goo.gl/forms/13oSZ9GOum");


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                setContentView(R.layout.errorweb);
            }
        });
    }
}