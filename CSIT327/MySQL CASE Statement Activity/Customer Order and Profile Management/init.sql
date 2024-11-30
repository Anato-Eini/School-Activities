CREATE TABLE CustomersData (
    cust_id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    dob DATE,
    gender VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE OrdersInfo (
    order_id INT PRIMARY KEY,
    cust_id INT,
    order_date DATE,
    order_time TIME,
    product_name VARCHAR(100),
    quantity INT,
    order_status VARCHAR(50),
    FOREIGN KEY (cust_id) REFERENCES CustomersData(cust_id)
);

INSERT INTO CustomersData (cust_id, first_name, last_name, dob, gender, email) VALUES
(1, 'Alice', 'Johnson', '1995-02-15', 'Female', 'alice.johnson@example.com'),
(2, 'Bob', 'Smith', '1980-07-20', 'Male', 'bob.smith@gmail.com'),
(3, 'Charlie', 'Brown', '1970-05-10', 'Male', 'charlie_brown@yahoo.com'),
(4, 'Diana', 'Davis', '1988-12-03', 'Female', 'diana_davis@gmail.com'),
(5, 'Eva', 'Martinez', '1992-09-25', 'Female', 'eva.martinez@example.com'),
(6, 'Frank', 'Lee', '1975-03-18', 'Male', 'frank_lee@yahoo.com'),
(7, 'Grace', 'Wilson', '1983-11-28', 'Female', 'grace.wilson@gmail.com'),
(8, 'Henry', 'Taylor', '1990-06-08', 'Male', 'henry_taylor@example.com'),
(9, 'Ivy', 'Anderson', '1987-04-14', 'Female', 'ivy.anderson@gmail.com'),
(10, 'Jack', 'Brown', '1968-12-30', 'Male', 'jack.brown@yahoo.com');

INSERT INTO OrdersInfo (order_id, cust_id, order_date, order_time, product_name, quantity, order_status) VALUES
(1, 1, '2024-02-28', '10:00', 'Smartphone', 1, 'Pending'),
(2, 2, '2024-02-28', '12:30', 'Laptop', 2, 'Delivered'),
(3, 3, '2024-02-29', '11:45', 'T-shirt', 3, 'Pending'),
(4, 4, '2024-02-29', '14:00', 'Headphones', 1, 'In Transit'),
(5, 5, '2024-02-28', '09:30', 'Tablet', 2, 'Pending'),
(6, 6, '2024-02-29', '13:15', 'Sneakers', 1, 'Delivered'),
(7, 7, '2024-02-28', '15:20', 'Smartwatch', 1, 'Pending'),
(8, 8, '2024-02-29', '10:45', 'Jeans', 2, 'In Progress'),
(9, 9, '2024-02-29', '11:00', 'T-shirt', 1, 'Delivered'),
(10, 10, '2024-02-28', '14:30', 'Bluetooth Speaker', 1, 'Pending'),
(11, 1, '2024-02-28', '16:00', 'Camera', 1, 'In Transit'),
(12, 2, '2024-02-29', '09:00', 'Keyboard', 1, 'Delivered'),
(13, 3, '2024-02-29', '10:30', 'Monitor', 1, 'In Progress'),
(14, 4, '2024-02-28', '12:15', 'Smartphone', 2, 'Delivered'),
(15, 5, '2024-02-29', '14:45', 'Laptop', 1, 'In Progress'),
(16, 6, '2024-02-28', '08:45', 'Tablet', 1, 'Pending'),
(17, 7, '2024-02-28', '11:30', 'Smartwatch', 2, 'In Progress'),
(18, 8, '2024-02-29', '13:00', 'Headphones', 1, 'Delivered'),
(19, 9, '2024-02-29', '15:15', 'Sneakers', 1, 'In Transit'),
(20, 10, '2024-02-28', '16:30', 'Bluetooth Speaker', 2, 'Delivered'),
(21, 1, '2024-02-29', '09:45', 'Camera', 1, 'Pending'),
(22, 2, '2024-02-29', '11:15', 'Keyboard', 1, 'Delivered'),
(23, 3, '2024-02-28', '13:30', 'Monitor', 1, 'In Progress'),
(24, 4, '2024-02-28', '15:00', 'Smartphone', 1, 'Delivered'),
(25, 5, '2024-02-29', '16:15', 'Laptop', 2, 'In Progress'),
(26, 6, '2024-02-28', '09:30', 'Tablet', 1, 'Pending'),
(27, 7, '2024-02-28', '11:45', 'Smartwatch', 1, 'In Progress'),
(28, 8, '2024-02-29', '14:00', 'Headphones', 1, 'Delivered'),
(29, 9, '2024-02-29', '15:30', 'Sneakers', 1, 'In Transit'),
(30, 10, '2024-02-28', '17:00', 'Bluetooth Speaker', 2, 'Delivered');