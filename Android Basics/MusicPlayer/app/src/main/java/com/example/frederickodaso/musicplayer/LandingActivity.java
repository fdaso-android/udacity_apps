package com.example.frederickodaso.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LandingActivity extends AppCompatActivity {

    private Button libraryButton;
    private Button storeButton;
    private Button streamingButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        libraryButton = (Button) findViewById(R.id.library_button);
        storeButton = (Button) findViewById(R.id.store_button);
        streamingButton = (Button) findViewById(R.id.streaming_button);
        settingsButton = (Button) findViewById(R.id.settings_button);

        libraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent libraryIntent = new Intent(LandingActivity.this, LibraryActivity.class);
                startActivity(libraryIntent);
            }
        });

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storeIntent = new Intent(LandingActivity.this, StoreActivity.class);
                startActivity(storeIntent);
            }
        });

        streamingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent streamingIntent = new Intent(LandingActivity.this, StreamingActivity.class);
                startActivity(streamingIntent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(LandingActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
    }
}
