package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mDisplayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDisplayView = (TextView) findViewById(R.id.tv_display);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        Intent mainIntent = getIntent();
        if (mainIntent.hasExtra(Intent.EXTRA_TEXT)) {
            mDisplayView.setText(mainIntent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}