package com.example.frederickodaso.booklisting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_QUERY = "https://www.googleapis.com/books/v1/volumes";
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "AIzaSyAWYaqcGPFmL8NN6EKTK5eikkhm9U7IgDo";

    private Button mSearchButton;
    private EditText mSearchText;
    private String mQuerySearchText;
    private BookAdapter adapter;
    private ProgressBar mProgressBar;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            mSearchText = (EditText) findViewById(R.id.search_editText);

            mSearchButton = (Button) findViewById(R.id.search_Button);
            mSearchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mEmptyStateTextView.setVisibility(View.GONE);
                    mQuerySearchText = mSearchText.getText().toString();
                    BookAsyncTask task = new BookAsyncTask();
                    task.execute();
                }
            });

            ListView booksListView = (ListView) findViewById(R.id.listView);

            final ArrayList<Book> books = new ArrayList<>();

            adapter = new BookAdapter(this, books);
            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            mProgressBar.setVisibility(View.GONE);
            booksListView.setEmptyView(mEmptyStateTextView);
            booksListView.setAdapter(adapter);
        } else {
            ListView booksListView = (ListView) findViewById(R.id.listView);

            final ArrayList<Book> books = new ArrayList<>();
            adapter = new BookAdapter(this, books);
            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            mProgressBar.setVisibility(View.GONE);
            booksListView.setEmptyView(mEmptyStateTextView);
            booksListView.setAdapter(adapter);
        }


    }

    private class BookAsyncTask extends AsyncTask<URL, Void, List<Book>> {
        @Override
        protected void onPreExecute() {
            adapter.clear();
            mEmptyStateTextView.setVisibility(View.GONE);
        }

        protected List<Book> doInBackground(URL... params) {
            Uri baseUri = Uri.parse(BASE_QUERY);
            Uri.Builder uriBuilder = baseUri.buildUpon();

            uriBuilder.appendQueryParameter("q", mQuerySearchText);
            uriBuilder.appendQueryParameter("key", API_KEY);

            URL url = createUrl(uriBuilder.toString());

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            // Return the {@link Book} object as the result fo the {@link BookAsyncTask}
            return extractFeatureFromJson(jsonResponse);
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            adapter.clear();
            mEmptyStateTextView.setText(R.string.no_books);
            mProgressBar.setVisibility(View.GONE);

            if (books != null && !books.isEmpty()) {
                adapter.addAll(books);
                mProgressBar.setVisibility(View.GONE);
            }
        }
        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error code: "+ urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                // TODO: Handle the exception
                Log.e(LOG_TAG, "IOException was caught:", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return an {@link List<Book>} object by parsing out information
         * about the books from the input bookJSON string.
         */
        private List<Book> extractFeatureFromJson(String bookJSON) {

            List<Book> books = new ArrayList<>();
            String description;
            String title;
            String author;
            String smallThumbnail;
            JSONObject imageLinks;
            JSONArray authors;
            Drawable bookImage = null;
            try {
                JSONObject baseJsonResponse = new JSONObject(bookJSON);
                JSONArray itemsArray = baseJsonResponse.getJSONArray("items");

                // If there are results in the features array
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject firstItem = itemsArray.getJSONObject(i);
                    JSONObject volumeInfo = firstItem.getJSONObject("volumeInfo");

                    try {
                        title = volumeInfo.getString("title");
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "One of the books does not have a title.", e);
                        title = "N/A";
                    }

                    try {
                        authors = volumeInfo.getJSONArray("authors");
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "One of the books does not have a list of authors.", e);
                        authors = null;
                    }

                    if (authors == null) {
                        author = "N/A";
                    } else {
                        try {
                            author = authors.getString(0);
                        } catch (JSONException e) {
                            Log.e(LOG_TAG, "One of the books does not have an author.", e);
                            author = "N/A";
                        }
                    }

                    try {
                        description = volumeInfo.getString("description");
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "One of the books does not have a description.", e);
                        description = "N/A";
                    }

                    try {
                        imageLinks = volumeInfo.getJSONObject("imageLinks");
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "One of the books does not have Image Links.", e);
                        imageLinks = null;
                    }

                    if (imageLinks == null) {
                        bookImage = null;
                    } else {
                        try {
                            smallThumbnail = imageLinks.getString("smallThumbnail");
                            bookImage = LoadImageFromJSONResponse(smallThumbnail);
                        } catch (JSONException e) {
                            Log.e(LOG_TAG, "One of the books does not have a thumbnail.", e);
                            description = "N/A";
                        }
                    }

                    Book book = new Book(title, author, description, bookImage);
                    books.add(book);

                }
                return books;
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the book JSON results", e);
            }
            return null;
        }

        private Drawable LoadImageFromJSONResponse(String url) {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                return Drawable.createFromStream(is, "content");
            } catch (Exception e) {
                return null;
            }
        }
    }
}
