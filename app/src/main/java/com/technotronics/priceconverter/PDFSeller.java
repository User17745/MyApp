package com.technotronics.priceconverter;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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
            Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Instrumentation inst = new Instrumentation();
                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_HOME);
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_F);
                    }
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_SHORT).show();
                    }
                }
            }).start();
        }
        return super.onOptionsItemSelected(item);
    }
}
