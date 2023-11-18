package com.e2.medicalequipment.dto;

import com.e2.medicalequipment.model.Address;

public class UpdateAddressDTO{
    public Long id;
    public String street;
    public String city;
    public String country;
    public UpdateAddressDTO() {
    }
    public UpdateAddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.country = address.getCountry();
    }
}
