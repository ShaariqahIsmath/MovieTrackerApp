package com.example.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;


public class DBHelper  extends SQLiteOpenHelper {

    //create database and determine names of table and columns

    public static final String DATABASE_NAME = "MOVIESTODAY.db";
    public static final String TABLE_NAME = "movies_details";
    public static final String TABLE_NAME02 = "Favourite_table";
    public static final String COLUMN0 = "ID";
    public static final String COLUMN1 = "Name";
    public static final String COLUMN2 = "Year";
    public static final String COLUMN3 = "Director";
    public static final String COLUMN4 = "Crew";
    public static final String COLUMN5 = "Rate";
    public static final String COLUMN6 = "Review";
    public static final String COLUMN7 = "Favourites";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR, Year INTEGER, Director VARCHAR, Crew VARCHAR, Rate INTEGER, Review VARCHAR )";
        db.execSQL(CREATE_TABLE);

        String CREATE_TABLE02 = "CREATE TABLE " + TABLE_NAME02 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR, Year INTEGER, Director VARCHAR, Crew VARCHAR, Rate INTEGER, Review VARCHAR, Favourites VARCHAR )";
        db.execSQL(CREATE_TABLE02);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME02);
        onCreate(db);

    }

    //for creating a movie and adding to database into respective columns

    public boolean addMovie(String name, String year, String director, String crew, String rate, String review){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1, name);
        values.put(COLUMN2, year);
        values.put(COLUMN3, director);
        values.put(COLUMN4, crew);
        values.put(COLUMN5, rate);
        values.put(COLUMN6, review);

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    //adding movie to favourites column

    public boolean addFavouriteMovie(String name, String year, String director, String crew, String rate, String review, String favourite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1, name);
        values.put(COLUMN2, year);
        values.put(COLUMN3, director);
        values.put(COLUMN4, crew);
        values.put(COLUMN5, rate);
        values.put(COLUMN6, review);
        values.put(COLUMN7, favourite);

        long res = db.insert(TABLE_NAME02, null, values);

        if (res == -1){
            return false;
        }
        else {
            return true;
        }
    }

    //view all names of movies created

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //to retrieve and display all favourite movies from database

    public Cursor getFavMovie(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME02;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    //remove movie from favourites column

    public Boolean removeFromFavourites (String fav)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor data = DB.rawQuery("SELECT * FROM " + TABLE_NAME02, null);

        if (data.getCount() > 0) {
            long result = DB.delete(TABLE_NAME02,"Favourites=?", new String[]{fav});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public ArrayList<String> getAllDetails(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor data = DB.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        StringBuilder sb = new StringBuilder();

        ArrayList<String> movieDeets = new ArrayList<>();

        while (data.moveToNext()){
            String string1 = data.getString(1);
            String string2 = data.getString(3);
            String string3 = data.getString(4);

            String setOfValues = string1 + ": " + "\n" +"Director: " + string2  +"\n"+ "Cast: " + string3;

            movieDeets.add(String.valueOf(setOfValues).toLowerCase());
        }

        return movieDeets;

    }

    public void UpdateDetails(int Id, String title, String year, String director, String crew, String rate, String review){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1, title);
        values.put(COLUMN2, year);
        values.put(COLUMN3, director);
        values.put(COLUMN4, crew);
        values.put(COLUMN5, rate);
        values.put(COLUMN6, review);

        database.update(TABLE_NAME, values, "ID="+Id, null);
    }

    public Cursor getEditDetails(String title){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor data = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Name='"+title+"'", null);
        return data;
    }








}
