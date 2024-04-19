package com.example.forum.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET name = ? where id = ?"
            )){
            String name = "James";
            int id = 5;
            statement.setString(1, name);
            statement.setInt(2, id);
            int row = statement.executeUpdate();
            System.out.println("Row updated: " + row);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
