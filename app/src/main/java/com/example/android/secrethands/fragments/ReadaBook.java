package com.example.android.secrethands.fragments;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.secrethands.R;
import com.example.android.secrethands.localdatabase.BookContract;
import com.example.android.secrethands.localdatabase.BookDbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadaBook extends Fragment {


    public ReadaBook() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reada_book, container, false);


        ImageView startbook =(ImageView) view.findViewById(R.id.beginreadingbutton);
        final EditText bookname = (EditText) view.findViewById(R.id.nameofthebok);
        final EditText numofpages = (EditText) view.findViewById(R.id.numberofbookspages);
        final EditText numofpagesperday = (EditText) view.findViewById(R.id.pagesperday);

        startbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookDbHelper mDbHelper = new BookDbHelper(getContext());
                ContentValues contentValues=new ContentValues();
                contentValues.put(BookContract.BookEntry.COLUMN_BOOK_NAME,bookname.getText().toString());
                contentValues.put(BookContract.BookEntry.COLUMN_NUMBER_OF_PAGES, Integer.valueOf(numofpages.getText().toString()));
                contentValues.put(BookContract.BookEntry.COLUMN_NUMBER_OF_PAGES_PER_DAY, Integer.valueOf(numofpagesperday.getText().toString()));
                SQLiteDatabase database= mDbHelper.getWritableDatabase();
                long id= database.insert(BookContract.BookEntry.TABLE_NAME,null,contentValues);
                Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();


            }
        });

        return view;

    }



}
