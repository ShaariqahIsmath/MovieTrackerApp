package com.example.movies;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MovieDetails extends Activity {

    DBHelper DB;
    private EditText mName, mYear, mDirector, mCast, mReview;
    private RatingBar mRate;
    private int Id;
    private Button save, backtoDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        DB = new DBHelper(this);
        mName = (EditText)findViewById(R.id.edOne);
        mYear = (EditText)findViewById(R.id.edTwo);
        mDirector = (EditText)findViewById(R.id.edThree);
        mCast = (EditText)findViewById(R.id.edFour);
        mRate = (RatingBar) findViewById(R.id.edFive);
        mReview = (EditText)findViewById(R.id.edSix);
        save = (Button)findViewById(R.id.saved);
        backtoDisplay = findViewById(R.id.returnToEdit);


        String label = getIntent().getStringExtra("Title");
        mName.setText(label);

        Cursor data = DB.getEditDetails(label);
        while (data.moveToNext()){
            Id = data.getInt(0);
            String year = data.getString(2);
            String director = data.getString(3);
            String cast = data.getString(4);
            String rate = data.getString(5);
            String review = data.getString(6);

            mYear.setText(year);
            mDirector.setText(director);
            mCast.setText(cast);
            mRate.setRating(Float.parseFloat(rate));
            mReview.setText(review);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.UpdateDetails(Id, mName.getText().toString(), mYear.getText().toString(), mDirector.getText().toString(), mCast.getText().toString(), String.valueOf(mRate.getRating()), mReview.getText().toString());
                Toast.makeText(MovieDetails.this, "Movie has been successfully updated!!", Toast.LENGTH_LONG).show();

            }
        });

        backtoDisplay.setOnClickListener(v -> {
            setBacktoDisplay();
        });

    }

    private void setBacktoDisplay(){
        Intent int2 = new Intent(this, EditMovies.class);
        startActivity(int2);
    }



    public void getToHomePage(View v){

        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);

    }

}
