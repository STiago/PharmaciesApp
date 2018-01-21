package com.example.victoria.pharmaciesapp.client;

import java.util.List;

public class PharmacyDetailsDto {

    private Integer id;
    private String name;
    private String address;
    private String province;
    private String city;
    private String postalCode;
    private String latitude;
    private String longitude;
    private List<DrugStockDto> drugs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<DrugStockDto> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<DrugStockDto> drugs) {
        this.drugs = drugs;
    }

    @Override
    public String toString() {
        return "PharmacyDetailsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", drugs=" + drugs +
                '}';
    }
}
