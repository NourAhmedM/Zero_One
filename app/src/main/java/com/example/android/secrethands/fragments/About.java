package com.example.android.secrethands.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.secrethands.R;
import com.example.android.secrethands.datastructures.Doctor;
import com.example.android.secrethands.datastructures.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {
    String uID;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView name;
    TextView ageView;
    TextView genderView;


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
        View view= inflater.inflate(R.layout.fragment_about, container, false);
        ageView=(TextView)view.findViewById(R.id.age);
        name=(TextView)view.findViewById(R.id.name);
        genderView=(TextView)view.findViewById(R.id.gender);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users").child("KNGmtCxP1TfE2NJHIGMizqJHksg2");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            //   Toast.makeText(getContext(), dataSnapshot.toString(), Toast.LENGTH_SHORT).show();

                Toast.makeText(getContext(),dataSnapshot.child("username").getValue(String.class),Toast.LENGTH_SHORT).show();

             //  long age=dataSnapshot.child("age").getValue(Long.class);
             //   String username=dataSnapshot.child("username").getValue(String.class);
               // String gender=dataSnapshot.child("gender").getValue(String.class);
                //name.setText(username);
                //genderView.setText(gender);
             //    ageView.setText(String.valueOf(age));

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

        return view;
    }

}
