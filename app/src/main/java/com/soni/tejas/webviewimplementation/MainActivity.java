package com.soni.tejas.webviewimplementation;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText field, username;
    private WebView browser;
    private SwipeRefreshLayout myswipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username= findViewById(R.id.username);
        field = (EditText) findViewById(R.id.urlField);
        browser = (WebView) findViewById(R.id.webView1);

        //field.setTooltipText("Get set go!"); //API level 26 or above required
        browser.setWebViewClient(new MyBrowser());

        myswipe=findViewById(R.id.swiperefresh);
        myswipe.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        field.setText("");
                        username.setText("");
                        myswipe.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "Items Refreshed!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    public void open(View view) {

        String url = field.getText().toString();
        url = "http://" + url;
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // enabled javascript
        browser.loadUrl(url);
    }

    private class MyBrowser extends WebViewClient {

        @Override
        // ignore browser
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
//            view.getContext().startActivity(
//                    new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        }
    }
}