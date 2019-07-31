package com.example.android.secrethands.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.secrethands.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment {


    int i;
    @SuppressLint("ValidFragment")
    public IntroFragment(int i) {
        this.i = i;
    }

    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_intro, container, false);
        TextView textView=(TextView)view.findViewById(R.id.text);
        if(i==0){
            textView.setText("Here we focus on improving your overall mental health");
        }
        else if(i==1){
            textView.setText("We Suggest You With various Daily Routines that eventualy help you get over bad moods");
        }
        else if(i==2){
            textView.setText("Doctors can talk to you wherever you are ");
        }
        return view;
    }

}
