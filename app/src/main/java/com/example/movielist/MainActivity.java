package com.example.movielist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MovieDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        dbHelper = new MovieDatabaseHelper(this);

        setSupportActionBar(toolbar);
        populateListView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AddData("Avengers");
                populateListView();
            }
        });

        ListView list = findViewById(R.id.movielist);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        populateListView();
    }

    public void AddData(String newEntry){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", newEntry);
        getContentResolver().insert(MovieContentProvider.CONTENT_URI, contentValues);
    }

    public void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in listview.");
        ListView listView = findViewById(R.id.movielist);

        Cursor data = getContentResolver().query(MovieContentProvider.CONTENT_URI, null, null, null, null);
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
            ids.add(data.getInt(0));
        }

        CustomAdapter adapter2 = new CustomAdapter(this, listData, ids);
        listView.setAdapter(adapter2);

        //ListAdapter adapter = new ArrayAdapter<>(this, R.layout.item_list, listData);
        //listView.setAdapter(adapter);

        data.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}