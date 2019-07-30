package com.example.android.secrethands.fragments;


import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.secrethands.AddSession;
import com.example.android.secrethands.R;
import com.example.android.secrethands.adapters.SessionAdapterDoctor;
import com.example.android.secrethands.localdatabase.BookContract;
import com.example.android.secrethands.localdatabase.BookCursorAdapter;
import com.example.android.secrethands.localdatabase.BookDbHelper;
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
public class Session extends Fragment  {
    FloatingActionButton fab;
    ListView listView;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;




    private ArrayList<com.example.android.secrethands.datastructures.Session> sessions;
    private SessionAdapterDoctor sessionAdapterDoctor;
    public Session() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_session, container, false);

        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid()).child("Sessions");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.example.android.secrethands.datastructures.Session oneSession=dataSnapshot.getValue(com.example.android.secrethands.datastructures.Session.class);
                sessions.add(oneSession);
                sessionAdapterDoctor.notifyDataSetChanged();
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
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        sessions=new ArrayList<>();
        sessionAdapterDoctor=new SessionAdapterDoctor(getContext(),sessions);

        listView=(ListView)view.findViewById(R.id.list);
        listView.setAdapter(sessionAdapterDoctor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddSession.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
