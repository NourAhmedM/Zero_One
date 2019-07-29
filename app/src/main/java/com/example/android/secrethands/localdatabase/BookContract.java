package com.example.android.secrethands.localdatabase;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Aly on 7/29/2019.
 */

public class BookContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.secrethands";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_BOOKS = "books";
    public static final class BookEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);
        public final static String TABLE_NAME="Books";
        public final static String _ID=BaseColumns._ID;
        public final static String COLUMN_BOOK_NAME="name";
        public final static String COLUMN_NUMBER_OF_PAGES="NumberOfPage";

        public final static String COLUMN_NUMBER_OF_PAGES_PER_DAY="NumberOfPagePerDay";



        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;


    }

}
