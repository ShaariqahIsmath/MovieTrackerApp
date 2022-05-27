package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DisplayMovies extends AppCompatActivity {

    public static final String TAG = "DisplayMovies";
    ArrayList<String> list;
    ArrayAdapter adapter;
    DBHelper db;
    Button fav;
    String S;

    ListView movieList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        db = new DBHelper(this);
        list = new ArrayList<>();
        fav = findViewById(R.id.fav);
        movieList = findViewById(R.id.listView);
        viewData();

        this.movieList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.movieList.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, "onItemClick: " +position);
            S = ((CheckedTextView) movieList.getChildAt(position)).getText().toString();
          //  Toast.makeText(DisplayMovies.this, "", Toast.LENGTH_SHORT).show();
        });



        fav.setOnClickListener(v -> {
            SparseBooleanArray sp = movieList.getCheckedItemPositions();
            StringBuilder sb= new StringBuilder();

            for(int i=0;i<sp.size();i++){
                if(sp.valueAt(i)==true){
                    String s = S;
                    sb = sb.append(" "+s);
                }

            }
            AddFavouriteMovie(null, null, null, null, null, null, String.valueOf(sb));
            finish();
            startActivity(getIntent());

        });
    }

    public void AddFavouriteMovie(String movieName, String releaseYear, String movieDirector, String crew, String rating, String reviews, String favourite){
        boolean insertData = db.addFavouriteMovie(movieName, releaseYear, movieDirector, crew, rating, reviews, favourite);

        if (insertData == true){
            Toast.makeText(DisplayMovies.this, "Data has been saved successfully!!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(DisplayMovies.this, "Empty Fields are NOT allowed!", Toast.LENGTH_LONG).show();

        }
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

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, list);
            movieList.setAdapter(adapter);
        }
    }

    public void getToHomePage(View v){

        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);

    }

}