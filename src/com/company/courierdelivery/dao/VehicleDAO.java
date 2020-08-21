package com.company.courierdelivery.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.company.courierdelivery.model.Address;
import com.company.courierdelivery.model.Vehicle;
import com.company.courierdelivery.utils.DatabaseConnection;

public class VehicleDAO {

    public static final String SELECT_ALL_VEHICLES = "select * from vehicle";

    // Insert vehicle into the database
    public void insertVehicle(Vehicle vehicle) throws SQLException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into vehicle(user_id,width,height,capacity,status) values(?,?,?,?,?);");
            if(vehicle.getUserId() != 0) {
                preparedStatement.setInt(1, vehicle.getUserId());
            }
            else{
                preparedStatement.setNull(1, Types.INTEGER);
            }
            preparedStatement.setDouble(2, vehicle.getWidth());
            preparedStatement.setDouble(3, vehicle.getHeight());
            preparedStatement.setDouble(4, vehicle.getCapacity());
            preparedStatement.setString(5, vehicle.getStatus());
            // System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateVehicleUser(int vehicleId, int userId) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("update vehicle set user_id = ? where id = ?");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, vehicleId);
        preparedStatement.executeUpdate();

    }

    public void updateVehicleStatus(int vehicleId, String status) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("update vehicle set status = ? where id = ?");
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, vehicleId);
        preparedStatement.executeUpdate();

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
                int userId = rs.getInt("user_id");
                Double width = rs.getDouble("width");
                Double height = rs.getDouble("height");
                Double capacity = rs.getDouble("capacity");
                String status = rs.getString("status");
                vehicles.add(new Vehicle(id, userId, width, height, capacity, status));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }

    public Vehicle getVehicleById(int id){
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from `vehicle` where id = ?;");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int vehicleId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                Double width = rs.getDouble("width");
                Double height = rs.getDouble("height");
                Double capacity = rs.getDouble("capacity");
                String status = rs.getString("status");
                return new Vehicle(id, userId, width, height, capacity, status);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
