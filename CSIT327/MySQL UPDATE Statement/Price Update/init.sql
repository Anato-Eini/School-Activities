CREATE TABLE ProductPrices (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    price DECIMAL(10, 2)
);

INSERT INTO ProductPrices (product_name, price)
VALUES
('Laptop', 899.99),
('Smartphone', 699.99),
('Smartwatch', 199.99),
('Headphones', 149.99),
('Television', 399.99),
('Luxury Watch', 999.99),
('Designer Handbag', 799.99),
('Leather Wallet', 49.99),
('Formal Shoes', 149.99),
('Running Shoes', 79.99);
