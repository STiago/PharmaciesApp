package com.example.victoria.pharmaciesapp.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.victoria.pharmaciesapp.MainActivity;
import com.example.victoria.pharmaciesapp.client.PharmaciesBackend;
import com.example.victoria.pharmaciesapp.client.PharmacyDto;

import java.util.List;

/**
 * Created by victoria on 21/01/18.
 */
public class PharmacySearchTask extends AsyncTask<String, Void, List<PharmacyDto>>{

    private final MainActivity mainActivity;

    public PharmacySearchTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected List<PharmacyDto> doInBackground(String... inputs) {
        String searchText = inputs[0];
        Log.d("PharmacySearchTask", searchText);
        return getPharmacies(searchText);
    }

    @Override
    protected void onPostExecute(final List<PharmacyDto> pharmacies) {
        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                mainActivity.populateListView(pharmacies);
            }
        });
    }

    private List<PharmacyDto> getPharmacies(String searchText) {

        try {
            PharmaciesBackend pharmaciesBackend = PharmaciesBackend.getInstance();
            List<PharmacyDto> pharmacies = pharmaciesBackend.getPharmaciesWithDrug(searchText).execute().body();
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
