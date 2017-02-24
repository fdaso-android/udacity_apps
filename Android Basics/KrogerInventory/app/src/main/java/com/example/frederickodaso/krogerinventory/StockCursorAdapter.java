package com.example.frederickodaso.krogerinventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.frederickodaso.krogerinventory.data.StockContract;

/**
 * Created by frederickodaso on 2/8/17.
 */

public class StockCursorAdapter extends CursorAdapter {

    private int mQuantity;
    private int mSold;
    private Uri mCurrentItem;

    public StockCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = View.inflate(context,R.layout.stock_item, null);
        final ViewHolder holder = new ViewHolder();

        holder.name = (TextView) view.findViewById(R.id.name);
        holder.price = (TextView) view.findViewById(R.id.price);
        holder.quantity = (TextView) view.findViewById(R.id.quantity);
        holder.sold = (TextView) view.findViewById(R.id.sold);
        holder.sale = (Button) view.findViewById(R.id.sale);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        final int positon = cursor.getPosition();

        holder.sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToPosition(positon);
                mCurrentItem = ContentUris.withAppendedId(StockContract.StockEntry.CONTENT_URI, cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                mQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                mSold = cursor.getInt(cursor.getColumnIndex(StockContract.StockEntry.COLUMN_STOCK_SOLD));
                if (cursor.getInt(cursor.getColumnIndexOrThrow("quantity")) == 0) {
                    mQuantity = 0;
                    ContentValues values = new ContentValues();
                    values.put(StockContract.StockEntry.COLUMN_STOCK_SUPPLIER, cursor.getString(cursor.getColumnIndexOrThrow(StockContract.StockEntry.COLUMN_STOCK_SUPPLIER)));
                    values.put(StockContract.StockEntry.COLUMN_STOCK_NAME, cursor.getString(cursor.getColumnIndexOrThrow(StockContract.StockEntry.COLUMN_STOCK_NAME)));
                    values.put(StockContract.StockEntry.COLUMN_STOCK_PRICE, cursor.getString(cursor.getColumnIndexOrThrow(StockContract.StockEntry.COLUMN_STOCK_PRICE)));
                    values.put(StockContract.StockEntry.COLUMN_STOCK_QUANTITY, mQuantity);
                    values.put(StockContract.StockEntry.COLUMN_STOCK_SOLD, mSold);
                    context.getContentResolver().update(mCurrentItem, values, null, null);
                } else {
                    mQuantity -= 1;
                    mSold += 1;
                    ContentValues values = new ContentValues();
                    values.put(StockContract.StockEntry.COLUMN_STOCK_SUPPLIER, cursor.getString(cursor.getColumnIndexOrThrow(StockContract.StockEntry.COLUMN_STOCK_SUPPLIER)));
                    values.put(StockContract.StockEntry.COLUMN_STOCK_NAME, cursor.getString(cursor.getColumnIndexOrThrow(StockContract.StockEntry.COLUMN_STOCK_NAME)));
                    values.put(StockContract.StockEntry.COLUMN_STOCK_PRICE, cursor.getString(cursor.getColumnIndexOrThrow(StockContract.StockEntry.COLUMN_STOCK_PRICE)));
                    values.put(StockContract.StockEntry.COLUMN_STOCK_QUANTITY, mQuantity);
                    values.put(StockContract.StockEntry.COLUMN_STOCK_SOLD, mSold);
                    context.getContentResolver().update(mCurrentItem, values, null, null);
                }
            }
        });

        holder.name.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        String priceDisplay = String.format(context.getResources().getString(R.string.price), Integer.toString(cursor.getInt(cursor.getColumnIndexOrThrow("price"))));
        holder.price.setText(priceDisplay);
        String quantityDisplay = String.format(context.getResources().getString(R.string.quantity), Integer.toString(cursor.getInt(cursor.getColumnIndexOrThrow("quantity"))));
        holder.quantity.setText(quantityDisplay);
        String soldDisplay = String.format(context.getResources().getString(R.string.sold), Integer.toString(cursor.getInt(cursor.getColumnIndexOrThrow("sold"))));
        holder.sold.setText(soldDisplay);
    }

    static class ViewHolder {
        TextView name;
        TextView price;
        TextView quantity;
        TextView sold;
        Button sale;
    }

}
