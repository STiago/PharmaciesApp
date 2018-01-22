package com.example.victoria.pharmaciesapp.client;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PharmaciesBackend {

    private static PharmaciesBackend pharmaciesBackend;
    private static PharmaciesService pharmaciesService;

    private PharmaciesBackend (){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.13:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pharmaciesService = retrofit.create(PharmaciesService.class);
    }

    public static PharmaciesBackend getInstance(){

        if(pharmaciesBackend == null){
            pharmaciesBackend = new PharmaciesBackend();
        }

        return pharmaciesBackend;
    }


    public Call<List<PharmacyDto>> getPharmacies(){
        return pharmaciesService.getPharmacies();
    }

    public Call<List<PharmacyDto>> getPharmaciesWithDrug(String drugName){
        return pharmaciesService.getPharmacies(drugName);
    }


    public Call<PharmacyDetailsDto> getPharmacyDetails(Integer pharmacyId){
        return pharmaciesService.getPharmacyDetails(pharmacyId);
    }
}
