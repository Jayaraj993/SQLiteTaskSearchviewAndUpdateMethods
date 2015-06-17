package com.example.emd029.sqlite_task.SlidingTablayout;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by EMD029 on 6/4/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]={"homes","events"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //set a fragment depend on a position
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new Tab1();
                break;
            case 1:
                fragment = new Tab2();
                break;
            default:
                fragment = new Tab1();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return Titles.length;
    }
}
