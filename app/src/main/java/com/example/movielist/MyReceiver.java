package com.example.movielist;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        //if data is received and is a valid movie, it is added to the database.
        String data = intent.getStringExtra("data");
        if(data != null) {
            Gson gson = new Gson();
            Movie movie = gson.fromJson(data, Movie.class);

            if(movie.Title != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("title", movie.Title);
                contentValues.put("year", movie.Year);
                contentValues.put("genre", movie.Genre);
                context.getContentResolver().insert(MovieContentProvider.CONTENT_URI, contentValues);
            }
        }

        //the user is returned to the main activity.
        Intent i = new Intent();
        i.setClassName("com.example.movielist", "com.example.movielist.MainActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}