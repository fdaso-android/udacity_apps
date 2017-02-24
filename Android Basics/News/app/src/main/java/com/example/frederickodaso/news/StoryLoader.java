package com.example.frederickodaso.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by frederickodaso on 2/7/17.
 */

public class StoryLoader extends AsyncTaskLoader<List<Story>> {

    private String mURL;
    public StoryLoader(Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Story> loadInBackground() {
        return GuardianQuery.fetchStoryData(mURL);
    }
}
