package com.example.frederickodaso.krogerinventory;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.frederickodaso.krogerinventory.data.StockContract;

public class StockActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    StockCursorAdapter mStockCursorAdapter;
    private int mCurrentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        getLoaderManager().initLoader(0, null, this);

        ListView stockListView = (ListView) findViewById(R.id.listView);

        View emptyView = findViewById(R.id.empty_textView);
        stockListView.setEmptyView(emptyView);

        mStockCursorAdapter = new StockCursorAdapter(this, null);
        stockListView.setAdapter(mStockCursorAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAPetIntent = new Intent(StockActivity.this, DetailActivity.class);
                startActivity(addAPetIntent);
            }
        });

        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editAPetIntent = new Intent(StockActivity.this, DetailActivity.class);
                editAPetIntent.setData(ContentUris.withAppendedId(StockContract.StockEntry.CONTENT_URI, id));
                startActivity(editAPetIntent);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                StockContract.StockEntry._ID,
                StockContract.StockEntry.COLUMN_STOCK_SUPPLIER,
                StockContract.StockEntry.COLUMN_STOCK_NAME,
                StockContract.StockEntry.COLUMN_STOCK_PRICE,
                StockContract.StockEntry.COLUMN_STOCK_QUANTITY,
                StockContract.StockEntry.COLUMN_STOCK_CATEGORY,
                StockContract.StockEntry.COLUMN_STOCK_SOLD
        };

        return new CursorLoader(this,
                StockContract.StockEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mStockCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mStockCursorAdapter.swapCursor(null);
    }
}
