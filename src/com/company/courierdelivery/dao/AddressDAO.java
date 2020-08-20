package com.company.courierdelivery.dao;

import com.company.courierdelivery.model.Address;
import com.company.courierdelivery.model.Order;
import com.company.courierdelivery.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {

    public int insertAddress(Address address) throws SQLException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into `address`(id, country, town, postal_code, street, house_number)"
                            + " values(?,?,?,?,?,?);");

            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getTown());
            preparedStatement.setString(4, address.getPostal_code());
            preparedStatement.setString(5, address.getStreet());
            preparedStatement.setString(6, address.getHouse_number());
            preparedStatement.executeUpdate();

            //Get the last inserted id
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public List<Address> getAllAddress() {

        List<Address> addresses = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from `address`;");

            // System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String country = rs.getString("country");
                String town = rs.getString("town");
                String postal_code = rs.getString("postal_code");
                String street = rs.getString("street");
                String house_number = rs.getString("house_number");

                addresses.add(new Address(id, country, town, postal_code, street, house_number));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return addresses;
    }

    public Address getAddressById(int id) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from `address` where id = ?;");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int address_id = rs.getInt("id");
                String country = rs.getString("country");
                String town = rs.getString("town");
                String postal_code = rs.getString("postal_code");
                String street = rs.getString("street");
                String house_number = rs.getString("house_number");

                return new Address(address_id, country, town, postal_code, street, house_number);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
