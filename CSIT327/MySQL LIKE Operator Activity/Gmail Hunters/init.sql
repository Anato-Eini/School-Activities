CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(100),
    zip_code VARCHAR(20)
);

INSERT INTO Customers (full_name, email, address, city, zip_code) 
VALUES 
('John Doe', 'john.doe@gmail.com', '123 Elm St', 'Springfield', '12345'),
('Jane Smith', 'jane.smith@yahoo.com', '456 Oak St', 'Rivertown', '67890'),
('Michael Johnson', 'michael.johnson@gmail.com', '789 Maple St', 'Hilltown', '54321'),
('Emily Brown', 'emily.brown@gmail.com', '101 Pine St', 'Lakeview', '98765'),
('David Wilson', 'david.wilson@yahoo.com', '202 Birch St', 'Sunnyside', '13579');