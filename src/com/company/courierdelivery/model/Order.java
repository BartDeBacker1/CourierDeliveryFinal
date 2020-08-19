package com.company.courierdelivery.model;

// Order Class with all attributes, Constructor and Setter/Getters
public class Order {

    private int id;
    private String customerName;
    private double width;
    private double height;
    private double capacity; // width x height
    private String sourceAddress;
    private String destinationAddress;
    private String status;
    private String orderPlacingDate;

    public Order() {

    }

    public Order(int id, String customerName, double width, double height, double capacity, String sourceAddress,
                 String destinationAddress, String status, String orderPlacingDate) {
        super();
        this.id = id;
        this.customerName = customerName;
        this.width = width;
        this.height = height;
        this.capacity = capacity;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.status = status;
        this.orderPlacingDate = orderPlacingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderPlacingDate() {
        return orderPlacingDate;
    }

    public void setOrderPlacingDate(String orderPlacingDate) {
        this.orderPlacingDate = orderPlacingDate;
    }
}
