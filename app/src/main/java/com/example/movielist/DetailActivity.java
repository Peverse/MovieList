package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Cursor data = getContentResolver().query(MovieContentProvider.CONTENT_URI, null, "ID=?", new String[]{String.valueOf(getIntent().getLongExtra("id", 0))}, null);
        EditText title = findViewById(R.id.titleEdit);
        EditText year = findViewById(R.id.yearEdit);

        while(data.moveToNext()) {
            title.setText(data.getString(1));
            year.setText(data.getString(0));
        }

        Button save = findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("title", String.valueOf(title.getText()));
                getContentResolver().update(MovieContentProvider.CONTENT_URI, contentValues, "ID=?", new String[]{String.valueOf(getIntent().getLongExtra("id", 0))});

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button delete = findViewById(R.id.deleteBtn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(MovieContentProvider.CONTENT_URI,"ID=?", new String[]{String.valueOf(getIntent().getLongExtra("id", 0))});

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}