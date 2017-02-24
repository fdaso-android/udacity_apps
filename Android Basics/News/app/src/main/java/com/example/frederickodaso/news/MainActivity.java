package com.example.frederickodaso.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Story>> {

    private static final String BASE_QUERY = "http://content.guardianapis.com/search?api-key=c01851af-e25b-415d-abe9-52d202d2cf77";

    StoryAdapter adapter;
    ProgressBar mProgressBar;
    TextView mEmptyStateTextView;
    ListView storyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {

            getLoaderManager().initLoader(0, null, this);
            storyListView = (ListView) findViewById(R.id.story_ListView);
            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            mEmptyStateTextView = (TextView) findViewById(R.id.empty_textView);

            final List<Story> stories = new ArrayList<>();
            adapter = new StoryAdapter(this,stories);
            storyListView.setEmptyView(mEmptyStateTextView);
            storyListView.setAdapter(adapter);

            storyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent storyIntent = new Intent(Intent.ACTION_VIEW);
                    storyIntent.setData(Uri.parse(stories.get(position).getUrl()));
                    startActivity(storyIntent);
                }
            });
        } else {
            final List<Story> stories = new ArrayList<>();
            adapter = new StoryAdapter(this,stories);
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            storyListView.setEmptyView(mEmptyStateTextView);
            storyListView.setAdapter(adapter);
        }



    }

    static class ViewHolder {
        TextView articleTitleTextView;
        TextView newsSectionTextView;
        TextView webPublicationDateTextView;
    }

    @Override
    public Loader<List<Story>> onCreateLoader(int id, Bundle args) {
//        Uri baseUri = Uri.parse(BASE_QUERY);
//        Uri.Builder uriBuilder = baseUri.buildUpon();
//
//        //uriBuilder.appendQueryParameter("q", "money");
//        uriBuilder.appendQueryParameter("key", API_KEY);

        return new StoryLoader(this, BASE_QUERY);
    }

    @Override
    public void onLoadFinished(Loader<List<Story>> loader, List<Story> stories) {
        adapter.clear();
        mEmptyStateTextView.setText(R.string.no_stories);
        mProgressBar.setVisibility(View.GONE);

        if (stories != null && !stories.isEmpty()) {
            adapter.addAll(stories);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Story>> loader) {
        adapter.clear();
    }
}
