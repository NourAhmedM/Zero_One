package com.example.android.secrethands.localdatabase;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.secrethands.R;

/**
 * Created by Aly on 7/30/2019.
 */

public class BookCursorAdapter extends CursorAdapter {
    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.book_list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name=(TextView)view.findViewById(R.id.book_name);
        TextView page=(TextView)view.findViewById(R.id.book_page);
        int nameIndex=cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_NAME);
        int pagesIndex=cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NUMBER_OF_PAGES);
        String bookName=cursor.getString(nameIndex);
        int bookPage= cursor.getInt(pagesIndex);
        name.setText(bookName);
        page.setText(String.valueOf(bookPage));

    }
}



/* mDbHelper=new BookDbHelper(getContext());
         ContentValues contentValues=new ContentValues();
         contentValues.put(BookContract.BookEntry.COLUMN_BOOK_NAME,"Read Book");
         contentValues.put(BookContract.BookEntry.COLUMN_NUMBER_OF_PAGES,30);
         contentValues.put(BookContract.BookEntry.COLUMN_NUMBER_OF_PAGES_PER_DAY, 5);
         SQLiteDatabase database= mDbHelper.getWritableDatabase();
         long id= database.insert(BookContract.BookEntry.TABLE_NAME,null,contentValues);
         Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();*/
