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
import com.example.android.secrethands.localdatabase.BookContract;
import com.example.android.secrethands.localdatabase.BookCursorAdapter;
import com.example.android.secrethands.localdatabase.BookDbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class Session extends Fragment  {
    FloatingActionButton fab;
    ListView listView;
    BookDbHelper mDbHelper;
    BookCursorAdapter mCursorAdapter;

    private static final int PET_LOADER=0;



    public Session() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_session, container, false);
        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        listView=(ListView)view.findViewById(R.id.list);
        mCursorAdapter=new BookCursorAdapter(getContext(),null);
        listView.setAdapter(mCursorAdapter);
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
