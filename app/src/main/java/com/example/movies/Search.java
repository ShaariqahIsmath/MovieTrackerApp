package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    private ArrayList<String> dataList = new ArrayList<>();
    private ArrayList<String> displayList = new ArrayList<>();
    DBHelper database;
    ListView listView;
    String search;
    int count = 0;
    private EditText input;
    Button searchAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchAction = (Button)findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.listView3);
        input = (EditText)findViewById(R.id.searchInput);

        database = new DBHelper(this);
        dataList = database.getAllDetails();

        for (String i : dataList){
            System.out.println(i);;
        }

        searchAction.setOnClickListener(v -> {
            if (count >= 1){
                displayList.clear();
            }
            search = input.getText().toString().toLowerCase();
            for (int i = 0; i < dataList.size(); i++){
                if (dataList.get(i).contains(search)){
                    displayList.add(dataList.get(i));
                }
            }

            showMovieDeetsList();
            count++;
        });

    }

    public void showMovieDeetsList(){
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);
    }

    public void getToHomePage(View v){

        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);

    }

}