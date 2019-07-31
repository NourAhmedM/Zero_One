package com.example.android.secrethands.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.secrethands.R;
import com.example.android.secrethands.adapters.SessionsPatientPresDoctorProfile;
import com.example.android.secrethands.datastructures.*;
import com.example.android.secrethands.datastructures.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableSessions extends Fragment {
    String uID;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    ArrayList<Session> sessions;
    SessionsPatientPresDoctorProfile sessionsPatientPresDoctorProfile;

    public AvailableSessions() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public AvailableSessions(String uID) {
        this.uID = uID;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_available_sessions, container, false);
        sessions=new ArrayList<>();
        sessionsPatientPresDoctorProfile=new SessionsPatientPresDoctorProfile(getContext(),0,sessions);
        ListView listView=(ListView)view.findViewById(R.id.list);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users").child(uID).child("Sessions");
      //  Toast.makeText(getContext(), uID, Toast.LENGTH_SHORT).show();


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
           //     Toast.makeText(getContext(),dataSnapshot.toString(),Toast.LENGTH_SHORT).show();
             //   Toast.makeText(getContext(),"blalblabla",Toast.LENGTH_SHORT).show();
                com.example.android.secrethands.datastructures.Session oneSession=dataSnapshot.getValue(com.example.android.secrethands.datastructures.Session.class);
                sessions.add(oneSession);
                sessionsPatientPresDoctorProfile.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setAdapter(sessionsPatientPresDoctorProfile);


        return view;
    }

}
