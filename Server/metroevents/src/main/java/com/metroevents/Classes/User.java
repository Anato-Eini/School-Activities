package com.metroevents.Classes;

import java.sql.Timestamp;
import java.util.UUID;

public class User {
    public UUID id;
    public String firstName;
    public String lastName;
    public String userName;
    public Timestamp createdAt;
    public Timestamp updatedAt;

    public User(UUID id, String firstName, String lastName, String userName, Timestamp createdAt,
            Timestamp updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}