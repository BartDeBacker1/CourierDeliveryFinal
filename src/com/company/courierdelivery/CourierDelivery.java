package com.company.courierdelivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.company.courierdelivery.dao.OrderDAO;
import com.company.courierdelivery.dao.VehicleDAO;
import com.company.courierdelivery.model.Order;
import com.company.courierdelivery.model.Vehicle;

public class CourierDelivery {

    public static VehicleDAO vehicleDAO = new VehicleDAO();
    public static OrderDAO orderDAO = new OrderDAO();


    public static void insertVehicle() {
        Scanner scan = new Scanner(System.in);
        Vehicle vehicle = new Vehicle();
        System.out.println("Enter Vehicle Width: ");
        vehicle.setWidth(scan.nextDouble());
        System.out.println("Enter Vehicle Height");
        vehicle.setHeight(scan.nextDouble());
        scan.nextLine(); // skip the newline character
        vehicle.setStatus("EMPTY");
        vehicle.setCapacity(vehicle.getHeight() * vehicle.getWidth());

        try {
            vehicleDAO.insertVehicle(vehicle);
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }


    public static void displayAllVehicles() {
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleDAO.getAllVehicles();

        for (Vehicle vehicle : vehicles) {
            System.out.println("-----------------");
            System.out.println("Vehicle Id: " + vehicle.getId());
            System.out.println("Vehicle Capacity: " + vehicle.getWidth() + " x " + vehicle.getHeight());
            System.out.println("Vehicle Status: " + vehicle.getStatus());
            System.out.println("-----------------");
        }

        if (vehicles.size() == 0) {
            System.out.println("No Vehicles Found");
        }
    }


    public static void displayAllOrders() {
        ArrayList<Order> orders = (ArrayList<Order>) orderDAO.getAllOrders();

        for (Order order : orders) {
            System.out.println("-----------------");
            System.out.println("Order Id: " + order.getId());
            System.out.println("Order user id: " + order.getUserId());
            System.out.println("Order address id: " + order.getDestinationAddressId());
            System.out.println("Order Dimensions: " + order.getWidth() + " x " + order.getHeight());
            System.out.println("Order Status: " + order.getStatus());
            System.out.println("Order comment: " + order.getComment());
            System.out.println("Order Placing Date: " + order.getOrderPlacingDate());
            System.out.println("-----------------");
        }

        if (orders.size() == 0) {
            System.out.println("No Orders Found");
        }
    }


    public static void changeOrderStatus() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Order Id: ");
        int orderId = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter new order status: ");
        String status = scan.nextLine();

        if (!orderDAO.isOrderPresent(orderId)) {
            System.out.println("Invalid Order Id");
        } else {
            orderDAO.updateOrderStatus(orderId, status);
        }

    }


    public static void showIndividualVehicleStatus() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Vehicle Id: ");
        int id = scan.nextInt();

        String status = "";
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleDAO.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                status = vehicle.getStatus();
            }
        }

        if (status.equals("")) {
            System.out.println("Invalid Vehicle Id");
        } else {
            System.out.println("Vehicle Status: " + status);
        }

    }





    public static void placeAnOrder() {
        Scanner scan = new Scanner(System.in);
        Order order = new Order();
        System.out.println("Enter Customer Name: ");
        order.setCustomerName(scan.nextLine());
        System.out.println("Enter Parcel Width: ");
        order.setWidth(scan.nextDouble());
        System.out.println("Enter Parcel Height");
        order.setHeight(scan.nextDouble());
        scan.nextLine();
        System.out.println("Enter Source Address: ");
        order.setSourceAddress(scan.nextLine());
        System.out.println("Enter Destination Address: ");
        order.setDestinationAddress(scan.nextLine());

        order.setCapacity(order.getWidth() * order.getHeight());
        order.setStatus("Placed");
        order.setOrderPlacingDate(String.valueOf(java.time.LocalDate.now()));

        try {
            orderDAO.insertOrder(order);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while (true) {
            System.out.println("Enter 1 to insert vehicle into to system.");
            System.out.println("Enter 2 to view all vehicles.");
            System.out.println("Enter 3 to place an order.");
            System.out.println("Enter 4 to view all orders.");
            System.out.println("Enter 5 to update order status");
            System.out.println("Enter 6 to view status of individual vehicle");
            System.out.println("Enter 7 to search order using Customer Name.");
            System.out.println("Enter 8 to exit the system.");

            i = scan.nextInt();
            switch (i) {
                case 1:
                    insertVehicle();
                    break;
                case 2:
                    displayAllVehicles();
                    break;
                case 3:
                    placeAnOrder();
                    break;
                case 4:
                    displayAllOrders();
                    break;
                case 5:
                    changeOrderStatus();
                    break;
                case 6:
                    showIndividualVehicleStatus();
                    break;
                case 7:
                    searchOrderUsingName();
                    break;
                case 8:
                    System.out.println("Exiting the system.");
                    System.exit(1);
                    break;
                default:
                    break;
            }
        }
    }
}
