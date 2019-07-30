package com.example.android.secrethands.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.secrethands.R;
import com.example.android.secrethands.adapters.DoctorListAdapter;
import com.example.android.secrethands.datastructures.Doctor;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseDoctor extends Fragment {

    private DoctorListAdapter adapter;
    private ArrayList<Doctor> doctors;
    private ListView listView;


    public ChooseDoctor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_doctor, container, false);
       doctors=new ArrayList<>();
       adapter=new DoctorListAdapter(getContext(),doctors);
       listView=(ListView)view.findViewById(R.id.doctor_list_view);
       listView.setAdapter(adapter);



        return view;
    }

}
