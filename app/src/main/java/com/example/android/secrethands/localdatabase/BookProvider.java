package com.example.android.secrethands.localdatabase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Aly on 7/29/2019.
 */

public class BookProvider extends ContentProvider {
    /** Tag for the log messages */
    public static final String LOG_TAG = BookProvider.class.getSimpleName();
    private BookDbHelper mDbHelper;
    /** URI matcher code for the content URI for the Books table */
    private static final int BookS = 100;

    /** URI matcher code for the content URI for a single Book in the Books table */
    private static final int Book_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // TODO: Add 2 content URIs to URI matcher
        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY,BookContract.PATH_BOOKS,BookS);
        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY,BookContract.PATH_BOOKS+"/#",Book_ID);
    }

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        mDbHelper=new BookDbHelper(getContext());
        // TODO: Create and initialize a BookDbHelper object to gain access to the Books database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database=mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match=sUriMatcher.match(uri);
        switch (match)
        {
            case BookS:
                cursor= database.query(BookContract.BookEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Book_ID:
                selection= BookContract.BookEntry._ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=database.query(BookContract.BookEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("cannot query unknown URI "+uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BookS:
                return insertBook(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a Book into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertBook(Uri uri, ContentValues values) {
        String name = values.getAsString(BookContract.BookEntry.COLUMN_BOOK_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Book requires a name");
        }

        SQLiteDatabase database= mDbHelper.getWritableDatabase();
        long id= database.insert(BookContract.BookEntry.TABLE_NAME,null,values);


        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        // TODO: Insert a new Book into the Books database table with the given ContentValues

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BookS:
                return updateBook(uri, contentValues, selection, selectionArgs);
            case Book_ID:
                // For the Book_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = BookContract.BookEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateBook(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    /**
     * Update Books in the database with the given content values. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more Books).
     * Return the number of rows that were successfully updated.
     */
    private int updateBook(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }
        // If the {@link BookEntry#COLUMN_Book_NAME} key is present,
        // check that the name value is not null.


        // If the {@link BookEntry#COLUMN_Book_WEIGHT} key is present,
        // check that the weight value is valid.


        // No need to check the breed, any value is valid (including null).

        SQLiteDatabase database= mDbHelper.getWritableDatabase();
        int rows= database.update(BookContract.BookEntry.TABLE_NAME,values,selection,selectionArgs);
        if(rows!=0)
            getContext().getContentResolver().notifyChange(uri,null);

        // TODO: Update the selected Books in the Books database table with the given ContentValues

        // TODO: Return the number of rows that were affected
        return rows;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowIsDeleted;
        SQLiteDatabase database=mDbHelper.getWritableDatabase();
        final int match=sUriMatcher.match(uri);
        switch (match){
            case BookS:
                rowIsDeleted= database.delete(BookContract.BookEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case Book_ID:
                selection= BookContract.BookEntry._ID+ "=?";
                selectionArgs= new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowIsDeleted= database.delete(BookContract.BookEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for "+uri);


        }
        if(rowIsDeleted!=0) {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowIsDeleted;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BookS:
                return BookContract.BookEntry.CONTENT_LIST_TYPE;
            case Book_ID:
                return BookContract.BookEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
