package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Address;

public class UpdateAddressDTO{
    public Long id;
    public String street;
    public String city;
    public String country;
    public double lat;
    public double lng;
    public UpdateAddressDTO() {
    }
    public UpdateAddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.lat = address.getLatitude();
        this.lng = address.getLongitude();
    }
}
