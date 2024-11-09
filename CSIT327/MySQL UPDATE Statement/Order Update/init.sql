CREATE TABLE CustomerOrders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    order_date DATE,
    total_amount DECIMAL(10, 2)
);

INSERT INTO CustomerOrders (customer_name, order_date, total_amount)
VALUES
('John Doe', '2024-05-10', 100.00),
('Emily Clark', '2024-05-15', 150.00),
('Michael Smith', '2024-05-20', 200.00),
('Anna Johnson', '2024-05-25', 120.00),
('Robert Brown', '2024-06-01', 180.00),
('Laura Wilson', '2024-06-05', 90.00),
('Linda Taylor', '2024-06-10', 110.00),
('James Moore', '2024-06-15', 130.00),
('Patricia Jones', '2024-06-20', 160.00),
('David Miller', '2024-06-25', 140.00);
