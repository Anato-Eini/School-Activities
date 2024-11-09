CREATE TABLE ProductSales (
    sale_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    category VARCHAR(50),
    unit_price DECIMAL(10,2),
    quantity_sold INT,
    sale_date DATE
);

INSERT INTO ProductSales (product_name, category, unit_price, quantity_sold, sale_date) VALUES
('Laptop', 'Electronics', 1200.00, 50, '2023-01-15'),
('TV', 'Electronics', 800.00, 100, '2023-01-20'),
('Smartphone', 'Electronics', 600.00, 150, '2023-01-25'),
('Headphones', 'Electronics', 100.00, 200, '2023-01-10'),
('Refrigerator', 'Appliances', 1500.00, 30, '2023-01-10'),
('Microwave', 'Appliances', 200.00, 120, '2023-01-20'),
('Washing Machine', 'Appliances', 1000.00, 80, '2023-01-30'),
('T-Shirt', 'Clothing', 20.00, 500, '2023-01-15'),
('Jeans', 'Clothing', 50.00, 200, '2023-01-25'),
('Sneakers', 'Clothing', 80.00, 150, '2023-01-05');
