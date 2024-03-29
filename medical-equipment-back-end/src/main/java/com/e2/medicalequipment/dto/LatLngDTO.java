package com.e2.medicalequipment.dto;

public class LatLngDTO {
    double lat;
    double lng;

    public LatLngDTO() {
    }

    public LatLngDTO(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
