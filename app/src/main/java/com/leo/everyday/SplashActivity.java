package com.leo.everyday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class SplashActivity extends AppCompatActivity {

    private ShimmerTextView shimmerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        shimmerTextView = findViewById(R.id.shimmer_tv);
        final Shimmer shimmer = new Shimmer();
        shimmer.start(shimmerTextView);
        shimmerTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmer.cancel();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }
}
