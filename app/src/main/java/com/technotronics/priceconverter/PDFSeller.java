package com.technotronics.priceconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by HP Lap on 11-Feb-16.
 */
public class PDFSeller extends AppCompatActivity{

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weblist);

        /*WebView mWebView=new WebView(PDFSeller.this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        //mWebView.getSettings().setPluginsEnabled(true);
        mWebView.loadUrl("https://drive.google.com/open?id=0B79FZKj9iMYiRTd4UDl4bzM5Z3c");
        setContentView(mWebView);*/

        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl("https://drive.google.com/open?id=0B79FZKj9iMYiRTd4UDl4bzM5Z3c");


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                setContentView(R.layout.errorweb);
            }
        });

        FloatingActionButton refBut = (FloatingActionButton) findViewById(R.id.fab);
         refBut.setOnClickListener(Refresh);
    }

    private View.OnClickListener Refresh = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mWebView.reload();
        }
    };
}
