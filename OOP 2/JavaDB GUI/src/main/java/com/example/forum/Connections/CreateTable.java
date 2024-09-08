package com.example.forum.Connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void init_Tables(Connection connection) {
        try(Statement statement = connection.createStatement()){
            String query;
            query = "CREATE TABLE IF NOT EXISTS tbluserAcct(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL)";
            statement.execute(query);
            System.out.println("useracct table created successfully");
            query = "CREATE TABLE IF NOT EXISTS tblpost (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "author_id INT NOT NULL," +
                    "body TEXT NOT NULL" +
                    ")";
            statement.execute(query);
            System.out.println("post table created successfully");
            query = "CREATE TABLE IF NOT EXISTS tblreply(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "author_id INT NOT NULL," +
                    "post_id INT NOT NULL," +
                    "body TEXT NOT NULL)";
            statement.execute(query);
            System.out.println("reply table created successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
