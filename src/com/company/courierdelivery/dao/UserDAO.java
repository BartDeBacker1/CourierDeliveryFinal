package com.company.courierdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.company.courierdelivery.model.User;
import com.company.courierdelivery.utils.DatabaseConnection;

public class UserDAO {

    public User getUserById(int id) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from `users` where id = ?;");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                int addressId = rs.getInt("address_id");
                String username = rs.getString("username");
                String hash = rs.getString("hash");
                String status = rs.getString("status");
                String email = rs.getString("email");

                return new User(id, addressId, username, hash, status, email);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
