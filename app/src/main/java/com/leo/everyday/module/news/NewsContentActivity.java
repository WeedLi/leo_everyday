package com.leo.everyday.module.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.leo.everyday.R;
import com.leo.everyday.widget.LeoWebView;

public class NewsContentActivity extends AppCompatActivity {


    public static void actionToNewsContentActivity(Context context, String url, String name) {
        Intent intent = new Intent();
        intent.setClass(context, NewsContentActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LeoWebView webView = findViewById(R.id.news_web_view);
        webView.load(getIntent().getStringExtra("url"));
        webView.setOnWebviewLoadListener(new LeoWebView.OnWebviewLoadListener() {
            @Override
            public void onLoadSuccess() {

            }

            @Override
            public void onLoadError() {
                Toast.makeText(NewsContentActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
