package com.example.victoria.pharmaciesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.victoria.pharmaciesapp.client.PharmacyDetailsDto;
import com.example.victoria.pharmaciesapp.tasks.PharmacyDetailsTask;

public class PharmacyDrugsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_drugs);
        Intent intent = getIntent();
        int pharmacyId = intent.getIntExtra("pharmacyId", -1);
        new PharmacyDetailsTask(this).execute(pharmacyId);
    }

    public void populateListView(PharmacyDetailsDto pharmacyDetailsDto) {
        ListView itemsListView  = findViewById(R.id.pharmacy_drugs_list);

        PharmacyDrugsAdapter pharmacyDrugsAdapter =
                new PharmacyDrugsAdapter(getApplicationContext(), pharmacyDetailsDto);

        itemsListView.setAdapter(pharmacyDrugsAdapter);
    }
}
