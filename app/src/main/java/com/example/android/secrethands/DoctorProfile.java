package com.example.android.secrethands;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.secrethands.adapters.AvailableSessionViewPager;
import com.example.android.secrethands.adapters.ScheduleViewPager;

public class DoctorProfile extends AppCompatActivity {
    private ViewPager viewPager;
    private AvailableSessionViewPager viewPagerAdapter;
    private TabLayout tabLayout;
    String ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        ID=getIntent().getStringExtra("ID");

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPagerAdapter = new AvailableSessionViewPager(getSupportFragmentManager(),ID);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);


    }
}
