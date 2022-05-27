package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity {

    public static final String TAG = "Favourites";

    DBHelper db;
    Button save;
    ListView favList;
    String S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        db = new DBHelper(this);
        save = (Button) findViewById(R.id.save);
        favList = (ListView) findViewById(R.id.favListView);


        this.favList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.favList.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(TAG, "onItemClick: " + position);
            S = ((CheckedTextView) favList.getChildAt(position)).getText().toString();

        });

        this.displayFavouriteMovie();


        save.setOnClickListener(v -> {
            SparseBooleanArray sp = favList.getCheckedItemPositions();

            for (int i = 0; i < favList.getCount(); i++){
                if (!sp.get(i)){
                    Boolean checker = db.removeFromFavourites(S);
                    Toast.makeText(this, "Movie has been removed from Favourites!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private void displayFavouriteMovie(){
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        Cursor cursor = db.getFavMovie();
        int selectedMovie = 1;

       /* if (cursor.getCount() == 0){
            Toast.makeText(Favourites.this, "No favourites :(", Toast.LENGTH_SHORT).show();
            return;
        }*/
        ArrayList<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(7));
            favList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, list));
       //     favList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            for (int i = 0; i < selectedMovie; i++){
                favList.setItemChecked(i,true);
            }
            selectedMovie++;
        }
    }

    public void getToHomePage(View v){

        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);

    }










}