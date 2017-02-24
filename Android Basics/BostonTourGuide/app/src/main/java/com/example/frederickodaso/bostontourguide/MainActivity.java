package com.example.frederickodaso.bostontourguide;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity's View to the Views specified in activty_main.xml
        setContentView(R.layout.activity_main);

        // Bind the ViewPager to its corresponding variable in Java code
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Instantiate a new LocationAdapter
        LocationAdapter adapter = new LocationAdapter(getSupportFragmentManager(), this);

        // Set the ViewPager to use the new LocationAdapter
        viewPager.setAdapter(adapter);

        // Bind the TabLayout to its corresponding variable in Java code
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        // Set the TabLayout to be used with the identified ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }
}
