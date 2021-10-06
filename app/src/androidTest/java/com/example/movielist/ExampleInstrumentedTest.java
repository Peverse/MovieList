package com.example.movielist;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Uri added;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.movielist", appContext.getPackageName());
    }

    @Test
    public void addMovie() {
        ContentValues values = new ContentValues();
        values.put("title", "test");
        values.put("genre", "test");
        values.put("year", "test");
        ContentResolver resolver = InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver();
        added = resolver.insert(MovieContentProvider.CONTENT_URI, values);

        Cursor cursor = resolver.query(added, null, null, null);
        cursor.moveToLast();
        assertEquals(cursor.getString(1), "test");
    }

    @Test
    public void deleteMovie() {
        ContentResolver resolver = InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver();

        Cursor cursor = resolver.query(MovieContentProvider.CONTENT_URI, null, null, null);
        cursor.moveToLast();
        Integer row = cursor.getInt(0);
        assertEquals(1, resolver.delete(MovieContentProvider.CONTENT_URI, "ID=?", new String[]{String.valueOf(row)}));
    }
}