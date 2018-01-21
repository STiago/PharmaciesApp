package com.example.victoria.pharmaciesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.victoria.pharmaciesapp.client.DrugStockDto;
import com.example.victoria.pharmaciesapp.client.PharmacyDetailsDto;

/**
 * Created by victoria on 21/01/18.
 */
public class PharmacyDrugsAdapter extends BaseAdapter {

    private Context context;
    private PharmacyDetailsDto pharmacyDetails;

    public PharmacyDrugsAdapter(Context applicationContext,
                                PharmacyDetailsDto pharmacyDetails) {
        this.context = applicationContext;
        this.pharmacyDetails = pharmacyDetails;
    }

    @Override
    public int getCount() {

        if (pharmacyDetails == null || pharmacyDetails.getDrugs() == null) {
            return 0;
        }

        return pharmacyDetails.getDrugs().size();
    }

    @Override
    public Object getItem(int i) {

        if (getCount() == 0) {
            return null;
        }
        return pharmacyDetails.getDrugs().get(i);

    }

    @Override
    public long getItemId(int i) {
        return ( (DrugStockDto )getItem(i)) .getId();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        convertView = inflateLayout(convertView, parent);

        DrugStockDto currentDrug = (DrugStockDto) getItem(position);

        populateDrugData(convertView, currentDrug);

        return convertView;
    }

    private View inflateLayout(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_drugs, parent, false);
        }
        return convertView;
    }

    private void populateDrugData(View convertView, DrugStockDto currentDrug) {
        // get the TextView for item name and item description
        TextView textViewDrugName = convertView.findViewById(R.id.text_drug_name);

        TextView textViewDrugItems = convertView.findViewById(R.id.text_drug_item);

        //Sets the text for item name and item description from the current item object
        textViewDrugName.setText(currentDrug.getName());

        textViewDrugItems.setText(currentDrug.getItems().toString());
    }
}
