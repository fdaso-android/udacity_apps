package com.example.frederickodaso.news;

/**
 * Created by frederickodaso on 2/7/17.
 */

public class Story {

    private String mArticleTitle;
    private String mNewsSection;
    private String mWebPublicationDate;
    private String mUrl;

    public Story(String articleTitle, String newsSection, String webPublicationDate, String url) {
        mArticleTitle = articleTitle;
        mNewsSection = newsSection;
        mWebPublicationDate = webPublicationDate;
        mUrl = url;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getNewsSection() {
        return mNewsSection;
    }

    public String getUrl() {
        return mUrl;
    }
}
