package com.example.android.secrethands.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.secrethands.fragments.Schedule;
import com.example.android.secrethands.fragments.SignupDoctor;
import com.example.android.secrethands.fragments.SignupUser;

/**
 * Created by Aly on 7/29/2019.
 */

public class ScheduleViewPager  extends FragmentPagerAdapter {
    public static final int numberOfPages=3;

    Schedule scheduleToday;
    Schedule scheduleSecondday;
    Schedule scheduleThirdday;
    public ScheduleViewPager(FragmentManager fm) {
        super(fm);

       scheduleToday=new Schedule();
       scheduleSecondday=new Schedule();
       scheduleThirdday=new Schedule();


    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return scheduleToday;

            case 1:
                return scheduleSecondday;
            case 2:
                return scheduleThirdday;

            default:
                return scheduleToday;
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
                return "Today";
            case 1:
                return "Tommorow";

            default:
                return "Day After Tommorow";
        }
    }
}
