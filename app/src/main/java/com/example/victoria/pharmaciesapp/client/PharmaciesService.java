package com.example.victoria.pharmaciesapp.client;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface PharmaciesService {

    @GET("/backend/front-office/pharmacy")
    Call<List<PharmacyDto>> getPharmacies();

    @GET("/backend/front-office/pharmacy/{id}")
    Call<PharmacyDetailsDto> getPharmacyDetails(@Path("id") Integer id);
}
