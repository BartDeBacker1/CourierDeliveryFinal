package com.company.courierdelivery.model;

public class User {
    private int id;
    private int addressId;
    private String username;
    private String hash;
    private String status;
    private String email;

    public User() {

    }

    public User(int id, int addressId, String username, String hash, String status, String email) {
        this.id = id;
        this.addressId = addressId;
        this.username = username;
        this.hash = hash;
        this.status = status;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}