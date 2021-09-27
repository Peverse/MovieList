package com.example.movielist;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class MovieContentProvider extends ContentProvider {
    public MovieContentProvider() {
    }

    private MovieDatabaseHelper dbHelper;
    static final Uri CONTENT_URI = Uri.parse("content://com.example.movielist.provider/movies");

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return dbHelper.getWritableDatabase().delete("MovieDatabase", selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long newRowId = dbHelper.getWritableDatabase().insert("MovieDatabase", null, values);
        return ContentUris.withAppendedId(CONTENT_URI, newRowId);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MovieDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return dbHelper.getReadableDatabase().query("MovieDatabase", projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return dbHelper.getWritableDatabase().update("MovieDatabase", values, selection, selectionArgs);
    }
}