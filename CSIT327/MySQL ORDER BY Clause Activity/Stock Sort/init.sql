CREATE TABLE ProductInventory (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    category VARCHAR(100),
    quantity INT,
    unit_price DECIMAL(10,2),
    last_updated DATETIME
);

INSERT INTO ProductInventory (product_name, category, quantity, unit_price, last_updated)
VALUES
('Laptop', 'Electronics', 50, 800.00, '2023-01-10 08:30:00'),
('Smartphone', 'Electronics', 100, 600.00, '2023-01-15 10:45:00'),
('Headphones', 'Electronics', 200, 50.00, '2023-02-05 14:20:00'),
('T-shirt', 'Apparel', 300, 15.00, '2023-01-20 12:00:00'),
('Jeans', 'Apparel', 150, 30.00, '2023-01-25 09:00:00'),
('Backpack', 'Accessories', 80, 40.00, '2023-02-10 11:30:00'),
('Sunglasses', 'Accessories', 120, 25.00, '2023-01-30 13:15:00');
