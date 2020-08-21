package com.company.courierdelivery;

import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

import com.company.courierdelivery.dao.AddressDAO;
import com.company.courierdelivery.dao.OrderDAO;
import com.company.courierdelivery.dao.UserDAO;
import com.company.courierdelivery.dao.VehicleDAO;
import com.company.courierdelivery.model.Address;
import com.company.courierdelivery.model.Order;
import com.company.courierdelivery.model.User;
import com.company.courierdelivery.model.Vehicle;


public class CourierDelivery {

    public static VehicleDAO vehicleDAO = new VehicleDAO();
    public static OrderDAO orderDAO = new OrderDAO();
    public static UserDAO userDAO = new UserDAO();
    public static AddressDAO addressDAO = new AddressDAO();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            running = showMainMenu();
        }
    }

    public static boolean showMainMenu(){

        Scanner scan = new Scanner(System.in);
        int i = 0;
        System.out.println("######### MAIN MENU ########");
        System.out.println("Enter 1 to show vehicle menu.");
        //System.out.println("Enter 2 to show user menu.");
        System.out.println("Enter 2 to show order menu.");
        System.out.println("Enter 3 to exit the application.");
        System.out.println("############################");

        i = scan.nextInt();
        switch (i) {
            case 1:
                while(showVehicleMenu());
                break;
            case 2:
                while(showOrderMenu());
                break;
            case 3:
                return false;
            default:
                break;
        }
        return true;
    }

    public static boolean showVehicleMenu(){
        Scanner scan = new Scanner(System.in);
        int i = 0;

        System.out.println("######### VEHICLE MENU ########");
        System.out.println("Enter 1 to show all vehicles.");
        System.out.println("Enter 2 to show vehicle status.");
        System.out.println("Enter 3 to add a new vehicle.");
        System.out.println("Enter 4 to set vehicle driver.");
        System.out.println("Enter 5 to fill vehicle.");
        System.out.println("Enter 6 to return to main menu.");
        System.out.println("###############################");

        i = scan.nextInt();
        switch (i) {
            case 1:
                displayAllVehicles();
                break;
            case 2:
                displayVehicle();
                break;
            case 3:
                insertVehicle();
                break;
            case 4:
                setVehicleDriver();
                break;
            case 5:
                fillVehicle();
                break;
            case 6:
                return false;
            default:
                break;
        }
        return true;
    }

    public static boolean showOrderMenu(){
        Scanner scan = new Scanner(System.in);
        int i = 0;

        System.out.println("######### ORDER MENU ########");
        System.out.println("Enter 1 to show all orders.");
        System.out.println("Enter 2 to place a new order.");
        System.out.println("Enter 3 to update order status.");
        System.out.println("Enter 4 to return to main menu.");
        System.out.println("#############################");

        i = scan.nextInt();
        switch (i) {
            case 1:
                displayAllOrders();
                break;
            case 2:
                placeOrder();
                break;
            case 3:
                updateOrderStatus();
                break;
            case 4:
                return false;
            default:
                break;
        }
        return true;
    }

    public static void displayAllVehicles() {

        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleDAO.getAllVehicles();

        System.out.println("-----------------");
        System.out.println("VEHICLE OVERVIEW");

        for (Vehicle vehicle : vehicles) {
            System.out.println("ID: " + vehicle.getId());
            if(vehicle.getUserId() != 0) {
                System.out.println("Courier ID: " + vehicle.getUserId());
            }else{
                System.out.println("Courier ID: No courrier assigned yet.");
            }
            System.out.println("Status: " + vehicle.getStatus());
            System.out.println("Total capacity: " + vehicle.getWidth() * vehicle.getHeight());
            System.out.println("Remaining Capacity: " + vehicle.getCapacity());
            System.out.println("-----------------");
        }

        if (vehicles.size() == 0) {
            System.out.println("-----------------");
            System.out.println("No Vehicles Found");
            System.out.println("-----------------");
        }
    }

    public static void displayVehicle(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter vehicle id: ");

        Vehicle vehicle = vehicleDAO.getVehicleById(scan.nextInt());

        if(vehicle != null){
            System.out.println("-----------------");
            System.out.println("VEHICLE INFO");
            System.out.println("-----------------");

            System.out.println("ID: " + vehicle.getId());

            if(vehicle.getUserId() != 0) {
                System.out.println("Courier ID:" + vehicle.getUserId());
            }else{
                System.out.println("Courier ID: No courrier assigned yet.");
            }

            System.out.println("Status: " + vehicle.getStatus());
            System.out.println("Total capacity: " + vehicle.getWidth() * vehicle.getHeight());
            System.out.println("Remaining Capacity: " + vehicle.getCapacity());

            System.out.println("-----------------");
            System.out.println("CARGO");

            List<Order> cargo = orderDAO.getOrderByVehicleId(vehicle.getId());

            for (Order order : cargo) {
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

            if(cargo.isEmpty()){
                System.out.println("-----------------");
                System.out.println("This vehicle has no cargo.");
                System.out.println("-----------------");
            }

        }
        else{
            System.out.println("Invalid vehicle ID. Aborting operation...");
        }

    }

    public static void insertVehicle() {

        Scanner scan = new Scanner(System.in);
        Vehicle vehicle = new Vehicle();
        System.out.println("Enter Vehicle Width: ");
        vehicle.setWidth(scan.nextDouble());
        System.out.println("Enter Vehicle Height");
        vehicle.setHeight(scan.nextDouble());
        scan.nextLine(); // skip the newline character
        vehicle.setStatus("AT_DEPOT");
        vehicle.setUserId(0);
        vehicle.setCapacity(vehicle.getHeight() * vehicle.getWidth());

        try {
            vehicleDAO.insertVehicle(vehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setVehicleDriver(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Vehicle ID: ");

        Vehicle vehicle = vehicleDAO.getVehicleById(scan.nextInt());
        if(vehicle == null){
            System.out.println("Invalid vehicle ID. Aborting operation...");
            return;
        }else if (vehicle.getUserId() != 0){
            System.out.println("Driver already assigned. Are you sure you wish to override? (y/n)");

            if(scan.next().equals("y")) {
                return;
            }
        }
        System.out.println("Enter User ID: ");
        int userId = scan.nextInt();
        if(userDAO.getUserById(userId) == null){
            System.out.println("Invalid user ID. Aborting operation...");
            return;
        }

        try {
            vehicleDAO.updateVehicleUser(vehicle.getId(), userId);
            System.out.println("Successfully updated driver.");
        }
        catch (SQLException e) {
            System.out.println("Failed to update driver: " + e.getMessage());
        }
    }

    public static void fillVehicle(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Vehicle ID: ");

        Vehicle vehicle = vehicleDAO.getVehicleById(scan.nextInt());
        if(vehicle == null){
            System.out.println("Invalid vehicle ID. Aborting operation...");
            return;
        }else if(!vehicle.getStatus().equals("AT_DEPOT")){
            System.out.println("This vehicle is not available. Aborting operation...");
            return;
        }

        //Take the oldest orders until the capacity is reached.
        List<Order> orderList = orderDAO.getOrderByStatus("PROCESSING");
        orderList.sort(Comparator.comparing(Order::getOrderPlacingDate));

        if(orderList.isEmpty()){
            System.out.println("No orders found. Aborting operation...");
            return;
        }

        List<Order> ordersToLoad = new ArrayList<Order>();
        double capacity = vehicle.getCapacity();

        for (Order order:orderList) {
            double order_capacity = order.getHeight() * order.getWidth();
            if(capacity - order_capacity < 0){
                break;
            }else{
                capacity = capacity - order_capacity;
                ordersToLoad.add(order);
            }
        }

        //Sort by town
        ordersToLoad.sort(Comparator.comparing(Order::getDestinationAddressId));

        //Update order statuses
        for (Order order:ordersToLoad) {
            orderDAO.updateOrderStatus(order.getId(), "PREPARING");
            orderDAO.updateOrderVehicle(order.getId(), vehicle.getId());
        }

        try {
            vehicleDAO.updateVehicleStatus(vehicle.getId(), "LOADING");
        }catch(SQLException e){
            System.out.println("Failed to update vehicle status: " + e.getMessage());
            System.out.println("Aborting operation...");
            return;
        }

        //Print out all orders in order of loading.

        System.out.println("-----------------");
        System.out.println("VEHICLE LOADING OVERVIEW");

        for (Order order : ordersToLoad) {
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
    }

    public static void displayAllOrders() {
        ArrayList<Order> orders = (ArrayList<Order>) orderDAO.getAllOrders();

        System.out.println("-----------------");
        System.out.println("ORDER OVERVIEW");

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
            System.out.println("-----------------");
            System.out.println("No Orders Found");
            System.out.println("-----------------");
        }
    }

    public static void placeOrder(){
        Scanner scan = new Scanner(System.in);
        Order order = new Order();

        //Get the user
        System.out.println("Enter the user ID :");
        int userId = scan.nextInt();
        User user = userDAO.getUserById(userId);

        if(user == null){
            System.out.println("Invalid user ID. Aborting operation...");
            return;
        }

        order.setUserId(userId);
        scan.nextLine();
        //Ask if the delivery address is the recipients address
        System.out.println("Would you like the order to be sent to the recipients address? (y/n)");

        if(scan.nextLine().equals("y")) {
            order.setDestinationAddressId(user.getAddressId());
        }
        else {
            System.out.println("Please enter the country: ");
            String country = scan.nextLine();

            System.out.println("Please enter the town: ");
            String town = scan.nextLine();

            System.out.println("Please enter the postal_code: ");
            String postal_code = scan.nextLine();

            System.out.println("Please enter the street: ");
            String street = scan.nextLine();

            System.out.println("Please enter the house number: ");
            String house_number = scan.nextLine();

            Address address = new Address(0, country, town, postal_code, street, house_number);

            try {
                order.setDestinationAddressId(addressDAO.insertAddress(address));
            }catch (SQLException e){
                System.out.println("Failed to create a new address: " + e.getMessage());
                System.out.println("Aborting operation...");
                return;
            }
        }

        System.out.println("Enter the width:");
        order.setWidth(scan.nextDouble());
        System.out.println("Enter the height:");
        order.setHeight(scan.nextDouble());
        scan.nextLine();
        System.out.println("Enter an additional comment if desired:");
        order.setComment(scan.nextLine());
        order.setOrderPlacingDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        order.setStatus("PROCESSING");

        try {
            orderDAO.insertOrder(order);
            System.out.println("Successfully placed new order.");
        }catch(SQLException e){
            System.out.println("Failed to create order: " + e.getMessage());
        }
    }

    public static void updateOrderStatus() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Order Id: ");
        int orderId = scan.nextInt();
        scan.nextLine();

        Order order = orderDAO.getOrderById(orderId);

        if (order == null) {
            System.out.println("Invalid Order Id. Aborting operation...");
            return;
        }

        System.out.println("Enter new order status: ");
        String status = scan.nextLine();

        if(!status.equals("PROCESSING") && !status.equals("PREPARING") && !status.equals("IN_TRANSIT") && !status.equals("DELIVERED")){
            System.out.println("Invalid order status. Viable options are 'PROCESSING', 'PREPARING', 'IN_TRANSIT' or 'DELIVERED' Aborting operation...");
            return;
        }

        orderDAO.updateOrderStatus(orderId, status);


        if(status.equals("PROCESSING") || status.equals("DELIVERED")){
            orderDAO.updateOrderVehicle(orderId, 0);
        }

        System.out.println("Successfully updated order status.");

    }
}
