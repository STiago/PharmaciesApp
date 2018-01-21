package com.example.victoria.pharmaciesapp.tasks;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.victoria.pharmaciesapp.MainActivity;
import com.example.victoria.pharmaciesapp.client.PharmaciesBackend;
import com.example.victoria.pharmaciesapp.client.PharmacyDto;

import java.util.List;

/**
 * Created by victoria on 21/01/18.
 */
public class PharmaciesTask extends AsyncTask<Void, Void, List<PharmacyDto>>{

    private final MainActivity mainActivity;

    public PharmaciesTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected List<PharmacyDto> doInBackground(Void... voids) {
        return getPharmacies();
    }

    @Override
    protected void onPostExecute(final List<PharmacyDto> pharmacies) {
        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                mainActivity.populateListView(pharmacies);
            }
        });
    }

    private List<PharmacyDto> getPharmacies() {

        try {
            PharmaciesBackend pharmaciesBackend = PharmaciesBackend.getInstance();
            List<PharmacyDto> pharmacies = pharmaciesBackend.getPharmacies().execute().body();
            Log.d("PHARMACIES", pharmacies.toString());
            return pharmacies;
        } catch (Exception exception) {

            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast toast = Toast.makeText(mainActivity.getApplicationContext(),
                            "Cannot fetch pharmacies", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            return null;
        }
    }
}
