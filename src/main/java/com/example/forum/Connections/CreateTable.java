package com.example.forum.Connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement()){
            String query;
            query = "CREATE TABLE IF NOT EXISTS userAcct(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL)";
            statement.execute(query);
            System.out.println("useracct created successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
