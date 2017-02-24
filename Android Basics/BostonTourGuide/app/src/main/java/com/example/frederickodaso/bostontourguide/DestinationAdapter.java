package com.example.frederickodaso.bostontourguide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by frederickodaso on 2/5/17.
 */

public class DestinationAdapter extends ArrayAdapter<Destination> {

    private int mColorResourceId;

    public DestinationAdapter(Activity context, ArrayList<Destination> destinations, int color) {
        super(context, 0, destinations);
        mColorResourceId = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.dest_item, parent, false);
        }

        final Destination currentDestination = getItem(position);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.locationName_textView);
        locationTextView.setText(currentDestination.getLocationName());

        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.locationDescription_textView);
        descriptionTextView.setText(currentDestination.getDescription());

        ImageView locationImageView = (ImageView) listItemView.findViewById(R.id.location_imageView);
        locationImageView.setImageResource(currentDestination.getImage());

        ImageView openInBrowserImageView = (ImageView) listItemView.findViewById(R.id.openInBrowser_imageView);
        openInBrowserImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(currentDestination.getURL()));
                getContext().startActivity(browserIntent);
            }
        });

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
