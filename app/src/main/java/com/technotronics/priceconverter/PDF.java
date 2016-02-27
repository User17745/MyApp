package com.technotronics.priceconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class PDF extends AppCompatActivity {

	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weblist);

		/*mWebView = new WebView(PDF.this);
		mWebView.getSettings().setJavaScriptEnabled(true);

		mWebView.setWebViewClient(new WebViewClient());
		//mWebView.getSettings().setPluginsEnabled(true);
		mWebView.loadUrl("https://docs.zoho.com/file/v7xm916ea0e7bd5544edfa210afd98f01fc4a");
		setContentView(mWebView);*/

		mWebView = (WebView) findViewById(R.id.mWebView);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new WebViewClient());
			mWebView.loadUrl("https://docs.zoho.com/file/v7xm916ea0e7bd5544edfa210afd98f01fc4a");

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