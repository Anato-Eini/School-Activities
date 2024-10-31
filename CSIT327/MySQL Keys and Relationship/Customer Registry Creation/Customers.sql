CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    name VARCHAR(100)
);

INSERT INTO Customers (email, name) VALUES ("john@example.com", "John Doe");

INSERT INTO Customers (email, name) VALUES ("jane@example.com", "Jane Doe");