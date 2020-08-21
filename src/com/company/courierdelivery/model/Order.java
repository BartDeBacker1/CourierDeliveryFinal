package com.company.courierdelivery.model;

import java.sql.Date;

// Order Class with all attributes, Constructor and Setter/Getters
public class Order {

    private int id;
    private int userId;
    private int vehicleId;
    private int destinationAddressId;
    private String status;
    private double width;
    private double height;
    private String comment;
    private Date orderPlacingDate;

    public Order() {

    }

    public Order(int id, int userId, int destinationAddressId, String status, double width, double height, String comment, Date orderPlacingDate, int vehicleId) {
        this.id = id;
        this.userId = userId;
        this.destinationAddressId = destinationAddressId;
        this.status = status;
        this.width = width;
        this.height = height;
        this.comment = comment;
        this.orderPlacingDate = orderPlacingDate;
        this.vehicleId = vehicleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDestinationAddressId() {
        return destinationAddressId;
    }

    public void setDestinationAddressId(int destinationAddressId) {
        this.destinationAddressId = destinationAddressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getOrderPlacingDate() {
        return orderPlacingDate;
    }

    public void setOrderPlacingDate(Date orderPlacingDate) {
        this.orderPlacingDate = orderPlacingDate;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
