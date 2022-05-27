package com.example.movies;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String movieName;
    private int year;
    private String director;
    private String cast;
    private int rating;
    private String review;
    private boolean active;

    public Movie(){

    }

    public Movie(String movieName){
        this.movieName = movieName;

    }

    public Movie(String movieName, boolean active){
        this.movieName = movieName;
        this.active = true;
    }

    public Movie(String movieName, int year, String director, String cast, int rating, String review){
        this.movieName = movieName;
        this.year = year;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.review = review;

    }

    public Movie(int id, String movieName, int year, String director, String cast, int rating, String review){
        this.id = id;
        this.movieName = movieName;
        this.year = year;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.review = review;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getMovieName(){
        return movieName;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public String getDirector(){
        return director;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public String getCast(){
        return cast;
    }

    public void setCast(String cast){
        this.cast = cast;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public String getReview(){
        return review;
    }
    public void setReview(String review){
        this.review = review;
    }


    public boolean isActive(){
        return active;

    }

    public void setActive(boolean active){
        this.active = active;
    }

    public String toString(){
        return this.movieName;
    }

}
