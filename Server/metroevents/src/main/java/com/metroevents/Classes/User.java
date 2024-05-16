package com.metroevents.Classes;

import java.sql.Timestamp;

public class User {
    public String id;
    public String firstName;
    public String lastName;
    public String userName;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public User(String id, String firstName, String lastName, String userName, Timestamp createdAt,
            Timestamp updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}