package com.company.courierdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.courierdelivery.model.Vehicle;
import com.company.courierdelivery.utils.DatabaseConnection;

public class VehicleDAO {

    public static final String SELECT_ALL_VEHICLES = "select * from vehicle";

    // Insert vehicle into the database
    public void insertVehicle(Vehicle vehicle) throws SQLException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into vehicle(width,height,capacity,status) values(?,?,?,?);");

            preparedStatement.setDouble(1, vehicle.getWidth());
            preparedStatement.setDouble(2, vehicle.getHeight());
            preparedStatement.setDouble(3, vehicle.getCapacity());
            preparedStatement.setString(4, vehicle.getStatus());
            // System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve all vehicles from the database
    public List<Vehicle> getAllVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEHICLES);

            // System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Double width = rs.getDouble("width");
                Double height = rs.getDouble("height");
                Double capacity = rs.getDouble("capacity");
                String status = rs.getString("status");
                vehicles.add(new Vehicle(id, width, height, capacity, status));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }
}
