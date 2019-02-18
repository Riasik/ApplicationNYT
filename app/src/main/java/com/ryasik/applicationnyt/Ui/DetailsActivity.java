package com.ryasik.applicationnyt.Ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ryasik.applicationnytexample.R;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        try {
            final String url = getIntent().getStringExtra("url");

            WebView webView = (WebView) findViewById(R.id.wvArticle);

            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    try {
                        view.loadUrl(url);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    return true;
                }
            });

            webView.loadUrl(url);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
