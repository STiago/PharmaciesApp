package com.example.victoria.pharmaciesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.victoria.pharmaciesapp.client.PharmacyDto;

import java.util.List;

/**
 * Created by victoria on 21/01/18.
 */
public class PharmaciesAdapter extends BaseAdapter {

    private Context context;
    private List<PharmacyDto> pharmacies;

    public PharmaciesAdapter(Context context, List<PharmacyDto> pharmacyList) {
        super();

        this.context = context;
        this.pharmacies = pharmacyList;
    }

    @Override
    public int getCount() {
        if (pharmacies == null) return 0;
        return pharmacies.size();
    }

    @Override
    public Object getItem(final int position) {
        if (getCount() == 0) return null;
        return pharmacies.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return ((PharmacyDto) getItem(position)).getId();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        // inflate the layout for each list row
        convertView = inflateLayout(convertView, parent);

        // get current item to be displayed
        PharmacyDto currentPharmacy = (PharmacyDto) getItem(position);

        populatePharmacyData(convertView, currentPharmacy);

        configureOnClick(convertView, currentPharmacy);

        // returns the view for the current row
        return convertView;
    }

    private void populatePharmacyData(View convertView, PharmacyDto currentPharmacy) {
        // get the TextView for item name and item description
        TextView textViewPharmacyName = convertView.findViewById(R.id.text_pharmacy_name);

        TextView textViewPharmacyProvince = convertView.findViewById(R.id.text_pharmacy_province);

        //Sets the text for item name and item description from the current item object
        textViewPharmacyName.setText(currentPharmacy.getName());

        textViewPharmacyProvince.setText(currentPharmacy.getProvince());
    }

    private void configureOnClick(View convertView, final PharmacyDto currentPharmacy) {
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PharmacyDrugsActivity.class);

                intent.putExtra("pharmacyId", currentPharmacy.getId());

                context.startActivity(intent);
            }
        });
    }

    private View inflateLayout(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_pharmacies, parent, false);
        }
        return convertView;
    }


}
