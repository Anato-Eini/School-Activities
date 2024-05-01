package Classes;

import java.time.LocalDateTime;

public class User {
    public String id;
    public String firstName;
    public String LastName;
    public String userName;
    public LocalDateTime createdAt;

    public User(String id, String firstName, String lastName, String userName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        LastName = lastName;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime updatedAt;
}