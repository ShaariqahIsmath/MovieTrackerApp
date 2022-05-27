package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class EditMovies extends AppCompatActivity {

    public static final String TAG = "EditMovies";
    ArrayList<String> list;
    ArrayAdapter adapter;
    DBHelper db;
    ListView movieList;
    String S;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);

        db = new DBHelper(this);
        list = new ArrayList<>();
        movieList = findViewById(R.id.listView);
        this.movieList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.movieList.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, "onItemClick: " + position);
            S = ((CheckedTextView) movieList.getChildAt(position)).getText().toString();

        });

       movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent ints = new Intent(EditMovies.this, MovieDetails.class);
               ints.putExtra("Title",list.get(position));
               startActivity(ints);

           }
       });
        viewData();

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