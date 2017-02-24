package com.example.frederickodaso.bostontourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParksFragment extends Fragment {


    public ParksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dest_list, container, false);

        final ArrayList<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination(getString(R.string.boston_public_garden), getString(R.string.boston_public_garden_description),
                R.drawable.publicgardenboston, getString(R.string.boston_public_garden_url)));
        destinations.add(new Destination(getString(R.string.boston_common), getString(R.string.boston_common_description),
                R.drawable.bostoncommons, getString(R.string.boston_common_url)));
        destinations.add(new Destination(getString(R.string.back_bay_fens), getString(R.string.back_bay_fens_description),
                R.drawable.boston_back_bay_fens_park, getString(R.string.back_bay_fens_url)));
        destinations.add(new Destination(getString(R.string.phillips_street_park), getString(R.string.phillips_street_park_description),
                R.drawable.phillipsstreet, getString(R.string.phillips_street_park_url)));
        destinations.add(new Destination(getString(R.string.franklin_park), getString(R.string.franklin_park_description),
                R.drawable.franklinpark, getString(R.string.franklin_park_url)));
        destinations.add(new Destination(getString(R.string.east_boston_memorial_park), getString(R.string.east_boston_memorial_park_description),
                R.drawable.memorialpark, getString(R.string.east_boston_memorial_park_url)));

        DestinationAdapter adapter = new DestinationAdapter(getActivity(), destinations, R.color.colorParksBackground);

        ListView listView = (ListView) rootView.findViewById(R.id.dest_list);

        listView.setAdapter(adapter);

        return rootView;
    }

}
