package com.example.forum.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE From users where name =?")){
            String name = "James";
            statement.setString(1, name);
            int rows = statement.executeUpdate();
            System.out.println("Rows deleted: " + rows);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
