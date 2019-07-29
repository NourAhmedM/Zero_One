package com.example.android.secrethands;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.secrethands.adapters.SignupViewPager;

public class Signup extends AppCompatActivity {
    private ViewPager viewPager;
    private SignupViewPager viewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Signup Page");
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPagerAdapter = new SignupViewPager(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }
}
