package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToFirstDestination(View v) {

        Intent int1 = new Intent(MainActivity.this, RegisterMovie.class);
        startActivity(int1);

    }

    public void goToSecondDestination(View v) {

        Intent int2 = new Intent(MainActivity.this, DisplayMovies.class);
        startActivity(int2);

    }

    public void goToThirdDestination(View v) {

        Intent int3 = new Intent(MainActivity.this, Favourites.class);
        startActivity(int3);

    }

    public void goToFourthDestination(View v) {

        Intent int4 = new Intent(MainActivity.this, EditMovies.class);
        startActivity(int4);

    }

    public void goToFifthDestination(View v) {

        Intent int5 = new Intent(MainActivity.this, Search.class);
        startActivity(int5);

    }

    public void goToSixthDestination(View v) {

        Intent int6 = new Intent(MainActivity.this, Ratings.class);
        startActivity(int6);

    }
}