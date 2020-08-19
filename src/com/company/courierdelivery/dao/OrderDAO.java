package com.company.courierdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.courierdelivery.model.Order;
import com.company.courierdelivery.utils.DatabaseConnection;

public class OrderDAO {

    // Insert order into the database
    public void insertOrder(Order order) throws SQLException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into `order`(customer_name,width,height,capacity,"
                            + "source_address,destination_address,status,order_placing_date)"
                            + " values(?,?,?,?,?,?,?,?);");

            preparedStatement.setString(1, order.getCustomerName());
            preparedStatement.setDouble(2, order.getWidth());
            preparedStatement.setDouble(3, order.getHeight());
            preparedStatement.setDouble(4, order.getCapacity());
            preparedStatement.setString(5, order.getSourceAddress());
            preparedStatement.setString(6, order.getDestinationAddress());
            preparedStatement.setString(7, order.getStatus());
            preparedStatement.setString(8, order.getOrderPlacingDate());
            // System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve all orders from the database
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from `order`;");

            // System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String customer_name = rs.getString("customer_name");
                Double width = rs.getDouble("width");
                Double height = rs.getDouble("height");
                Double capacity = rs.getDouble("capacity");
                String source_address = rs.getString("source_address");
                String destination_address = rs.getString("destination_address");
                String status = rs.getString("status");
                String orderPlacingDate = rs.getString("order_placing_date");
                orders.add(new Order(id, customer_name, width, height, capacity, source_address, destination_address,
                        status, orderPlacingDate));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    // Checks whether the Order is present in the database
    public boolean isOrderPresent(int id) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from `order` where id = ?;");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            int retrievedId = -1;
            if (rs.next()) {
                retrievedId = rs.getInt("id");
            }

            if (retrievedId == -1)
                return false;
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Updates the order status in the database
    public void updateOrderStatus(int id, String status) {

        String query = "update `order` SET status = ?" + " where id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);

            // System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            System.out.println("Order status updated.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
