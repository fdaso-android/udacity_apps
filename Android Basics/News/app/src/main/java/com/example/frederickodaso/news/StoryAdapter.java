package com.example.frederickodaso.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by frederickodaso on 2/7/17.
 */

public class StoryAdapter extends ArrayAdapter<Story> {

    public StoryAdapter(Context context, List<Story> stories) {
        super(context, 0, stories);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainActivity.ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
            holder = new MainActivity.ViewHolder();

            holder.articleTitleTextView = (TextView) convertView.findViewById(R.id.articleTitle_textView);
            holder.newsSectionTextView = (TextView) convertView.findViewById(R.id.newsSection_textView);
            holder.webPublicationDateTextView = (TextView) convertView.findViewById(R.id.webPublicationDate_textView);
            convertView.setTag(holder);
        } else {
            holder = (MainActivity.ViewHolder) convertView.getTag();
        }

        Story story = getItem(position);

        holder.articleTitleTextView.setText(story.getArticleTitle());
        holder.newsSectionTextView.setText(story.getNewsSection());
        holder.webPublicationDateTextView.setText(story.getWebPublicationDate());

        return convertView;
    }
}
