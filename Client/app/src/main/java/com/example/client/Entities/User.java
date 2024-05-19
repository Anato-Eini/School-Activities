package com.example.client.Entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;



public class User {
    public enum Privilege {
        USER ((String) "User"),
        ORGANIZER ((String) "Organizer"),
        ADMIN ((String) "Admin");
        public final String value;

        Privilege(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
        public static Privilege fromValue(String value) {
            for (Privilege privilege : Privilege.values()) {
                if (privilege.value.equalsIgnoreCase(value)) {
                    return privilege;
                }
            }
            throw new IllegalArgumentException("Unknown privilege: " + value);
        }
    }


    public String id;
    public String firstName;
    public String lastName;
    public String username;
    public Privilege privilege;
    public Timestamp createdAt;
    public Timestamp updatedAt;
    public User(String id, String firstName, String lastName, String username, Privilege privilege, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.privilege = privilege;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}