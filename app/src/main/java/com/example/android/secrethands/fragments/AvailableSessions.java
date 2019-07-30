package com.example.android.secrethands.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.secrethands.R;
import com.example.android.secrethands.adapters.SessionAdapter;
import com.example.android.secrethands.datastructures.Session;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableSessions extends Fragment {


    public AvailableSessions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_available_sessions, container, false);

        ListView sessionList=(ListView) view.findViewById(R.id.availablesessions);
        ArrayList<Session> sessionsArray=new ArrayList<>();
        SessionAdapter adapter=new SessionAdapter(getContext(),0,sessionsArray);
        sessionList.setAdapter(adapter);


        return view;
    }

}
