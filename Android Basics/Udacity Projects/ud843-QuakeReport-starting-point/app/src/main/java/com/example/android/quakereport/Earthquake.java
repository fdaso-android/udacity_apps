package com.example.android.quakereport;

/**
 * Created by frederickodaso on 1/29/17.
 */
public class Earthquake {

    private String mEarthquakeLocation;
    private double mEarthquakeMagnitude;
    private long mEarthquakeDate;
    private String mURL;

    public Earthquake(String earthquakeLocation, double earthquakeMagnitude, long earthquakeDate, String url) {
        mEarthquakeLocation = earthquakeLocation;
        mEarthquakeMagnitude = earthquakeMagnitude;
        mEarthquakeDate = earthquakeDate;
        mURL = url;
    }

    public String getEarthquakeLocation() {
        return mEarthquakeLocation;
    }

    public double getEarthquakeMagnitude() {
        return mEarthquakeMagnitude;
    }

    public String getURL() {
        return mURL;
    }

    public long getEarthquakeDate() {
        return mEarthquakeDate;
    }
}
