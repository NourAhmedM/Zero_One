package com.example.android.secrethands.localdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aly on 7/29/2019.
 */

public class BookDbHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="books.db";
    private static final int DATABASE_VERSION=1;

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATING_BOOK_TABLE= "CREATE TABLE "+ BookContract.BookEntry.TABLE_NAME +"("
                + BookContract.BookEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookContract.BookEntry.COLUMN_BOOK_NAME +" TEXT NOT NULL, "
                + BookContract.BookEntry.COLUMN_NUMBER_OF_PAGES+" INTEGER NOT NULL,"
                + BookContract.BookEntry.COLUMN_NUMBER_OF_PAGES_PER_DAY+" INTEGER NOT NULL);";

        db.execSQL(CREATING_BOOK_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
