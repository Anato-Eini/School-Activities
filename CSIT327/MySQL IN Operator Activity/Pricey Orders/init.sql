CREATE TABLE CustomerOrders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    product_name VARCHAR(100),
    quantity INT,
    unit_price DECIMAL(10, 2),
    order_date DATE
);

INSERT INTO CustomerOrders (customer_name, product_name, quantity, unit_price, order_date) 
VALUES
('Alice', 'Laptop', 1, 899.99, '2024-01-10'),
('Bob', 'Smartphone', 2, 599.99, '2024-02-15'),
('Charlie', 'Headphones', 3, 79.99, '2024-03-20'),
('David', 'Tablet', 4, 149.99, '2024-04-05'),
('Alice', 'Camera', 2, 499.99, '2024-05-10'),
('Bob', 'Smartwatch', 1, 199.99, '2024-06-15'),
('Charlie', 'Speaker', 5, 69.99, '2024-07-20'),
('David', 'Mouse', 3, 29.99, '2024-08-05'),
('Alice', 'Printer', 1, 299.99, '2024-09-10'),
('Bob', 'Keyboard', 2, 49.99, '2024-10-15');