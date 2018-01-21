package com.example.victoria.pharmaciesapp.tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.victoria.pharmaciesapp.PharmacyDrugsActivity;
import com.example.victoria.pharmaciesapp.client.PharmaciesBackend;
import com.example.victoria.pharmaciesapp.client.PharmacyDetailsDto;

import java.io.IOException;

public class PharmacyDetailsTask extends AsyncTask<Integer, Void, PharmacyDetailsDto> {
    private final PharmacyDrugsActivity pharmacyDrugsActivity;

    public PharmacyDetailsTask(PharmacyDrugsActivity pharmacyDrugsActivity) {
        this.pharmacyDrugsActivity = pharmacyDrugsActivity;
    }

    @Override
    protected PharmacyDetailsDto doInBackground(Integer... integers) {
        Integer pharmacyId = integers[0];
        return getPharmacyDetails(pharmacyId);
    }

    @Override
    protected void onPostExecute(final PharmacyDetailsDto pharmacyDetails) {

        pharmacyDrugsActivity.runOnUiThread(new Runnable() {
            public void run() {
                pharmacyDrugsActivity.populateListView(pharmacyDetails);
            }
        });
    }

    private PharmacyDetailsDto getPharmacyDetails(int pharmacyId)  {
        try {
            PharmaciesBackend pharmaciesBackend = PharmaciesBackend.getInstance();
            return  pharmaciesBackend.getPharmacyDetails(pharmacyId).execute().body();
        } catch (Exception e) {

            pharmacyDrugsActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast toast = Toast.makeText(pharmacyDrugsActivity.getApplicationContext(),
                            "Cannot fetch pharmacy details", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            return null;
        }
    }
}
