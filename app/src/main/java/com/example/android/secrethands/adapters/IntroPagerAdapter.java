package com.example.android.secrethands.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.secrethands.fragments.IntroFragment;
import com.example.android.secrethands.fragments.Schedule;

/**
 * Created by Aly on 7/31/2019.
 */

public class IntroPagerAdapter extends FragmentPagerAdapter {
    public static final int numberOfPages=3;
    IntroFragment introFragment1;
    IntroFragment introFragment2;
    IntroFragment introFragment3;
    public IntroPagerAdapter(FragmentManager fm) {
        super(fm);

        introFragment1=new IntroFragment(0);
        introFragment2=new IntroFragment(1);
        introFragment3=new IntroFragment(2);


    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return introFragment1;

            case 1:
                return introFragment2;
            case 2:
                return introFragment3;

            default:
                return introFragment1;
        }

    }

    @Override
    public int getCount() {
        return numberOfPages;
    }


}
