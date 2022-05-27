package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Ratings extends AppCompatActivity {

    public static final String TAG = "Ratings";
    ArrayList<String> list;
    ArrayAdapter adapter;
    DBHelper db;
    ListView movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        db = new DBHelper(this);
        list = new ArrayList<>();
        movieList = findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(Ratings.this, android.R.layout.simple_list_item_single_choice, list);
        viewData();

        movieList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void viewData() {
        Cursor data = db.getListContents();
        if (data.getCount() == 0) {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (data.moveToNext()) {
                list.add(data.getString(1));
                Collections.sort(list);
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            movieList.setAdapter(adapter);
        }
    }

    public void getToHomePage(View v){

        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);

    }
}