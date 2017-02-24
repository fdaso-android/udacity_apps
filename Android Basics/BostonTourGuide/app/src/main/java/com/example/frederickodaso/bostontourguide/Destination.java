package com.example.frederickodaso.bostontourguide;

/**
 * Created by frederickodaso on 2/5/17.
 */

public class Destination {

    private String mLocationName;
    private String mDescription;
    private int mImage;
    private String mURL;

    public Destination(String locationName, String description, int image, String URL) {
        mLocationName = locationName;
        mDescription = description;
        mImage = image;
        mURL = URL;
    }

    public String getURL() {
        return mURL;
    }

    public String getLocationName() {
        return mLocationName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getImage() {
        return mImage;
    }
}
