package com.example.android.secrethands.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.secrethands.DoctorProfile;
import com.example.android.secrethands.R;
import com.example.android.secrethands.adapters.DoctorListAdapter;
import com.example.android.secrethands.datastructures.Doctor;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseDoctor extends Fragment {

    private DoctorListAdapter adapter;
    private ArrayList<Doctor> doctors;
    private ListView listView;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;

    public ChooseDoctor() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mMessageDatabaseReference.addChildEventListener(mChildEventListener);
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        mMessageDatabaseReference=mFirebaseDatabase.getReference().child("Users");
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    if(dataSnapshot.child("type").getValue(long.class)==(long)2){
                        Doctor doctor = dataSnapshot.getValue(Doctor.class);

                        doctors.add(doctor);
                        adapter.notifyDataSetChanged();



                    }

                    //}



                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            //  mMessageDatabaseReference.addChildEventListener(mChildEventListener);

        }
        mMessageDatabaseReference.addChildEventListener(mChildEventListener);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMessageDatabaseReference.removeEventListener(mChildEventListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_doctor, container, false);
      //  mFirebaseDatabase= FirebaseDatabase.getInstance();

       doctors=new ArrayList<>();
       adapter=new DoctorListAdapter(getContext(),doctors);
       listView=(ListView)view.findViewById(R.id.doctor_list_view);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(
               new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       Intent intent=new Intent(getContext(), DoctorProfile.class);
                       intent.putExtra("ID",doctors.get(i).getID());
                       startActivity(intent);
                   }
               }
       );

      //  mMessageDatabaseReference=mFirebaseDatabase.getReference().child("Users");





        return view;
    }

}
