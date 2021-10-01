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
        String data = intent.getStringExtra("data");
        Gson gson = new Gson();

        Movie movie = gson.fromJson(data, Movie.class);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", movie.Title);
        context.getContentResolver().insert(MovieContentProvider.CONTENT_URI, contentValues);
    }
}