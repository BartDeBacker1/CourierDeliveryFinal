package com.company.courierdelivery.model;

//Vehicle Class with all attributes, Constructor and Setter/Getters
public class Vehicle {

    private int id;
    private double width;
    private double height;
    private double capacity; // width x height
    private String status;

    public Vehicle() {
        super();
    }

    public Vehicle(int id, double width, double height, double capacity, String status) {
        super();
        this.id = id;
        this.width = width;
        this.height = height;
        this.capacity = capacity;
        this.status = status;
    }

    public Vehicle(double width, double height, String status) {
        super();
        this.width = width;
        this.height = height;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
