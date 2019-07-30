package com.example.android.secrethands.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.secrethands.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {
    String uID;


    public About() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public About(String uID) {
        this.uID = uID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
