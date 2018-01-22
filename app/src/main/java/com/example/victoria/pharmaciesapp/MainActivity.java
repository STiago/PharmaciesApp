package com.example.victoria.pharmaciesapp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.victoria.pharmaciesapp.client.PharmacyDto;
import com.example.victoria.pharmaciesapp.tasks.PharmaciesTask;
import com.example.victoria.pharmaciesapp.tasks.PharmacySearchTask;

import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new PharmaciesTask(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));


        searchView.setQueryHint("Search Drug");


        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                new PharmaciesTask(MainActivity.this).execute();

                return false;
            }
        });


        return true;
    }

    public void populateListView(List<PharmacyDto> pharmacies) {
        ListView itemsListView  = findViewById(R.id.pharmacies_list);

        //create pharmaciesAdapter object
        PharmaciesAdapter pharmaciesAdapter =
                new PharmaciesAdapter(getApplicationContext(), pharmacies);

        //set custom pharmaciesAdapter as pharmaciesAdapter to our list view
        itemsListView.setAdapter(pharmaciesAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String userInput) {

        new PharmacySearchTask(this).execute(userInput);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String userInput) {

        if(userInput.length() > 3){
            new PharmacySearchTask(this).execute(userInput);
        }

        return true;
    }
}
