package com.example.frederickodaso.booklisting;

import android.graphics.drawable.Drawable;

/**
 * Created by frederickodaso on 2/6/17.
 */

public class Book {

    private String mAuthorName;
    private String mBookTitle;
    private String mBookDescription;
    private Drawable mBookThumbnail;

    public Book(String authorName, String bookTitle, String bookDescription, Drawable bookThumbnail) {
        mAuthorName = authorName;
        mBookTitle = bookTitle;
        mBookDescription = bookDescription;
        mBookThumbnail = bookThumbnail;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getBookDescription() {
        return mBookDescription;
    }

    public Drawable getBookThumbnail() {
        return mBookThumbnail;
    }
}
