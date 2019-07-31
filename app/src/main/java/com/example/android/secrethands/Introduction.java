package com.example.android.secrethands;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.secrethands.adapters.IntroPagerAdapter;
import com.example.android.secrethands.adapters.ScheduleViewPager;

public class Introduction extends AppCompatActivity {
    private ViewPager viewPager;
    private IntroPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPagerAdapter = new IntroPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        Button start=(Button)findViewById(R.id.login);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Introduction.this,Signin.class);
                startActivity(intent);
            }
        });
    }
}
