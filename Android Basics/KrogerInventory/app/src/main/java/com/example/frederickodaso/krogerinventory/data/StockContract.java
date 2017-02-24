package com.example.frederickodaso.krogerinventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by frederickodaso on 2/8/17.
 */

public final class StockContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.stock";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_STOCK = "stock";

    public StockContract() {
    }

    public static abstract class StockEntry implements BaseColumns {

        public static final String TABLE_NAME = "stock";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_STOCK_SUPPLIER = "supplier";
        public static final String COLUMN_STOCK_NAME = "name";
        public static final String COLUMN_STOCK_QUANTITY = "quantity";
        public static final String COLUMN_STOCK_PRICE = "price";
        public static final String COLUMN_STOCK_CATEGORY = "category";
        public static final String COLUMN_STOCK_SOLD = "sold";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STOCK);

        public static final int TYPE_BABY_ITEMS = 0;
        public static final int TYPE_BAKING = 1;
        public static final int TYPE_BEVERAGES = 2;
        public static final int TYPE_BREAD_BAKERY = 3;
        public static final int TYPE_CANNED_GOODS = 4;
        public static final int TYPE_CEREAL = 5;
        public static final int TYPE_DAIRY = 6;
        public static final int TYPE_DELI = 7;
        public static final int TYPE_FROZEN_FOODS = 8;
        public static final int TYPE_MEATS = 9;
        public static final int TYPE_MISC = 10;



        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STOCK;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STOCK;
    }
}
