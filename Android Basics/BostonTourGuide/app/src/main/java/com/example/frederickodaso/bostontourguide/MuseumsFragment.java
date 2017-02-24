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
public class MuseumsFragment extends Fragment {


    public MuseumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dest_list, container, false);

        final ArrayList<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination(getString(R.string.fine_arts), getString(R.string.fine_arts_description),
                R.drawable.mfa, getString(R.string.mfa_url)));
        destinations.add(new Destination(getString(R.string.jfk_presidential_library), getString(R.string.jfk_description),
                R.drawable.jfk, getString(R.string.jfk_url)));
        destinations.add(new Destination(getString(R.string.science), getString(R.string.science_description),
                R.drawable.ms, getString(R.string.science_url)));
        destinations.add(new Destination(getString(R.string.isabella_stewart_gardner), getString(R.string.isg_description),
                R.drawable.isgm, getString(R.string.isg_url)));
        destinations.add(new Destination(getString(R.string.uss_constitution), getString(R.string.uss_description),
                R.drawable.uss, getString(R.string.uss_url)));
        destinations.add(new Destination(getString(R.string.institute_of_contemporary_art), getString(R.string.ica_description),
                R.drawable.ica, getString(R.string.ica_url)));


        DestinationAdapter adapter = new DestinationAdapter(getActivity(), destinations, R.color.colorMuseumsBackground);

        ListView listView = (ListView) rootView.findViewById(R.id.dest_list);

        listView.setAdapter(adapter);

        return rootView;
    }

}
