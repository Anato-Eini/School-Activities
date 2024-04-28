package com.example.client.Entities;

import java.time.LocalDateTime;
import java.util.UUID;

enum Privilege {
    user,
    organizer,
    admin
}
public class User {
    public UUID id;
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public Privilege privilege;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
