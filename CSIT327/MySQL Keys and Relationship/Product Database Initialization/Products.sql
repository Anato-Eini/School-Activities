CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    price DECIMAL(10, 2)
);

INSERT INTO Products (product_name, price) VALUES ("Laptop", 999.99), ("Smartphone", 599.99);