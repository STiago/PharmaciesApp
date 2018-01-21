package com.example.victoria.pharmaciesapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.victoria.pharmaciesapp.client.PharmaciesBackend;
import com.example.victoria.pharmaciesapp.client.PharmacyDto;
import com.example.victoria.pharmaciesapp.tasks.PharmaciesTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new PharmaciesTask(this).execute();
    }

    public void populateListView(List<PharmacyDto> pharmacies) {
        ListView itemsListView  = findViewById(R.id.pharmacies_list);

        //create pharmaciesAdapter object
        PharmaciesAdapter pharmaciesAdapter =
                new PharmaciesAdapter(getApplicationContext(), pharmacies);

        //set custom pharmaciesAdapter as pharmaciesAdapter to our list view
        itemsListView.setAdapter(pharmaciesAdapter);
    }
}
