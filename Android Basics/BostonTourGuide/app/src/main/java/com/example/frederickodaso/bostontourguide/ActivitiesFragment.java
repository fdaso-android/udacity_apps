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
public class ActivitiesFragment extends Fragment {


    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dest_list, container, false);

        final ArrayList<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination(getString(R.string.freedom_trail), getString(R.string.ft_description),
                R.drawable.freedom, getString(R.string.ft_url)));
        destinations.add(new Destination(getString(R.string.boston_harbor), getString(R.string.bh_description),
                R.drawable.harbor, getString(R.string.bh_url)));
        destinations.add(new Destination(getString(R.string.faneuil_hall), getString(R.string.fh_description),
                R.drawable.faneuil, getString(R.string.fh_url)));
        destinations.add(new Destination(getString(R.string.new_england_aquarium), getString(R.string.nea_description),
                R.drawable.neaq, getString(R.string.nea_url)));
        destinations.add(new Destination(getString(R.string.fenway_park), getString(R.string.fp_description),
                R.drawable.fenway, getString(R.string.fp_url)));
        destinations.add(new Destination(getString(R.string.paul_revere_house), getString(R.string.prh_description),
                R.drawable.prh, getString(R.string.prh_url)));

        DestinationAdapter adapter = new DestinationAdapter(getActivity(), destinations, R.color.colorActivitiesBackground);

        ListView listView = (ListView) rootView.findViewById(R.id.dest_list);

        listView.setAdapter(adapter);

        return rootView;
    }

}
