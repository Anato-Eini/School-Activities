package com.example.forum.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "Insert into users (name, email) values (?, ?)"
            )){
            String name = "James Kenneth S. Acabal", email = "james.kenneth@gmail.com";
            statement.setString(1, name);
            statement.setString(2, email);
            int rows = statement.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
