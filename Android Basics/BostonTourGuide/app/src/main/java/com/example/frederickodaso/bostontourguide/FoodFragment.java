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
public class FoodFragment extends Fragment {


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dest_list, container, false);

        final ArrayList<Destination> destinations = new ArrayList<>();
        destinations.add(new Destination(getString(R.string.mei_mei), getString(R.string.mei_mei_description),
                R.drawable.meimei, getString(R.string.mei_mei_url)));
        destinations.add(new Destination(getString(R.string.five_napkin_burger), getString(R.string.five_napkin_burger_description),
                R.drawable.fivenapkin, getString(R.string.five_napkin_burger_url)));
        destinations.add(new Destination(getString(R.string.chicken_and_rice_guys), getString(R.string.chicken_and_rice_guys_description),
                R.drawable.cnrguys, getString(R.string.chicken_and_rice_guys_url)));
        destinations.add(new Destination(getString(R.string.clover_food_lab), getString(R.string.clover_food_lab_description),
                R.drawable.clover, getString(R.string.clover_food_lab_url)));
        destinations.add(new Destination(getString(R.string.bon_me), getString(R.string.bon_me_description),
                R.drawable.bonme, getString(R.string.bon_me_url)));
        destinations.add(new Destination(getString(R.string.legal_sea_foods), getString(R.string.legal_sea_food_description),
                R.drawable.legalseafoods, getString(R.string.legal_sea_foods_url)));

        DestinationAdapter adapter = new DestinationAdapter(getActivity(), destinations, R.color.colorFoodBackground);

        ListView listView = (ListView) rootView.findViewById(R.id.dest_list);

        listView.setAdapter(adapter);

        return rootView;
    }

}
