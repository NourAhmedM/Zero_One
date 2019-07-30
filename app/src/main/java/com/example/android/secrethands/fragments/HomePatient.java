package com.example.android.secrethands.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.secrethands.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePatient extends Fragment {


    public HomePatient() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home_patient, container, false);
       // ImageView imageView=(ImageView)
        return view;
    }

}
