package com.company.courierdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.courierdelivery.model.Users;
import com.company.courierdelivery.utils.DatabaseConnection;

public class UsersDAO {

    public boolean isUserPresent(int id) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from `users` where id = ?;");
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
}
