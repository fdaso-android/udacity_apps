package com.example.frederickodaso.bostontourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by frederickodaso on 2/5/17.
 */

public class LocationAdapter extends FragmentPagerAdapter {

    // Declaration of member variables
    private Context mContext; // Has the context of the activity that is hosting the fragment
    private String[] tabTitles; // Stores the string values of the titles of each tab

    public LocationAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        tabTitles = new String[] {mContext.getString(R.string.category_parks), mContext.getString(R.string.category_museums),
        mContext.getString(R.string.category_sites), mContext.getString(R.string.category_food)};
    }

    @Override
    public Fragment getItem(int position) {
        // TODO: Add category fragments once their activities and individual respective fragments are created and defined
        if (position == 0) {
            return new ParksFragment();
        } else if (position == 1){
            return new MuseumsFragment();
        } else if (position == 2) {
            return new ActivitiesFragment();
        } else {
            return new FoodFragment();
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
