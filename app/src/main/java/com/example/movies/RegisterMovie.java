package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterMovie extends AppCompatActivity {

    EditText one, two, three, four, five, six;
    Button save;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        one = (EditText)findViewById(R.id.editOne);
        two = (EditText)findViewById(R.id.editTwo);
        three = (EditText)findViewById(R.id.editThree);
        four = (EditText)findViewById(R.id.editFour);
        five = (EditText)findViewById(R.id.editFive);
        six = (EditText)findViewById(R.id.editSix);

        save = (Button)findViewById(R.id.save);
        myDB = new DBHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = one.getText().toString();

                String year = two.getText().toString();
                int intYear = Integer.parseInt(year);

                String director = three.getText().toString();
                String crew = four.getText().toString();

                String rate = five.getText().toString();
                int intRate = Integer.parseInt(rate);

                String review = six.getText().toString();

                if (name.length() == 0 || year.length() == 0 || director.length() == 0 || crew.length() == 0 || rate.length() == 0 || review.length() == 0) {
                    Toast.makeText(RegisterMovie.this, "Empty Fields are NOT allowed!!", Toast.LENGTH_LONG).show();
                }/*else if (intYear <= 1895) {
                    Toast.makeText(RegisterMovie.this, "Invalid Year!", Toast.LENGTH_SHORT).show();
                }
                else if (intRate > 11) {
                    Toast.makeText(RegisterMovie.this, "Rate must be within a range of 1-10! ", Toast.LENGTH_SHORT).show();
                }
                else if (intYear > 1895 && intRate < 11){*/
                else {
                    AddMovie(name, year, director, crew, rate, review);
                    one.setText("");
                    two.setText("");
                    three.setText("");
                    four.setText("");
                    five.setText("");
                    six.setText("");
                    //}
                }
            }
        });
    }

    public void AddMovie(String movieName, String releaseYear, String movieDirector, String crew, String rating, String reviews){
        boolean insertData = myDB.addMovie(movieName, releaseYear, movieDirector, crew, rating, reviews);

        if (insertData == true){
            Toast.makeText(RegisterMovie.this, "Data has been saved successfully!!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(RegisterMovie.this, "Empty Fields are NOT allowed!", Toast.LENGTH_LONG).show();

        }
    }

    public void getToHomePage(View v){

        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);

    }


}