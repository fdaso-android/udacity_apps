package com.example.frederickodaso.krogerinventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by frederickodaso on 2/8/17.
 */

public class StockDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test5.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + StockContract.StockEntry.TABLE_NAME + " (" +
            StockContract.StockEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            StockContract.StockEntry.COLUMN_STOCK_SUPPLIER + " TEXT NOT NULL," +
            StockContract.StockEntry.COLUMN_STOCK_NAME + " TEXT NOT NULL, " +
            StockContract.StockEntry.COLUMN_STOCK_PRICE + " INTEGER NOT NULL, " +
            StockContract.StockEntry.COLUMN_STOCK_QUANTITY + " INTEGER NOT NULL DEFAULT 0, " +
            StockContract.StockEntry.COLUMN_STOCK_CATEGORY + " INTEGER NOT NULL, " +
            StockContract.StockEntry.COLUMN_STOCK_SOLD + " INTEGER DEFAULT 0); " ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StockContract.StockEntry.TABLE_NAME;

    public StockDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
