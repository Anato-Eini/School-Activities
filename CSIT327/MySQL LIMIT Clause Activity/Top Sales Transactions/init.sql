CREATE TABLE ProductSales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    sale_date DATE,
    quantity_sold INT,
    unit_price DECIMAL(10, 2),
    total_amount DECIMAL(10, 2)
);

INSERT INTO ProductSales (product_name, sale_date, quantity_sold, unit_price, total_amount)
VALUES
('Laptop', '2023-05-15', 3, 1200.00, 3600.00),
('Smartphone', '2023-08-20', 5, 800.00, 4000.00),
('Tablet', '2023-06-10', 2, 500.00, 1000.00),
('Laptop', '2023-07-01', 2, 1200.00, 2400.00),
('Smartphone', '2023-09-05', 3, 800.00, 2400.00),
('Tablet', '2023-10-15', 1, 500.00, 500.00),
('Laptop', '2023-11-20', 4, 1200.00, 4800.00),
('Smartphone', '2023-12-01', 2, 800.00, 1600.00),
('Tablet', '2024-01-15', 3, 500.00, 1500.00),
('Laptop', '2024-02-10', 5, 1200.00, 6000.00),
('Smartphone', '2024-03-05', 1, 800.00, 800.00),
('Tablet', '2024-04-20', 2, 500.00, 1000.00),
('Laptop', '2024-05-01', 3, 1200.00, 3600.00),
('Smartphone', '2024-06-15', 4, 800.00, 3200.00),
('Tablet', '2024-07-10', 2, 500.00, 1000.00);
