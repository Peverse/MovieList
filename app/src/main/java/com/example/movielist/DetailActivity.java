package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Cursor data = getContentResolver().query(MovieContentProvider.CONTENT_URI, null, "ID=?", new String[]{String.valueOf(getIntent().getLongExtra("id", 0))}, null);
        while(data.moveToNext()) {
            Log.d("detail", data.getString(1));
        }
    }
}