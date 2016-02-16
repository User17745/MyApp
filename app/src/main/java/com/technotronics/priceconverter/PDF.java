package com.technotronics.priceconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class PDF extends AppCompatActivity {


	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

		  WebView mWebView=new WebView(PDF.this);

		  mWebView.getSettings().setJavaScriptEnabled(true);

		  mWebView.setWebViewClient(new WebViewClient());
		  //mWebView.getSettings().setPluginsEnabled(true);
	      //mWebView.loadUrl("https://www.luminpdf.com/documents/oZT3fQ66ocrjSBEcz/share?sk=b4fd3eee-6e87-425a-87f3-d0522a06d225");
		  mWebView.loadUrl("https://docs.zoho.com/file/v7xm916ea0e7bd5544edfa210afd98f01fc4a");
		  setContentView(mWebView);

		  mWebView.setWebViewClient(new WebViewClient() {
			  @Override
			  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
			  {
				  setContentView(R.layout.errorweb);
			  }
		  });
	  }
	}