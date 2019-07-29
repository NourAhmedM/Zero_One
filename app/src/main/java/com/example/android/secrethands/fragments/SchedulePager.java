package com.example.android.secrethands.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.secrethands.R;
import com.example.android.secrethands.adapters.ScheduleViewPager;
import com.example.android.secrethands.adapters.SignupViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulePager extends Fragment {
    private ViewPager viewPager;
    private ScheduleViewPager viewPagerAdapter;
    private TabLayout tabLayout;


    public SchedulePager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_schedule_pager, container, false);
        viewPager = (ViewPager)view. findViewById(R.id.pager);
        viewPagerAdapter = new ScheduleViewPager(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout)view. findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
