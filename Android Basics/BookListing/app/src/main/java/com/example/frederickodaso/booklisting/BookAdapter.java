package com.example.frederickodaso.booklisting;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by frederickodaso on 2/6/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView authorsNameTextView = (TextView) convertView.findViewById(R.id.authorsName_textView);
        authorsNameTextView.setText(currentBook.getAuthorName());

        TextView bookTitleTextView = (TextView) convertView.findViewById(R.id.bookTitle_textView);
        bookTitleTextView.setText(currentBook.getBookTitle());

        TextView bookDescriptionTextView = (TextView) convertView.findViewById(R.id.bookDescription_textView);
        bookDescriptionTextView.setText(currentBook.getBookDescription());

        ImageView stockBookImageView = (ImageView)convertView.findViewById(R.id.stock_imageView);
        if (currentBook.getBookThumbnail() == null) {
            stockBookImageView.setImageResource(R.drawable.book);
        } else {
            stockBookImageView.setImageDrawable(currentBook.getBookThumbnail());
        }


        return convertView;
    }
}
