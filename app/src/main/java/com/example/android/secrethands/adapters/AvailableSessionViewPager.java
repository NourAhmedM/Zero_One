package com.example.android.secrethands.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.secrethands.fragments.About;
import com.example.android.secrethands.fragments.AvailableSessions;
import com.example.android.secrethands.fragments.Schedule;

/**
 * Created by Aly on 7/30/2019.
 */

public class AvailableSessionViewPager extends FragmentPagerAdapter {
    public static final int numberOfPages=2;
    String ID;

    About about;
    AvailableSessions availableSessions;
    public AvailableSessionViewPager(FragmentManager fm,String ID) {
        super(fm);
        this.ID=ID;

        about=new About(ID);
        availableSessions=new AvailableSessions(ID);


    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return about;
            case 1:
              return  availableSessions;


            default:
                return about;
        }

    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "About";
            case 1:
                return "Available Session";

            default:
                return "About";
        }
    }
}

