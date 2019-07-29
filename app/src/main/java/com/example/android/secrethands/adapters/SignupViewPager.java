package com.example.android.secrethands.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.secrethands.fragments.SignupDoctor;
import com.example.android.secrethands.fragments.SignupUser;

/**
 * Created by Aly on 7/29/2019.
 */

public class SignupViewPager  extends FragmentPagerAdapter {
    public static final int numberOfPages=2;

    SignupDoctor signupDoctor;
    SignupUser signupUser;
    public SignupViewPager(FragmentManager fm) {
        super(fm);

       signupDoctor=new SignupDoctor();
       signupUser=new SignupUser();


    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return signupUser;

            case 1:
                return signupDoctor;

            default:
                return signupUser;
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
                return "Signup User";
            case 1:
                return "Signup Doctor";

            default:
                return "Signup User";
        }
    }
}
