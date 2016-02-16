package com.technotronics.priceconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by HP Lap on 11-Feb-16.
 */
public class PDFSeller extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView mWebView=new WebView(PDFSeller.this);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient());
        //mWebView.getSettings().setPluginsEnabled(true);
        //mWebView.loadUrl("https://www.luminpdf.com/documents/oZT3fQ66ocrjSBEcz/share?sk=b4fd3eee-6e87-425a-87f3-d0522a06d225");
        mWebView.loadUrl("https://drive.google.com/open?id=0B79FZKj9iMYiRTd4UDl4bzM5Z3c");
        //mWebView.loadUrl("https://dochub.com/technotronicstore/mkmgpB/pricelist-11-02-16?dt=s82etl68hc1347g9");
        setContentView(mWebView);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                setContentView(R.layout.errorweb);
            }
        });
    }
}
