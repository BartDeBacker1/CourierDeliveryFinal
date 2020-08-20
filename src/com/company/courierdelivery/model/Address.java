package com.company.courierdelivery.model;

public class Address {

    private int id;
    private String country;
    private String town;
    private String postal_code;
    private String street;
    private String house_number;

    public Address(int id, String country, String town, String postal_code, String street, String house_number) {
        this.id = id;
        this.country = country;
        this.town = town;
        this.postal_code = postal_code;
        this.street = street;
        this.house_number = house_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }
}
